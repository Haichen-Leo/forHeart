package com.example.forheart;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.forheart.db.FoodDao;
import com.example.forheart.db.FoodGroupDao;
import com.example.forheart.db.FoodTestDatabase;
import com.example.forheart.model.FoodGroup;
import com.example.forheart.util.DbTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FoodDbTest {
    private FoodDao foodDao;
    private FoodGroupDao foodGroupDao;
    private FoodTestDatabase foodTestDatabase;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        foodTestDatabase = Room.inMemoryDatabaseBuilder(context, FoodTestDatabase.class).build();
        foodDao = foodTestDatabase.getFoodDao();
        foodGroupDao = foodTestDatabase.getFoodGroupDao();
    }

    @After
    public void closeDb() throws IOException {
        foodTestDatabase.close();
    }

    @Test
    public void writeAndReadFoodGroup() throws Exception {
        DbTestUtil dbTestUtil = new DbTestUtil();
        FoodGroup foodGroup = dbTestUtil.createFoodGroup(11, "Non-alcoholic beverages");
        for (FoodGroup aFoodGroup: dbTestUtil.createSampleFoodGroups()) {
            foodGroupDao.insertFoodGroups(aFoodGroup);
        }
        FoodGroup byId = foodGroupDao.findFoodGroupById(11);
        assertTrue(byId.like(foodGroup));
    }
}
