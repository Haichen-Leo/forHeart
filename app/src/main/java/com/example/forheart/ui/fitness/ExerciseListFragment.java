package com.example.forheart.ui.fitness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.FragmentExerciseListBinding;
import com.example.forheart.model.Exercise;
import com.example.forheart.ui.BaseFragment;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class ExerciseListFragment extends BaseFragment {

    private FragmentExerciseListBinding binding;
    private RecyclerView recyclerView;
    private ExerciseListAdapter adapter;
    private LiveData<List<Exercise>> allExercises;
    private ExerciseListViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        String type = getArguments().getString("exercise_type");
        String category = getArguments().getString("exercise_category");
//        Toasty.info(getContext(), type + category, Toast.LENGTH_SHORT, true).show();
        adapter = new ExerciseListAdapter(getContext(), getNavigationFragment());
        recyclerView = binding.recyclerViewExercise;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mViewModel = new ViewModelProvider(this).get(ExerciseListViewModel.class);
        if (type.equals("moderate")){
            allExercises = mViewModel.getModerateCategory(category);
        } else {
            allExercises = mViewModel.getVigorousCategory(category);
        }

        allExercises.observe(getViewLifecycleOwner(), exercises -> {
            adapter.setAllExercise(exercises);
        });
    }
}
