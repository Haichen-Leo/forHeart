package com.example.forheart.ui.fitness;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.PlanDetailFragmentBinding;
import com.example.forheart.db.PlanRepository;
import com.example.forheart.model.Plan;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDetailFragment extends BaseFragment {

    private PlanDetailFragmentBinding binding;
    private PlanRepository planRepository;
    private static final int REQUEST_CODE_SUGGEST = 1;
    private static final String KEY_TYPE = "activity_type";
    private Calendar calendar;
    private String theDay;
    private String theTime;
    private String activity;
    private String type;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int duration;
    private String description;

    public static PlanDetailFragment newInstance() {
        return new PlanDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.plan_detail_fragment, container, false);
        binding = PlanDetailFragmentBinding.bind(root);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // activity suggest button
        type = getArguments().getString(String.valueOf(R.string.activity_type));
        binding.buttonSuggest.setOnClickListener(v -> {
            SuggestDialog dialog = SuggestDialog.newInstance(type);
            showDialog(dialog, REQUEST_CODE_SUGGEST);
        });

        // set current date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

        theDay = year + "-" + month + "-" + day;
        theTime = "19:00";
        binding.editTextDate.setText(parseDateFormat(theDay));
        binding.editTextWhen.setText(theTime);


        // date pick button
        binding.buttonDate.setOnClickListener(v -> {
            DatePickerDialog dialog =  new DatePickerDialog(getContext(), (view, year1, month1, dayOfMonth) -> {
                year = year1;
                month = month1 + 1;
                day = dayOfMonth;
                String newDay = year1 + "-" + (month1 + 1) + "-" +dayOfMonth;
                binding.editTextDate.setText(parseDateFormat(newDay));
            },year,month-1,day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.show();
        });

        // time pick button
        binding.buttonTime.setOnClickListener(v -> {
            TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                    hour = hourOfDay;
                    minute = minute1;
                    theTime = hour + ":" + minute;
                    binding.editTextWhen.setText(parseTimeFormat(theTime));
                }
            },hour,minute,true);
            dialog.show();
        });

        // set a duration, no longer than 300 minutes
        binding.editTextDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.isEmpty() == false) {
                    if(text.length()>=1 && text.startsWith("0")) {
                        s.replace(0,1,"");
                    }
                    int duration = Integer.valueOf(text);
                    if (duration > 300) {
                        binding.editTextDuration.setText("300");
                        Toast toast = Toast.makeText(getContext(),"That is too long!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // duration suggest button
        // -- need to be updated from database
        binding.buttonDuration.setOnClickListener(v -> {
            int recommendDuration = 0;
            if (type == "moderate") {
                recommendDuration = 60;
            } else {
                recommendDuration = 30;
            }
            binding.editTextDuration.setText(String.valueOf(recommendDuration));
            binding.editTextDuration.clearFocus();
        });

        // submit
        binding.buttonSubmit.setOnClickListener(v -> {
            activity = binding.editTextWhat.getText().toString();
            String durationStr = binding.editTextDuration.getText().toString();
            if (TextUtils.isEmpty(activity)) {
                binding.editTextWhat.requestFocus();
                ToastUtil.centerToast(getContext(), "Please set an activity!");
            } else if (TextUtils.isEmpty(durationStr)) {
                binding.editTextDuration.requestFocus();
                ToastUtil.centerToast(getContext(),"Please set the duration!");
            } else {
                duration = Integer.parseInt(durationStr);
                description = binding.editTextDes.getText().toString();
                Plan plan = new Plan(activity,type,year,month,day,hour,minute,duration,description,false);
                new PlanRepository(getContext()).insertPlans(plan);
                getNavigationFragment().popToRootFragment();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("New Plan");
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, @Nullable Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUGGEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                binding.editTextWhat.setText(data.getString(KEY_TYPE));
                binding.editTextWhat.clearFocus();
                hideKeyboard(getView());
            }
        }
    }


    // parse date format
    private String parseDateFormat(String input) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat toFormat = new SimpleDateFormat("d MMM, yyyy");
        try {
            String reformattedStr = toFormat.format(fromFormat.parse(input));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Null";
    }

    // parse time format
    private String parseTimeFormat(String input) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("HH:m");
        SimpleDateFormat toFormat = new SimpleDateFormat("HH:mm");
        try {
            String reformattedStr = toFormat.format(fromFormat.parse(input));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Null";
    }


    // get InputMethodManager to close soft keyboard
    private void hideKeyboard(View v) {

        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
}
