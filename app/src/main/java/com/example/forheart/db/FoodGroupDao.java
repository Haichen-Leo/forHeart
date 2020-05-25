package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.forheart.model.FoodGroup;

/**
 * Interface class for Room
 */
@Dao
public interface FoodGroupDao {

    @Insert
    void insertFoodGroups(FoodGroup... foodGroups);

    @Query("SELECT * FROM FoodGroup WHERE food_group_id = :foodGroupId")
    FoodGroup findFoodGroupById(int foodGroupId);

    @Query("SELECT * FROM FoodGroup ORDER BY food_group_name")
    LiveData<List<FoodGroup>> getAllFoodGroupsLive();
}
