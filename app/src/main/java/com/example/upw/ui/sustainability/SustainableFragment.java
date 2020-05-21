package com.example.upw.ui.sustainability;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONObject;


public class SustainableFragment extends Fragment {
    private ImageView imageView;
    private TextView textSusableTitle, textSusableDesc;

    public SustainableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sustainable, container, false);
        imageView = view.findViewById(R.id.imageView6);
        textSusableTitle = view.findViewById(R.id.textSusableTitle);
        textSusableDesc = view.findViewById(R.id.textSusableDesc);
        RequestData();
        return view;

    }

    private void RequestData() {
        String strUrl = "http://10.10.150.240:88/api/sustainability/sustainable";
        try {
            final MainAsynctask susFragmentTask = new MainAsynctask(getActivity(), strUrl, "Sustainable");
            susFragmentTask.execute();
            susFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        textSusableTitle.setText(new JSONObject(data).getString("title"));
                        textSusableDesc.setText(new JSONObject(data).getString("desc"));
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img"))
                                .into(imageView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onDataReceivedFailed() {
                    Toast.makeText(getContext(),"data received failed!",Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
