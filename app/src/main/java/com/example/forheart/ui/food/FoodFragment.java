package com.example.forheart.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodGroupFragmentBinding;

public class FoodFragment extends Fragment {

    private FoodGroupFragmentBinding binding;
    private FoodViewModel mViewModel;
    private RecyclerView recyclerView;
    private FoodGroupAdapter mAdapter;

    public static FoodFragment newInstance() {
        return new FoodFragment();
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
        mAdapter = new FoodGroupAdapter();
        mAdapter.setAllFoodGroups(mViewModel.getAllFoodGroups());
        recyclerView = binding.recyclerViewFoodGroup;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(mAdapter);
        binding.textViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int id = 0;
                bundle.putInt(String.valueOf(R.string.nav_food_id), id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_food_to_foodListFragment, bundle);
            }
        });

    }

}
