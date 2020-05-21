package com.example.upw.ui.u_lab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONObject;

public class U_LabFragment extends Fragment {

    private U_LabViewModel ULabViewModel;
    private VideoView videoView;
    private TextView txtUlabTitle, txtUlabVTitle, txtUlabdesc, txtUlabGarmentTitle, txtUlabGarmentDesc, txtUlabLapdipTitle, txtUlabLapdipDesc,
            txtUlabShipTitle, txtUlabShipDesc, txtUlabTrendsTitle, txtUlabTrendsDesc, txtUlabSeason1, txtUlabSeason2;
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ULabViewModel =
                ViewModelProviders.of(this).get(U_LabViewModel.class);
        View root = inflater.inflate(R.layout.fragment_u_lab, container, false);
//        final TextView textView = root.findViewById(R.id.text_u_lab);
//        ULabViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        videoView = root.findViewById(R.id.videoView);
        txtUlabTitle = root.findViewById(R.id.txtUlabTitle);
        txtUlabVTitle = root.findViewById(R.id.txtUlabVTitle);
        txtUlabdesc = root.findViewById(R.id.txtUlabdesc);
        txtUlabGarmentTitle = root.findViewById(R.id.txtUlabGarmentTitle);
        txtUlabGarmentDesc = root.findViewById(R.id.txtUlabGarmentDesc);
        txtUlabLapdipTitle = root.findViewById(R.id.txtUlabLapdipTitle);
        txtUlabLapdipDesc = root.findViewById(R.id.txtUlabLapdipDesc);
        txtUlabShipTitle = root.findViewById(R.id.txtUlabShipTitle);
        txtUlabShipDesc = root.findViewById(R.id.txtUlabShipDesc);
        txtUlabTrendsTitle = root.findViewById(R.id.txtUlabTrendsTitle);
        txtUlabTrendsDesc = root.findViewById(R.id.txtUlabTrendsDesc);
        txtUlabSeason1 = root.findViewById(R.id.txtUlabSeason1);
        txtUlabSeason2 = root.findViewById(R.id.txtUlabSeason2);
        imageView1 = root.findViewById(R.id.imageView1);
        imageView2 = root.findViewById(R.id.imageView2);
        imageView3 = root.findViewById(R.id.imageViewtwo);
        imageView4 = root.findViewById(R.id.imageView4);
        imageView5 = root.findViewById(R.id.imageView5);
        getData();
        return root;
    }

    private void getData() {
        String strUrl = "http://10.10.150.240:88/api/ulab";
        try {
            final MainAsynctask ULabFragmentTask = new MainAsynctask(getActivity(), strUrl, "U_Lab");
            ULabFragmentTask.execute();
            ULabFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        videoView.setVideoPath(new JSONObject(data).getString("video"));
                        MediaController mediaController = new MediaController(getContext());
                        //VideoView与MediaController建立关联
                        videoView.setMediaController(mediaController);
                        videoView.start();
                        videoView.requestFocus();
//                        txtUlabTitle.setText(new JSONObject(data).getString("dyes"));
//                        txtUlabVTitle.setText(new JSONObject(data).getString("what_remark"));
                        txtUlabdesc.setText(new JSONObject(data).getString("what_remark"));
                        txtUlabGarmentTitle.setText(new JSONObject(data).getString("virtual_garment"));
                        txtUlabGarmentDesc.setText(new JSONObject(data).getString("garment_remark"));
                        txtUlabLapdipTitle .setText(new JSONObject(data).getString("virtual_lapdip"));
                        txtUlabLapdipDesc.setText(new JSONObject(data).getString("lapdip_remark"));
                        txtUlabShipTitle .setText(new JSONObject(data).getString("scholarship"));
                        txtUlabShipDesc.setText(new JSONObject(data).getString("scholarship_remark"));
                        txtUlabTrendsTitle.setText(new JSONObject(data).getString("colour_trends"));
                        txtUlabTrendsDesc.setText(new JSONObject(data).getString("colour_trends_remark"));
                        txtUlabSeason1.setText(new JSONObject(data).getString("colour_trends_item1"));
                        txtUlabSeason2.setText(new JSONObject(data).getString("colour_trends_item2"));
//                        textDyes.setText(new JSONObject(data).getString("dyes"));
//                        texrDyesDesc.setText(new JSONObject(data).getString("dyes_desc2"));
//                        textWasWater.setText(new JSONObject(data).getString("wastewater"));
//                        textWaWaterDesc.setText(new JSONObject(data).getString("wastewater_desc"));
//                        textEnv.setText(new JSONObject(data).getString("environmental"));
//                        textEnvDesc.setText(new JSONObject(data).getString("environmental_desc"));
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img1"))
                                .into(imageView1);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img3"))
                                .into(imageView2);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img2"))
                                .into(imageView3);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img4"))
                                .into(imageView4);
                        Glide.with(getActivity()).load(new JSONObject(data).getString("img5"))
                                .into(imageView5);
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