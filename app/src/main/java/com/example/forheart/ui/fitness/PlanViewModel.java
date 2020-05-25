package com.example.forheart.ui.fitness;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.forheart.db.PlanRepository;
import com.example.forheart.model.Plan;

import java.util.List;

/**
 * View Model for workout page
 */
public class PlanViewModel extends AndroidViewModel {

    private PlanRepository planRepository;

    public PlanViewModel(@NonNull Application application) {
        super(application);
        planRepository = new PlanRepository(application);
    }

    LiveData<List<Plan>> getAllPlansLive() {
        return planRepository.getAllPlansLive();
    }

}
