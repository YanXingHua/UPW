package com.example.upw.ui.registerLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.upw.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RegAsyncTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private String postValue, url, page;

    //private WebView webView;
    public AsyncResponse asyncResponse;

    public void setOnAsyncResponse(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public RegAsyncTask(Context context, String url, String postValue, String page) {
        this.context = context;
        this.postValue = postValue;
        this.url = url;
        this.page = page;
    }


    @Override
    protected String doInBackground(Void... voids) {

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
        }

        String result = HttpUtils.submitPostData(url, postValue);
        try {
            if (page == "register") {
                result = new JSONObject(result).getString("message");

                //WebView.loadDataWithBaseURL(null,string,"text/html","utf-8",null);
            } else if (page == "login") {
                if (new JSONObject(result).getInt("code") == 200) {
                    String a = new JSONObject(result).getString("api_token");
                    SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                    //获取编辑器
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //存入登录状态时的用户名
                    editor.putString("token", a);
                    //提交修改
                    editor.apply();
                    result = "Login Success";
                } else {
                    result = new JSONObject(result).getString("message");
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //.loadDataWithBaseURL(null,result,"text/html","utf-8",null);
        return result;


    }

    @Override
    protected void onPostExecute(String i) {
        super.onPostExecute(i);
        asyncResponse.onDataReceivedSuccess();
        Toast.makeText(context, i, Toast.LENGTH_SHORT).show();
        //text.setText(i);
    }

    //@Override
//    protected void onProgressUpdate(Integer... values)
//    {
//
//        //第二个参数为Int
//       //text.setText(""+values[0]);
//    }


}
