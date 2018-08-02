package com.zt.agw.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by come on 2016/12/8.
 */
public class Tools {
    private Context context;
    public Tools(Context context){
        this.context = context;
    }
    public void showToast(String str){
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.show();
    }
}
