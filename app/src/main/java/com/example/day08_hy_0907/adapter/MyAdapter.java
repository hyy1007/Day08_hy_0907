package com.example.day08_hy_0907.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day08_hy_0907.R;
import com.example.day08_hy_0907.bean.Bean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Bean.DataBean> list;
    private Context context;

    public MyAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.item_layout,null );
            holder=new ViewHolder();
            holder.text_name=convertView.findViewById(R.id.text_name);
            holder.text_aprice=convertView.findViewById(R.id.text_aprice);
            holder.text_nprice=convertView.findViewById(R.id.text_nprice);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //赋值
        holder.text_name.setText(list.get(position).getTitle());
        holder.text_aprice.setText(list.get(position).getPrice());
        holder.text_nprice.setText((int) list.get(position).getBargainPrice());
        holder.img_view.setImageResource(list.get(position).getImages());
        return convertView;
    }
    public class ViewHolder{
        private TextView text_name,text_aprice,text_nprice;
        private ImageView img_view;
    }
}
