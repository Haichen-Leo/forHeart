package com.example.forheart.ui.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.model.FoodGroup;
import com.example.forheart.db.FoodGroupRepository;

import java.util.ArrayList;
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

    List<FoodGroup> getAllFoodGroups() {
        ArrayList<FoodGroup> foodGroups = new ArrayList<>();
        int[] foodGroupIds = {
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27,
                28,
                29,
                31,
                32,
                34
        };
        String[] foodGroupNames = {
                "Non-alcoholic beverages",
                "Cereals and cereal products",
                "Cereal based products and dishes",
                "Fats and oils",
                "Fish and seafood products and dishes",
                "Fruit products and dishes",
                "Egg products and dishes",
                "Meat, poultry and game products and dishes",
                "Milk products and dishes",
                "Dairy & meat substitutes",
                "Soup",
                "Seed and nut products and dishes",
                "Savoury sauces and condiments",
                "Vegetable products and dishes",
                "Legume and pulse products and dishes",
                "Snack foods",
                "Sugar products and dishes",
                "Confectionery and cereal/nut/fruit/seed bars",
                "Alcoholic beverages",
                "Miscellaneous",
                "Infant formulae and foods",
                "Reptiles, amphibia and insects"
        };
        for (int i = 0; i < foodGroupIds.length; i++) {
            foodGroups.add(new FoodGroup(foodGroupIds[i], foodGroupNames[i]));
        }
        return foodGroups;
    }

}
