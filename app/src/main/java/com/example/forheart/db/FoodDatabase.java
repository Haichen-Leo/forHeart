package com.example.forheart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.forheart.model.Food;
import com.example.forheart.model.FoodGroup;

@Database(entities = {Food.class, FoodGroup.class}, version = 1, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    private static FoodDatabase INSTANCE;
    public static synchronized FoodDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FoodDatabase.class, "food_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract FoodDao getFoodDao();
    public abstract FoodGroupDao getFoodGroupDao();
}
