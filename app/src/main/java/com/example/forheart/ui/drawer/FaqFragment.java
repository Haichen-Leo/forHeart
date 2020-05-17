package com.example.forheart.ui.drawer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forheart.R;
import com.example.forheart.databinding.TipsFaqBinding;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaqFragment extends BaseFragment {

    private TipsFaqBinding faqBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tips_faq, container, false);
        faqBinding = faqBinding.bind(view);
        return faqBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        faqBinding.layoutTips.setOnClickListener(v -> {
            TipsFragment fragment = new TipsFragment();
            getNavigationFragment().redirectToFragment(fragment,false);
        });
    }

    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}