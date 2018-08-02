package com.zt.agw.view.home.model;

/**
 * Created by come on 2016/12/2.
 */
public class DiscoverItemModel {
    private String url1 = null;
    private String title1 = null;
    private String gold1 = null;
    private String pin1 = null;

    private String url2 = null;
    private String title2 = null;
    private String gold2 = null;
    private String pin2 = null;
    public DiscoverItemModel(String url1,String title1,String gold1,String pin1,
                             String url2,String title2,String gold2,String pin2){
        this.url1 = url1;
        this.title1 = title1;
        this.gold1 = gold1;
        this.pin1 = pin1;

        this.url2 = url2;
        this.title2 = title2;
        this.gold2 = gold2;
        this.pin2 = pin2;
    }
    public DiscoverItemModel(String url1,String title1,String gold1,String pin1){
        this.url1 = url1;
        this.title1 = title1;
        this.gold1 = gold1;
        this.pin1 = pin1;
    }
    public String getUrl1(){
        return this.url1;
    }
    public String getUrl2(){
        return this.url2;
    }
    public String getTitle1(){
        return this.title1;
    }
    public String getTitle2(){
        return this.title2;
    }
    public String getGold1(){
        return this.gold1;
    }
    public String getGold2(){
        return this.gold2;
    }
    public String getPin1(){
        return this.pin1;
    }
    public String getPin2(){
        return this.pin2;
    }
}
