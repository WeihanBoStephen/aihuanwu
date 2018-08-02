package com.zt.agw.view.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.view.home.model.MessageItemModel;

import java.util.List;

/**
 * Created by come on 2016/12/5.
 */
public class MessageAdapter extends BaseAdapter{

    Context context;
    List<MessageItemModel> list;
    private LayoutInflater inflater = null;

    public MessageAdapter(Context context , List<MessageItemModel> list){
        this.context = context;
        this.list = list;
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
            convertView = inflater.inflate(R.layout.message_item,null);
            holder.name = (TextView)convertView.findViewById(R.id.txt_name);
            holder.message = (TextView)convertView.findViewById(R.id.txt_message);
            holder.time = (TextView)convertView.findViewById(R.id.txt_time);
            holder.head = (ImageView)convertView.findViewById(R.id.img_head);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        //MyImageLoader imgLoader = MyImageLoader.getInstanImage();
        //imgLoader.ImageLoaderRec(list.get(position).getImgUrl(), holder.head);
        holder.name.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.message.setText(list.get(position).getMessage());
        return convertView;
    }
    public static class ViewHolder{
        public TextView name;
        public TextView message;
        public TextView time;
        public ImageView head;
    }
}
