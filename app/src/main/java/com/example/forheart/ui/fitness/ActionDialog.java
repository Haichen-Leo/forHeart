package com.example.forheart.ui.fitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.FragmentHelper;

/**
 * Class to show dialogs in plan action page
 */
public class ActionDialog extends AwesomeFragment {

    static final String ACTION_TYPE = "action_type";

    // constructor
    static ActionDialog newInstance(String type) {
        ActionDialog dialog = new ActionDialog();
        Bundle bundle = FragmentHelper.getArguments(dialog);
        bundle.putString(ACTION_TYPE, type);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // show different dialog based on the argument
        String type = getArguments().getString(ACTION_TYPE);
        Bundle result = new Bundle();
        // start dialog
        if (type.equals(ActionFragment.ACTION_START)) {
            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Start")
                    .setMessage("Start now? \nYou will be notified when it is done")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_OK, result);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_CANCELED, result);
                    })
                    .create();
            return dialog;
        }
        // done dialog
        if (type.equals(ActionFragment.ACTION_DONE)) {
            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Done")
                    .setMessage("Mark it as Done? \nResult will be added to your progress")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_OK, result);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_CANCELED, result);
                    })
                    .create();
            return dialog;
        }
        // delete dialog
        if (type.equals(ActionFragment.ACTION_DELETE)) {
            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Remove")
                    .setMessage("Remove this plan?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_OK, result);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        setResult(Activity.RESULT_CANCELED, result);
                    })
                    .create();
            return dialog;
        }
        return super.onCreateDialog(savedInstanceState);
    }
}
