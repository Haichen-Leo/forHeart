package com.example.forheart.service;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forheart.db.FoodDatabase;
import com.example.forheart.db.FoodGroupDao;
import com.example.forheart.model.FoodGroup;

import java.util.List;

public class FoodGroupRepository {
    private LiveData<List<FoodGroup>> allFoodGroupsLive;
    private FoodGroupDao foodGroupDao;

    public FoodGroupRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getINSTANCE(context.getApplicationContext());
        foodGroupDao = foodDatabase.getFoodGroupDao();
        allFoodGroupsLive = foodGroupDao.getAllFoodGroups();
    }

    public LiveData<List<FoodGroup>> getAllFoodGroupsLive() {
        return allFoodGroupsLive;
    }

//    public void insertFoodGroups(FoodGroup... foodGroups) {
//        new InsertAsyncTaskFoodGroup(foodGroupDao).execute(foodGroups);
//    }
//
//    static class InsertAsyncTaskFoodGroup extends AsyncTask<FoodGroup, Void, Void> {
//        private FoodGroupDao foodGroupDao;
//
//        InsertAsyncTaskFoodGroup(FoodGroupDao foodGroupDao) {
//            this.foodGroupDao = foodGroupDao;
//        }
//
//        @Override
//        protected Void doInBackground(FoodGroup... foodGroups) {
//            foodGroupDao.insertFoodGroups(foodGroups);
//            return null;
//        }
//    }
}
