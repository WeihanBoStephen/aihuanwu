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
 * Created by come on 2016/12/1.
 */
public class RegisterSuccessActivity extends AppCompatActivity {

    Context context;

    private Button enterBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_success);
        initView();
        initListener();
    }
    public void initView(){
        context = this;
        enterBt = (Button)findViewById(R.id.bt_enter);
        SetTop s = new SetTop(this,"注册完成","#FEB500");
        s.hideBackBt();
        s.setTitleFontColor("#ffffff");
    }
    public void initListener(){
        enterBt.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.bt_enter:
                    intent = new Intent(context, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
