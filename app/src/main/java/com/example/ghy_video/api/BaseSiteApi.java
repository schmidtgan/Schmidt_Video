package com.example.ghy_video.api;

import com.example.ghy_video.model.Channel;

/**
 * Created by Administrator on 2017/10/26.
 */

public abstract class BaseSiteApi {
    public abstract void onGetChannelAlbums(Channel channel,int pageNo,int pageSize,OnGetChannelAlbumListener listener);
}
