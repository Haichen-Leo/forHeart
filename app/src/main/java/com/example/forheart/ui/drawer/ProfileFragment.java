package com.example.forheart.ui.drawer;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.forheart.R;
import com.example.forheart.databinding.FragmentProfileBinding;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;

import es.dmoral.toasty.Toasty;


public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding binding;
    private Preference_UserProfile userProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        userProfile = Preference_UserProfile.getInstance(getContext());
        // nickname
        binding.editTextNickname.setText(userProfile.getNickname());
        binding.buttonNameApply.setOnClickListener(v -> {
            String newName = binding.editTextNickname.getText().toString();
            if(TextUtils.isEmpty(newName)){
                Toasty.info(getContext(),"Name empty!", Toast.LENGTH_SHORT, true).show();
            } else {
                userProfile.putNickname(newName);
                binding.editTextNickname.clearFocus();
                hideKeyboard(getView());
                Toasty.success(getContext(),"Success", Toast.LENGTH_SHORT, true).show();
            }

        });

        // task type
        if (userProfile.getTaskType().equals("Heavy")) {
            binding.radioGroupTask.check(R.id.radioButton_heavy);
            binding.textViewExplain.setText(R.string.explain_heavy);
        } else if (userProfile.getTaskType().equals("Normal") ){
            binding.radioGroupTask.check(R.id.radioButton_normal);
            binding.textViewExplain.setText(R.string.explain_normal);
        } else {
            binding.radioGroupTask.check(R.id.radioButton_light);
            binding.textViewExplain.setText(R.string.explain_light);
        }

        binding.radioGroupTask.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioButton_heavy:
                    binding.textViewExplain.setText(R.string.explain_heavy);
                    break;
                case R.id.radioButton_normal:
                    binding.textViewExplain.setText(R.string.explain_normal);
                    break;
                case R.id.radioButton_light:
                    binding.textViewExplain.setText(R.string.explain_light);
                    break;
            }
        });

        binding.buttonPreferApply.setOnClickListener(v -> {
            int checkId = binding.radioGroupTask.getCheckedRadioButtonId();
            switch (checkId) {
                case R.id.radioButton_heavy:
                    userProfile.putTaskType("Heavy");
                    Toasty.success(getContext(), "Task changed", Toast.LENGTH_SHORT, true).show();
                    break;
                case R.id.radioButton_normal:
                    userProfile.putTaskType("Normal");
                    Toasty.success(getContext(), "Task changed", Toast.LENGTH_SHORT, true).show();
                    break;
                case R.id.radioButton_light:
                    userProfile.putTaskType("Light");
                    Toasty.success(getContext(), "Task changed", Toast.LENGTH_SHORT, true).show();
                    break;
            }
        });


        // record
        binding.textiewWeekMod.setText(userProfile.getWeekModerateCount() + " min");
        binding.textiewWeekVig.setText(userProfile.getWeekVigorousCount() + " min");
        binding.textiewTotalMod.setText(userProfile.getTotalModerateCount() + " min");
        binding.textiewTotalVig.setText(userProfile.getTotalVigorousCount() + " min");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Profile");
    }

    // get InputMethodManager to close soft keyboard
    private void hideKeyboard(View v) {

        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
}
