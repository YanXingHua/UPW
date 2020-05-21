package com.example.upw.ui.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;
import com.wrbug.timeline.TimeLineView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OverViewActivity extends AppCompatActivity {
    //group数据
    private ArrayList<String> mGroupList = new ArrayList<>();
    //item数据
    private ArrayList<ArrayList<String>> mItemSet = new ArrayList<>();
    private MyExpandListView expandableListView;
    private ExtendableListViewAdapter adapter;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private TextView tvMyInformation, tvMyOrders, tvMyResrved, tvMyFavourite, tvNoOrders, tvNoResrved,tvAddFavourite,tvFavList2,
            tvMyResrved2,tvexhname,tvDate,tvTime,tvVenue,tvappDetail,tvreference,tvdate,tvtime,tvreserveid,tvreservedate,tvreservetime,
            tvordernumber,tvcsno,tvorderdate,tvlogistics,tvMyOrders2;
    private ConstraintLayout constraintLayout1,constraintLayout2,constraintLayout3,constraintLayout4,constraintLayout5,constraintLayout6;
    private XCRoundImageView imageView;
    private ImageView imageView8;
    private String[] txts = new String[5];
    private TimeLineView mView1;
//reserved那里少了一条线，
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view);
        String strUrl = "http://10.10.150.240:88/api/overview?api_token=" + getIntent().getStringExtra("token");
        imageView=findViewById(R.id.imageView);
        expandableListView = findViewById(R.id.ExpandableListView);
        tvMyInformation = findViewById(R.id.tvMyInformation);
        tvMyOrders = findViewById(R.id.tvMyOrders1);
        tvMyResrved = findViewById(R.id.tvMyResrved1);
        tvMyFavourite = findViewById(R.id.tvMyFavourite1);
        tvNoOrders = findViewById(R.id.tvNoOrders);
        tvNoResrved = findViewById(R.id.tvNoResrved);
        tvAddFavourite=findViewById(R.id.tvAddFavourite);
        tvFavList2=findViewById(R.id.tvMyFavourite2);
        recyclerView=findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        constraintLayout1=findViewById(R.id.constraintLayout1);//135是没有数据，246是有数据
        constraintLayout2=findViewById(R.id.constraintLayout2);
        constraintLayout3=findViewById(R.id.constraintLayout3);
        constraintLayout4=findViewById(R.id.constraintLayout4);
        constraintLayout5=findViewById(R.id.constraintLayout5);
        constraintLayout6=findViewById(R.id.constraintLayout6);
        expandableListView.setGroupIndicator(null);
        tvMyResrved2 = findViewById(R.id.tvMyResrved2);
        tvexhname = findViewById(R.id.tvexhname);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvVenue = findViewById(R.id.tvVenue);
        tvappDetail = findViewById(R.id.tvappDetail);
        tvreference = findViewById(R.id.tvreference);
        tvdate = findViewById(R.id.tvdate);
        tvtime = findViewById(R.id.tvtime);
        tvreserveid = findViewById(R.id.tvreserveid);
        tvreservedate = findViewById(R.id.tvreservedate);
        tvreservetime = findViewById(R.id.tvreservetime);
        tvordernumber= findViewById(R.id.tvordernumber);
        tvcsno= findViewById(R.id.tvcsno);
        tvorderdate= findViewById(R.id.tvorderdate);
        tvlogistics= findViewById(R.id.tvlogistics);
        tvMyOrders2= findViewById(R.id.tvMyOrders2);
        imageView8=findViewById(R.id.imageView8);

        mView1 = findViewById(R.id.timeLineView);
        getData(strUrl);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean groupExpanded = parent.isGroupExpanded(groupPosition);
//              设置指示器 位置，开关
                adapter.setIndicatorState(groupPosition, groupExpanded);
                return false;
            }
        });
        TextView textView6=findViewById(R.id.textView6);
        SharedPreferences sp = getSharedPreferences("loginInfo", 0);
        textView6.setText(sp.getString("sp_email",""));


