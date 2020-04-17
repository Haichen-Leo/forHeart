package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import com.example.forheart.model.Food;

@Dao
public interface FoodDao {

    @Insert
    void insertFoods(Food... foods);

    @Update
    void updateFoods(Food... foods);

    @Query("DELETE FROM Food")
    void deleteAllFoods();

    @Query("SELECT * FROM Food ORDER BY food_item_name")
    LiveData<List<Food>> getAllFoods();

    @Query("SELECT * FROM Food WHERE food_item_name LIKE :pattern ORDER BY food_item_name")
    LiveData<List<Food>> findFoodsWithPattern(String pattern);

}
