package com.example.upw.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;

import com.example.upw.ui.company.GetRequest;
import com.example.upw.ui.registerLogin.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainAsynctask extends AsyncTask<String, Void, String> {
    private Context context;
    private String strUrl,page,resultData;

    public MainAsynctask(Context context, String strUrl,String page) {
        this.context = context;
        this.strUrl = strUrl;
        this.page = page;
    }

    AsyncResponse asyncResponse;
    public void setOnAsyncResponse(AsyncResponse asyncResponse)
    {
        this.asyncResponse = asyncResponse;
    }

    public static interface AsyncResponse {
        void
        onDataReceivedSuccess(String data);
        void onDataReceivedFailed();
    }


    @Override
    protected  String doInBackground(String... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        resultData= GetRequest.submitGetData(strUrl);
        try {
            if(page!="OverViewInformation"){
                resultData=new JSONObject(resultData).getString("data");
            }

            Spanned title= Html.fromHtml(resultData);
            resultData=title.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        asyncResponse.onDataReceivedSuccess(resultData);
    }
}
