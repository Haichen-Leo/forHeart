package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.forheart.model.FoodGroup;

@Dao // Database access object
public interface FoodGroupDao {

    @Insert
    void insertFoodGroups(FoodGroup... foodGroups);

    @Update
    void updateFoodGroup(FoodGroup... foodGroups);

    @Query("DELETE FROM FoodGroup")
    void deleteAllFoodGroups();

    @Query("SELECT * FROM FoodGroup WHERE food_group_id = :foodGroupId")
    FoodGroup findFoodGroupById(int foodGroupId);

    @Query("SELECT * FROM FoodGroup ORDER BY food_group_name")
    LiveData<List<FoodGroup>> getAllFoodGroupsLive();

    @Query("SELECT * FROM FoodGroup ORDER BY food_group_name")
    List<FoodGroup> getAllFoodGroups();

    @Query("DELETE FROM FoodGroup WHERE food_group_id = 0")
    void deleteZero();
}
