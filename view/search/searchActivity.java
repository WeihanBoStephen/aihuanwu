package com.zt.agw.view.search;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.home.model.DiscoverItemModel;
import com.zt.agw.view.search.Adapter.resultAdapter;
import com.zt.agw.view.search.model.RecordSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by come on 2016/12/8.
 */
public class searchActivity extends AppCompatActivity {

    private Context context;
    private TextView cancelBt,clearBt;
    private EditText searchEdit;
    private ListView resultListview;
    private MyListView historyListview;

    private SQLiteDatabase db;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);

    private BaseAdapter adapter;

    private ScrollView scrollView;
    private LinearLayout resultLn;

    private List<DiscoverItemModel> resultList = new ArrayList<DiscoverItemModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        context = this;
        initView();
        addListener();
        getData();
    }
    public void addListener(){
        cancelBt.setOnClickListener(btnOnClickListener);
        clearBt.setOnClickListener(btnOnClickListener);
        searchEdit.setOnClickListener(btnOnClickListener);
        resultLn.setOnClickListener(btnOnClickListener);
    }
    public void initView(){
        cancelBt = (TextView)findViewById(R.id.cancel_bt);
        searchEdit = (EditText)findViewById(R.id.search_edit);
        scrollView = (ScrollView)findViewById(R.id.history_scroll);
        clearBt = (TextView)findViewById(R.id.clear_history_bt);
        historyListview = (MyListView) findViewById(R.id.listView);
        resultListview = (ListView)findViewById(R.id.search_result_listview);
        resultLn = (LinearLayout)findViewById(R.id.result_ln);
        // 搜索框的键盘搜索键点击回调
        searchEdit.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
                    searchBtListener();
                }
                return false;
            }
        });
        // 搜索框的文本变化实时监听
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String tempName = searchEdit.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
                queryData(tempName);

            }
        });
        historyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                searchEdit.setText(name);
                searchBtListener();
            }
        });
    }
    public void searchBtListener(){
        resultList.clear();
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
        boolean hasData = hasData(searchEdit.getText().toString().trim());
        if (!hasData) {
            insertData(searchEdit.getText().toString().trim());
            queryData("");
        }
        scrollView.setVisibility(View.GONE);
        //添加搜索按钮按下后调用服务器搜索字段
        if(searchEdit.getText().toString().equals("盆景")){
            resultList.add(new DiscoverItemModel("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg","盆景","45","4.5"));
        }
        setResultList(resultList);
    }
    public void setResultList(List<DiscoverItemModel> list){
        resultAdapter adapter = new resultAdapter(list,context);
        resultListview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void getData(){
        // 第一次进入查询所有的历史记录
        queryData("");
    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.cancel_bt:
                    finish();
                    break;
                case R.id.clear_history_bt:
                    deleteData();
                    queryData("");
                    break;
                case R.id.search_edit:
                    queryData("");
                    break;
                case R.id.result_ln:
                    scrollView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };

    //数据库操作
    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        if(cursor.getCount() == 0){
            scrollView.setVisibility(View.GONE);
        }else{
            scrollView.setVisibility(View.VISIBLE);
            // 创建adapter适配器对象
            adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                    new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            // 设置适配器
            historyListview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }
    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }
    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
}
