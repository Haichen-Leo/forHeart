package com.example.forheart.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.forheart.model.Plan;

import java.util.List;

public class PlanRepository {

    private LiveData<List<Plan>> allPlansLive;
    private PlanDao planDao;

    public PlanRepository(Context context) {
        PlanDatabase planDatabase = PlanDatabase.getINSTANCE(context.getApplicationContext());
        planDao = planDatabase.getPlanDao();
    }

    public LiveData<List<Plan>> getAllPlansLive() {
        allPlansLive = planDao.getAllPlans();
        return allPlansLive;
    }

    public LiveData<Plan> findPlanById(int planId) {
        return planDao.findPlanById(planId);
    }

    public void insertPlans(Plan... plans) {
        new InsertAsyncTask(planDao).execute(plans);
    }

    public void updatePlans(Plan... plans) {
        new UpdateAsyncTask(planDao).execute(plans);
    }

    public void deletePlans(Plan... plans) {
        new DeleteAsyncTask(planDao).execute(plans);
    }

    public void deleteAllPlans() {
        new DeleteAllAsyncTask(planDao).execute();
    }

    public LiveData<Plan> getLastPlan() {
        return planDao.getLastPlan();
    }


    static class InsertAsyncTask extends AsyncTask<Plan, Void, Void> {
        private PlanDao planDao;
        InsertAsyncTask(PlanDao planDao) {
            this.planDao = planDao;
        }
        @Override
        protected Void doInBackground(Plan... plans) {
            planDao.insertPlans(plans);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Plan, Void, Void> {
        private PlanDao planDao;
        UpdateAsyncTask(PlanDao planDao) {
            this.planDao = planDao;
        }
        @Override
        protected Void doInBackground(Plan... plans) {
            planDao.updatePlans(plans);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Plan, Void, Void> {
        private PlanDao planDao;
        DeleteAsyncTask(PlanDao planDao) {
            this.planDao = planDao;
        }
        @Override
        protected Void doInBackground(Plan... plans) {
            planDao.deletePlans(plans);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlanDao planDao;
        DeleteAllAsyncTask(PlanDao planDao) {
            this.planDao = planDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            planDao.deleteAllPlans();
            return null;
        }
    }

}
