package com.example.forheart.ui.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.PrimaryKey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forheart.R;
import com.example.forheart.databinding.LoginPageBinding;
import com.example.forheart.model.Preference_UserProfile;
import com.example.forheart.ui.BaseFragment;
import com.example.forheart.util.ToastUtil;
import com.navigation.androidx.AwesomeToolbar;
import com.navigation.androidx.Style;

import es.dmoral.toasty.Toasty;

/**
 *  Fragment to handle first setup
 */
public class LoginFragment extends BaseFragment {

    private LoginPageBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LoginPageBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        binding.loginBt.setOnClickListener(v -> {
            String str = binding.unEdit.getText().toString();
            if (str.equals("")){
                Toasty.info(getContext(), "Hey, can you tell me your nick name?", Toast.LENGTH_SHORT, true).show();
            } else {
                Bundle result = new Bundle();
                result.putString("nickname", str);
                setResult(Activity.RESULT_OK, result);
                dismissFragment();
            }
        });
    }

    @Override
    protected void onCustomStyle(@NonNull Style style) {
        super.onCustomStyle(style);
        style.setSwipeBackEnabled(false);
    }

    @Nullable
    @Override
    protected AwesomeToolbar onCreateAwesomeToolbar(View parent) {
        return null;
    }

    @Override
    protected boolean onBackPressed() {
        return true;
    }


}
