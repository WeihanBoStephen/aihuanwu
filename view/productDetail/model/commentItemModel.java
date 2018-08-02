package com.zt.agw.view.productDetail.model;

/**
 * Created by come on 2016/12/7.
 */
public class commentItemModel {
    private String headUrl;
    private String name;
    private String male;
    private String comment;
    private String time;
    public commentItemModel(String headUrl,String name,String male,String comment,String time){
        this.headUrl = headUrl;
        this.name = name;
        this.male = male;
        this.comment = comment;
        this.time = time;
    }
    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMale(String male){
        this.male = male;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getHeadUrl(){return this.headUrl;}
    public String getName(){return this.name;}
    public String getMale(){return this.male;}
    public String getComment(){return this.comment;}
    public String getTime(){return this.time;}
}
