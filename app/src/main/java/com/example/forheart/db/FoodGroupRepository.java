package com.example.forheart.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.FoodGroup;

import java.util.List;

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

//    public void deleteFoodGroup(FoodGroup... foodGroups) {
//        new DeleteAsyncTaskFood(foodGroupDao).execute(foodGroups);
//    }

//    static class DeleteAsyncTaskFood extends AsyncTask<FoodGroup, Void, Void> {
//        private FoodGroupDao foodGroupDao;
//
//        DeleteAsyncTaskFood(FoodGroupDao foodGroupDao){
//            this.foodGroupDao = foodGroupDao;
//        }
//
//        @Override
//        protected Void doInBackground(FoodGroup... foodGroups) {
//            foodGroupDao.deleteFoodGroup(foodGroups);
//            return null;
//        }
//    }
}
