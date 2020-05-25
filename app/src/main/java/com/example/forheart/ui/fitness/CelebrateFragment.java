package com.example.forheart.ui.fitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forheart.R;
import com.example.forheart.ui.BaseFragment;

/**
 * Fragment to show celebrating page
 */
public class CelebrateFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.workout_feedback, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        setTitle("Congratulations");
    }
}
