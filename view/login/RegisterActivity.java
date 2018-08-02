package com.zt.agw.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.SetTop;

/**
 * Created by come on 2016/11/30.
 */
public class RegisterActivity extends AppCompatActivity {

    Context context;

    private TextView protocolTv;

    private Button verifyBt,nextBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        initView();
        initListener();
    }
    public void initView(){
        context = this;
        SetTop s = new SetTop(this,"手机号","#FEB500");
        s.setTitleFontColor("#ffffff");
        Resources resources = context.getResources();
        Drawable d = resources.getDrawable(R.drawable.return_white);
        s.setBackIv(d);
        protocolTv = (TextView)findViewById(R.id.protocol_tv);
        verifyBt = (Button)findViewById(R.id.bt_verify);
        nextBt = (Button)findViewById(R.id.bt_next);
    }
    public void initListener(){
        protocolTv.setOnClickListener(btnOnClickListener);
        nextBt.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.protocol_tv:
                    intent = new Intent(context, RegisterProtocolActivity.class);
                    context.startActivity(intent);
                    break;
                case R.id.bt_next:
                    intent = new Intent(context,RegisterSuccessActivity.class);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}
