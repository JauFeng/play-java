package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import controllers.common.ErrorMessage;
import controllers.common.SuccessMessage;
import models.UserInfo;
import play.Logger;
import play.cache.CacheApi;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.LoginService;
import utils.JWTUtils;
import utils.RandomUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Singleton
public class LoginController extends Controller {

    // Redis cache api.
    private final CacheApi cacheApi;

    private final LoginService loginService;
    private final FormFactory formFactory;

    @Inject
    public LoginController(LoginService loginService,
                           FormFactory formFactory,
                           CacheApi cacheApi) {
        this.cacheApi = cacheApi;

        this.loginService = loginService;
        this.formFactory = formFactory;
    }

    /**
     * Login.
     * <p>
     * Method: POST
     * URI: /api/v1/login
     * Param: roleName
     * Param: password
     *
     * @return
     */
    public Result login() {
        Result result;

        Form<UserInfo> userInfoForm = formFactory.form(UserInfo.class).bindFromRequest();

        if (userInfoForm.hasErrors()) {
            result = badRequest(userInfoForm.errorsAsJson());
        } else {
            String roleName = Optional.ofNullable(userInfoForm.get()).map(UserInfo::getRoleName).orElse("");
            String password = Optional.ofNullable(userInfoForm.get()).map(UserInfo::getPassword).orElse("");

            switch (loginService.validate(roleName, password)) {
                case LOGINSUCCESS:
                    UserInfo userInfo = loginService.findUserByName(userInfoForm.get().getRoleName());
                    String token = RandomUtil.createRandomName("userid", 10003);
//                    String token = JWTUtils.encode(Json.toJson(userInfo).toString());
                    SuccessMessage successMessage =
                            new SuccessMessage(token,
                                    userInfo.getRoleName(),
                                    userInfo.getLevel(),
                                    userInfo.getExperience(),
                                    userInfo.getPortrait(),
                                    userInfo.getSignature(),
                                    userInfo.getSex(),
                                    Optional.ofNullable(userInfo.getBirthDay()).map(Date::toString).orElse(""),
                                    userInfo.getAddress(),
                                    -1f); // TODO: 2016/6/30 12f is shit value.
                    saveToken(userInfo.getUserId(), token);
                    result = ok(Json.toJson(successMessage));
                    Logger.info("[" + userInfo.getRoleName() + "]" + " is login success on " + Instant.now());
                    break;
                case UNKOWNROLENAME:
                    result = badRequest(Json.toJson(new ErrorMessage("roleName.unknown")));
                    break;
                case INVALIDPASSWORD:
                    result = badRequest(Json.toJson(new ErrorMessage("password.invalid")));
                    break;
                default:
                    result = badRequest(Json.toJson(new ErrorMessage("something.unknown")));
                    break;
            }
        }
        return result;
    }

    /**
     * Login by token.
     * <p>
     * Method: GET
     * URI: /api/v1/logout
     * Param: {"token" : "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEwMDAzLCJyb2xlTmFtZSI6Im5ubm4iLCJkZXZpY2VJZCI6IiIsInBob25lTnVtYmVyIjoiMTgyMTAxMzQ2MTEiLCJtYWlsQWRkcmVzcyI6bnVsbCwicGFzc3dvcmQiOiIxMjM0NTYiLCJsZXZlbCI6MSwiZXhwIjowLCJzaWduYXR1cmUiOm51bGwsInBvcnRyYWl0IjoiMiIsInNleCI6MCwiYmlydGhEYXkiOm51bGwsInJvbGVDcmVhdGVUaW1lIjoxNDYzNjIzMTM1MDAwLCJhZGRyZXNzIjpudWxsfQ.Nayaw4hEOkx2xhmhQWTZCPtsEV50_4MuhczS-roBaNY0nPTpaTdIdWfxawPWlommEj_g9wVAE8yt8KA3loSL4Q"}
     * <p>
     * TODO: will be removed.
     *
     * @return
     */
    public Result loginByToken() {
        Result result = null;
        JsonNode json = request().body().asJson();
        if (json == null) {
            result = badRequest("Expecting Json data");
        } else {
            String token = json.findPath("token").textValue();
            if (token == null) {
                result = badRequest("Missing parameter [token]");
            } else {
//                String decode = JWTUtils.decode(token);
//                UserInfo userInfo = Json.fromJson(Json.parse(JWTUtils.decode(token)), UserInfo.class);

                Object userID = cacheApi.get(token);

                if (userID == null) {
                    result = badRequest("Invalid [token]");
                } else {
                    result = ok(Json.parse("{\"userID\":" + userID + "}"));
                }


//                SuccessMessage successMessage =
//                        new SuccessMessage(token,
//                                userInfo.getRoleName(),
//                                userInfo.getLevel(),
//                                userInfo.getExperience(),
//                                userInfo.getPortrait(),
//                                userInfo.getSignature(),
//                                userInfo.getSex(),
//                                Optional.ofNullable(userInfo.getBirthDay()).map(Date::toString).orElse(""),
//                                userInfo.getAddress(),
//                                -1f); // TODO: 2016/6/30 12f is shit value.
//                result = ok(Json.toJson(successMessage));
//                Logger.info("[" + userInfo.getRoleName() + "]" + " is login success on " + Instant.now());
            }
        }
        return result;
    }

    /**
     * Logout.
     *
     * @return
     */
    // TODO: complete logout.
    public Result logout() {
        String token = "useridHC0003";

        removeToken(token);

//        Logger.info("[" + "UserID: " + userID + "]" + " is logout on " + Instant.now());
        return ok("[token: " + token + "] is logout.");
    }

    /**
     * Save user <- token to Redis cache.
     *
     * @param userID
     * @param token
     */
    private void saveToken(long userID, String token) {
        cacheApi.set(token, userID);
        Logger.info("Redis cache saved [" + token + " <- UserID:" + userID + "].");
    }

    /**
     * Remove token <- userID from Redis cache.
     *
     * @param token
     */
    private void removeToken(String token) {
        Optional.ofNullable(token).ifPresent(_token -> {
            cacheApi.remove(_token);
            cacheApi.remove("classTag::" + _token);
        });
        Logger.info("Redis cache deleted [" + token + "].");
    }
}

