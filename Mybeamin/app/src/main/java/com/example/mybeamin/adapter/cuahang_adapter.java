package com.example.mybeamin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cuahang_adapter extends RecyclerView.Adapter<cuahang_adapter.ItemHolder> {
    ArrayList<cuahang> cuahang_adapters;
    Context context;

    public cuahang_adapter(ArrayList<cuahang> cuahang_adapters, Context context) {
        this.cuahang_adapters = cuahang_adapters;
        this.context = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.homnaycogi,null);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        cuahang cuahang = cuahang_adapters.get(position);
        holder.Namech.setText(cuahang.getNamech());
        Glide.with(context)
                .load(cuahang.getHinhanh())
                .into(holder.Hinhanh);
    }

    @Override
    public int getItemCount() {
        return cuahang_adapters.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder
    {
        public ImageView Hinhanh;
        public  TextView Namech;
        public ItemHolder(View view)
        {super(view);
            Hinhanh=view.findViewById(R.id.anhch);
            Namech=view.findViewById(R.id.namech);


        }
    }

}
