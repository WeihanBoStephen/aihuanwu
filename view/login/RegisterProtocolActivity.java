package com.zt.agw.view.login;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.SetTop;

/**
 * Created by come on 2016/11/30.
 */
public class RegisterProtocolActivity extends AppCompatActivity {
    TextView contentTv1,contentTv2,contentTv3;
    private final String content1 = "卓创资讯的各项网络电子服务的所有权和运营权归山东卓创资讯集团，" +
            "任何单位和个人非经本公司授权不得以本公司及其所有的网站名义进行商业或非商业活动。" +
            "卓创资讯提供的服务将严格按照本公司及本网发布的服务条款和操作规则执行。用户必须确认所有服务条款并完成注册程序，" +
            "才能成为卓创资讯的正式用户。";
    private final String content2 = "卓创资讯运用自己的电脑操作系统通过国际互联网络为用户提供网络服务。" +
            "由于服务栏目和内容的不同，要求用户必须： (1)自行配备上网的所需设备， 包括个人电脑、调制解调器或其他必备上网装置。" +
            " (2)自行负担个人上网所支付的与此服务有关的电话费用、 网络费用。 (3)提供详尽、准确的个人资料。" +
            " (4)不断更新注册资料，符合及时、详尽、准确的要求。 如果用户提供的资料包含有不正确的信息，" +
            "卓创资讯保留结束用户使用网络服务资格的权利。 ";
    private final String content3 = "卓创资讯根据自身的服务内容，以及与用户达成的协议提供服务。用户在享用本网提供的专项服务的同时，" +
            "同意接受网站提供的各类信息服务。卓创资讯有权在必要时修改服务条款，卓创资讯服务条款一旦发生变动，将会在重要页面上提示修改内容。" +
            "如果不同意所改动的内容，用户可以在与本网取得联系后取消获得的网络服务。如果用户继续享用网络服务，则视为接受服务条款的变动。" +
            "卓创资讯保留随时修改或中断服务而不需提前告知用户的权利。卓创资讯行使修改或中断服务的权利，不需对用户或第三方负责。";
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_protocol);
        context = this;
        initView();
    }
    public void initView(){
        contentTv1 = (TextView)findViewById(R.id.content1);
        contentTv2 = (TextView)findViewById(R.id.content2);
        contentTv3 = (TextView)findViewById(R.id.content3);
        contentTv1.setText(content1);
        contentTv2.setText(content2);
        contentTv3.setText(content3);
        SetTop s = new SetTop(this,"","#FEB500");
        Resources resources = context.getResources();
        Drawable d = resources.getDrawable(R.drawable.return_white);
        s.setBackIv(d);
    }
}
