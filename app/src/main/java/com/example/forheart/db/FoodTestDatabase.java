package com.example.forheart.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.forheart.model.Food;
import com.example.forheart.model.FoodGroup;

@Database(entities = {Food.class, FoodGroup.class}, version = 1, exportSchema = false)
public abstract class FoodTestDatabase extends RoomDatabase {

    public abstract FoodDao getFoodDao();
    public abstract FoodGroupDao getFoodGroupDao();
}
