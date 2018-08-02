package com.zt.agw.view.productDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.SetHomeTop;
import com.zt.agw.view.home.Adapter.ImagePagerAdapter;
import com.zt.agw.view.productDetail.Adapter.commentAdapter;
import com.zt.agw.view.productDetail.model.commentItemModel;
import com.zt.agw.view.productDetail.widget.CustomPopwindow;
import com.zt.agw.view.widget.bannerView.CircleFlowIndicator;
import com.zt.agw.view.widget.bannerView.ViewFlow;
import com.zt.agw.view.widget.scrollview.OverScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by come on 2016/12/7.
 */
public class productDetailActivity extends AppCompatActivity {

    Context context;
    Activity activity;
    private ListView listView;
    private ImageView returnIv;
    private LinearLayout returnLn,shareLn;
    private ArrayList<String> imageUrlList = new ArrayList<String>();
    private ArrayList<String> linkUrlArray= new ArrayList<String>();
    private ArrayList<commentItemModel> listData= new ArrayList<commentItemModel>();
    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private TextView followBt,sendMessageBt,commentCountTv;
    private commentAdapter adapter;
    private CustomPopwindow customPopwindow;
    private DisplayMetrics dm;
    private OverScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        context = this;
        activity = this;
        initView();
        setListener();
        getData();
    }
    public void initView(){
        SetHomeTop s = new SetHomeTop(this);
        Resources resources = context.getResources();
        Drawable d = resources.getDrawable(R.drawable.return_white);
        s.setSearch(d,true);
        d = resources.getDrawable(R.drawable.share);
        s.setAdd(d,true);
        s.setPullDown(null,false);
        s.setTitle("物品详情");
        returnIv = (ImageView)findViewById(R.id.search_iv);
        ViewGroup.LayoutParams params = returnIv.getLayoutParams();
        params.width =30;
        returnIv.setLayoutParams(params);

        scrollView = (OverScrollView)findViewById(R.id.scrollView1);
        listView = (ListView)findViewById(R.id.listview);
        returnLn = (LinearLayout)findViewById(R.id.search_ln);
        mViewFlow = (ViewFlow)findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator)findViewById(R.id.viewflowindic);
        followBt = (TextView)findViewById(R.id.follow_bt);
        sendMessageBt = (TextView)findViewById(R.id.send_message_bt);
        commentCountTv = (TextView)findViewById(R.id.comment_count_tv);
        shareLn = (LinearLayout)findViewById(R.id.add_ln);
    }
    public void setListener(){

        returnLn.setOnClickListener(btnOnClickListener);
        shareLn.setOnClickListener(btnOnClickListener);
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.search_ln:
                    finish();
                    break;
                case R.id.add_ln:
                    showPopupWindow(shareLn);
                    break;
                default:
                    break;
            }
        }
    };
    public void getData(){
        //轮播图片的url地址
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        //点击轮播广告图片跳转的页面地址
        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/44301359");
        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/43486527");
        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/44648121");
        linkUrlArray
                .add("http://blog.csdn.net/finddreams/article/details/44619589");
        initBanner(imageUrlList);//设置轮播图
        //获取评论数据
        listData.add(new commentItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","喵小姐","male","是啊，很喜欢！送给你","2"));
        listData.add(new commentItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","喵小姐","male","是啊，很喜欢！送给你","2"));
        listData.add(new commentItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","喵小姐","male","是啊，很喜欢！送给你","2"));

        setList(listData);
    }
    public void setList(List<commentItemModel> list){
        commentCountTv.setText(String.valueOf(list.size()));//显示评论个数
        list.add(new commentItemModel("","","","",""));
        adapter = new commentAdapter(context,list);
        listView.setAdapter(adapter);
        //设置listview高度(由于listview与scrollview结合，listview高度需动态更改)
        int listViewHeight = 0;
        int adaptCount = adapter.getCount();
        for(int i=0;i<adaptCount;i++){
            View temp = adapter.getView(i,null,listView);
            temp.measure(0,0);
            listViewHeight += temp.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = this.listView.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.FILL_PARENT;
        layoutParams.height = listViewHeight;
        listView.setLayoutParams(layoutParams);
        scrollView.smoothScrollTo(0,0);//更新完listview后滑动scrollview到顶部
    }
    /**
     * 设置首页轮播广告
     * @param imageUrlList 轮播广告图片的url地址
     */
    private void initBanner(ArrayList<String> imageUrlList) {
        mViewFlow.setAdapter(new ImagePagerAdapter(context, imageUrlList, linkUrlArray).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size());
        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放
    }

    /**
     * 显示弹出框
     * @param parent
     */
    private void showPopupWindow(View parent) {
        customPopwindow = new CustomPopwindow(activity);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        customPopwindow.showAtLocation(parent,  Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
