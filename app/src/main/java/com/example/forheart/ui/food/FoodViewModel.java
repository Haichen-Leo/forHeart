package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.FoodGroupRepository;
import com.example.forheart.model.FoodGroup;

import java.util.List;

/**
 * ViewModel for food page
 */
public class FoodViewModel extends AndroidViewModel {
    private FoodGroupRepository foodGroupRepository;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodGroupRepository = new FoodGroupRepository(application);
    }

    LiveData<List<FoodGroup>> getAllFoodGroupsLive() {
        return foodGroupRepository.getAllFoodGroupsLive();
    }
}
