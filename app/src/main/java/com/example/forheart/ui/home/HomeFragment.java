package com.example.forheart.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forheart.R;
import com.example.forheart.databinding.HomeFragmentBinding;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.ui.drawer.ProfileFragment;
import com.example.forheart.ui.drawer.TipsFragment;
import com.example.forheart.ui.food.FoodListFragment;
import com.example.forheart.util.ProgressUtil;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.ToolbarButtonItem;

import java.util.Calendar;

public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private Preference_UserProfile userProfile;

    private static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // first login page
        userProfile = Preference_UserProfile.getInstance(getContext());
        if (userProfile.getLogin() == false) {
            LoginFragment fragment = new LoginFragment();
            getNavigationFragment().presentFragment(fragment,0);
        } else {
            String nickname = userProfile.getNickname();
            binding.nickname.setText(nickname);
            userProfile.addNicknameOnChangedListener(nickname1 -> {
                binding.nickname.setText(nickname1);
            });
        }

        // daily recommend - top 20
        binding.cardViewDailyRecommend.setOnClickListener(v -> {
            FoodListFragment listFragment = new FoodListFragment();
            Bundle args = FragmentHelper.getArguments(listFragment);
            args.putInt(String.valueOf(R.string.nav_food_group_id),-1);
            getNavigationFragment().pushFragment(listFragment);
        });

        // setup weekly exercise count
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        if (userProfile.getWeekOfYear() != weekOfYear) {
            userProfile.putWeekOfYear(weekOfYear);
            userProfile.putWeekModerateCount(0);
            userProfile.putWeekVigorousCount(0);
            userProfile.putTaskFinished(0);
        }

        // setup circle progress
        String taskType = userProfile.getTaskType();
        ProgressUtil.init(taskType);
        float progress = ProgressUtil.transVig(userProfile.getWeekVigorousCount()) + userProfile.getWeekModerateCount();
        binding.circleView.setMaxValue(ProgressUtil.getModerate());
        binding.circleView.setValueAnimated(progress, 1500);
        userProfile.addTaskTypeOnChangedListener(newTaskType -> {
            ProgressUtil.init(newTaskType);
            float newProgress = ProgressUtil.transVig(userProfile.getWeekVigorousCount()) + userProfile.getWeekModerateCount();
            binding.circleView.setMaxValue(ProgressUtil.getModerate());
            binding.circleView.setValueAnimated(newProgress, 1500);
        });

        // setup weekly exercise count
        binding.textViewModerateMin.setText(String.valueOf(userProfile.getWeekModerateCount()));
        binding.textViewVigorousMin.setText(String.valueOf(userProfile.getWeekVigorousCount()));
        userProfile.addWeekModerateCountOnChangedListener(weekmoderatecount -> {
            binding.textViewModerateMin.setText(String.valueOf(weekmoderatecount));
            binding.circleView.setValueAnimated(ProgressUtil.transVig(userProfile.getWeekVigorousCount()) + weekmoderatecount, 1500 );
        });
        userProfile.addWeekVigorousCountOnChangedListener(weekvigorouscount -> {
            binding.textViewVigorousMin.setText(String.valueOf(weekvigorouscount));
            binding.circleView.setValueAnimated(ProgressUtil.transVig(weekvigorouscount) + userProfile.getWeekModerateCount(), 1500);
        });

        // profile listener
        binding.cardViewHealthyProfile.setOnClickListener(v -> {
            ProfileFragment fragment = new ProfileFragment();
            getNavigationFragment().pushFragment(fragment);
        });

        // tips listener
        binding.cardViewTips.setOnClickListener(v -> {
            TipsFragment fragment = new TipsFragment();
            getNavigationFragment().pushFragment(fragment);
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("forHEART");

        if (isNavigationRoot()) {
            String iconUri = "font://FontAwesome/" + fromCharCode(61641) + "/24";
            ToolbarButtonItem.Builder builder = new ToolbarButtonItem.Builder();
            builder.icon(iconUri).listener(view -> {
                DrawerFragment drawerFragment = getDrawerFragment();
                if (drawerFragment != null) {
                    drawerFragment.toggleMenu();
                }
            });
            setLeftBarButtonItem(builder.build());
        }
//        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if (resultCode == Activity.RESULT_OK){
                String nickname = data.getString("nickname");
                userProfile.putNickname(nickname);
                binding.nickname.setText(nickname);
                userProfile.putLogin(true);
            }
        }
    }
}
