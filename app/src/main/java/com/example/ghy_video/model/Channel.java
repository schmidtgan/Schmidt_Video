package com.example.ghy_video.model;

import android.content.Context;

import com.example.ghy_video.R;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/22.
 */

public class Channel implements Serializable {
    public static final  int SHOW =1;
    public static final  int MOVIE =2;
    public static final  int COMIC =3;
    public static final  int DOCUMENTRY =4;
    public static final  int MUSIC =5;
    public static final  int VARIETY =6;
    public static final  int LIVE=7;
    public static final  int FAVORITE =8;
    public static final  int HISTORY =9;
    private Context mContext;
    public Channel(int id, Context context){
        channelId = id;
        mContext = context;
        switch (channelId){
            case SHOW:
                channelName =mContext.getResources().getString(R.string.channel_series);
                break;
            case COMIC:
                channelName =mContext.getResources().getString(R.string.channel_comic);
                break;
            case DOCUMENTRY:
                channelName =mContext.getResources().getString(R.string.channel_documentary);
                break;
            case MUSIC:
                channelName =mContext.getResources().getString(R.string.channel_music);
                break;
            case VARIETY:
                channelName =mContext.getResources().getString(R.string.channel_variety);
                break;
            case LIVE:
                channelName =mContext.getResources().getString(R.string.channel_live);
                break;
            case FAVORITE:
                channelName =mContext.getResources().getString(R.string.channel_favorite);
                break;
            case HISTORY:
                channelName =mContext.getResources().getString(R.string.channel_history);
                break;
            case MOVIE :
                channelName = mContext.getResources().getString(R.string.channel_movie);
            default:
                break;
        }
    }
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    private int channelId;
    private String channelName;
}
