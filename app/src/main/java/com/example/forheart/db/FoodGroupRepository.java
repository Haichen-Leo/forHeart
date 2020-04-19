package com.example.forheart.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.FoodGroup;

import java.util.List;

public class FoodGroupRepository {
    private LiveData<List<FoodGroup>> allFoodGroupsLive;
    private FoodGroupDao foodGroupDao;

    public FoodGroupRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getINSTANCE(context.getApplicationContext());
        foodGroupDao = foodDatabase.getFoodGroupDao();
//        allFoodGroupsLive = foodGroupDao.getAllFoodGroupsLive();
    }

    public LiveData<List<FoodGroup>> getAllFoodGroupsLive() {
        return allFoodGroupsLive;
    }

}
