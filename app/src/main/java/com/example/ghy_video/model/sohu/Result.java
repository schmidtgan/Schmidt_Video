package com.example.ghy_video.model.sohu;

import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2017/10/27.
 */
/*
*  搜狐数据频道数据返回
*/
public class Result {
    @Expose
    private long status;
    @Expose
    private String statusText;
    @Expose
    private Data data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
