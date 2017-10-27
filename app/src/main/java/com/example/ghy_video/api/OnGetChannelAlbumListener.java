package com.example.ghy_video.api;

import com.example.ghy_video.model.AlbumList;
import com.example.ghy_video.model.ErrorInfo;

/**
 * Created by Administrator on 2017/10/26.
 */

public interface OnGetChannelAlbumListener {
    void onGetChannelAlbumSuccess(AlbumList albumList);
    void onGetChannelAlbumFailed(ErrorInfo info);
}
