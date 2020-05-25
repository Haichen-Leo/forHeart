package com.example.forheart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * activity class to show welcome page when first login
 */
public class IntroActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicator;
    private MaterialButton buttonOnBoardAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        layoutOnBoardingIndicator = findViewById(R.id.indicator);
        buttonOnBoardAction = findViewById(R.id.button_onBoarding_action);
        setupOnBoardingItems();
        // set up viewpager
        ViewPager2 onBoardingViewPager = findViewById(R.id.viewpager);
        onBoardingViewPager.setAdapter(onBoardingAdapter);
        setupOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);
        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

        // change button listener
        buttonOnBoardAction.setOnClickListener(v -> {
            if (onBoardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void setupOnBoardingItems() {

        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        // first page
        OnBoardingItem item1 = new OnBoardingItem();
        item1.setTitle("Eat well, live well");
        item1.setDescription("Paying attention to what you eat is the first step that you can take in preventing heart disease.");
        item1.setImage(R.drawable.boarding_one);

        // second page
        OnBoardingItem item2 = new OnBoardingItem();
        item2.setTitle("Sit less, move more");
        item2.setDescription("By being more active, you reduce the chance of developing heart disease.");
        item2.setImage(R.drawable.boarding_two);

        // third page
        OnBoardingItem item3 = new OnBoardingItem();
        item3.setTitle("Start making better choices");
        item3.setDescription("Get heart nutrition information and create a weekly exercise plan with reminders for your own health.");
        item3.setImage(R.drawable.boarding_three);

        onBoardingItems.add(item1);
        onBoardingItems.add(item2);
        onBoardingItems.add(item3);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);

    }

    // change indicator
    private void setupOnBoardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicator.addView(indicators[i]);
        }
    }

    // method to set button on different pages
    private void setCurrentOnBoardingIndicator(int index) {
        int childCount = layoutOnBoardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            }
        }
        if (index == onBoardingAdapter.getItemCount() - 1) {
            buttonOnBoardAction.setText("Start");
        } else {
            buttonOnBoardAction.setText("Skip");
        }
    }
}