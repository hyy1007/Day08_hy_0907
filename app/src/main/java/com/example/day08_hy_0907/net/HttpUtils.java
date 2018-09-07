package com.example.day08_hy_0907.net;

import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static final HttpUtils ourInstance = new HttpUtils();

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
    }
    private getDataCallback getDataCallback;

    public void setGetDataCallback(HttpUtils.getDataCallback getDataCallback) {
        this.getDataCallback = getDataCallback;
    }
    public void setUrl(String url){

    }

    public class loadDataTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    return CharStreams.toString(new InputStreamReader(connection.getInputStream(),"utf-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            getDataCallback.CallBack(s);
            super.onPostExecute(s);
        }
    }

    //接口
    public interface getDataCallback{
        void CallBack(String result);
    }
}
