package com.zt.agw.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zt.agw.R;

/**
 * Created by come on 2016/12/1.
 */
public class FragmentMine extends Fragment {

    private RelativeLayout mySendRl,myRecieveRl,myCommentRl,markMarketRl,myLevelRl,settingRl;
    private TextView nameTv,markTv,mySendTv,myRecieveTv,myCommentTv;
    private ImageView headIv;
    private Button registerBt;
    private Context context;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initial();
        return view;
    }
    public void initial(){
        mySendRl = (RelativeLayout)view.findViewById(R.id.my_send_rl);
        myRecieveRl = (RelativeLayout)view.findViewById(R.id.my_recieve_rl);
        myCommentRl = (RelativeLayout)view.findViewById(R.id.my_comment_rl);
        markMarketRl = (RelativeLayout)view.findViewById(R.id.my_mark_rl);
        myLevelRl = (RelativeLayout)view.findViewById(R.id.my_level_rl);
        settingRl = (RelativeLayout)view.findViewById(R.id.setting_rl);
        nameTv = (TextView)view.findViewById(R.id.txt_name);
        markTv = (TextView)view.findViewById(R.id.txt_mark);
        mySendTv = (TextView)view.findViewById(R.id.my_send_tv);
        myRecieveTv = (TextView)view.findViewById(R.id.my_recieve_tv);
        myCommentTv = (TextView)view.findViewById(R.id.my_comment_tv);
        headIv = (ImageView)view.findViewById(R.id.img_head);
        registerBt = (Button)view.findViewById(R.id.register_bt);
    }
    @Override
    public void onStart() {
        super.onStart();

    }
}
