package com.example.forheart.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.forheart.model.Plan;

import java.util.List;

@Dao
public interface PlanDao {

    @Insert
    void insertPlans(Plan... plans);

    @Update
    void updatePlans(Plan... plans);

    @Delete
    void deletePlans(Plan... plans);

    @Query("DELETE FROM `Plan`")
    void deleteAllPlans();

    @Query("SELECT * FROM `Plan` ORDER BY plan_id")
    LiveData<List<Plan>> getAllPlans();

    @Query("SELECT * FROM `Plan` WHERE plan_id = :planId")
    LiveData<Plan> findPlanById(int planId);

    @Query("SELECT * FROM `Plan` ORDER BY plan_id DESC LIMIT 1")
    LiveData<Plan> getLastPlan();
}
