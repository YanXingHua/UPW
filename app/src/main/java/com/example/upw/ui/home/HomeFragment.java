package com.example.upw.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private View root;
    private ViewPager viewPager;
    private HomeAdapter homeAdapter;
    private List<String> actPic = null;

    private LinearLayout linearLayout;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getActivity().getApplication(), this)).get(HomeViewModel.class);
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = root.findViewById(R.id.ViewPager);
        homeAdapter = new HomeAdapter();
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        initView();
        return root;
    }

    private void initView() {
        String strUrl = "http://10.10.150.240:88/api/slideshow";
        try {
            final MainAsynctask homeFragmentTask = new MainAsynctask(getActivity(), strUrl, "Home");
            homeFragmentTask.execute();
            homeFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        actPic = new ArrayList<>();
                        actPic.add(new JSONObject(data).getString("img1"));
                        actPic.add(new JSONObject(data).getString("img2"));
                        homeAdapter.setData(actPic);
                        viewPager.setAdapter(homeAdapter);

                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                //viewPager.addOnPageChangeListener(this);
                            }

                            @Override
                            public void onPageSelected(int position) {
                                //viewpager停下来的时候
                                int realPosition;
                                if (homeAdapter.getDataRealSize() != 0) {
                                    realPosition = position % homeAdapter.getDataRealSize();
                                } else {
                                    realPosition = 0;
                                }
                                setSelectPoint(realPosition);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });//1

                        linearLayout = root.findViewById(R.id.LinearLayout);
                        insertPoint();
                        viewPager.setCurrentItem(homeAdapter.getDataRealSize() * 100, false);

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

    private void insertPoint() {
        for (int i = 0; i < actPic.size(); i++) {
            View point = new View(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            layoutParams.leftMargin = 20;
            // point.setBackgroundResource(R.drawable.shape_point_nomol);

            point.setLayoutParams(layoutParams);
            linearLayout.addView(point);
        }
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View point = linearLayout.getChildAt(i);
            if (i != realPosition) {
                //白色
                point.setBackgroundResource(R.drawable.shape_point_normal);
            } else {
                //选中色
                point.setBackgroundResource(R.drawable.shape_point_selected);
            }
        }
    }
}