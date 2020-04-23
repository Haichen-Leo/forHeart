package com.example.forheart.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.forheart.model.Food;
import com.example.forheart.model.FoodGroup;

@Database(entities = {Food.class, FoodGroup.class}, version = 2, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    private static FoodDatabase INSTANCE;
    public static synchronized FoodDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FoodDatabase.class, "food_database")
//                    .addMigrations(MIGRATION_1_2)
//                    .build();
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FoodDatabase.class, "food_database_2")
                    .createFromAsset("database/food_database_v2")
                    .build();
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE INDEX IF NOT EXISTS index_food_group_id ON food(food_group_id)");
        }
    };

    public abstract FoodDao getFoodDao();
    public abstract FoodGroupDao getFoodGroupDao();
}
