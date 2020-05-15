package com.example.forheart.ui.fitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forheart.databinding.FitnessFragmentBinding;
import com.example.forheart.model.Plan;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.ProgressUtil;
import com.navigation.androidx.DrawerFragment;
import com.navigation.androidx.ToolbarButtonItem;

import java.util.List;

public class FitnessFragment extends BaseFragment {

    private PlanViewModel mViewModel;
    private FitnessFragmentBinding binding;
    private RecyclerView recyclerView;
    private PlanAdapter adapter;
    private LiveData<List<Plan>> allPlans;
    private Preference_UserProfile userProfile;

    private static String fromCharCode(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FitnessFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        userProfile = Preference_UserProfile.getInstance(getContext());
        adapter = new PlanAdapter(getContext(), getNavigationFragment());
        recyclerView = binding.recyclerViewPlan;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mViewModel = new ViewModelProvider(this).get(PlanViewModel.class);
        allPlans = mViewModel.getAllPlansLive();
        allPlans.observe(getViewLifecycleOwner(), plans -> adapter.setAllPlans(plans));

        // setup circle progress
        String taskType = userProfile.getTaskType();
        ProgressUtil.init(taskType);
        float progress = ProgressUtil.transVig(userProfile.getWeekVigorousCount()) + userProfile.getWeekModerateCount();
        binding.circleView.setMaxValue(ProgressUtil.getModerate());
        binding.circleView.setValueAnimated(progress, 1500);

        // setup weekly exercise count
        binding.textViewModerateMin.setText(String.valueOf(userProfile.getWeekModerateCount()));
        binding.textViewVigorousMin.setText(String.valueOf(userProfile.getWeekVigorousCount()));
        userProfile.addWeekModerateCountOnChangedListener(weekmoderatecount -> {
            binding.textViewModerateMin.setText(String.valueOf(weekmoderatecount));
            binding.circleView.setValueAnimated(ProgressUtil.transVig(userProfile.getWeekVigorousCount()) + weekmoderatecount, 1500 );
        });
        userProfile.addWeekVigorousCountOnChangedListener(weekvigorouscount -> {
            binding.textViewVigorousMin.setText(String.valueOf(weekvigorouscount));
            binding.circleView.setValueAnimated(ProgressUtil.transVig(weekvigorouscount) + userProfile.getWeekModerateCount(), 1500);
        });

        binding.taskType.setText(userProfile.getTaskType());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Workout");
        // nav - menu
        if (isNavigationRoot()) {
            String iconUri = "font://FontAwesome/" + fromCharCode(61641) + "/24";
            ToolbarButtonItem.Builder builder = new ToolbarButtonItem.Builder();
            builder.icon(iconUri).listener(view -> {
                DrawerFragment drawerFragment = getDrawerFragment();
                if (drawerFragment != null) {
                    drawerFragment.toggleMenu();
                }
            });
            setLeftBarButtonItem(builder.build());
        }
        binding.buttonNewPlan.setOnClickListener(v -> {
            PlanSetFragment fragment = new PlanSetFragment();
            getNavigationFragment().pushFragment(fragment);
        });

    }

}
