package com.example.mybeamin.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.model.cuahang;

import java.util.ArrayList;

public class danhsachcuahang_adapter extends BaseAdapter {
    ArrayList<cuahang> cuahang_adapters;
    Context context;

    public danhsachcuahang_adapter(ArrayList<cuahang> cuahang_adapters, Context context) {
        this.cuahang_adapters = cuahang_adapters;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cuahang_adapters.size();
    }

    @Override
    public Object getItem(int position) {
        return cuahang_adapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
        TextView tencuahang,gioithieucuahang;
        ImageView anhcuahang;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.listviewcuahang,null);
            viewHolder.anhcuahang=convertView.findViewById(R.id.anhcuahang);
            viewHolder.tencuahang=convertView.findViewById(R.id.tencuahang);
            viewHolder.gioithieucuahang=convertView.findViewById(R.id.gioithieucuahang);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        cuahang cuahang= (com.example.mybeamin.model.cuahang) getItem(position);
        viewHolder.tencuahang.setText(cuahang.getNamech());
        viewHolder.gioithieucuahang.setMaxLines(3);
        viewHolder.gioithieucuahang.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.gioithieucuahang.setText(cuahang.getGioithieu());
        Glide.with(context)
                .load(cuahang.getHinhanh())
                .into(viewHolder.anhcuahang);
        return convertView;
    }
}
