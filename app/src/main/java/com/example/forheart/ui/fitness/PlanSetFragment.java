package com.example.forheart.ui.fitness;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodGroupFragmentBinding;
import com.example.forheart.databinding.PlanSetFragmentBinding;
import com.example.forheart.ui.BaseFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.Style;

public class PlanSetFragment extends BaseFragment {

    private AwesomeToolbar toolbar;
    private PlanSetFragmentBinding binding;

//    public static PlanSetFragment newInstance() {
//        return new PlanSetFragment();
//    }

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("New Plan");
    }
}
