package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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
     * @return recommended food from all categories if the group is empty
     */
    LiveData<List<Food>> getRecommend(int groupId) {
        if (isEmpty(groupId)) {
            return foodRepository.findBestFoods();
        } else {
            return foodRepository.findBestFoodsWithGroup(groupId);
        }
    }

    /**
     * Hard coded food groups that have empty non-zero foods
     * To be modified if the database updates
     */
    boolean isEmpty(int groupId) {
        int[] empty = {0, 17, 19, 20, 21, 27, 29, 32, 34};
        for (int i = 0; i<empty.length; i++) {
            if (empty[i] == groupId){
                return true;
            }
        }
        return  false;
    }
}
