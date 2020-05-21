package com.example.upw.ui.sustainability;


import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NaturalFragment extends Fragment {
    private TextView textViewBigTitle, textViewTitle1, textViewdesc1, textViewTitle2, textViewdesc2, textViewdesc3, textViewTitle4, textViewdesc4, textViewTitle5, textViewdesc5;
    private ImageView image1, image2, image3, image4, image5_1, image5_2;

    public NaturalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_natural, container, false);
        textViewBigTitle = view.findViewById(R.id.textViewBigTitle);

        textViewTitle1 = view.findViewById(R.id.textViewTitle1);
        textViewdesc1 = view.findViewById(R.id.textViewdesc1);
        textViewTitle2 = view.findViewById(R.id.textViewTitle2);
        textViewdesc2 = view.findViewById(R.id.textViewdesc2);
        textViewdesc3 = view.findViewById(R.id.textViewdesc3);
        textViewTitle4 = view.findViewById(R.id.textViewTitle4);
        textViewdesc4 = view.findViewById(R.id.textViewdesc4);
        textViewTitle5 = view.findViewById(R.id.textViewTitle5);
        textViewdesc5 = view.findViewById(R.id.textViewdesc5);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        image5_1 = view.findViewById(R.id.image5_1);
        image5_2 = view.findViewById(R.id.image5_2);
        LoadData();
        return view;
    }

    private void LoadData() {
        String strUrl = "http://10.10.150.240:88/api/sustainability/natural";
        try {
            final MainAsynctask natFragmentTask = new MainAsynctask(getActivity(), strUrl, "Nature");
            natFragmentTask.execute();
            natFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {

                        textViewBigTitle.setText(new JSONObject(data).getString("fibers"));
                        textViewTitle1.setText(new JSONObject(data).getString("merino_wool"));
                        textViewdesc1.setText(new JSONObject(data).getString("merino_wool_desc"));
                        textViewTitle2.setText(new JSONObject(data).getString("cashmere"));
                        textViewdesc2.setText(new JSONObject(data).getString("cashmere_desc"));
                        textViewdesc3.setText(new JSONObject(data).getString("sfa"));
                        textViewTitle4.setText(new JSONObject(data).getString("yak"));
                        textViewdesc4.setText(new JSONObject(data).getString("yak_desc"));
                        textViewTitle5.setText(new JSONObject(data).getString("cotton"));
                        textViewdesc5.setText(new JSONObject(data).getString("cotton_desc"));

                        Glide.with(getActivity()).load(new JSONObject(data).getString("img2"))
                                .into(image1);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img1"))
                                .into(image2);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img5"))
                                .into(image3);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img3"))
                                .into(image4);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img4"))
                                .into(image5_1);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img6"))
                                .into(image5_2);
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
