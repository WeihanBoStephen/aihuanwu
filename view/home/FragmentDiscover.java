package com.zt.agw.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.zt.agw.R;
import com.zt.agw.view.home.Adapter.DiscoverAdapter;
import com.zt.agw.view.home.Adapter.ImagePagerAdapter;
import com.zt.agw.view.home.model.DiscoverItemModel;
import com.zt.agw.view.widget.bannerView.CircleFlowIndicator;
import com.zt.agw.view.widget.bannerView.ViewFlow;
import com.zt.agw.view.widget.scrollview.OverScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by come on 2016/12/1.
 */
public class FragmentDiscover extends Fragment implements OverScrollView.OverScrollListener, OverScrollView.OverScrollTinyListener {

    private Context context;
    private View view;
    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private OverScrollView scrollView;
    private ListView listView;
    private List<DiscoverItemModel> list = new ArrayList<DiscoverItemModel>();//listview数据

    private ArrayList<String> imageUrlList = new ArrayList<String>();
    private ArrayList<String> linkUrlArray= new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        view = inflater.inflate(R.layout.fragment_discover, container, false);
        initView();
        getData();
        return view;
    }
    public void initView(){
        mViewFlow = (ViewFlow)view.findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator)view.findViewById(R.id.viewflowindic);
        scrollView = (OverScrollView) view.findViewById(R.id.scrollView1);
        listView = (ListView)view.findViewById(R.id.product_list);
        scrollView.setOverScrollListener(this);
        scrollView.setOverScrollTinyListener(this);
    }

    /**
     * 网络数据获取
     */
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
        //获取产品List中内容
        list.add(new DiscoverItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title1","45","4.5",
                "http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title2","45","4.5"));
        list.add(new DiscoverItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title3","45","4.5",
                "http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title4","45","4.5"));
        list.add(new DiscoverItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title5","45","4.5",
                "http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","title4","45","4.5"));
        setList(list);
        initBanner(imageUrlList);
    }
    public void setList(List<DiscoverItemModel> list){
        DiscoverAdapter adapter = new DiscoverAdapter(list,context);
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
        scrollView.smoothScrollTo(0,0);//更新完listview后回到顶部
    }
    @Override
    public void onStart() {
        super.onStart();
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
     * 下拉刷新接口
     */
    @Override
    public void headerScroll() {
        Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show();
    }

    /**
     * 上拉刷新接口
     */
    @Override
    public void footerScroll() {
        Toast.makeText(context, "上拉刷新", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void scrollDistance(int tinyDistance, int totalDistance) {

    }

    @Override
    public void scrollLoosen() {

    }
}
