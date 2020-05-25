package com.example.forheart.ui.drawer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.navigation.androidx.AwesomeFragment;

public class ContactDialog extends AwesomeFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Thank you!")
                .setMessage("We have received your feedback")
                .setNegativeButton("OK", (dialogInterface, i) -> {

                })
                .create();

        return dialog;

    }
}
