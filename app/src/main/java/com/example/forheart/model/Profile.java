package com.example.forheart.model;

import com.skydoves.preferenceroom.KeyName;
import com.skydoves.preferenceroom.PreferenceEntity;

/**
 * Class used for SharedPreference management
 */
@PreferenceEntity("UserProfile")
public class Profile {

    // key attribute to check if the user have logged in
    // after first login, the value will be set to true
    @KeyName("login")
    protected final boolean login = false;
    @KeyName("nickname")
    protected final String userNickName = null;
    protected final int totalModerateCount = 0;
    protected final int totalVigorousCount = 0;
    protected final int weekModerateCount = 0;
    protected final int weekVigorousCount = 0;
    // keep the week of year value, used to reset weekly progress
    protected final int weekOfYear = 0;
    protected final int progress = 0;
    protected final String taskType = "Normal";
    // key attribute to track plan index
    protected final int planIndex = 0;
    // used to check if the weekly task is done
    protected final int taskFinished = 0;
}
