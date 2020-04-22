package com.example.forheart.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class FoodBean implements Parcelable {
    private String foodId;
    private String foodName;
    private double totalDietaryFibre;
    private double totalOmega3;
    private double totalMonounsaturated;
    private double folicAcid;
    private double lycopene;
    private double magnesium;
    private double totalFolates;
    private double potassium;
    private double totalScore;
    private int foodGroupId;

    public void createFoodBean (Food food) {
        this.foodId = food.getFoodId();
        this.foodName = food.getFoodName();
        this.totalDietaryFibre = food.getTotalDietaryFibre();
        this.totalOmega3 = food.getTotalOmega3();
        this.totalMonounsaturated = food.getTotalMonounsaturated();
        this.folicAcid = food.getFolicAcid();
        this.lycopene = food.getLycopene();
        this.magnesium = food.getMagnesium();
        this.totalFolates = food.getTotalFolates();
        this.potassium = food.getPotassium();
        this.totalScore = food.getTotalScore();
        this.foodGroupId = food.getFoodGroupId();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString("foodId", getFoodId());
        bundle.putString("foodName", getFoodName());
        bundle.putDouble("totalDietaryFibre", getTotalDietaryFibre());
        bundle.putDouble("totalOmega3", getTotalOmega3());
        bundle.putDouble("totalMonounsaturated", getTotalMonounsaturated());
        bundle.putDouble("folicAcid", getFolicAcid());
        bundle.putDouble("lycopene", getLycopene());
        bundle.putDouble("magnesium", getMagnesium());
        bundle.putDouble("totalFolates", getTotalFolates());
        bundle.putDouble("potassium", getPotassium());
        bundle.putDouble("totalScore", getTotalScore());
        bundle.putInt("foodGroupId", getFoodGroupId());
        dest.writeBundle(bundle);
    }

    public static final Creator<FoodBean> CREATOR = new Creator<FoodBean>() {
        @Override
        public FoodBean createFromParcel(Parcel in) {
            Bundle bundle = in.readBundle();
            FoodBean foodBean = new FoodBean();
            foodBean.setFoodId(bundle.getString("foodId"));
            foodBean.setFoodName(bundle.getString("foodName"));
            foodBean.setTotalDietaryFibre(bundle.getDouble("totalDietaryFibre"));
            foodBean.setTotalOmega3(bundle.getDouble("totalOmega3"));
            foodBean.setTotalMonounsaturated(bundle.getDouble("totalMonounsaturated"));
            foodBean.setFolicAcid(bundle.getDouble("folicAcid"));
            foodBean.setLycopene(bundle.getDouble("lycopene"));
            foodBean.setMagnesium(bundle.getDouble("magnesium"));
            foodBean.setTotalFolates(bundle.getDouble("totalFolates"));
            foodBean.setPotassium(bundle.getDouble("potassium"));
            foodBean.setTotalScore(bundle.getDouble("totalScore"));
            foodBean.setFoodGroupId(bundle.getInt("foodGroupId"));
            return foodBean;
        }

        @Override
        public FoodBean[] newArray(int size) {
            return new FoodBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getTotalDietaryFibre() {
        return totalDietaryFibre;
    }

    public double getTotalOmega3() {
        return totalOmega3;
    }

    public double getTotalMonounsaturated() {
        return totalMonounsaturated;
    }

    public double getFolicAcid() {
        return folicAcid;
    }

    public double getLycopene() {
        return lycopene;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public double getTotalFolates() {
        return totalFolates;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public int getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setTotalDietaryFibre(double totalDietaryFibre) {
        this.totalDietaryFibre = totalDietaryFibre;
    }

    public void setTotalOmega3(double totalOmega3) {
        this.totalOmega3 = totalOmega3;
    }

    public void setTotalMonounsaturated(double totalMonounsaturated) {
        this.totalMonounsaturated = totalMonounsaturated;
    }

    public void setFolicAcid(double folicAcid) {
        this.folicAcid = folicAcid;
    }

    public void setLycopene(double lycopene) {
        this.lycopene = lycopene;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public void setTotalFolates(double totalFolates) {
        this.totalFolates = totalFolates;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public void setFoodGroupId(int foodGroupId) {
        this.foodGroupId = foodGroupId;
    }
}
