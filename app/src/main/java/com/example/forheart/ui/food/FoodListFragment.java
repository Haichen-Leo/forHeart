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
import com.example.forheart.databinding.FoodListFragmentBinding;
import com.example.forheart.ui.BaseFragment;

public class FoodListFragment extends BaseFragment {

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
            mViewModel.getAllFoodsLive().observe(getViewLifecycleOwner(), foods -> foodListAdapter.setAllFoods(foods));
        } else {
            mViewModel.findFoodsWithGroup(id).observe(getViewLifecycleOwner(), foods -> foodListAdapter.setAllFoods(foods));
        }

    }

}
