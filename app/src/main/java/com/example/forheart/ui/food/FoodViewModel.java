package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.model.FoodGroup;
import com.example.forheart.service.FoodGroupRepository;

import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private FoodGroupRepository foodGroupRepository;
    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodGroupRepository = new FoodGroupRepository(application);
    }

    LiveData<List<FoodGroup>> getAllFoodGroupsLive() {
        return foodGroupRepository.getAllFoodGroupsLive();
    }

//    void insertFoodGroups(FoodGroup... foodGroups) {
//        foodGroupRepository.insertFoodGroups(foodGroups);
//    }
}
