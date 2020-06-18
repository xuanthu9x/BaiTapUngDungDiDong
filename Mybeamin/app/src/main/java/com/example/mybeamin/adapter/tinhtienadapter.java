package com.example.mybeamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.model.menu;
import com.example.mybeamin.model.modelgiohang;
import com.example.mybeamin.model.sanpham;

import java.util.ArrayList;

public class tinhtienadapter extends BaseAdapter {
   ArrayList<modelgiohang> modelgiohangs;
    Context context;

    public tinhtienadapter(ArrayList<modelgiohang> modelgiohangs, Context context) {
        this.modelgiohangs = modelgiohangs;
        this.context = context;
    }

    public ArrayList<modelgiohang> getModelgiohangs() {
        return modelgiohangs;
    }

    public void setModelgiohangs(ArrayList<modelgiohang> modelgiohangs) {
        this.modelgiohangs = modelgiohangs;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelgiohangs.size();
    }

    @Override
    public Object getItem(int position) {
        return modelgiohangs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modelgiohangs.get(position).Idsp;
    }

    public  class viewholer
    {
        TextView tenmonan,gia,sl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholer viewholer=null;
        if(convertView==null)
        {
            viewholer=new viewholer();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dsthanhtoan,null);
            viewholer.tenmonan=convertView.findViewById(R.id.tengiohangxx);
            viewholer.gia=convertView.findViewById(R.id.giagiohangxx);
            viewholer.sl=convertView.findViewById(R.id.slgiohangxx);
            convertView.setTag(viewholer);
        }
        else
        {
            viewholer= (tinhtienadapter.viewholer) convertView.getTag();
        }
        modelgiohang sanpham= (modelgiohang) getItem(position);
        viewholer.tenmonan.setText(sanpham.getNamesp());
        viewholer.sl.setText(sanpham.getSl()+"");
        int tien = Integer.parseInt(sanpham.getGiasp()) * sanpham.getSl();
        viewholer.gia.setText(tien+"");
        return convertView;
    }
}
