package com.example.ghy_video.api;

import android.content.Context;

import com.example.ghy_video.model.Channel;
import com.example.ghy_video.model.Site;

/**
 * Created by Administrator on 2017/10/26.
 */

public class SiteApi {

    public void onGetChannelAlbums(Context context,int pageMo, int pageNo, int pageSize, int siteId, int channelId, OnGetChannelAlbumListener listener){
        switch (siteId){
            case Site.LETV:
                new LetvApi().onGetChannelAlbums(new Channel(channelId,context),pageNo,pageSize,listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(new Channel(channelId,context),pageNo,pageSize,listener);
                break;
        }
    }
}
