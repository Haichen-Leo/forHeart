package com.example.forheart.ui.food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forheart.R;
import com.example.forheart.databinding.RecommandFragmentBinding;
import com.example.forheart.model.Food;
import com.navigation.androidx.AwesomeFragment;
import com.navigation.androidx.FragmentHelper;

import java.util.List;
import java.util.ResourceBundle;

public class RecommandFragment extends AwesomeFragment {

    private RecommandViewModel mViewModel;
    private View root;
    private LiveData<List<Food>> recommend;
    static final String KEY_VALUE = "groupId";

    // get new recommend dialog instance with group id
    public static RecommandFragment newInstance(int num) {

        RecommandFragment fragment = new RecommandFragment();
        Bundle bundle = FragmentHelper.getArguments(fragment);
        bundle.putInt(KEY_VALUE, num);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.recommand_fragment, container, false);
        int i = getArguments().getInt(KEY_VALUE);
        mViewModel = new ViewModelProvider(this).get(RecommandViewModel.class);
        RecommendAdapter adapter = new RecommendAdapter(getContext(),this);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView_recommend);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recommend = mViewModel.getRecommend(i);
        recommend.observe(getViewLifecycleOwner(), adapter::setAllFoods);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
