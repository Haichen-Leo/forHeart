package com.example.forheart.ui.drawer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.ContactFragmentBinding;
import com.example.forheart.network.HttpPostAsyncTask;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

/**
 * Fragment Class for Contact Page
 */
public class ContactFragment extends BaseFragment {

    private ContactFragmentBinding binding;

    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        binding = ContactFragmentBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // send feedback to server
        binding.buttonSend.setOnClickListener(v -> {

            // if feedback empty
            String feedback = binding.formText.getText().toString().trim();
            if (TextUtils.isEmpty(feedback)) {
                Toasty.info(getContext(),"Feedback is empty!", Toast.LENGTH_SHORT).show();
            } else {
                // connect to server
                String url = "http://54.206.117.113/feedback";
                Map<String, String> postData = new HashMap<>();
                postData.put("feedback", binding.formText.getText().toString());
                HttpPostAsyncTask task = new HttpPostAsyncTask(postData);
                task.execute(url);
                binding.formText.setText("");
                ContactDialog dialog = new ContactDialog();
                showDialog(dialog,0);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get network permission in sdk > android.M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                // check permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)) {
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET}, 1);
                }
            }
        }

    }
}
