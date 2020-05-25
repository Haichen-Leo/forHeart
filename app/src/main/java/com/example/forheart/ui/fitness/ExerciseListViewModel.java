package com.example.forheart.ui.fitness;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.ExerciseRepository;
import com.example.forheart.model.Exercise;

import java.util.List;

/**
 * ViewModel for exercise list
 */
public class ExerciseListViewModel extends AndroidViewModel {

    private ExerciseRepository exerciseRepository;

    public ExerciseListViewModel(@NonNull Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
    }

    LiveData<List<Exercise>> getAllExercises(){
        return exerciseRepository.getAllExercises();
    }

    LiveData<List<Exercise>> getModerateCategory(String category) {
        return exerciseRepository.getModerateCategory(category);
    }

    LiveData<List<Exercise>> getVigorousCategory(String category) {
        return exerciseRepository.getVigorousCategory(category);
    }

    LiveData<List<Exercise>> getAllModerates() {
        return exerciseRepository.getAllModerates();
    }

}
