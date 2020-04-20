package com.example.forheart.ui.food;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodListFragmentBinding;
import com.example.forheart.model.Food;
import com.navigation.androidx.AwesomeFragment;

import java.util.List;

public class FoodListFragment extends AwesomeFragment {

    private FoodListFragmentBinding binding;
    private FoodListViewModel mViewModel;
    private RecyclerView recyclerView;
    private FoodListAdapter foodListAdapter;

    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FoodListFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.recyclerViewFood;
        foodListAdapter = new FoodListAdapter(getContext());
        recyclerView.setAdapter(foodListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mViewModel = new ViewModelProvider(this).get(FoodListViewModel.class);
        int id = getArguments().getInt(String.valueOf(R.string.nav_food_group_id));
        if (id == 0) {
            mViewModel.getAllFoodsLive().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
                @Override
                public void onChanged(List<Food> foods) {
                    foodListAdapter.setAllFoods(foods);
                }
            });
        } else {
            mViewModel.findFoodsWithGroup(id).observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
                @Override
                public void onChanged(List<Food> foods) {
                    foodListAdapter.setAllFoods(foods);
                }
            });
        }

    }

}
