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
 * Created by come on 2016/11/30.
 */
public class SetTop {

    private RelativeLayout rl;
    private LinearLayout back;
    private TextView titleTv;
    private Activity context;
    private String title;
    private String c;
    private ImageView backIv;

    public SetTop(Activity context){
        this.context = context;
        initial();
        final Activity a = context;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
    }
    public SetTop(Activity context, String title, String c){
        this.context = context;
        this.title = title;
        this.c = c;
        initial();
        titleTv.setText(title);
        rl.setBackgroundColor(Color.parseColor(c));
        back.setBackgroundColor(Color.parseColor(c));
        final Activity a = context;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
    }
    public void setBackIv(Drawable d){
        if(d != null){
            backIv.setImageDrawable(d);
        }
    }
    private void initial(){
        rl = (RelativeLayout)context.findViewById(R.id.relative);
        back = (LinearLayout)context.findViewById(R.id.back);
        titleTv = (TextView)context.findViewById(R.id.login_title);
        backIv = (ImageView)context.findViewById(R.id.img_back);
    }
    public void setTitle(String title) {
        titleTv.setText(title);
    }
    public void setBackground(String c){
        rl.setBackgroundColor(Color.parseColor(c));
        back.setBackgroundColor(Color.parseColor(c));
    }
    public void hideBackBt(){
        back.setVisibility(View.GONE);
    }
    public void setTitleFontColor(String c){
        titleTv.setTextColor(Color.parseColor(c));
    }
}
