package com.example.forheart.ui.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.forheart.R;
import com.example.forheart.databinding.TipsBinding;
import com.example.forheart.ui.BaseFragment;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.FragmentHelper;

/**
 * Fragment class to show tips
 */
public class TipsFragment extends BaseFragment {

    private TipsBinding tipsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tips, container, false);
        tipsBinding = tipsBinding.bind(view);
        return tipsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        tipsBinding.layoutFaq.setOnClickListener(v -> {
            FaqFragment fragment = new FaqFragment();
            getNavigationFragment().redirectToFragment(fragment,false);
        });

        // go to different tip content
        tipsBinding.tipsOne.setOnClickListener(v -> {
            TipsContent fragment = new TipsContent();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putInt("tips", 1);
            getNavigationFragment().pushFragment(fragment);
        });
        tipsBinding.tipsTwo.setOnClickListener(v -> {
            TipsContent fragment = new TipsContent();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putInt("tips", 2);
            getNavigationFragment().pushFragment(fragment);
        });
        tipsBinding.tipsThree.setOnClickListener(v -> {
            TipsContent fragment = new TipsContent();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putInt("tips", 3);
            getNavigationFragment().pushFragment(fragment);
        });
        tipsBinding.tipsFour.setOnClickListener(v -> {
            TipsContent fragment = new TipsContent();
            Bundle args = FragmentHelper.getArguments(fragment);
            args.putInt("tips", 4);
            getNavigationFragment().pushFragment(fragment);
        });
    }


    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }
}
