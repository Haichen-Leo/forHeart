package com.example.forheart;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.forheart.ui.drawer.MenuFragment;
import com.example.forheart.ui.fitness.FitnessFragment;
import com.example.forheart.ui.food.FoodFragment;
import com.example.forheart.ui.home.HomeFragment;
import com.navigation.androidx.AwesomeActivity;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.NavigationFragment;
import com.navigation.androidx.Style;
import com.navigation.androidx.TabBarFragment;
import com.navigation.androidx.TabBarItem;

public class MainActivity extends AwesomeActivity {

    // This activity base on Androidx and AwesomeNavigation library for navigation configures
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ui - set translucent status bar
        setStatusBarTranslucent(true);

        // ui - set navigation structure
        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            NavigationFragment homeNavigation = new NavigationFragment();
            homeNavigation.setRootFragment(homeFragment);
            homeNavigation.setTabBarItem(new TabBarItem("Home", R.drawable.ic_home_black_24dp));

            FoodFragment foodFragment = new FoodFragment();
            NavigationFragment foodNavigation = new NavigationFragment();
            foodNavigation.setRootFragment(foodFragment);
            foodNavigation.setTabBarItem(new TabBarItem("Nutrition", R.drawable.ic_search_black_24dp));

            FitnessFragment fitnessFragment = new FitnessFragment();
            NavigationFragment fitnessNavigation = new NavigationFragment();
            fitnessNavigation.setRootFragment(fitnessFragment);
            fitnessNavigation.setTabBarItem(new TabBarItem("Fitness", R.drawable.ic_fitness_center_black_24dp));

            TabBarFragment tabBarFragment = new TabBarFragment();
            tabBarFragment.setChildFragments(homeNavigation, foodNavigation,fitnessNavigation);

            DrawerFragment drawerFragment = new DrawerFragment();
            drawerFragment.setMenuFragment(new MenuFragment());
            drawerFragment.setContentFragment(tabBarFragment);
            drawerFragment.setMaxDrawerWidth(300); // set menu width

            setActivityRootFragment(drawerFragment);
        }

    }

    // custom navigation util styles
    @Override
    protected void onCustomStyle(@NonNull Style style) {
        style.setSwipeBackEnabled(true);
        style.setStatusBarColor(Color.parseColor("#4CAF50"));
//        style.setNavigationBarColor(Color.WHITE);
    }
}
