package com.zt.agw.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zt.agw.R;
import com.zt.agw.view.welcome.WelcomeActivity;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        context = this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
