package com.zt.agw.view.home.model;

/**
 * Created by come on 2016/12/5.
 */
public class MessageItemModel {
    private String imgUrl;
    private String title;
    private String message;
    private String time;
    public MessageItemModel(String imgUrl,String title,String message,String time){
        this.imgUrl = imgUrl;
        this.title = title;
        this.message = message;
        this.time = time;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getImgUrl(){return this.imgUrl;}
    public String getTitle(){return this.title;}
    public String getMessage(){return this.message;}
    public String getTime(){return this.time;}
}
