package com.example.ghy_video.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */

public class Data {
    @Expose
    private int count;
    @Expose
    @SerializedName("videos")
    private List<ResultAlbum> resultAlbumlist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultAlbum> getResultAlbumlist() {
        return resultAlbumlist;
    }

    public void setResultAlbumlist(List<ResultAlbum> resultAlbumlist) {
        this.resultAlbumlist = resultAlbumlist;
    }
}
