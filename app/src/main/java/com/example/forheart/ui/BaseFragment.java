package com.example.forheart.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.DrawerFragment;

/**
 * Base fragment for un-root fragments extend
 */
public class BaseFragment extends AwesomeFragment {

    // enable drawer swipe for child fragments
    @Override
    public void onResume() {
        super.onResume();
        DrawerFragment drawerFragment = getDrawerFragment();
        if (drawerFragment != null) {
            drawerFragment.setMenuInteractive(isNavigationRoot());
        }
    }
}
