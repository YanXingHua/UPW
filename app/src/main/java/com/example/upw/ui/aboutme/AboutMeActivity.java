package com.example.upw.ui.aboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.upw.R;
import com.example.upw.ui.MainAsynctask;
import com.example.upw.ui.overview.MyExpandListView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class AboutMeActivity extends AppCompatActivity {
    private ImageView imageView9, imageView10, imageView11;
    TextView tv1, tv2;
    private MyExpandListView expandableListView1;
    private AboutMeExableListViewAdapter adapter;
    //private  List<List<String>> ChildList=new ArrayList<>();
    private List<String> GroupList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        String strUrl = "http://10.10.150.240:88/api/aboutme/index?api_token=" + getIntent().getStringExtra("token");
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        tv1 = findViewById(R.id.tvname);
        tv2 = findViewById(R.id.tvemail);
        expandableListView1 = findViewById(R.id.ExpandableListView1);
        expandableListView1.setGroupIndicator(null);
        getData(strUrl);
        expandableListView1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean groupExpanded = parent.isGroupExpanded(groupPosition);
//              设置指示器 位置，开关
                adapter.setIndicatorState(groupPosition, groupExpanded);
                return false;
            }
        });
    }

    private void getData(String strUrl) {
        try {
            final MainAsynctask AboutMeFragmentTask = new MainAsynctask(this, strUrl, "AboutMe");
            AboutMeFragmentTask.execute();
            AboutMeFragmentTask.setOnAsyncResponse(new MainAsynctask.AsyncResponse() {
                @Override
                public void onDataReceivedSuccess(String data) {
                    try {
                        tv1.setText(new JSONObject(new JSONObject(data).getString("$QrCode")).getString("name"));
                        tv2.setText(new JSONObject(new JSONObject(data).getString("$QrCode")).getString("email"));
                        Bitmap mBitmap = createQRCodeBitmap(new JSONObject(new JSONObject(data).getString("$QrCode")).getString("email"), 300, 300, "UTF-8", "H", "2", Color.BLACK, Color.WHITE);
                        imageView9.setImageBitmap(mBitmap);
                        Glide.with(AboutMeActivity.this).load(new JSONObject(new JSONObject(data).getString("$QrCode")).getString("card"))
                                .into(imageView10);
                        Glide.with(AboutMeActivity.this).load(new JSONObject(new JSONObject(data).getString("$QrCode")).getString("backgroundimage"))
                                .into(imageView11);

                        GroupList.add(new JSONObject(data).getString("personal_information"));
                        GroupList.add(new JSONObject(data).getString("shipping_address"));
                        GroupList.add(new JSONObject(data).getString("billing_address"));
                        GroupList.add(new JSONObject(data).getString("change_password"));
                        //getItemList(new JSONObject(data).getJSONArray("$user"));
                        ArrayList<String> itemList = new ArrayList<>();
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("company"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("title"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("firstname"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("surname"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("email"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("telephone"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("market_id"));
                        itemList.add(new JSONObject(new JSONObject(data).getString("$user")).getString("is_receive_email"));
                        List<List<String>> ChildList = new ArrayList<>();
                        ChildList.add(itemList);
//                        List<List<HashMap<String, String>>> shipAdds = getItemList(new JSONObject(data).getJSONArray("$shipping_addrs"));
//                        List<List<HashMap<String, String>>> billAdds = getItemList(new JSONObject(data).getJSONArray("billing_addrs"));
//                        List<List<HashMap<String, String>>> markets = getItemList(new JSONObject(data).getJSONArray("markets"));
//                        List<List<HashMap<String, String>>> countries = getItemList(new JSONObject(data).getJSONArray("countries"));
//                        List<List<HashMap<String, String>>> provinces = getItemList(new JSONObject(data).getJSONArray("provinces"));
                        ////第一个下拉
                        ////试试用键值对，然后循环直接读完所有的元素，Adapter根据键取值
                        //adapter = new AboutMeExableListViewAdapter(AboutMeActivity.this, GroupList, ChildList, shipAdds, billAdds, markets, countries, provinces);

                        ArrayList<AboutMe>list1=getAboutList(new JSONObject(data).getJSONArray("$shipping_addrs"));
                        ArrayList<AboutMe>list2=getAboutList(new JSONObject(data).getJSONArray("billing_addrs"));



                        adapter = new AboutMeExableListViewAdapter(AboutMeActivity.this,GroupList,ChildList,list1,list2);
                        expandableListView1.setAdapter(adapter);
                        expandableListView1.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                            @Override
                            public void onGroupExpand(int groupPosition) {
                                int count = adapter.getGroupCount();
                                for (int i = 0; i < count; i++) {
                                    if (i != groupPosition) {
                                        expandableListView1.collapseGroup(i);
                                    }
                                }
                            }
                        });
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

    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            String character_set, String error_correction_level,
                                            String margin, int color_black, int color_white) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置

            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private  ArrayList<AboutMe> getAboutList(JSONArray str) {
        ArrayList<AboutMe>list=new ArrayList<>();
        try {
            ArrayList<AboutMeinfo>ch_list = new ArrayList<AboutMeinfo>();
            for (int i = 0; i < str.length(); i++) {
                JSONObject jsonObject = str.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String type = jsonObject.getString("type");
                String company = jsonObject.getString("company");
                String receiver = jsonObject.getString("receiver");
                String street = jsonObject.getString("street");
                String tel = jsonObject.getString("tel");
                String zip = jsonObject.getString("zip");
                String city = jsonObject.getString("city");
                String province = jsonObject.getString("province");
                String country = jsonObject.getString("country");
                Boolean default_address = jsonObject.getBoolean("default_address");
                String addr = jsonObject.getString("addr");
                String country_name = jsonObject.getString("country_name");
                String cust_no = jsonObject.getString("cust_no");
                ch_list.add(new AboutMeinfo(id, type, company, receiver, street, tel, zip, city, province, country, default_address, addr, country_name, cust_no));
                list.add(new AboutMe(ch_list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
