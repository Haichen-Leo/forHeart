package com.example.forheart.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.Food;

import java.util.List;

/*
 * Repository class that get food data from database
 */
public class FoodRepository {

    private LiveData<List<Food>> allFoodsLive;
    private FoodDao foodDao;

    public FoodRepository(Context context) {
        FoodDatabase foodDatabase = FoodDatabase.getINSTANCE(context.getApplicationContext());
        foodDao = foodDatabase.getFoodDao();
    }

    public LiveData<List<Food>> getAllFoodsLive() {
        allFoodsLive = foodDao.getAllFoods();
        return allFoodsLive;
    }

    public LiveData<List<Food>> findFoodsWithGroup(int groupId) {
        return foodDao.findFoodsWithGroup(groupId);
    }

    // return a list of food matching a certain pattern
    public LiveData<List<Food>> findFoodsWithPattern(String pattern) {
        return foodDao.findFoodsWithPattern("%" + pattern + "%");
    }

    // return a list of food matching a certain pattern within a food category
    public LiveData<List<Food>> findFoodsWithPattern(int groupId, String pattern) {
        return foodDao.findFoodsWithPattern(groupId,"%" + pattern + "%");
    }

    // recommendation
    public LiveData<List<Food>> findBestFoods() {
        return foodDao.findBestFoods();
    }

    // recommendation within certain food group
    public LiveData<List<Food>> findBestFoodsWithGroup(int groupId) {
        return foodDao.findBestFoodsWithGroup(groupId);
    }

    // recommendation - top 20
    public LiveData<List<Food>> findAllBestFoods() {
        return foodDao.findAllBestFoods();
    }

}
