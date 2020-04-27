package com.example.forheart.ui.fitness;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.PlanRepository;
import com.example.forheart.model.Plan;

public class PlanDetailViewModel extends AndroidViewModel {
    private PlanRepository repository;

    public PlanDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new PlanRepository(application);
    }

    LiveData<Plan> getLastPlan() {
        return repository.getLastPlan();
    }

    void insertPlans(Plan... plans) {
        repository.insertPlans(plans);
    }
}
