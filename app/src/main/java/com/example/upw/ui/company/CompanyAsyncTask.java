//package com.example.upw.ui.company;
//
//import android.content.Context;
//import android.nfc.Tag;
//import android.os.AsyncTask;
//import android.text.Html;
//import android.text.Spanned;
//import android.util.Log;
//import android.webkit.WebView;
//
//import com.example.upw.R;
//import com.example.upw.ui.registerLogin.AsyncResponse;
//import com.example.upw.ui.registerLogin.HttpUtils;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CompanyAsyncTask extends AsyncTask<String,Void,String> {
//    private Context context;
//    private String  url, page;
//    public  String resultData=null;
//    private String Tag="result";
//
//
//
//    comAsyncResponse comAsyncResponse;
//    public void setOnAsyncResponse(comAsyncResponse comAsyncResponse)
//    {
//        this.comAsyncResponse = comAsyncResponse;
//    }
//
//
//    @Override
//    protected  String doInBackground(String... params) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//        }
//
//         resultData=GetRequest.submitGetData(url);
//       try {
//         resultData=new JSONObject(resultData).getString("data");
//           Spanned title=Html.fromHtml(resultData);
//           resultData=title.toString();
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
////        comAsyncResponse.onDataReceivedSuccess(resultData);
//        return resultData;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        comAsyncResponse.onDataReceivedSuccess(resultData);
//    }
//
//        public static interface comAsyncResponse {
//        void
//        onDataReceivedSuccess(String data);
//        void onDataReceivedFailed();
//    }
//
////    public static interface SusAsyncResponse {
////        void
////        onDataReceivedSuccess(String data);
////        void onDataReceivedFailed();
////    }
//
//    public CompanyAsyncTask(Context context, String url, String page) {
//        this.context = context;
//        this.url = url;
//        this.page = page;
//    }
//}
