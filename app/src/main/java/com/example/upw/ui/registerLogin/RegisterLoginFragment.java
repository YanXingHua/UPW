package com.example.upw.ui.registerLogin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.upw.R;
import com.example.upw.ui.registerLogin.RegisterLoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterLoginFragment extends Fragment {

    private RegisterLoginViewModel registerLoginViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registerLoginViewModel =
                ViewModelProviders.of(this).get(RegisterLoginViewModel.class);
        View root = inflater.inflate(R.layout.activity_register_login, container, false);
//        final TextView textView = root.findViewById(R.id.text_u_lab);
//        registerLoginViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register_login, container, false);
//    }

}
