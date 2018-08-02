package com.zt.agw.view.home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zt.agw.R;
import com.zt.agw.view.home.Adapter.MessageAdapter;
import com.zt.agw.view.home.model.MessageItemModel;
import com.zt.agw.view.widget.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by come on 2016/12/1.
 */
public class FragmentMessage extends Fragment {

    private Context context;
    private MyListView listView;
    private View view;
    private  List<MessageItemModel> list = new ArrayList<MessageItemModel>();
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        view = inflater.inflate(R.layout.fragment_message, container, false);
        initView();
        getData();
        setListener();
        return view;
    }
    public void setListener(){
        //下拉刷新接口
        listView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });
    }
    public void initView(){
        listView = (MyListView)view.findViewById(R.id.message_listview);
    }
    public void getData(){
        list.add(new MessageItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","香香","需要么？","刚刚"));
        list.add(new MessageItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","香香","需要么？","刚刚"));
        list.add(new MessageItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","香香","需要么？","刚刚"));
        list.add(new MessageItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","香香","需要么？","刚刚"));
        setList(list);
    }
    public void setList(List<MessageItemModel> list){
        adapter = new MessageAdapter(context,list);
        listView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();

    }
}