//        txts[0] = "111111111";
//        txts[1] = "2111122";
//        txts[2] = "3111133";
//        txts[3] = "运送";
//        txts[4] = "到达";

    }

    private void getData(String strUrl) {
        try {
            final MainAsynctask OverViewShopFragmentTask = new MainAsynctask(this, strUrl, "OverViewInformation");
            OverViewShopFragmentTask.execute();
            OverViewShopFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        tvMyInformation.setText(new JSONObject(data).getString("my_information"));
                        tvMyOrders.setText(new JSONObject(data).getString("my_orders"));
                        tvMyResrved.setText(new JSONObject(data).getString("my_resrved"));
                        tvMyFavourite.setText(new JSONObject(data).getString("my_favourite"));
                        tvNoOrders.setText(new JSONObject(data).getString("no_orders"));
                        tvNoResrved.setText(new JSONObject(data).getString("no_resrved"));
                        tvAddFavourite.setText("+"+new JSONObject(data).getString("add_favourite"));

                        tvFavList2.setText(new JSONObject(data).getString("my_favourite"));
                        tvMyResrved2.setText(new JSONObject(data).getString("my_resrved"));
                        String resrved=new JSONObject(data).getString("resrved");
                        tvexhname.setText(new JSONObject(resrved).getString("exhibition_name"));
                        tvDate.setText(new JSONObject(resrved).getString("start_date")+"/"+new JSONObject(resrved).getString("end_date"));
                        tvTime.setText(new JSONObject(resrved).getString("start_time")+"-"+new JSONObject(resrved).getString("end_time"));
                        tvVenue.setText(new JSONObject(resrved).getString("venue"));
                        tvappDetail.setText(new JSONObject(data).getString("appointment_detail"));
                        tvreference.setText(new JSONObject(data).getString("reference"));
                        tvdate.setText(new JSONObject(data).getString("date"));
                        tvtime.setText(new JSONObject(data).getString("time"));
                        tvreserveid.setText(new JSONObject(resrved).getString("reserve_id"));
                        tvreservedate.setText(new JSONObject(resrved).getString("reserve_date"));
                        tvreservetime.setText(new JSONObject(resrved).getString("reserve_time"));

                        tvMyOrders2.setText(new JSONObject(data).getString("my_orders"));
                        tvordernumber.setText(new JSONObject(data).getString("order_number"));
                        tvcsno.setText(new JSONObject(new JSONObject(data).getString("order")).getString("cs_no"));
                        tvorderdate.setText(new JSONObject(data).getString("order_date"));
                        tvlogistics.setText(new JSONObject(data).getString("logistics"));
//                        Glide.with(OverViewActivity.this).load(new JSONObject(data).getString("img2"))
//                                .into(image1);

                        //下拉地址
                        mGroupList.add(new JSONObject(data).getString("shipping_address"));
                        mGroupList.add(new JSONObject(data).getString("billing_address"));
                        getItemList(new JSONObject(data).getString("shipping_addr"));
                        getItemList(new JSONObject(data).getString("billing_addr"));
                        adapter = new ExtendableListViewAdapter(OverViewActivity.this, mGroupList, mItemSet);
                        expandableListView.setAdapter(adapter);
                        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                            @Override
                            public void onGroupExpand(int groupPosition) {
                                int count = adapter.getGroupCount();
                                for (int i = 0; i < count; i++) {
                                    if (i != groupPosition) {
                                        expandableListView.collapseGroup(i);
                                    }
                                }
                            }
                        });

                        //运送流程
                        if(!new JSONObject(data).getString("order_status").equals("null")){
                            JSONArray txtsList=new JSONObject(data).getJSONArray("order_status");
                            for (int i = 0; i < txtsList.length(); i++) {
                                JSONObject oj = txtsList.getJSONObject(i);
                                txts[i]=(String)oj.get("status");
                            }
                            mView1.builder().pointStrings(txts, 1)
                                    //.startedCircleColor(Color.BLUE)
                                    //.underwayCircleColor(Color.BLUE)
                                    .preCircleColor(Color.GRAY)
                                    .startedLineColor(Color.BLUE)
                                    .preLineColor(Color.GRAY)
//                .startedStringColor(Color.BLUE)
//                .underwayStringColor(Color.BLUE)
                                    .preStringColor(Color.GRAY)
                                    .load();   //开始绘制
                        }

                        //心愿单  默认显示无数据的135
                        List<List<String>> favList=new ArrayList<>();

                        if(!new JSONObject(data).getString("dtls").equals("null")){
                            constraintLayout1.setVisibility(View.GONE);
                            constraintLayout3.setVisibility(View.GONE);
                            constraintLayout5.setVisibility(View.GONE);
                            constraintLayout2.setVisibility(View.VISIBLE);
                            constraintLayout4.setVisibility(View.VISIBLE);
                            constraintLayout6.setVisibility(View.VISIBLE);
                            JSONArray dtlList=new JSONObject(data).getJSONArray("dtls");
                            for (int i = 0; i < dtlList.length(); i++) {
                                JSONObject oj = dtlList.getJSONObject(i);
                                List<String> a=new ArrayList<>();
                                a.add((String) oj.get("unit"));
                                a.add((String)oj.get("category_name"));
                                a.add((String)oj.get("color_no"));
                                a.add(oj.get("yarn_count")+"Nm- "+oj.get("guage"));
                                a.add((String)oj.get("color_name"));
                                a.add((String)oj.get("image"));
                                favList.add(a);
                            }
                        }
                        recycleViewAdapter=new RecycleViewAdapter(OverViewActivity.this,favList);

                        recyclerView.setAdapter(recycleViewAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDataReceivedFailed() {
                    //Toast.makeText(, "data received failed!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getItemList(String ship) {
        ArrayList<String> itemList = new ArrayList<>();
        if (ship.equals("null"))//判断使用哪一个窗口
        {
            itemList.add("null");
            mItemSet.add(itemList);
        } else {
            try {

                itemList.add(new JSONObject(ship).getString("company"));
                itemList.add(new JSONObject(ship).getString("receiver_name"));
                itemList.add(new JSONObject(ship).getString("telephone"));
                itemList.add(new JSONObject(ship).getString("addr"));
                mItemSet.add(itemList);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
