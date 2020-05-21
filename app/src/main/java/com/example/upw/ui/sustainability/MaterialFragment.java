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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialFragment extends Fragment {

    private TextView textMaterTitle1, textMaterTitle2, textMater1_1, textMater1_2, textMater2_1, textMater2_2, textMater3_1, textMater3_2, textMater4_1, textMater4_2, textMater5_1, textMater5_2, textMater6_1, textMater6_2, textMater7_1, textMater7_2, textMaterDesc;
    private ImageView imageViewMater;

    public MaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material, container, false);
        textMaterTitle1 = view.findViewById(R.id.textMaterTitle1);
        textMaterTitle2 = view.findViewById(R.id.textMaterTitle2);
        textMater1_1 = view.findViewById(R.id.textMater1_1);
        textMater1_2 = view.findViewById(R.id.textMater1_2);
        textMater2_1 = view.findViewById(R.id.textMater2_1);
        textMater2_2 = view.findViewById(R.id.textMater2_2);
        textMater3_1 = view.findViewById(R.id.textMater3_1);
        textMater3_2 = view.findViewById(R.id.textMater3_2);
        textMater4_1 = view.findViewById(R.id.textMater4_1);
        textMater4_2 = view.findViewById(R.id.textMater4_2);
        textMater5_1 = view.findViewById(R.id.textMater5_1);
        textMater5_2 = view.findViewById(R.id.textMater5_2);
        textMater6_1 = view.findViewById(R.id.textMater6_1);
        textMater6_2 = view.findViewById(R.id.textMater6_2);
        textMater7_1 = view.findViewById(R.id.textMater7_1);
        textMater7_2 = view.findViewById(R.id.textMater7_2);
        textMaterDesc = view.findViewById(R.id.textMaterDesc);
        imageViewMater=view.findViewById(R.id.imageViewMater);
        getData();
        return view;

    }
private void getData(){
    String strUrl = "http://10.10.150.240:88/api/sustainability/materials";
    try {
        final MainAsynctask materFragmentTask = new MainAsynctask(getActivity(), strUrl, "Material");
        materFragmentTask.execute();
        materFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
            @Override
            public void onDataReceivedSuccess(String data) {
                try {
                    List<String> materItem=new ArrayList<>();
                    List<String> materSub=new ArrayList<>();
                    JSONArray materialsList=new JSONObject(data).getJSONArray("materials");
                    for (int i = 0; i < materialsList.length(); i++) {
                        JSONObject oj = materialsList.getJSONObject(i);
                        materItem.add((String) oj.get("item"));
                        materSub.add((String)oj.get("sub"));

                    }
                    textMaterTitle1.setText(new JSONObject(data).getString("man_made"));
                    textMaterTitle2.setText(new JSONObject(data).getString("recycled"));
                    textMater1_1.setText(materItem.get(0));
                    textMater1_2.setText(materSub.get(0));
                    textMater2_1.setText(materItem.get(1));
                    textMater2_2.setText(materSub.get(1));
                    textMater3_1.setText(materItem.get(2));
                    textMater3_2.setText(materSub.get(2));
                    textMater4_1.setText(materItem.get(3));
                    textMater4_2.setText(materSub.get(3));
                    textMater5_1.setText(materItem.get(4));
                    textMater5_2.setText(materSub.get(4));
                    textMater6_1.setText(materItem.get(5));
                    textMater6_2.setText(materSub.get(5));
                    textMater7_1.setText(materItem.get(6));
                    textMater7_2.setText(materSub.get(6));
                    textMaterDesc.setText(new JSONObject(data).getString("recycled_desc"));
                    Glide.with(getActivity()).load(new JSONObject(data).getString("img"))
                            .into(imageViewMater);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onDataReceivedFailed() {
                Toast.makeText(getContext(), "data received failed!", Toast.LENGTH_LONG).show();
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
