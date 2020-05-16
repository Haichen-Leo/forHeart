package com.example.forheart.ui.fitness;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.forheart.R;
import com.example.forheart.databinding.ActionFragmentBinding;
import com.example.forheart.model.Plan;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.DateTimeUtils;
import com.example.forheart.util.ToastUtil;

import es.dmoral.toasty.Toasty;

public class ActionFragment extends BaseFragment {

    private ActionViewModel mViewModel;
    private ActionFragmentBinding binding;
    private LiveData<Plan> livePlan;
    private Plan aPlan;
    private static final int REQUEST_CODE_START = 1;
    private static final int REQUEST_CODE_DONE = 2;
    private static final int REQUEST_CODE_DELETE = 3;
    private static final String ACTION_TYPE = "action_type";
    static final String ACTION_START = "action_start";
    static final String ACTION_DONE = "action_done";
    static final String ACTION_DELETE = "action_delete";

    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.action_fragment, container, false);
        binding = ActionFragmentBinding.bind(root);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        aPlan = new Plan();
        int planId = getArguments().getInt(String.valueOf(R.string.nav_plan_id));
        mViewModel = new ViewModelProvider(this).get(ActionViewModel.class);
        livePlan = mViewModel.findPlanById(planId);
        livePlan.observe(getViewLifecycleOwner(), plan -> {
            if (plan!=null){
                // new plan instance for update/delete
                aPlan.setId(plan.getId());
                aPlan.setActivity(plan.getActivity());
                aPlan.setType(plan.getType());
                aPlan.setYear(plan.getYear());
                aPlan.setMonth(plan.getMonth());
                aPlan.setDay(plan.getDay());
                aPlan.setHour(plan.getHour());
                aPlan.setMinute(plan.getMinute());
                aPlan.setDuration(plan.getDuration());
                aPlan.setDone(plan.isDone());
                aPlan.setDescription(plan.getDescription());

                // view plan details
                String datetime = plan.getDay() + " " + plan.getMonth() + " " + plan.getHour() + " " + plan.getMinute();
                String duration = plan.getDuration() + " min";
                String type = plan.getType();
                binding.textViewActivity.setText(plan.getActivity());
                binding.textViewDatetime.setText(DateTimeUtils.parseDateTimeFormat(datetime));
                binding.textViewDuration.setText(duration);
                binding.workoutType.setText(type);
                binding.textViewDes.setText(plan.getDescription());
                if(plan.isDone() == true) {
//                    binding.textViewDone.setVisibility(View.VISIBLE);
                    binding.imageButtonStart.setBackgroundResource(R.drawable.action_bt_disable_bg);
                    binding.imageButtonDone.setBackgroundResource(R.drawable.action_bt_disable_bg);
                } else {
                    // start button
                    binding.imageButtonStart.setOnClickListener(v -> {
                        ActionDialog dialog = ActionDialog.newInstance(ACTION_START);
                        showDialog(dialog,REQUEST_CODE_START);
                    });

                    // done button
                    binding.imageButtonDone.setOnClickListener(v -> {
                        ActionDialog dialog = ActionDialog.newInstance(ACTION_DONE);
                        showDialog(dialog,REQUEST_CODE_DONE);
                    });
                }
                // remove button
                binding.imageButtonRemove.setOnClickListener(v -> {
                    ActionDialog dialog = ActionDialog.newInstance(ACTION_DELETE);
                    showDialog(dialog,REQUEST_CODE_DELETE);
                });

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Physical Activity");
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_START) {
            if (resultCode == Activity.RESULT_OK) {
                // set new alarm
            }
        }
        if (requestCode == REQUEST_CODE_DONE) {
            if (resultCode == Activity.RESULT_OK) {
                aPlan.setDone(true);
                mViewModel.updatePlans(aPlan);
                getNavigationFragment().popToRootFragment();
                // count exercise time
                Preference_UserProfile profile = Preference_UserProfile.getInstance(getContext());
                int duration = aPlan.getDuration();
                int weekCount = 0;
                int totalCount = 0;
                if (aPlan.getType().equals("moderate")) {
                    weekCount = profile.getWeekModerateCount() + duration;
                    totalCount = profile.getTotalModerateCount() + duration;
                    profile.putWeekModerateCount(weekCount);
                    profile.putTotalModerateCount(totalCount);
                } else {
                    weekCount = profile.getWeekVigorousCount() + duration;
                    totalCount = profile.getTotalVigorousCount() + duration;
                    profile.putWeekVigorousCount(weekCount);
                    profile.putTotalVigorousCount(totalCount);
                }
//                ToastUtil.bottomToast(getContext(),"Activity Done");
                Toasty.success(getContext(),"Activity Done!", Toast.LENGTH_SHORT, true).show();
            }
        }
        if (requestCode == REQUEST_CODE_DELETE) {
            if (resultCode == Activity.RESULT_OK) {
                mViewModel.deletePlans(aPlan);
                getNavigationFragment().popToRootFragment();
//                ToastUtil.bottomToast(getContext(),"Activity Removed");
                Toasty.info(getContext(),"Activity Removed", Toast.LENGTH_SHORT, true).show();
            }
        }
    }
}
