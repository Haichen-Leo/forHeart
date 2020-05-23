package com.example.forheart.ui.fitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forheart.R;
import com.example.forheart.databinding.PlanSetFragmentBinding;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;

public class PlanSetFragment extends BaseFragment {

    private AwesomeToolbar toolbar;
    private PlanSetFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PlanSetFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        binding.buttonModerate.setOnClickListener(v -> {
            PlanDetailFragment fragment = new PlanDetailFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString(String.valueOf(R.string.activity_type), "moderate");
            getNavigationFragment().pushFragment(fragment);
        });
        binding.buttonVigorous.setOnClickListener(v -> {
            PlanDetailFragment fragment = new PlanDetailFragment();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString(String.valueOf(R.string.activity_type), "vigorous");
            getNavigationFragment().pushFragment(fragment);
        });

        // back
        binding.back.setOnClickListener(v -> {
            getNavigationFragment().popFragment();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("New Plan");
    }

    // hide toolbar
    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
