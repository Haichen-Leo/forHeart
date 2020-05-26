package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.FoodRepository;
import com.example.forheart.model.Food;

import java.util.List;

public class RecommandViewModel extends AndroidViewModel {
    private FoodRepository foodRepository;

    public RecommandViewModel(@NonNull Application application) {
        super(application);
        this.foodRepository = new FoodRepository(application);
    }

    /**
     * Get recommended food in a live-data list
     * @param groupId id of current food group
     * @return recommended food from all categories if no more than 3 good food within this group
     */
    LiveData<List<Food>> getRecommend(int groupId) {
        if (!goodCategory(groupId)) {
            return foodRepository.findBestFoods();
        } else {
            return foodRepository.findBestFoodsWithGroup(groupId);
        }
    }


    /**
     * Check if there are more than 3 good food
     * @param groupId within this group
     */
    boolean goodCategory(int groupId) {
        int[] group = {12, 15, 22, 25, 31};
        for (int i=0; i<group.length; i++) {
            if (group[i] == groupId) {
                return true;
            }
        }
        return false;
    }
}
