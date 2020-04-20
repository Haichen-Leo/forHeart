package com.example.forheart.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.ui.BaseFragment;
import com.example.forheart.R;
import com.example.forheart.databinding.HomeFragmentBinding;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.ToolbarButtonItem;

public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;

    private static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        binding = HomeFragmentBinding.bind(root);
        return binding.getRoot();
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
        binding.textViewTest.setText("Home fragment");

    }

}
