package com.example.upw.ui.company;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetRequest {
    public static String submitGetData(String strUrl){
        String resultData="";
try {
    URL url = new URL(strUrl);

    //打开连接
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        //设置请求超时时间
        urlConnection.setConnectTimeout(1000);
        urlConnection.setRequestProperty("accept", "*/*");
        urlConnection.setRequestProperty("connection", "keep-alive");
        urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh");

        //开始连接
        urlConnection.connect();
        int responseCode = urlConnection.getResponseCode();
        if(responseCode == 200) {

        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            resultData+=line;
        }
        bufferedReader.close();
    }

}catch (Exception e){
    e.printStackTrace();

}
//System.out.println(resultData);
return resultData;
    }
}
