package com.example.upw.ui.exhibitions_events;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.upw.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

private TextView textViewDate,textViewTitle,textViewContent;
private ImageView imageView1;
    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String strImage=getArguments().getString("strImage");
        String strDate=getArguments().getString("strDate");
        String strTitle=getArguments().getString("strTitle");
        String strContent=getArguments().getString("strContent");
        textViewDate=getView().findViewById(R.id.textViewDate);
                textViewTitle=getView().findViewById(R.id.textViewTitle);
        textViewContent=getView().findViewById(R.id.textViewContent);
        imageView1=getView().findViewById(R.id.imageView1);
        textViewDate.setText(strDate);
        textViewTitle.setText(strTitle);
                textViewContent.setText(strContent);
        Glide.with(getActivity()).load(strImage).into(imageView1);
    }
}
