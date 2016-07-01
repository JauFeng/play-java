package services;

import models.UserInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonalCenterService {

//    private UserInfo userInfo;

    @Inject
    public PersonalCenterService() {
        super();
    }

    public UserInfo findUserInfoById(long userId) {
        return UserInfo.finder.byId(String.valueOf(userId));
    }
}
