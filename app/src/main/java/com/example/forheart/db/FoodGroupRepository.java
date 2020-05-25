package com.example.forheart.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.FoodGroup;

import java.util.List;

/*
 * Repository class that get food group data from database
 */
public class FoodGroupRepository {
    private LiveData<List<FoodGroup>> allFoodGroupsLive;
    private FoodGroupDao foodGroupDao;

    public FoodGroupRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getINSTANCE(context.getApplicationContext());
        foodGroupDao = foodDatabase.getFoodGroupDao();
    }

    public LiveData<List<FoodGroup>> getAllFoodGroupsLive() {
        allFoodGroupsLive = foodGroupDao.getAllFoodGroupsLive();
        return allFoodGroupsLive;
    }

}
