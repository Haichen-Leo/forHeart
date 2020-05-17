package com.example.forheart.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.forheart.model.Exercise;
import com.example.forheart.model.Plan;

@Database(entities = {Plan.class, Exercise.class}, version = 2, exportSchema = false)
public abstract class PlanDatabase extends RoomDatabase {

    private static PlanDatabase INSTANCE;
    public static synchronized PlanDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PlanDatabase.class, "plan_database")
//                    .fallbackToDestructiveMigration()
//                    .build();

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlanDatabase.class, "plan_database_3")
                    .createFromAsset("database/plan_database_v2")
                    .build();
        }
        return INSTANCE;
    }

    public abstract PlanDao getPlanDao();
    public abstract ExerciseDao getExerciseDao();
}
