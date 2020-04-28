package com.example.forheart.model;

import com.skydoves.preferenceroom.KeyName;
import com.skydoves.preferenceroom.PreferenceEntity;
import com.skydoves.preferenceroom.PreferenceFunction;

@PreferenceEntity("UserProfile")
public class Profile {

    @KeyName("login")
    protected final boolean login = false;
    @KeyName("nickname")
    protected final String userNickName = null;
    protected final int totalModerateCount = 0;
    protected final int totalVigorousCount = 0;
    protected final int weekModerateCount = 0;
    protected final int weekVigorousCount = 0;
    protected final int preferType = -1;

//    @PreferenceFunction("nickname")
//    public String putUserNickFunction(String nickname) {
//        return "Hello, " + nickname;
//    }
//
//    @PreferenceFunction("nickname")
//    public String getUserNickFunction(String nickname) {
//        return nickname;
//    }
}
