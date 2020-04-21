package com.example.forheart.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodDetailFragmentBinding;
import com.example.forheart.model.FoodBean;
import com.example.forheart.ui.BaseFragment;

public class FoodDetailFragment extends BaseFragment {

    private FoodDetailFragmentBinding binding;
    private FoodDetailViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FoodDetailFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FoodDetailViewModel.class);

//        String foodId = getArguments().getString(String.valueOf(R.string.nav_food_id));
        assert getArguments() != null;
        FoodBean foodBean = getArguments().getParcelable(String.valueOf(R.string.nav_food_id));
        String foodId = foodBean.getFoodId();
        Double foodScore = foodBean.getTotalScore();
        int foodGroupId = foodBean.getFoodGroupId();
        binding.textViewTest.setText(foodId);
        binding.textViewTest2.setText(foodBean.getFoodName());
        binding.textViewTest3.setText(String.valueOf(foodScore));
    }

}
