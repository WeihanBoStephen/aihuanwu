package com.zt.agw.view.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zt.agw.R;
import com.zt.agw.view.home.model.DiscoverItemModel;
import com.zt.agw.view.productDetail.productDetailActivity;
import com.zt.agw.view.widget.image.XCRoundRectImageView;

import java.util.List;

/**
 * Created by come on 2016/12/2.
 */
public class DiscoverAdapter extends BaseAdapter {

    private List<DiscoverItemModel> list;
    private Context context;
    private LayoutInflater inflater = null;

    private ImageLoader load1,load2;
    public DiscoverAdapter(List<DiscoverItemModel> list , Context context){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //MyImageLoader imgLoader = MyImageLoader.getInstanImage();
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.discover_item,null);
            holder.iv1 = (XCRoundRectImageView)convertView.findViewById(R.id.item1_iv1);
            holder.iv2 = (XCRoundRectImageView)convertView.findViewById(R.id.item2_iv1);
            holder.title1 = (TextView)convertView.findViewById(R.id.item1_title_tv);
            holder.title2 = (TextView)convertView.findViewById(R.id.item2_title_tv);
            holder.goldTv1 = (TextView)convertView.findViewById(R.id.item1_gold_tv);
            holder.goldTv2 = (TextView)convertView.findViewById(R.id.item2_gold_tv);
            holder.pinTv1 = (TextView)convertView.findViewById(R.id.item1_pin_tv);
            holder.pinTv2 = (TextView)convertView.findViewById(R.id.item2_pin_tv);
            holder.ln1 = (LinearLayout)convertView.findViewById(R.id.item1_ln);
            holder.ln2 = (LinearLayout)convertView.findViewById(R.id.item2_ln);
            holder.rl02 = (RelativeLayout) convertView.findViewById(R.id.relative02);
            holder.titleLinear02 = (LinearLayout)convertView.findViewById(R.id.item2_title_linear);
           convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        if(list.get(position).getUrl2() != null){
            //设置标题，价格，距离
            holder.title1.setText(list.get(position).getTitle1());
            holder.goldTv1.setText(list.get(position).getGold1());
            holder.pinTv1.setText(list.get(position).getPin1());
            holder.title2.setText(list.get(position).getTitle2());
            holder.goldTv2.setText(list.get(position).getGold2());
            holder.pinTv2.setText(list.get(position).getPin2());
            //设置图片
            holder.iv1.setImageResource(R.drawable.head);
            holder.iv2.setImageResource(R.drawable.head);
            //设置监听器
            holder.ln1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, productDetailActivity.class);
                    context.startActivity(intent);
                }
            });
            holder.ln2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, productDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }else{
            //隐藏横向第二个item
            holder.rl02.setVisibility(View.GONE);
            holder.titleLinear02.setVisibility(View.GONE);
            holder.iv2.setVisibility(View.GONE);
            //设置标题，价格，距离
            holder.title1.setText(list.get(position).getTitle1());
            holder.goldTv1.setText(list.get(position).getGold1());
            holder.pinTv1.setText(list.get(position).getPin1());
            //设置图片
            holder.iv1.setImageResource(R.drawable.head);
            //设置监听器
            holder.ln1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, productDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    public static class ViewHolder{
        public LinearLayout titleLinear02;
        public RelativeLayout rl02;
        public LinearLayout ln1;
        public LinearLayout ln2;
        public XCRoundRectImageView iv1;
        public XCRoundRectImageView iv2;
        public TextView title1;
        public TextView title2;
        public TextView goldTv1;
        public TextView goldTv2;
        public TextView pinTv1;
        public TextView pinTv2;
    }
}
