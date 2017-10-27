package com.example.ghy_video.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/27.
 */

public class AlbumList extends ArrayList <Album>{
    private static final String TAG = AlbumList.class.getSimpleName();
    public String toString(){
        for (Album a : this){
          Log.d(TAG,">> albumlist "+a.toString());//album相关list的值
        }
        return null;
    }
}
