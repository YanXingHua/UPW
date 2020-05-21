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

/**
 * A simple {@link Fragment} subclass.
 */
public class DyesFragment extends Fragment {
private TextView textDyes,texrDyesDesc,textWasWater,textWaWaterDesc,textEnv,textEnvDesc;
private ImageView imageView1,imageView2,imageView3;

    public DyesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dyes, container, false);
        textDyes=view.findViewById(R.id.textDyes);
        texrDyesDesc=view.findViewById(R.id.textDyesDesc);
        textWasWater=view.findViewById(R.id.textWasWater);
        textWaWaterDesc=view.findViewById(R.id.textWasWaterDesc);
        textEnv=view.findViewById(R.id.textEnv);
        textEnvDesc=view.findViewById(R.id.textEnvDesc);
        imageView1=view.findViewById(R.id.imageView1);
        imageView2=view.findViewById(R.id.imageView2);
        imageView3=view.findViewById(R.id.imageView6);
        LoadData();
        return view;
    }
private void LoadData(){
    String strUrl = "http://10.10.150.240:88/api/sustainability/dyes";
    try {
        final MainAsynctask DyesFragmentTask = new MainAsynctask(getActivity(), strUrl, "Dyes");
        DyesFragmentTask.execute();
        DyesFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
            @Override
            public void onDataReceivedSuccess(String data) {
                try {
                    textDyes.setText(new JSONObject(data).getString("dyes"));
                    texrDyesDesc.setText(new JSONObject(data).getString("dyes_desc2"));
                    textWasWater.setText(new JSONObject(data).getString("wastewater"));
                    textWaWaterDesc.setText(new JSONObject(data).getString("wastewater_desc"));
                    textEnv.setText(new JSONObject(data).getString("environmental"));
                    textEnvDesc.setText(new JSONObject(data).getString("environmental_desc"));
                    Glide.with(getActivity()).load(new JSONObject(data).getString("img1"))
                            .into(imageView1);
                    Glide.with(getActivity()).load(new JSONObject(data).getString("img2"))
                            .into(imageView2);
                    Glide.with(getActivity()).load(new JSONObject(data).getString("img3"))
                            .into(imageView3);
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
