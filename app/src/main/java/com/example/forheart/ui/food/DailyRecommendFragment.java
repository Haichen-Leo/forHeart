package com.example.forheart.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.databinding.FragmentDailyRecommendBinding;
import com.example.forheart.model.Food;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;

import java.util.List;

/**
 * Fragment that shows daily recommended food
 */
public class DailyRecommendFragment extends BaseFragment {

    private FragmentDailyRecommendBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_recommend, container, false);
        binding = FragmentDailyRecommendBinding.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = binding.recyclerViewDailyRecommend;
        FoodListAdapter foodListAdapter = new FoodListAdapter(getContext(), getNavigationFragment());
        recyclerView.setAdapter(foodListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FoodListViewModel mViewModel = new ViewModelProvider(this).get(FoodListViewModel.class);
        LiveData<List<Food>> filteredFoods = mViewModel.findAllBestFoods();
        filteredFoods.observe(getViewLifecycleOwner(), foods -> foodListAdapter.setAllFoods(foods));
    }

    // remove toolbar
    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
