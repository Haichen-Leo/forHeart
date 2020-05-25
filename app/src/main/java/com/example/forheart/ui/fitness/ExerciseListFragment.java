package com.example.forheart.ui.fitness;

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
import com.example.forheart.databinding.FragmentExerciseListBinding;
import com.example.forheart.model.Exercise;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;

import java.util.List;

/**
 * Fragment class that show the exercise list
 */
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
        // set adapter
        allExercises.observe(getViewLifecycleOwner(), exercises -> {
            adapter.setAllExercise(exercises);
        });

        // back
        binding.back.setOnClickListener(v -> {
            getNavigationFragment().popFragment();
        });

        // set header pic
        if (category.equals("bicycling")){
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.bike));
        } else if (category.equals("conditioning")) {
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.conditioning_exercise));
        } else if (category.equals("dancing")){
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.dance));
        } else if (category.equals("running")) {
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.run));
        } else if (category.equals("sports")) {
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.sports));
        } else if (category.equals("walking")) {
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.walk));
        } else if (category.equals("water activities")) {
            binding.imageViewExerciseCategory.setImageDrawable(getResources().getDrawable(R.drawable.water_activities));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Recommended Activities");
    }

    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
