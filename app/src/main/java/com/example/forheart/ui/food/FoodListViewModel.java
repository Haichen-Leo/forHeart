package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.FoodRepository;
import com.example.forheart.model.Food;

import java.util.List;

public class FoodListViewModel extends AndroidViewModel {
    private FoodRepository foodRepository;

    public FoodListViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);
    }

    LiveData<List<Food>> getAllFoodsLive() {
        return foodRepository.getAllFoodsLive();
    }

    LiveData<List<Food>> findFoodsWithGroup(int groupId) {
        return foodRepository.findFoodsWithGroup(groupId);
    }

    LiveData<List<Food>> findFoodsWithPattern(String pattern) {
        return foodRepository.findFoodsWithPattern(pattern);
    }

    LiveData<List<Food>> findFoodsWithGroup(int groupId, String pattern) {
        return foodRepository.findFoodsWithPattern(groupId, pattern);
    }
}
