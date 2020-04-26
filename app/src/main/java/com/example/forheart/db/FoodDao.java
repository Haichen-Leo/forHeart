package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.forheart.model.Food;

import java.util.List;

@Dao
public interface FoodDao {

//    @Insert
//    void insertFoods(Food... foods);
//
//    @Update
//    void updateFoods(Food... foods);
//
//    @Delete
//    void deleteFoods(Food... foods);
//
//    @Query("DELETE FROM Food")
//    void deleteAllFoods();

    @Query("SELECT * FROM Food ORDER BY food_item_name")
    LiveData<List<Food>> getAllFoods();

    @Query("SELECT * FROM Food WHERE food_item_name LIKE :pattern ORDER BY food_item_name")
    LiveData<List<Food>> findFoodsWithPattern(String pattern);

    @Query("SELECT * FROM Food WHERE food_group_id = :groupId ORDER BY food_item_name")
    LiveData<List<Food>> findFoodsWithGroup(int groupId);

    @Query("SELECT * FROM Food WHERE food_group_id = :groupId AND food_item_name LIKE :pattern ORDER BY food_item_name")
    LiveData<List<Food>> findFoodsWithPattern(int groupId, String pattern);

    @Query("SELECT * FROM Food ORDER BY total_score DESC LIMIT 3")
    LiveData<List<Food>> findBestFoods();

    @Query("SELECT * FROM Food WHERE food_group_id = :groupId ORDER BY total_score DESC LIMIT 3")
    LiveData<List<Food>> findBestFoodsWithGroup(int groupId);
}
