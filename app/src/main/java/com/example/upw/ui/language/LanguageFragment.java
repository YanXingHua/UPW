package com.example.upw.ui.language;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.upw.R;

public class LanguageFragment extends Fragment {

    private LanguageViewModel languageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        languageViewModel =
                ViewModelProviders.of(this).get(LanguageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_language, container, false);
        final TextView textView = root.findViewById(R.id.text_language);
        languageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}