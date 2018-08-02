package com.zt.agw.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zt.agw.R;

/**
 * Created by come on 2016/12/1.
 */
public class SetHomeTop {
    private Activity context;
    private LinearLayout searchLn,addLn,cityLn;
    private RelativeLayout rl;
    private ImageView searchIv,pullDownIv,addIv;
    private TextView titleTv;
    public SetHomeTop(Activity context){
        this.context = context;
        initial();
    }
    public void SetHomeTop(Activity context,String backgroundColor){
        this.context = context;
        initial();
        rl.setBackgroundColor(Color.parseColor(backgroundColor));
    }
    private void initial(){
        searchLn = (LinearLayout)context.findViewById(R.id.search_ln);
        cityLn = (LinearLayout)context.findViewById(R.id.city_ln);
        addLn = (LinearLayout)context.findViewById(R.id.add_ln);
        rl = (RelativeLayout)context.findViewById(R.id.relative);
        searchIv = (ImageView)context.findViewById(R.id.search_iv);
        pullDownIv = (ImageView)context.findViewById(R.id.pull_down_iv);
        addIv = (ImageView)context.findViewById(R.id.add_iv);
        titleTv = (TextView)context.findViewById(R.id.title_tv);
    }
    public void setSearch(Drawable d,boolean b){
        if(d != null){
            searchIv.setImageDrawable(d);
        }
        if(b){
            searchIv.setVisibility(View.VISIBLE);
        }else{
            searchIv.setVisibility(View.GONE);
        }
    }
    public void setPullDown(Drawable d, boolean b){
        if(d != null){
            pullDownIv.setImageDrawable(d);
        }
        if(b){
            pullDownIv.setVisibility(View.VISIBLE);
        }else{
            pullDownIv.setVisibility(View.GONE);
        }
    }
    public void setAdd(Drawable d, boolean b){
        if(d != null){
            addIv.setImageDrawable(d);
        }
        if(b){
            addIv.setVisibility(View.VISIBLE);
        }else{
            addIv.setVisibility(View.GONE);
        }
    }
    public void setTitle(String text){
        titleTv.setText(text);
    }
}
