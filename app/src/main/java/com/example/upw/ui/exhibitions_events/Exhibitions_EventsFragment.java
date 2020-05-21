package com.example.upw.ui.exhibitions_events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exhibitions_EventsFragment extends Fragment {

    private Exhibitions_EventsViewModel exhibitionsEventsViewModel;
    private ExhAdapter exhAdapter;
    private List<String> ExhPic = null;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView textpastEvent, textTitle1, textDate1, textMore1, textTitle2, textDate2, textMore2, textTitle3, textDate3, textMore3,textReserve;
    private ImageView imageView1, imageView2, imageView3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exhibitionsEventsViewModel =
                ViewModelProviders.of(this).get(Exhibitions_EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exhibitions_events, container, false);
//        final TextView textView = root.findViewById(R.id.text_exhibitions_events);
////        exhibitionsEventsViewModel.getText().observe(this, new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
////        });
        viewPager = root.findViewById(R.id.viewPager);
        linearLayout = root.findViewById(R.id.linearLayout);

        textpastEvent = root.findViewById(R.id.textpastEvent);
        textTitle1 = root.findViewById(R.id.textTitle1);
        textDate1 = root.findViewById(R.id.textDate1);
        textMore1 = root.findViewById(R.id.textMore1);
        textTitle2 = root.findViewById(R.id.textTitle2);
        textDate2 = root.findViewById(R.id.textDate2);
        textMore2 = root.findViewById(R.id.textMore2);
        textTitle3 = root.findViewById(R.id.textTitle3);
        textDate3 = root.findViewById(R.id.textDate3);
        textMore3 = root.findViewById(R.id.textMore3);
        textReserve=root.findViewById(R.id.textReserve);
        imageView1 = root.findViewById(R.id.imageView1);
        imageView2 = root.findViewById(R.id.imageView2);
        imageView3 = root.findViewById(R.id.imageView3);
        exhAdapter = new ExhAdapter();
        initView();

        return root;
    }

    private void initView() {
        String strUrl = "http://10.10.150.240:88/api/exhibitions/index";
        try {

            final MainAsynctask ExhFragmentTask = new MainAsynctask(getActivity(), strUrl, "ExhibitionsEvents");
            ExhFragmentTask.execute();
            ExhFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        textpastEvent.setText(new JSONObject(data).getString("past_event"));
                        final List<String> textTitleList = new ArrayList<>();
                        final List<String> textDateList = new ArrayList<>();
                        final List<String>imageList=new ArrayList<>();
                        final List<String>blogContentList=new ArrayList<>();
                        JSONArray blogList = new JSONObject(data).getJSONArray("blogs");
                        for (int i = 0; i <blogList.length() ; i++) {
                            JSONObject obj = blogList.getJSONObject(i);
                            textTitleList.add((String) obj.get("title"));
                            textDateList.add((String)obj.get("date"));
                            imageList.add((String)obj.get("image1"));
                            blogContentList.add((String)obj.get("content"));
                        }
                        textTitle1.setText(textTitleList.get(0));
                        textDate1.setText(textDateList.get(0));
                        textMore1.setText(new JSONObject(data).getString("learn_more"));
                        textTitle2.setText(textTitleList.get(1));
                        textDate2.setText(textDateList.get(1));

                        textMore2.setText(new JSONObject(data).getString("learn_more"));
                        textTitle3.setText(textTitleList.get(2));
                        textDate3.setText(textDateList.get(2));
                        textMore3.setText(new JSONObject(data).getString("learn_more"));
                        Glide.with(getActivity()).load(imageList.get(0)).into(imageView1);
                        Glide.with(getActivity()).load(imageList.get(1)).into(imageView2);
                        Glide.with(getActivity()).load(imageList.get(2)).into(imageView3);
                        ExhPic = new ArrayList<>();
                        List<String> subTitleList = new ArrayList<>();
                        List<String> contentList = new ArrayList<>();
                        String strContent = "";
                        List<String> reserveList = new ArrayList<>();
                        JSONArray ExhPicList = new JSONObject(data).getJSONArray("up_coming_events");
                        for (int i = 0; i < ExhPicList.length(); i++) {
                            JSONObject oj = ExhPicList.getJSONObject(i);
                            ExhPic.add(oj.getString("image_phone"));
                            subTitleList.add(oj.getString("subtitle"));
                            JSONArray jsonArray = oj.getJSONArray("content");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                strContent += jsonArray.get(j) + "\n";
                            }
                            contentList.add(strContent);
                            strContent = "";
                            if (i == 0) {
                                reserveList.add(new JSONObject(data).getString("reserve"));
                            } else {
                                reserveList.add(new JSONObject(data).getString("learn_more"));
                            }
                        }
                        textReserve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(textReserve.getText().equals("Learn more")){
                                    setBundle(v,imageList,textDateList,textTitleList,blogContentList,2);
                                }else{

                                }
                            }
                        });
                        textMore1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setBundle(v,imageList,textDateList,textTitleList,blogContentList,0);
                            }
                        });
                        textMore2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setBundle(v,imageList,textDateList,textTitleList,blogContentList,1);
                            }
                        });
                        textMore3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setBundle(v,imageList,textDateList,textTitleList,blogContentList,2);
                            }
                        });
                        exhAdapter.setExhPic(ExhPic, subTitleList, contentList, reserveList);
                        viewPager.setAdapter(exhAdapter);

                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                //viewPager.addOnPageChangeListener(this);
                            }

                            @Override
                            public void onPageSelected(int position) {
                                //viewpager停下来的时候
                                int realPosition;
                                if (exhAdapter.getDataRealSize() != 0) {
                                    realPosition = position % exhAdapter.getDataRealSize();
                                } else {
                                    realPosition = 0;
                                }
                                setSelectPoint(realPosition);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });//1


                        insertPoint();
                        viewPager.setCurrentItem(exhAdapter.getDataRealSize() * 100, false);

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

    private void setBundle(View v,List<String>imageList,List<String>textDateList,List<String>textTitleList,List<String>blogContentList,Integer count){
        Bundle bundle=new Bundle();
        bundle.putString("strImage",imageList.get(count));
        bundle.putString("strDate",textDateList.get(count));
        bundle.putString("strTitle",textTitleList.get(count));
        bundle.putString("strContent",blogContentList.get(count));
        NavController navController= Navigation.findNavController(v);
        navController.navigate(R.id.action_nav_exhibitions_events_to_blog27Fragment,bundle);
    }
    private void insertPoint() {
        for (int i = 0; i < ExhPic.size(); i++) {
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