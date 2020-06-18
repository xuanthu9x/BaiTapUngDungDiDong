package com.example.mybeamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.model.menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class menu_adapter extends BaseAdapter {
    ArrayList<menu> menuArrayList;
    Context context;

    public menu_adapter(ArrayList<menu> menuArrayList, Context context) {
        this.menuArrayList = menuArrayList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return menuArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class viewholer
    {
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholer viewholer=null;
        if(convertView==null)
        {
            viewholer=new viewholer();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.danhsachcuahang,null);
            viewholer.textView=convertView.findViewById(R.id.tencuahang);
            viewholer.imageView=convertView.findViewById(R.id.anhcuahang);
            convertView.setTag(viewholer);
        }
        else
        {
            viewholer= (viewholer) convertView.getTag();
        }
        menu menu= (menu) getItem(position);
        viewholer.textView.setText(menu.getTenmenu());
        Glide.with(context)
                .load(menu.getAnhmenu())
                .into(viewholer.imageView);
        return convertView;
    }
}
