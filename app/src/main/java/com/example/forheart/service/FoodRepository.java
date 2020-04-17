package com.example.forheart.service;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forheart.db.FoodDao;
import com.example.forheart.db.FoodDatabase;
import com.example.forheart.model.Food;

import java.util.List;

public class FoodRepository {

    private LiveData<List<Food>> allFoodsLive;
    private FoodDao foodDao;

    public FoodRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getINSTANCE(context.getApplicationContext());
        foodDao = foodDatabase.getFoodDao();
        allFoodsLive = foodDao.getAllFoods();
    }

    public LiveData<List<Food>> getAllFoodsLive() {
        return allFoodsLive;
    }

    // return a list of food matching a certain pattern
    public LiveData<List<Food>> findFoodsWithPattern(String pattern) {
        return foodDao.findFoodsWithPattern("%" + pattern + "%");
    }

//    public void insertFoods(Food...foods) {
//        new InsertAsyncTaskFood(foodDao).execute(foods);
//    }

//    static class InsertAsyncTaskFood extends AsyncTask<Food, Void, Void> {
//        private FoodDao foodDao;
//
//        InsertAsyncTaskFood(FoodDao foodDao) {
//            this.foodDao = foodDao;
//        }
//
//        @Override
//        protected Void doInBackground(Food... foods) {
//            foodDao.insertFoods(foods);
//            return null;
//        }
//    }
}
