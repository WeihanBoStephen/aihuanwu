package com.zt.agw.view.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zt.agw.R;
import com.zt.agw.view.SetHomeTop;
import com.zt.agw.view.citySelection.CitySelecterActivity;
import com.zt.agw.view.search.searchActivity;

/**
 * Created by come on 2016/12/1.
 */
public class HomeActivity extends FragmentActivity{

    private static final int RESULT_LOCATION = 97;
    private String location = "同城";
    private FragmentDiscover fragmentDiscover;
    private FragmentMessage fragmentMessage;
    private FragmentMine fragmentMine;

    private FrameLayout discoverFl,messageFl,mineFl;

    private ImageView discoverIv,messageIv,mineIv;

    private LinearLayout cityLn;

    private Context context;

    private SetHomeTop s;

    private LinearLayout searchLn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_discover);

        context = this;
        initView();
        setListener();
    }
    public void initView(){
        discoverFl = (FrameLayout)findViewById(R.id.layout_discover);
        messageFl = (FrameLayout)findViewById(R.id.layout_message);
        mineFl = (FrameLayout)findViewById(R.id.layout_mine);

        discoverIv = (ImageView)findViewById(R.id.image_discover);
        messageIv = (ImageView)findViewById(R.id.image_message);
        mineIv = (ImageView)findViewById(R.id.image_mine);
        cityLn = (LinearLayout)findViewById(R.id.city_ln);
        searchLn = (LinearLayout)findViewById(R.id.search_ln);
        clickDiscoverBtn();
    }
    public void setListener(){
        discoverFl.setOnClickListener(btnOnClickListener);
        messageFl.setOnClickListener(btnOnClickListener);
        mineFl.setOnClickListener(btnOnClickListener);
        searchLn.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.layout_discover:
                    clickDiscoverBtn();
                    break;
                case R.id.layout_message:
                    clickMessageBtn();
                    break;
                case R.id.layout_mine:
                    clickMineBtn();
                    break;
                case R.id.search_ln:
                    intent = new Intent(context, searchActivity.class);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
    private void clickDiscoverBtn(){
        fragmentDiscover = new FragmentDiscover();
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragmentDiscover);
        fragmentTransaction.commit();

        discoverFl.setSelected(true);
        discoverIv.setSelected(true);

        messageFl.setSelected(false);
        messageIv.setSelected(false);

        mineFl.setSelected(false);
        mineIv.setSelected(false);

        s = new SetHomeTop(this);
        Resources resources = this.getResources();
        Drawable add = resources.getDrawable(R.drawable.add);
        Drawable search = resources.getDrawable(R.drawable.search);
        Drawable pullDown = resources.getDrawable(R.drawable.pull_down);
        s.setAdd(add,true);
        s.setSearch(search,true);
        s.setPullDown(pullDown,true);
        s.setTitle(location);
        cityLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CitySelecterActivity.class);
                startActivityForResult(intent, RESULT_LOCATION);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            return;
        }
        if(requestCode == RESULT_LOCATION && resultCode==1111){
            String key = "";
            if(null != data.getStringExtra("location"))
                key = data.getStringExtra("location");
            location = key;
            s.setTitle(location);
        }

    }
    private void clickMessageBtn(){
        fragmentMessage = new FragmentMessage();
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content,fragmentMessage);
        fragmentTransaction.commit();

        messageFl.setSelected(true);
        messageIv.setSelected(true);

        discoverFl.setSelected(false);
        discoverIv.setSelected(false);

        mineFl.setSelected(false);
        mineIv.setSelected(false);

        SetHomeTop s = new SetHomeTop(this);
        s.setAdd(null,false);
        s.setSearch(null,false);
        s.setPullDown(null,false);
        s.setTitle("消息");
        cityLn.setOnClickListener(null);
    }
    private void clickMineBtn(){
        fragmentMine = new FragmentMine();
        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content,fragmentMine);
        fragmentTransaction.commit();

        mineFl.setSelected(true);
        mineIv.setSelected(true);

        discoverFl.setSelected(false);
        discoverIv.setSelected(false);

        messageFl.setSelected(false);
        messageIv.setSelected(false);

        SetHomeTop s = new SetHomeTop(this);
        s.setAdd(null,false);
        s.setSearch(null,false);
        s.setPullDown(null,false);
        s.setTitle("我的");
        cityLn.setOnClickListener(null);
    }
}
