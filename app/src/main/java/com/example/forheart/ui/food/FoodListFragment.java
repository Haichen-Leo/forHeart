package com.example.forheart.ui.food;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.R;
import com.example.forheart.databinding.FoodListFragmentBinding;
import com.example.forheart.model.Food;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.ToolbarButtonItem;

import java.util.List;

public class FoodListFragment extends BaseFragment {

    private FoodListFragmentBinding binding;
    private FoodListViewModel mViewModel;
    private RecyclerView recyclerView;
    private FoodListAdapter foodListAdapter;
    private LiveData<List<Food>> filteredFoods;
    private int groupId;

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
        foodListAdapter = new FoodListAdapter(getContext(), getNavigationFragment());
        recyclerView.setAdapter(foodListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mViewModel = new ViewModelProvider(this).get(FoodListViewModel.class);
        groupId = getArguments().getInt(String.valueOf(R.string.nav_food_group_id));
        if (groupId == 0) {
            filteredFoods = mViewModel.getAllFoodsLive();
            filteredFoods.observe(getViewLifecycleOwner(), foods -> foodListAdapter.setAllFoods(foods));
        } else {
            filteredFoods = mViewModel.findFoodsWithGroup(groupId);
            filteredFoods.observe(getViewLifecycleOwner(), foods -> foodListAdapter.setAllFoods(foods));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ToolbarButtonItem toolbarButtonItem = new ToolbarButtonItem.Builder().title("Search").build();
        setRightBarButtonItem(toolbarButtonItem);
        View searchBar = LayoutInflater.from(requireContext()).inflate(R.layout.search_bar_food, getAwesomeToolbar(), false);
        getAwesomeToolbar().addView(searchBar, new Toolbar.LayoutParams(-1, -1, Gravity.CENTER));

        // filter foods
        EditText searchInput = searchBar.findViewById(R.id.search_input);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pattern = s.toString().trim();
//                Toast.makeText(getActivity(), pattern, Toast.LENGTH_SHORT).show();
                filteredFoods.removeObservers(getViewLifecycleOwner());
                if (groupId == 0) {
                    filteredFoods = mViewModel.findFoodsWithPattern(pattern);
                } else {
                    filteredFoods = mViewModel.findFoodsWithGroup(groupId, pattern);
                }
                filteredFoods.observe(getViewLifecycleOwner(), foods -> {
                    foodListAdapter.setAllFoods(foods);
                });
            }
        });

        // clear edit text input
        ImageView clearButton = searchBar.findViewById(R.id.search_clear);
        clearButton.setOnClickListener(v -> {
            searchInput.setText("");
            hideKeyboard(v);
        });
    }

    // get InputMethodManager to close soft keyboard
    private void hideKeyboard(View v) {

        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    // close soft keyboard when navigate to other fragment
    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard(getView());
    }
}
