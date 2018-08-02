package com.zt.agw.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zt.agw.R;
import com.zt.agw.view.SetTop;
import com.zt.agw.view.home.HomeActivity;

/**
 * Created by come on 2016/11/30.
 */
public class LoginActivity extends AppCompatActivity {
    Context context;
    Button btLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        initView();
        setClickListener();
    }
    public void initView(){
        context = this;
        SetTop s = new SetTop(this);
        btLog = (Button)findViewById(R.id.bt_login);
    }
    public void setClickListener(){
        btLog.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.bt_login:
                    intent = new Intent(context, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
