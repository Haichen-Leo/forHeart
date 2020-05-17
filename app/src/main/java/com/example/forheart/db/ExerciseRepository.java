package com.example.forheart.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.Exercise;

import java.util.List;

public class ExerciseRepository {

    private LiveData<List<Exercise>> allExercises;
    private ExerciseDao exerciseDao;

    public ExerciseRepository(Context context) {
        PlanDatabase planDatabase = PlanDatabase.getINSTANCE(context.getApplicationContext());
        exerciseDao = planDatabase.getExerciseDao();
    }

    public LiveData<List<Exercise>> getAllExercises() {
        allExercises = exerciseDao.getAllExercises();
        return allExercises;
    }

    public LiveData<List<Exercise>> getModerateCategory(String category) {
        return exerciseDao.getModerateCategory(category);
    }

    public LiveData<List<Exercise>> getVigorousCategory(String category) {
        return exerciseDao.getVigorousCategory(category);
    }

    public LiveData<List<Exercise>> getAllModerates() {
        return exerciseDao.getAllModerates();
    }

    public void insertExercises(Exercise... exercises) {
        new InsertAsyncTask(exerciseDao).execute(exercises);
    }

    static class InsertAsyncTask extends AsyncTask<Exercise, Void, Void> {
        private ExerciseDao exerciseDao;
        InsertAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao = exerciseDao;
        }
        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.insertExercise(exercises);
            return null;
        }
    }
}
