package com.zt.agw.view.citySelection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.SetHomeTop;
import com.zt.agw.view.citySelection.adapter.SortAdapter;
import com.zt.agw.view.citySelection.bean.RegionInfo;
import com.zt.agw.view.citySelection.bean.SortModel;
import com.zt.agw.view.citySelection.db.RegionDAO;
import com.zt.agw.view.citySelection.utils.CharacterParser;
import com.zt.agw.view.citySelection.utils.ClearEditText;
import com.zt.agw.view.citySelection.utils.PinyinComparator;
import com.zt.agw.view.citySelection.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by come on 2016/12/6.
 */
public class CitySelecterActivity extends Activity
{
    private List<RegionInfo> provinceList;
    private List<RegionInfo> citysList;
    private List<String> provinces;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private TextView locationCityTv;
    private Context context;
    private ImageView returnIv;
    private LinearLayout returnLn;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_selecter);
        context = this;
        initData();
        initViews();
        addListener();
    }
    public void addListener(){
        returnLn.setOnClickListener(btnOnClickListener);
        locationCityTv.setOnClickListener(btnOnClickListener);
    }
    private void initData()
    {
        provinceList = RegionDAO.getProvencesOrCity(1);
        provinceList.addAll(RegionDAO.getProvencesOrCity(2));
        citysList = new ArrayList<RegionInfo>();
        provinces = new ArrayList<String>();
        for (RegionInfo info : provinceList)
        {
            provinces.add(info.getName().trim());
        }
        //mReMenCitys.add(new RegionInfo(180, 13, "苏州"));

    }

    private void initViews()
    {
        SetHomeTop s = new SetHomeTop(this);
        s.setAdd(null,false);
        Resources resources = context.getResources();
        Drawable d = resources.getDrawable(R.drawable.return_bt);
        s.setSearch(d,true);
        s.setPullDown(null,false);
        s.setTitle("选择城市或区域");
        returnIv = (ImageView)findViewById(R.id.search_iv);
        ViewGroup.LayoutParams params = returnIv.getLayoutParams();
        params.width =30;
        returnIv.setLayoutParams(params);
        returnLn = (LinearLayout)findViewById(R.id.search_ln);
        initCityList();

    }
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.search_ln:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
    public void initCityList(){

        View view = View.inflate(this, R.layout.head_city_list, null);
        locationCityTv = (TextView)view.findViewById(R.id.city_title_tv);
        locationCityTv.setText("苏州");

        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener()
        {

            @Override
            public void onTouchingLetterChanged(String s)
            {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1)
                {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.addHeaderView(view);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                if(position >0){
                    //Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position - 1)).getName(), Toast.LENGTH_SHORT).show();
                    hideSoftInput(mClearEditText.getWindowToken());
                    Intent data = new Intent();
                    data.putExtra("location", ((SortModel) adapter.getItem(position - 1)).getName());
                    setResult(1111, data);
                    finish();
                }else{
                    //Toast.makeText(getApplication(), locationCityTv.getText().toString(), Toast.LENGTH_SHORT).show();
                    hideSoftInput(mClearEditText.getWindowToken());
                    Intent data = new Intent();
                    data.putExtra("location",  locationCityTv.getText().toString());
                    setResult(1111, data);
                    finish();
                }


            }
        });

        // SourceDateList =
        // filledData(getResources().getStringArray(R.array.date));
        SourceDateList = filledData(provinceList);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        /*mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String cityName = mReMenCitys.get(position).getName();
                Toast.makeText(getApplication(), cityName, Toast.LENGTH_SHORT).show();
                hideSoftInput(mClearEditText.getWindowToken());
                Intent data = new Intent();
                data.putExtra("cityName", cityName);
                setResult(1110, data);

            }
        });*/
    }
    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<RegionInfo> date)
    {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++)
        {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getName());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]"))
            {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else
            {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr)
    {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr))
        {
            filterDateList = SourceDateList;
        } else
        {
            if (!provinces.contains(filterStr))
            {
                filterDateList.clear();
                for (SortModel sortModel : SourceDateList)
                {
                    String name = sortModel.getName();
                    if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString()))
                    {
                        filterDateList.add(sortModel);
                    }
                }
            } else
            {
                filterDateList.clear();
                for (int i = 0; i < provinceList.size(); i++)
                {
                    String name = provinceList.get(i).getName();
                    if (name.equals(filterStr))
                    {
                        filterDateList.addAll(filledData(RegionDAO.getProvencesOrCityOnParent(provinceList.get(i).getId())));
                    }
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    protected void hideSoftInput(IBinder token)
    {
        if (token != null)
        {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
