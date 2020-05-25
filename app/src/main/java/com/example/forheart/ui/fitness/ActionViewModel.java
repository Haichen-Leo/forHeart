package com.example.forheart.ui.fitness;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.PlanRepository;
import com.example.forheart.model.Plan;

/**
 * ViewModel for Action fragment
 */
public class ActionViewModel extends AndroidViewModel {
    private PlanRepository planRepository;

    public ActionViewModel(@NonNull Application application) {
        super(application);
        planRepository = new PlanRepository(application);
    }

    LiveData<Plan> findPlanById(int planId) {
        return planRepository.findPlanById(planId);
    }

    void updatePlans(Plan... plans) {
        planRepository.updatePlans(plans);
    }

    void deletePlans(Plan... plans) {
        planRepository.deletePlans(plans);
    }
}
