package com.example.ghy_video.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ghy_video.AppManager;

/**
 * Created by Administrator on 2017/10/26.
 */
//序列化接口
public class Album implements Parcelable {
    private String albumId;//专辑ID
    private int videoTotal;//专辑集数
    private String  title;//专辑名称
    private String subTitle;//专辑子标题
    private String director;//导演
    private String mainActor;//主演
    private String verImgUrl;//专辑竖图
    private String horImgUrl;//z专辑横图
    private String albumDesc;//专辑描述
    private Site site;//网站
    private String tip;
    private boolean isCompleted;
    private String letvStyle;
    public Album(int siteId){
        new Site(siteId);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    private Album(Parcel in)//私有的声明,从内存中去读数据
    {
        this.albumId = in.readString();
        this.videoTotal = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.director = in.readString();
        this.mainActor = in.readString();
        this.verImgUrl = in.readString();
        this.horImgUrl = in.readString();
        this.albumDesc = in.readString();
        this.tip = in.readString();
        this.site = new Site(in.readInt());
        this.isCompleted = in.readByte() != 0;
        this.letvStyle = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(albumId);//数据持久化需要写入内存或者文件当中去,所以需要写文件的方式
        dest.writeInt(videoTotal);
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(director);
        dest.writeString(mainActor);
        dest.writeString(verImgUrl);
        dest.writeString(horImgUrl);
        dest.writeString(albumDesc);
        dest.writeString(tip);
        dest.writeInt(site.getSiteId());
        dest.writeByte((byte) (isCompleted() ? 1: 0));
        dest.writeString(letvStyle);
    }
    public static final Parcelable.Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);//读数据
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(int videoTotal) {
        this.videoTotal = videoTotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getVerImgUrl() {
        return verImgUrl;
    }

    public void setVerImgUrl(String verImgUrl) {
        this.verImgUrl = verImgUrl;
    }

    public String getHorImgUrl() {
        return horImgUrl;
    }

    public void setHorImgUrl(String horImgUrl) {
        this.horImgUrl = horImgUrl;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getLetvStyle() {
        return letvStyle;
    }

    public void setLetvStyle(String letvStyle) {
        this.letvStyle = letvStyle;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", videoTotal=" + videoTotal +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", verImgUrl='" + verImgUrl + '\'' +
                ", horImgUrl='" + horImgUrl + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", site=" + site +
                ", tip='" + tip + '\'' +
                ", isCompleted=" + isCompleted +
                ", letvStyle='" + letvStyle + '\'' +
                '}';
    }
    public String toJson(){
        String ret = AppManager.getGson().toJson(this);
        return ret;
    }//将数据封装成jsom格式
    public Album fromJson(String json){
        Album album = AppManager.getGson().fromJson(json,Album.class);//将传进来的json直接映射成Album.class
        return album;
    }
}
