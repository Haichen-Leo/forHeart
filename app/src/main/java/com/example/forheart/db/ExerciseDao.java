package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.forheart.model.Exercise;

import java.util.List;

/**
 * Interface class for Room
 */
@Dao
public interface ExerciseDao {

    @Insert
    void insertExercise(Exercise... exercises);

    @Query("SELECT * FROM Exercise")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM Exercise WHERE exercise_type = 'Moderate'")
    LiveData<List<Exercise>> getAllModerates();

    @Query("SELECT * FROM Exercise WHERE exercise_type = 'Vigorous'")
    LiveData<List<Exercise>> getAllVigorous();

    @Query("SELECT * FROM Exercise WHERE exercise_type = 'Moderate' AND exercise_category = :category")
    LiveData<List<Exercise>> getModerateCategory(String category);

    @Query("SELECT * FROM Exercise WHERE exercise_type = 'Vigorous' AND exercise_category = :category")
    LiveData<List<Exercise>> getVigorousCategory(String category);

}
