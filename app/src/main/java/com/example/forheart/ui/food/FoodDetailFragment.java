package com.example.forheart.ui.food;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodDetailFragmentBinding;
import com.example.forheart.model.FoodBean;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.Style;

import java.text.DecimalFormat;

import es.dmoral.toasty.Toasty;

/**
 * Fragment class to show food details page
 */
public class FoodDetailFragment extends BaseFragment {

    private FoodDetailFragmentBinding binding;
    private FoodBean foodBean;

    AwesomeToolbar toolbar;

    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return toolbar;
    }

    @Override
    protected void onCustomStyle(@NonNull Style style) {
        style.setShadow(null);
        style.setToolbarBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected int preferredStatusBarColor() {
        if (isStatusBarTranslucent()) {
            return Color.TRANSPARENT;
        } else {
            return super.preferredStatusBarColor();
        }
    }

    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.food_detail_fragment, container, false);
        binding = FoodDetailFragmentBinding.bind(root);
        toolbar = root.findViewById(R.id.toolbar);
        if (isStatusBarTranslucent()) {
            appendStatusBarPadding(toolbar);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        foodBean = getArguments().getParcelable(String.valueOf(R.string.nav_food_id));
        int groupId = foodBean.getFoodGroupId();
        setPhoto(groupId);
        // set question mark
        binding.imageViewQuestion.setOnClickListener(v -> {
            String answer = "Scoring is purely based on our formula. Find more details in Tips for Heart.\nFor further assistance, please see a medical practitioner";
            Toasty.info(getContext(), answer, Toast.LENGTH_SHORT, true).show();
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        assert getArguments() != null;

        // set nutrition list
        String foodScore = df.format(foodBean.getTotalScore());
        binding.foodName.setText(foodBean.getFoodName());
        binding.foodScoreNumber.setText(" " + foodScore + "/5");
        binding.dietaryFibreNumber.setText(String.valueOf(foodBean.getTotalDietaryFibre()));
        binding.totalOmega3Number.setText(String.valueOf(foodBean.getTotalOmega3()));
        binding.monounsaturatedNumber.setText(String.valueOf(foodBean.getTotalMonounsaturated()));
        binding.folicAcidNumber.setText(String.valueOf(foodBean.getFolicAcid()));
        binding.lycopeneNumber.setText(String.valueOf(foodBean.getLycopene()));
        binding.MagnesiumNumber.setText(String.valueOf(foodBean.getMagnesium()));
        binding.totalFolatesNumber.setText(String.valueOf(foodBean.getTotalFolates()));
        binding.potassiumNumber.setText(String.valueOf(foodBean.getPotassium()));

        // change content based on marks
        if(foodBean.getTotalScore() < 1.95 ) {
            binding.foodType.setText(R.string.food_type_bad);
            binding.foodType.setTextColor(getResources().getColor(R.color.colorRed));
            binding.foodTypeMessage.setText(R.string.food_message_bad);
        } else if (foodBean.getTotalScore() < 2.95) {
            binding.foodType.setText(R.string.food_type_normal);
            binding.foodType.setTextColor(getResources().getColor(R.color.colorPink));
            binding.foodTypeMessage.setText(R.string.food_message_normal);
        }

        // get recommended food
        if (foodBean.getTotalScore() > 2.95) {
            binding.buttonRecommend.setText("Explore the best ingredients");
            binding.buttonRecommend.setOnClickListener(v -> {
                DailyRecommendFragment fragment = new DailyRecommendFragment();
                getNavigationFragment().redirectToFragment(fragment);
            });
        } else {
            binding.buttonRecommend.setOnClickListener(v -> {
                int groupId = foodBean.getFoodGroupId();
                RecommandFragment dialog = RecommandFragment.newInstance(groupId);
                showDialog(dialog,0);
            });
        }
    }

    // handle result from recommend dialog, redirect to recommended food detail page
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
            if (resultCode == 1) {
                FoodListFragment foodListFragment = new FoodListFragment();
                Bundle args = FragmentHelper.getArguments(foodListFragment);
                args.putInt(String.valueOf(R.string.nav_food_group_id),-1);
                getNavigationFragment().redirectToFragment(foodListFragment);
            }
        }
    }

    // set theme image for each food category
    private void setPhoto(int groupId) {
        if (groupId == 11){
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_11));
        } else if (groupId == 12) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_12));
        } else if (groupId == 13) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_13));
        } else if (groupId == 14) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_14));
        } else if (groupId == 15) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_15));
        } else if (groupId == 16) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_16));
        } else if (groupId == 17) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_17));
        } else if (groupId == 18) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_18));
        } else if (groupId == 19) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_19));
        } else if (groupId == 20) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_20));
        } else if (groupId == 21) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_21));
        } else if (groupId == 22) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_22));
        } else if (groupId == 23) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_23));
        } else if (groupId == 24) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_24));
        } else if (groupId == 25) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_25));
        } else if (groupId == 26) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_26));
        } else if (groupId == 27) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_27));
        } else if (groupId == 28) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_28));
        } else if (groupId == 29) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_29));
        } else if (groupId == 31) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_31));
        } else if (groupId == 32) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_32));
        } else if (groupId == 34) {
            binding.imageViewType.setImageDrawable(getResources().getDrawable(R.drawable.food_34));
        }


    }

}
