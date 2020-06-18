package com.example.mybeamin.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Dao;

import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.adapter.cuahang_adapter;
import com.example.mybeamin.adapter.danhsachcuahang_adapter;
import com.example.mybeamin.db.cuahangDB;
import com.example.mybeamin.db.cuahangDao;
import com.example.mybeamin.db.menuDB;
import com.example.mybeamin.db.menuDao;
import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.mybeamin.adapter.menu_adapter;
import com.squareup.picasso.Picasso;

public class store extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    danhsachcuahang_adapter danhsachcuahangadapter;
    ArrayList<cuahang> cuahangs;
    TextView textView;
    int idtheloai=-1;
    cuahangDao Dao;
    String theloaii;
    DrawerLayout drawerLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Dao= cuahangDB.getInstance(this).cuahangDao();
        anhxa();
        getidtheloai();
        actiontoolbar();
        if(idtheloai==-1)
        {
            taolistviewsearch();
        }
        else {
            taolistview();
        }
        selectcuahang();
    }

    private void selectcuahang() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(store.this,layoutmonan.class);
                intent.putExtra("idcuahang", cuahangs.get(position).getIdch());
                startActivity(intent);
                //drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void taolistviewsearch() {

        List<cuahang> dulie = getduilieucuahang();
        ArrayList<cuahang> x = new ArrayList<>(dulie.size());
        x.addAll(dulie);
        ArrayList<cuahang> ds = new ArrayList<>();
        for(int i =0;i<x.size();i++)
        {
            if(x.get(i).getNamech().indexOf(theloaii)>0)
            {
                ds.add(x.get(i));
            }
        }
        danhsachcuahangadapter = new danhsachcuahang_adapter(ds,getApplicationContext());
        listView.setAdapter(danhsachcuahangadapter);
    }

    private void taolistview() {
        List<cuahang> dulie = getduilieucuahang();
        ArrayList<cuahang> x = new ArrayList<>(dulie.size());
        x.addAll(dulie);
        ArrayList<cuahang> ds = new ArrayList<>();
        for(int i =0;i<x.size();i++)
        {
            if(x.get(i).getTheloai()==idtheloai)
            {
                ds.add(x.get(i));
            }
        }
        cuahangs = new ArrayList<>();
        cuahangs.addAll(ds);
        danhsachcuahangadapter = new danhsachcuahang_adapter(ds,getApplicationContext());
        listView.setAdapter(danhsachcuahangadapter);
    }

    private  List getduilieucuahang()
    {
        List<cuahang> x = new ArrayList<>();
        x=Dao.getcuahangs();
        return x ;
    }

    private void actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getidtheloai() {
        idtheloai=getIntent().getIntExtra("idtheloai",-1);
        theloaii=getIntent().getStringExtra("search");
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbasp);
        listView=findViewById(R.id.listviewcuahang);
        cuahangs= new ArrayList<>();
        danhsachcuahangadapter=new danhsachcuahang_adapter(cuahangs,getApplicationContext());
        listView.setAdapter(danhsachcuahangadapter);
        drawerLayout= findViewById(R.id.drawermonan);

    }
}
