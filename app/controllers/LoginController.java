package controllers;

import controllers.common.ErrorMessage;
import controllers.common.SuccessMessage;
import models.UserInfo;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.LoginService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;

@Singleton
public class LoginController extends Controller {

    private final LoginService loginService;
    private final FormFactory formFactory;

    @Inject
    public LoginController(LoginService loginService,
                           FormFactory formFactory) {
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
            switch (loginService.validate(userInfoForm.get().getRoleName(), userInfoForm.get().getPassword())) {
                case LOGINSUCCESS:
                    UserInfo userInfo = loginService.findUserByName(userInfoForm.get().getRoleName());
                    SuccessMessage successMessage =
                            new SuccessMessage("token", userInfo.getRoleName(), userInfo.getLevel(), userInfo.getExp(),
                                    userInfo.getPortrait(), userInfo.getSignature(), userInfo.getSex(),
                                    userInfo.getBirthDay().toString(), userInfo.getAddress(), 12f); // TODO: 2016/6/30 12f is shit value.
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

    public Result logout() {



        return null;
    }
}

