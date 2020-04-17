package com.example.forheart.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class FoodGroup {

    @PrimaryKey
    @ColumnInfo(name = "food_group_id")
    private int foodGroupId;
    @ColumnInfo(name = "food_group_name")
    private String foodGroupName;

    public FoodGroup(int foodGroupId, String foodGroupName) {
        this.foodGroupId = foodGroupId;
        this.foodGroupName = foodGroupName;
    }

    public int getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(int foodGroupId) {
        this.foodGroupId = foodGroupId;
    }

    public String getFoodGroupName() {
        return foodGroupName;
    }

    public void setFoodGroupName(String foodGroupName) {
        this.foodGroupName = foodGroupName;
    }

    public boolean like(FoodGroup aFoodGroup) {
        if (this.foodGroupId == aFoodGroup.foodGroupId
                && this.foodGroupName.equals(aFoodGroup.foodGroupName)) {
            return true;
        }
        return false;
    }
}
