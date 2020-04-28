package com.example.forheart.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.forheart.databinding.HomeFragmentBinding;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.ToastUtil;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.ToolbarButtonItem;

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
        userProfile = Preference_UserProfile.getInstance(getContext());
        if (userProfile.getLogin() == false) {
            LoginFragment fragment = new LoginFragment();
            getNavigationFragment().presentFragment(fragment,0);
        } else {
            String nickname = userProfile.getNickname();
            binding.welcome.setText("Hello, " + nickname);
        }

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
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if (resultCode == Activity.RESULT_OK){
                String nickname = data.getString("nickname");
                userProfile.putNickname(nickname);
                binding.welcome.setText("Hello, " + nickname);
                userProfile.putLogin(true);
            }
        }
    }
}
