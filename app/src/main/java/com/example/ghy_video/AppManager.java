package com.example.ghy_video;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/10/27.
 */

//okhttphegson相关初始化
public class AppManager extends Application {
    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
    }

    public static  Gson getGson(){
        return mGson;
    }
    public static OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }
    public static Context getContext(){
        return mContext;
    }
    public static Resources getResource(){
        return  mContext.getResources();//颜色,String,drawable
    }
    public static boolean isNetWorkAvailable(){
        //当前网络是否可用
        ConnectivityManager connectivityManager =(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null&&networkInfo.isConnected();
    }
}
