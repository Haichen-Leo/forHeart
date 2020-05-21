package com.example.forheart.ui.fitness;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.allyants.notifyme.NotifyMe;
import com.example.forheart.MainActivity;
import com.example.forheart.R;
import com.example.forheart.databinding.PlanDetailFragmentBinding;
import com.example.forheart.model.ExerciseBean;
import com.example.forheart.model.Plan;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.ToastUtil;
import com.navigation.androidx.FragmentHelper;
import com.navigation.androidx.NavigationFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class PlanDetailFragment extends BaseFragment {

    private PlanDetailFragmentBinding binding;
    private PlanDetailViewModel viewModel;
    private static final int REQUEST_CODE_SUGGEST = 1;
    private static final int REQUEST_CODE_SUGGEST_NEW = 2;
    private static final String KEY_TYPE = "activity_type";
    private int recommendDuration = 30;
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
    private Preference_UserProfile userProfile;

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
        viewModel = new ViewModelProvider(this).get(PlanDetailViewModel.class);

        type = getArguments().getString(String.valueOf(R.string.activity_type));
//        // configure theme
//        if (type.equals("vigorous")) {
//            int backgroud = R.drawable.vigorous_bt_bg;
//            binding.buttonSuggest.setBackgroundResource(R.drawable.vigorous_bt_bg);
//            binding.buttonDate.setBackgroundResource(R.drawable.vigorous_bt_bg);
//            binding.buttonTime.setBackgroundResource(R.drawable.vigorous_bt_bg);
//            binding.buttonDuration.setBackgroundResource(R.drawable.vigorous_bt_bg);
//            binding.buttonSubmit.setBackgroundResource(R.drawable.vigorous_bt_bg);
//        }

        // configure pic
        if (type.equals("moderate")) {
            binding.imageViewTypePic.setImageDrawable(getResources().getDrawable(R.drawable.ic_do_pilates));
        }

        // activity suggest button
//        binding.buttonSuggest.setOnClickListener(v -> {
//            SuggestDialog dialog = SuggestDialog.newInstance(type);
//            showDialog(dialog, REQUEST_CODE_SUGGEST);
//        });
        binding.buttonSuggest.setOnClickListener(v -> {
            NavigationFragment navigationFragment = new NavigationFragment();
            ExerciseCategoryFragment fragment = new ExerciseCategoryFragment();
            navigationFragment.setRootFragment(fragment);
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putString("exercise_type", type);
            presentFragment(navigationFragment, REQUEST_CODE_SUGGEST_NEW);

        });

        // set current date
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        hour = 19;
        minute = 0;

        theDay = year + "-" + month + "-" + day;
        theTime = hour + ":" + minute;
        binding.editTextDate.setText(parseDateFormat(theDay));
        binding.editTextWhen.setText(parseTimeFormat(theTime));


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

                    // if today, unable set a time before current
                    if (day == calendar.get(Calendar.DATE) && month == calendar.get(Calendar.MONTH) + 1) {

                        Calendar planCalendar = Calendar.getInstance();
                        planCalendar.set(Calendar.YEAR,year);
                        planCalendar.set(Calendar.MONTH,month-1);
                        planCalendar.set(Calendar.DATE,day);
                        planCalendar.set(Calendar.HOUR,hour);
                        planCalendar.set(Calendar.MINUTE,minute);
                        long startTime = planCalendar.getTimeInMillis();

                        if (startTime < System.currentTimeMillis()) {
                            Toasty.info(getContext(), "Oops, you select a time before the current.\nPlease choose a valid time again", Toast.LENGTH_SHORT, true).show();
                            hour = 19;
                            minute = 0;
                        }
                    }

                    theTime = hour + ":" + minute;
                    binding.editTextWhen.setText(parseTimeFormat(theTime));

                }
            },calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),false);
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
                        ToastUtil.centerToast(getContext(),"That is too long! We recommend taking exercise no longer than 300 min");
                    }
                }
            }
        });

        // question button
        binding.imageViewQuestion.setOnClickListener(v -> {
            Toasty.info(getContext(), "Get suggestions by clicking the RECOMMEND button based on our statistics", Toast.LENGTH_SHORT,true).show();
        });

        // duration suggest button
        // -- need to be updated from database
        binding.buttonDuration.setOnClickListener(v -> {
//            if (type == "moderate") {
//                recommendDuration = 60;
//            } else {
//                recommendDuration = 30;
//            }
            binding.editTextDuration.setText(String.valueOf(recommendDuration));
            binding.editTextDuration.clearFocus();
        });

        // submit
        binding.buttonSubmit.setOnClickListener(v -> {
            activity = binding.editTextWhat.getText().toString();
            String durationStr = binding.editTextDuration.getText().toString();
            if (TextUtils.isEmpty(activity)) {
                binding.editTextWhat.requestFocus();
//                ToastUtil.centerToast(getContext(), "Please set an activity!");
                Toasty.info(getContext(),"Please set an activity!", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(durationStr)) {
                binding.editTextDuration.requestFocus();
//                ToastUtil.centerToast(getContext(),"Please set the duration!");
                Toasty.info(getContext(),"Please set the duration!", Toast.LENGTH_SHORT).show();
            } else {
                // insert plan
                duration = Integer.parseInt(durationStr);
                description = binding.editTextDes.getText().toString();
                Plan plan = new Plan(activity,type,year,month,day,hour,minute,duration,description,false);
                viewModel.insertPlans(plan);

                // set alarm
//                viewModel.getLastPlan().observe(getViewLifecycleOwner(),lastPlan ->{

//                    calendar.set(Calendar.YEAR,year);
//                    calendar.set(Calendar.MONTH,month-1);
//                    calendar.set(Calendar.DATE,day);
//                    calendar.set(Calendar.HOUR,hour);
//                    calendar.set(Calendar.MINUTE,minute);
//                    long startTime = calendar.getTimeInMillis();
//                    int lastPlanId = viewModel.getLastPlan().getValue().getId();
//                    Toasty.info(getContext(), String.valueOf(lastPlanId), Toast.LENGTH_SHORT,true).show();

//                    alarmManagerUtils = AlarmManagerUtils.getINSTANCE(getContext());
//                    alarmManagerUtils.setAlarm(lastPlanId, startTime);
//                    alarmManagerUtils.setAlarm(0, startTime);
//                });

//                userProfile = Preference_UserProfile.getInstance(getContext());

                // last index
                userProfile = Preference_UserProfile.getInstance(getContext());
                int index = userProfile.getPlanIndex() + 1;
                userProfile.putPlanIndex(index);

                //set alarm
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month-1);
                calendar.set(Calendar.DATE,day);
                calendar.set(Calendar.HOUR,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);

                Intent intent = new Intent(requireContext(), MainActivity.class);
                intent.putExtra("planId", index);
                NotifyMe notifyMe = new NotifyMe.Builder(getContext())
                        .title("forHeart - Time to Start")
                        .content(activity + "\n" + duration + " mins")
                        .color(7,103,11,255)
                        .led_color(255,255,255,255)
                        .time(calendar)
                        .key(String.valueOf(index))
//                        .addAction(new Intent(), "Dismiss")
                        .addAction(intent,"Get it")
                        .large_icon(R.mipmap.ic_launcher_round)
                        .build();

                // nav - root
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
        if (requestCode == REQUEST_CODE_SUGGEST_NEW) {
            if (resultCode == Activity.RESULT_OK && data != null) {
//                Toasty.info(getContext(),"ok", Toast.LENGTH_SHORT, true).show();
                ExerciseBean bean = data.getParcelable("suggest_food");
                binding.editTextWhat.setText(bean.getName());
                recommendDuration = bean.getDuration();
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
        SimpleDateFormat toFormat = new SimpleDateFormat("hh:mm a");
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
