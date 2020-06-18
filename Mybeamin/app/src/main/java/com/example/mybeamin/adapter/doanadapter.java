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
import com.example.mybeamin.model.sanpham;

import java.util.ArrayList;

public class doanadapter extends BaseAdapter {
    ArrayList<sanpham> sanphamArrayList;
    Context context;

    public doanadapter(ArrayList<sanpham> sanphamArrayList, Context context) {
        this.sanphamArrayList = sanphamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanphamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sanphamArrayList.get(position).getIdsp();
    }
    public  class viewholer
    {
        TextView tenmonan,gia;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        doanadapter.viewholer viewholer=null;
        if(convertView==null)
        {
            viewholer=new viewholer();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dsdoan,null);
            viewholer.tenmonan=convertView.findViewById(R.id.tendoanadapter);
            viewholer.gia=convertView.findViewById(R.id.giadoanadapter);
            convertView.setTag(viewholer);
        }
        else
        {
            viewholer= (doanadapter.viewholer) convertView.getTag();
        }
        sanpham sanpham= (sanpham) getItem(position);
        viewholer.tenmonan.setText(sanpham.getNamesp());
        viewholer.gia.setText(sanpham.getGiasp());
        return convertView;
    }
}
