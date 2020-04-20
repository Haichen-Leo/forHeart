package com.example.forheart.ui.drawer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.R;
import com.example.forheart.databinding.MenuFragmentBinding;
import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.NavigationFragment;
import com.navigation.androidx.TabBarFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends AwesomeFragment {

    private MenuFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.menu_fragment, container, false);
        binding = MenuFragmentBinding.bind(root);

        // nav - to contact page
        binding.buttonContact.setOnClickListener(v -> {
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
