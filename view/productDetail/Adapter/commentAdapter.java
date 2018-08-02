package com.zt.agw.view.productDetail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zt.agw.R;
import com.zt.agw.utils.Tools;
import com.zt.agw.view.productDetail.model.commentItemModel;

import java.util.List;

/**
 * Created by come on 2016/12/7.
 */
public class commentAdapter extends BaseAdapter {

    private Context context;
    private List<commentItemModel> list;
    private LayoutInflater inflater = null;

    public commentAdapter(Context context, List<commentItemModel> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.comment_item,null);
            holder.headIv = (ImageView)convertView.findViewById(R.id.head_iv);
            holder.nameTv = (TextView)convertView.findViewById(R.id.name_tv);
            holder.maleIv = (ImageView)convertView.findViewById(R.id.male_iv);
            holder.commentTv = (TextView)convertView.findViewById(R.id.comment_tv);
            holder.timeTv = (TextView)convertView.findViewById(R.id.time_tv);
            holder.addCommentBt = (Button)convertView.findViewById(R.id.add_comment_bt);
            holder.rl = (RelativeLayout)convertView.findViewById(R.id.comment_item_rl);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        if(position<list.size()-1){
            holder.addCommentBt.setVisibility(View.GONE);
            holder.commentTv.setText(list.get(position).getComment());
            holder.nameTv.setText(list.get(position).getName());
            holder.timeTv.setText(list.get(position).getTime());
            final int p = position;
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools tools = new Tools(context);
                    tools.showToast("评论条目:"+(p+1));
                }
            });
        }else{
            holder.rl.setVisibility(View.GONE);
            holder.addCommentBt.setVisibility(View.VISIBLE);
            holder.headIv.setVisibility(View.GONE);
            holder.addCommentBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools tools = new Tools(context);
                    tools.showToast("添加评论");
                }
            });
        }
        return convertView;
    }
    public static class ViewHolder{
        public RelativeLayout rl;
        public ImageView headIv;
        public TextView nameTv;
        public TextView commentTv;
        public ImageView maleIv;
        public TextView timeTv;
        public Button addCommentBt;
    }
}
