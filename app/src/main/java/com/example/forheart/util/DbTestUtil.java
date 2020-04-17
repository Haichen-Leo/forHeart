package com.example.forheart.util;

import com.example.forheart.model.Food;
import com.example.forheart.model.FoodGroup;

import java.util.ArrayList;

/**
 * Util class for room database testing
 */
public class DbTestUtil {

    // return an object of FoodGroup
    public FoodGroup createFoodGroup(int foodGroupId, String foodGroupName) {
        return new FoodGroup(foodGroupId, foodGroupName);
    }

    // return an object of Food
    public Food createFood(String foodId, String foodName, double totalDietaryFibre, double totalOmega3,
                           double totalMonounsaturated, double folicAcid, double lycopene, double magnesium,
                           double totalFolates, double potassium, double totalScore, int foodGroupId) {
        return new Food(foodId, foodName, totalDietaryFibre, totalOmega3, totalMonounsaturated, folicAcid,
                lycopene, magnesium, totalFolates, potassium, totalScore, foodGroupId);
    }

    // return an arraylist of FoodGroups with sample data
    public ArrayList<FoodGroup> createSampleFoodGroups() {
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
            foodGroups.add(createFoodGroup(foodGroupIds[i], foodGroupNames[i]));
        }
        return foodGroups;
    }

    // return an arraylist of Food with sample data
    public ArrayList<Food> createSampleFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        String[] foodIds = {
                "F002980", "F004726", "F009535", "F000061", "F001152", "F002159", "F001973",
                "F001971", "F007848", "F007849", "F006830", "F000086", "F003721", "F003725",
                "F007035", "F002769", "F002407", "F002435", "F009176", "F009738", "F008571",
                "F008670", "F007502", "F008215", "F008029", "F008004", "F008204", "F004192",
                "F004002", "F005250", "F006844", "F003198", "F004607", "F004604", "F002939",
                "F002921", "F002955", "F009572", "F007870", "F008785", "F005647", "F003301", "F003300"
        };
        String[] foodNames = {
                "Cocoa powder", "Juice, lemon", "Wheat bran, unprocessed, uncooked", "Amaranth, grain, whole, uncooked",
                "Biscuit, savoury, rice cracker, plain", "Cake, lamington, unfilled", "Butter, plain, salted",
                "Butter, plain, no added salt", "Salmon, Pacific King, fillet, skinless, grilled, no added fat",
                "Salmon, Pacific King, fillet, skinless, raw", "Plum, salted", "Apple, bonza, unpeeled, raw",
                "Egg, chicken, whole, hard-boiled", "Egg, chicken, whole, poached", "Pork, loin roast, separable fat, raw",
                "Chicken, separable fat, composite, raw", "Cheese, brie", "Cheese, cottage", "Tofu (soy bean curd), firm, as purchased",
                "Yoghurt, soy based, berry flavoured, regular fat (3% fat)", "Soup, cream variety, instant dry mix",
                "Soup, vegetable, instant dry mix", "Psyllium, uncooked", "Seed, sunflower", "Sauce, pasta, basil pesto, commercial",
                "Sauce, fish, commercial", "Seaweed, nori, dried", "Garlic, peeled, fresh, fried, no added fat",
                "Flour, soya", "Lupin, whole, uncooked", "Popcorn, commercial, butter flavoured, salted",
                "Corn chips, plain, toasted, salted", "Jam, stone fruit", "Jam, plum", "Chocolate, milk, with nuts",
                "Chocolate, dark, high cocoa solids", "Cider, apple (alcohol approximately 4-5% v/v)", "Wine, red, cabernet sauvignon",
                "Salt substitute, potassium chloride", "Spread, yeast, vegemite", "Milk, human/breast, mature, fluid",
                "Crocodile, tail fillet, raw", "Crocodile, cooked, no added fat"
        };
        double[] totalDietaryFibres = {
                27.7, 2.5, 41.8, 11.1, 1.6, 2.4, 0.0, 0.0, 0.0, 0.0, 9.3, 3.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3.5, 0.2, 6.5, 3.3, 88.7, 10.8, 1.5, 1.2, 34.3, 27.3, 16.0, 41.5, 8.8, 5.0, 0.9, 0.9, 4.7, 1.2, 0.0, 0.0, 0.0, 6.5, 0.0, 0.0, 0.0
        };
        double[] totalOmega3 = {
                0.0, 0.0, 0.0, 0.0, 1.04, 0.0, 92.19, 92.19, 3822.15, 3420.83, 0.0, 0.0, 73.28, 69.6, 146.95, 0.0, 0.0, 5.4, 0.0, 0.0, 0.0, 3.99, 0.0, 0.0, 6.03, 78.75, 1778.48, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 17.46, 42.5, 121.07
        };
        double[] totalMonounsaturateds = {
                4.71, 0.0, 0.66, 1.35, 1.48, 1.36, 16.07, 16.07, 9.8, 8.77, 0.0, 0.0, 3.96, 3.7, 33.06, 31.56, 8.16, 1.41, 1.64, 1.52, 4.87, 0.74, 0.38, 9.85, 21.73, 0.05, 0.32, 0.36, 1.74, 1.86, 4.94, 10.84, 0.0, 0.0, 20.7, 9.46, 0.0, 0.0, 0.0, 0.58, 1.38, 0.64, 1.22
        };
        double[] folicAcids = {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2354.0, 0.0, 0.0, 0.0
        };
        double[] lycopenes = {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
        };
        double[] magnesiums = {
                590.0, 9.0, 450.0, 237.0, 46.0, 26.0, 2.0, 2.0, 30.0, 27.0, 120.0, 5.0, 10.0, 12.0, 4.0, 5.0, 20.0, 8.0, 78.0, 7.0, 14.0, 36.0, 34.0, 370.0, 32.0, 118.0, 320.0, 40.0, 285.0, 171.0, 142.0, 49.0, 4.0, 4.0, 85.0, 120.0, 2.0, 13.0, 1.0, 130.0, 3.0, 22.0, 32.0
        };
        double[] totalFolates = {
                62.0, 8.0, 100.0, 82.0, 29.0, 10.0, 6.0, 6.0, 0.0, 0.0, 111.0, 59.0, 83.0, 86.0, 11.0, 0.0, 50.0, 3.0, 30.0, 75.0, 110.0, 110.0, 0.0, 229.0, 34.0, 51.0, 1400.0, 4.0, 289.0, 350.0, 15.0, 17.0, 11.0, 11.0, 56.0, 13.0, 0.0, 0.0, 0.0, 2454.0, 5.0, 8.0, 11.0
        };
        double[] potassiums = {
                4400.0, 120.0, 1200.0, 508.0, 220.0, 200.0, 23.0, 23.0, 425.0, 380.0, 610.0, 88.0, 107.0, 146.0, 69.0, 63.0, 100.0, 123.0, 130.0, 55.0, 339.0, 22.0, 860.0, 460.0, 232.0, 215.0, 2900.0, 823.0, 2090.0, 570.0, 260.0, 166.0, 84.0, 84.0, 505.0, 440.0, 56.0, 117.0, 50009.0, 2122.0, 51.0, 370.0, 493.0
        };
        double[] totalScores = {
                3.27, 0.0, 3.08, 2.0, 0.0, 0.0, 1.0, 1.0, 4.24, 3.8, 1.01, 0.0, 0.0, 0.0, 2.05, 1.96, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0, 3.12, 1.35, 1.0, 2.3625, 1.54, 2.41, 1.89, 1.2, 0.0, 0.0, 0.0, 1.28, 1.01, 0.0, 0.0, 5.0, 3.7, 0.0, 0.0, 0.0
        };
        int[] foodGroupIds = {
                11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 31, 31, 32, 34, 34
        };

        for (int i = 0; i < foodIds.length; i++) {
            foods.add(createFood(foodIds[i],foodNames[i],totalDietaryFibres[i], totalOmega3[i],
                    totalMonounsaturateds[i], folicAcids[i], lycopenes[i], magnesiums[i], totalFolates[i], potassiums[i], totalScores[i], foodGroupIds[i]));
        }
        return foods;
    }
}
