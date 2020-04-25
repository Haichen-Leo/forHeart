package com.example.forheart.ui.food;

import android.app.Activity;
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
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;

import java.text.DecimalFormat;


public class FoodDetailFragment extends BaseFragment {

    private FoodDetailFragmentBinding binding;
//    private FoodDetailViewModel mViewModel;

    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }

    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FoodDetailFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(FoodDetailViewModel.class);
//        String foodId = getArguments().getString(String.valueOf(R.string.nav_food_id));
        assert getArguments() != null;
        FoodBean foodBean = getArguments().getParcelable(String.valueOf(R.string.nav_food_id));
        String foodId = foodBean.getFoodId();
        String foodScore = df.format(foodBean.getTotalScore());
        binding.foodName.setText(foodBean.getFoodName());
        binding.foodScoreNumber.setText(foodScore + " / 5");
        binding.dietaryFibreNumber.setText(String.valueOf(foodBean.getTotalDietaryFibre()));
        binding.totalOmega3Number.setText(String.valueOf(foodBean.getTotalOmega3()));
        binding.monounsaturatedNumber.setText(String.valueOf(foodBean.getTotalMonounsaturated()));
        binding.folicAcidNumber.setText(String.valueOf(foodBean.getFolicAcid()));
        binding.lycopeneNumber.setText(String.valueOf(foodBean.getLycopene()));
        binding.MagnesiumNumber.setText(String.valueOf(foodBean.getMagnesium()));
        binding.totalFolatesNumber.setText(String.valueOf(foodBean.getTotalFolates()));
        binding.potassiumNumber.setText(String.valueOf(foodBean.getPotassium()));

        if(foodBean.getTotalScore() < 1 ) {
            binding.foodType.setText("No help for heart!");
            binding.foodTypeMessage.setText("This food is not good enough for heart health, find other food in our database.");
        } else if (foodBean.getTotalScore() < 2) {
            binding.foodType.setText("Not bad");
            binding.foodTypeMessage.setText("You may find more healthy food in recommendation.");
        }
        
        binding.recomBt.setOnClickListener(v -> {
            int groupId = foodBean.getFoodGroupId();
            RecommandFragment dialog = RecommandFragment.newInstance(groupId);
            showDialog(dialog,0);
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                FoodDetailFragment fragment = new FoodDetailFragment();
                FoodBean foodBean = data.getParcelable(RecommandFragment.KEY_VALUE);
                Bundle args = FragmentHelper.getArguments(fragment);
                args.putParcelable(String.valueOf(R.string.nav_food_id), foodBean);
                getNavigationFragment().redirectToFragment(fragment);
            }
        }
    }

}
