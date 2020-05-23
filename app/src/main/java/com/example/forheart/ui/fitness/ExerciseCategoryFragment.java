package com.example.forheart.ui.fitness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.FragmentExerciseCategoryBinding;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;

import es.dmoral.toasty.Toasty;

public class ExerciseCategoryFragment extends BaseFragment {

    private FragmentExerciseCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseCategoryBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        String argsType = getArguments().getString("exercise_type");
//        Toasty.info(getContext(), args, Toast.LENGTH_SHORT, true).show();

        // button bicycle
        binding.cardBicycling.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "bicycling");
            getNavigationFragment().pushFragment(fragment);
        });

        // button conditioning
        binding.cardConditioning.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "conditioning");
            getNavigationFragment().pushFragment(fragment);
        });

        // button dancing
        binding.cardDancing.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "dancing");
            getNavigationFragment().pushFragment(fragment);
        });

        // button running
        binding.cardRunning.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "running");
            getNavigationFragment().pushFragment(fragment);
        });

        // button sports
        binding.cardSports.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "sports");
            getNavigationFragment().pushFragment(fragment);
        });

        // button walking
        binding.cardWalking.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "walking");
            getNavigationFragment().pushFragment(fragment);
        });

        // button water activities
        binding.cardWaterActivities.setOnClickListener(v -> {
            ExerciseListFragment fragment = new ExerciseListFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", argsType);
            args.putString("exercise_category", "water activities");
            getNavigationFragment().pushFragment(fragment);
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Recommended Activities");
    }

    // hide toolbar
    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
