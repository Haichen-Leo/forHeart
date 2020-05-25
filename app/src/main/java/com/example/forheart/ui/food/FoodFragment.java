package com.example.forheart.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodGroupFragmentBinding;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.ToolbarButtonItem;

/**
 * Fragment class to present nutrition page
 */
public class FoodFragment extends BaseFragment {

    private FoodGroupFragmentBinding binding;
    private FoodViewModel mViewModel;
    private RecyclerView recyclerView;
    private FoodGroupAdapter mAdapter;

    private static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FoodGroupFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        mAdapter = new FoodGroupAdapter(getContext(), getNavigationFragment());
        recyclerView = binding.recyclerViewFoodGroup;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(mAdapter);
        // nav - view a category
        mViewModel.getAllFoodGroupsLive().observe(getViewLifecycleOwner(), foodGroups -> mAdapter.setAllFoodGroups(foodGroups));

        // nav - view all foods
        binding.textViewAll.setOnClickListener(v -> {
            FoodListFragment allFoodList = new FoodListFragment();
            Bundle args = FragmentHelper.getArguments(allFoodList);
            int id = 0;
            args.putInt(String.valueOf(R.string.nav_food_group_id), id);
            getNavigationFragment().pushFragment(allFoodList);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Nutrition");
        // set up toolbar and drawer
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
    }
}
