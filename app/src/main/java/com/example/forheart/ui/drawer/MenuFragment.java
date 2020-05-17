package com.example.forheart.ui.drawer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.MenuFragmentBinding;
import com.example.forheart.model.Preference_UserProfile;
import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.NavigationFragment;
import com.navigation.androidx.TabBarFragment;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends AwesomeFragment {

    private MenuFragmentBinding binding;
    private Preference_UserProfile userProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = MenuFragmentBinding.inflate(getLayoutInflater());

        // set nickname
        userProfile = Preference_UserProfile.getInstance(getContext());
        binding.textViewNickname.setText(userProfile.getNickname());
        userProfile.addNicknameOnChangedListener(nickname1 -> {
            binding.textViewNickname.setText(nickname1);
        });

        // nav - to profile page
        binding.layoutProfile.setOnClickListener(v -> {
            ProfileFragment fragment = new ProfileFragment();
            getNavigationFragment().pushFragment(fragment);
        });

        // nav - to tips page
        binding.layoutTips.setOnClickListener(v -> {
            Toasty.info(getContext(), "tips", Toast.LENGTH_SHORT, true).show();
        });

        // nav - to faq page
        binding.layoutFaq.setOnClickListener(v -> {
            Toasty.info(getContext(), "FAQ", Toast.LENGTH_SHORT, true).show();
        });

        // nav - to contact page
        binding.layoutContact.setOnClickListener(v -> {
            requireNavigationFragment().pushFragment(new ContactFragment());
            requireDrawerFragment().closeMenu();
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public NavigationFragment getNavigationFragment() {
        DrawerFragment drawerFragment = getDrawerFragment();
        if (drawerFragment != null) {
            TabBarFragment tabBarFragment = drawerFragment.getContentFragment().getTabBarFragment();
            if (tabBarFragment != null) {
                return tabBarFragment.getSelectedFragment().getNavigationFragment();
            }
            return drawerFragment.getContentFragment().getNavigationFragment();
        }
        return super.getNavigationFragment();
    }
}
