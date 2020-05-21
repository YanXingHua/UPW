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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraceFragment extends Fragment {
    private TextView textTraeTitle, textAds1_1, textAds1_2, textAds2_1, textAds2_2, textAds3_1, textAds3_2, textDilAds2_1,
            textDilAds2_2, textDilAds3_1, textDilAds3_2, textDilDesc2, textYakRsp, textYakTitle, textYakSub1, textYakDesc1, textYakSub2, textYakDesc2;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;//有两张相近

    public TraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trace, container, false);
        textTraeTitle = view.findViewById(R.id.textTraeTitle);
        textAds1_1 = view.findViewById(R.id.textAds1_1);
        textAds1_2 = view.findViewById(R.id.textAds1_2);
        textAds2_1 = view.findViewById(R.id.textAds2_1);
        textAds2_2 = view.findViewById(R.id.textAds2_2);
        textAds3_1 = view.findViewById(R.id.textAds3_1);
        textAds3_2 = view.findViewById(R.id.textAds3_2);
        textDilAds2_1 = view.findViewById(R.id.textDilAds2_1);
        textDilAds2_2 = view.findViewById(R.id.textDilAds2_2);
        textDilAds3_1 = view.findViewById(R.id.textDilAds3_1);
        textDilAds3_2 = view.findViewById(R.id.textDilAds3_2);
        textDilDesc2 = view.findViewById(R.id.textDilDesc2);
        textYakRsp = view.findViewById(R.id.textYakRsp);
        textYakTitle = view.findViewById(R.id.textYakTitle);
        textYakSub1 = view.findViewById(R.id.textYakSub1);
        textYakDesc1 = view.findViewById(R.id.textYakDesc1);
        //textYakSub2 = view.findViewById(R.id.textYakSub2);
        textYakDesc2 = view.findViewById(R.id.textYakDesc2);
        imageView1 = view.findViewById(R.id.imageView1);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        imageView4 = view.findViewById(R.id.imageView4);
        imageView5 = view.findViewById(R.id.imageView5);
        LoadData();
        return view;
    }

    private void LoadData() {
        String strUrl = "http://10.10.150.240:88/api/sustainability/traceability";
        try {
            final MainAsynctask TraceFragmentTask = new MainAsynctask(getActivity(), strUrl, "Traceability");
            TraceFragmentTask.execute();
            TraceFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {

                        List<String> souceItem=new ArrayList<>();
                        List<String> souceSub=new ArrayList<>();
                        JSONArray sourcingList=new JSONObject(data).getJSONArray("sourcing");
                        for (int i = 0; i < sourcingList.length(); i++) {
                            JSONObject oj = sourcingList.getJSONObject(i);
                            souceItem.add((String) oj.get("item"));
                            souceSub.add((String)oj.get("sub"));

                        }
                        textTraeTitle.setText(new JSONObject(data).getString("responsible_sub"));
                        textAds1_1.setText(souceItem.get(2));
                        textAds1_2.setText(souceSub.get(2));
                        textAds2_1.setText(souceItem.get(1));
                        textAds2_2.setText(souceSub.get((1)));
                        textAds3_1.setText(souceItem.get(0));
                        textAds3_2.setText(souceSub.get(0));
                        textDilAds2_1.setText(souceSub.get(0));
                        textDilAds2_2.setText(souceItem.get(0));
                        textDilAds3_1.setText(souceSub.get(1));
                        textDilAds3_2.setText(souceItem.get(1));
                        textDilDesc2.setText(new JSONObject(new JSONObject(data).getString("modal_wool")).getString("desc"));
                        textYakRsp.setText(new JSONObject(data).getString("responsible"));
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img2"))
                                .into(imageView1);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img1"))
                                .into(imageView2);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img3"))
                                .into(imageView3);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img5"))
                                .into(imageView4);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img4"))
                                .into(imageView5);
                        String a=new JSONObject(data).getString("modal_yak");
                        textYakTitle.setText(new JSONObject(a).getString("title"));
                        textYakSub1.setText(new JSONObject(a).getString("sub"));
                        textYakDesc1.setText(new JSONObject(a).getString("desc"));
                        //textYakSub2.setText(new JSONObject(a).getString("sub2"));
                        textYakDesc2.setText(new JSONObject(a).getString("desc2"));
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
