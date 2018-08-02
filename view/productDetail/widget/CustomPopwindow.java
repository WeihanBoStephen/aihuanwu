package com.zt.agw.view.productDetail.widget;

/**
 * Created by come on 2016/12/8.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zt.agw.R;
import com.zt.agw.utils.Tools;


/**
 * Created by Administrator on 2016/4/12.
 */
public class CustomPopwindow extends PopupWindow {

    private View mView;
    private LinearLayout wechatLn,wechatFriendLn,weiboLn,qqLn,qzoneLn,copyLinkLn;
    private Button cancelBt;
    private Tools tools;
    private CustomPopwindow popwindow;

    public CustomPopwindow(Activity context){
        super(context);
        tools = new Tools(context);
        initView(context);
        addListener();
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.wechat_ln:
                    tools.showToast("微信");
                    break;
                case R.id.wechat_friend_ln:
                    tools.showToast("朋友圈");
                    break;
                case R.id.weibo_ln:
                    tools.showToast("微博");
                    break;
                case R.id.qq_ln:
                    tools.showToast("QQ");
                    break;
                case R.id.qzone_ln:
                    tools.showToast("QQ空间");
                    break;
                case R.id.copy_link_ln:
                    tools.showToast("复制链接");
                    break;
                case R.id.cancel_bt:
                    popwindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };
    private void addListener(){
        wechatLn.setOnClickListener(btnOnClickListener);
        wechatFriendLn.setOnClickListener(btnOnClickListener);
        weiboLn.setOnClickListener(btnOnClickListener);
        qqLn.setOnClickListener(btnOnClickListener);
        qzoneLn.setOnClickListener(btnOnClickListener);
        copyLinkLn.setOnClickListener(btnOnClickListener);
        cancelBt.setOnClickListener(btnOnClickListener);
    }
    private void initView(final Activity context) {
        // TODO Auto-generated method stub
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.popwindow_share,null);
        wechatLn = (LinearLayout)mView.findViewById(R.id.wechat_ln);
        wechatFriendLn = (LinearLayout)mView.findViewById(R.id.wechat_friend_ln);
        weiboLn = (LinearLayout)mView.findViewById(R.id.weibo_ln);
        qqLn = (LinearLayout)mView.findViewById(R.id.qq_ln);
        qzoneLn = (LinearLayout)mView.findViewById(R.id.qzone_ln);
        copyLinkLn = (LinearLayout)mView.findViewById(R.id.copy_link_ln);
        cancelBt = (Button)mView.findViewById(R.id.cancel_bt);
        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context,0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });
        popwindow = this;
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

}
