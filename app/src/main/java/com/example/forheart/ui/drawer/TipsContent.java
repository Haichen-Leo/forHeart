package com.example.forheart.ui.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forheart.R;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;

/**
 * Fragment to show tips content
 */
public class TipsContent extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // get argument from TipsFragment to show different tips
        int tipNum = getArguments().getInt("tips");
        switch (tipNum) {
            case 1:
                view = inflater.inflate(R.layout.tip_one, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.tip_two, container, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.tip_three, container, false);
                break;
            case 4:
                view = inflater.inflate(R.layout.tip_four, container, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipNum);
        }
        return view;
    }


    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
