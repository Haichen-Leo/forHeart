package com.example.forheart.ui.drawer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.R;
import com.example.forheart.databinding.ContactFragmentBinding;
import com.example.forheart.ui.BaseFragment;

/**
 * Fragment Class for Contact Page
 */
public class ContactFragment extends BaseFragment {

    private ContactFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        binding = ContactFragmentBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("Contact Us");
        binding.textViewTest.setText("Contact Us");
    }
}