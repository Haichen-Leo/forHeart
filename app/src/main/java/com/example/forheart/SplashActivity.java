package com.example.forheart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.forheart.model.Preference_UserProfile;
import com.navigation.androidx.AwesomeActivity;

public class SplashActivity extends AwesomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            Preference_UserProfile userProfile = Preference_UserProfile.getInstance(getApplicationContext());
            if (!userProfile.getLogin()) {
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            overridePendingTransition(R.anim.nav_fade_in, R.anim.nav_fade_out);
        }, 1500);
    }
}
