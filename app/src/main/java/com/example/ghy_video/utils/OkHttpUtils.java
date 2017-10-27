package com.example.ghy_video.utils;

import com.example.ghy_video.AppManager;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/10/27.
 */

public class OkHttpUtils {
    private static final String REQUEST_TAG = "okhttp";//标志位的作用
    public static Request buildRequest(String url){
        if (AppManager.isNetWorkAvailable()){
            Request request = new Request.Builder().
                    tag(REQUEST_TAG).url(url).build();
            return null;
        }
        return null;
    }
    public static void excute(String url, Callback callback){
        Request request = buildRequest(url);
        excute(request,callback);
    }
    public static void excute(Request request,Callback callback){
        AppManager.getOkHttpClient().newCall(request).enqueue(callback);
    }
}
