package com.example.forheart.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = FoodGroup.class,
        parentColumns = "food_group_id",
        childColumns = "food_group_id"),
        indices = {@Index("food_group_id")})
public class Food {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food_item_id")
    private String foodId;
    @ColumnInfo(name = "food_item_name")
    private String foodName;
    @ColumnInfo(name = "total_dietary_fibre_g")
    private double totalDietaryFibre;
    @ColumnInfo(name = "total_omega3_mg")
    private double totalOmega3;
    @ColumnInfo(name = "total_monounsaturated_g")
    private double totalMonounsaturated;
    @ColumnInfo(name = "folic_acid_ug")
    private double folicAcid;
    @ColumnInfo(name = "lycopene_ug")
    private double lycopene;
    @ColumnInfo(name = "magnesium_mg")
    private double magnesium;
    @ColumnInfo(name = "total_folates_ug")
    private double totalFolates;
    @ColumnInfo(name = "potassium_mg")
    private double potassium;
    @ColumnInfo(name = "total_score")
    private double totalScore;

    @ColumnInfo(name = "food_group_id")
    private int foodGroupId;

    public Food(String foodId, String foodName, double totalDietaryFibre, double totalOmega3,
                double totalMonounsaturated, double folicAcid, double lycopene, double magnesium,
                double totalFolates, double potassium, double totalScore, int foodGroupId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.totalDietaryFibre = totalDietaryFibre;
        this.totalOmega3 = totalOmega3;
        this.totalMonounsaturated = totalMonounsaturated;
        this.folicAcid = folicAcid;
        this.lycopene = lycopene;
        this.magnesium = magnesium;
        this.totalFolates = totalFolates;
        this.potassium = potassium;
        this.totalScore = totalScore;
        this.foodGroupId = foodGroupId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getTotalDietaryFibre() {
        return totalDietaryFibre;
    }

    public void setTotalDietaryFibre(double totalDietaryFibre) {
        this.totalDietaryFibre = totalDietaryFibre;
    }

    public double getTotalOmega3() {
        return totalOmega3;
    }

    public void setTotalOmega3(double totalOmega3) {
        this.totalOmega3 = totalOmega3;
    }

    public double getTotalMonounsaturated() {
        return totalMonounsaturated;
    }

    public void setTotalMonounsaturated(double totalMonounsaturated) {
        this.totalMonounsaturated = totalMonounsaturated;
    }

    public double getFolicAcid() {
        return folicAcid;
    }

    public void setFolicAcid(double folicAcid) {
        this.folicAcid = folicAcid;
    }

    public double getLycopene() {
        return lycopene;
    }

    public void setLycopene(double lycopene) {
        this.lycopene = lycopene;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getTotalFolates() {
        return totalFolates;
    }

    public void setTotalFolates(double totalFolates) {
        this.totalFolates = totalFolates;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public int getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(int foodGroupId) {
        this.foodGroupId = foodGroupId;
    }
}
