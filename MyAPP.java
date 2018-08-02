package com.zt.agw;

/**
 * Created by come on 2016/12/6.
 */

import android.app.Application;

import com.zt.agw.view.citySelection.db.DBManager;


public class MyAPP extends Application {
    private DBManager dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        //导入数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
//        dbHelper.closeDatabase();
    }
}
