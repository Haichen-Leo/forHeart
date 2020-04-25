package com.example.forheart.ui.fitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.FragmentHelper;

public class SuggestDialog extends AwesomeFragment {

    private CharSequence[] activites;

    static final String KEY_TYPE = "activity_type";


    public static SuggestDialog newInstance(String type) {
        SuggestDialog dialog = new SuggestDialog();
        Bundle bundle = FragmentHelper.getArguments(dialog);
        bundle.putString(KEY_TYPE, type);
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String type = getArguments().getString(KEY_TYPE);
        Bundle result = new Bundle();
        if (type.equals("moderate")){
            activites = new CharSequence[]{"Walking", "Yoga", "Dancing", "Hiking"};
        } else {
            activites = new CharSequence[]{"Bicycling", "Swimming", "Running", "Basketball", "Football"};
        }
        AlertDialog dialog =new AlertDialog.Builder(requireContext())
                .setTitle("Suggested activities")
                .setSingleChoiceItems(activites, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CharSequence sequence = activites[which];
                        result.putString(KEY_TYPE, (String) sequence);
                    }
                })
                .setPositiveButton("Select",(dialogInterface, i) -> {
                    setResult(Activity.RESULT_OK, result);
                })
                .setNegativeButton("Cancel",(dialogInterface, i) -> {
                    setResult(Activity.RESULT_CANCELED, result);
                })
                .create();
        return dialog;
    }
}
