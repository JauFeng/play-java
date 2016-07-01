package services;

import com.avaje.ebean.ExpressionList;
import models.UserInfo;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class LoginService {


    /**
     * Login validate username & password.
     *
     * @param roleName NickName
     * @param password Password
     * @return
     */
    public ValidateResult validate(String roleName, String password) {
        List<UserInfo> users = UserInfo.finder
                .fetch("roleName", "password")
                .where()
                .like("roleName", roleName)
                .findList();
        if (users.isEmpty()) {
            return ValidateResult.UNKOWNROLENAME;
        } else {
            return password.equals(users.get(0).getPassword()) ?
                    ValidateResult.LOGINSUCCESS :
                    ValidateResult.INVALIDPASSWORD;
        }
    }

    /**
     * Find user by username.
     *
     * @param username roleName
     * @return
     */
    public UserInfo findUserByName(String username) {
        return UserInfo.finder.where().ilike("roleName", username + "%").findUnique();
    }


    /**
     * Login validate username & password return status.
     */
    public enum ValidateResult {
        UNKOWNROLENAME, INVALIDPASSWORD, LOGINSUCCESS;
    }
}
