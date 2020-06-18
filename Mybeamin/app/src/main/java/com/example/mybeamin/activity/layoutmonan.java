package com.example.mybeamin.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Dao;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.mybeamin.R;
import com.example.mybeamin.adapter.cuahang_adapter;
import com.example.mybeamin.adapter.danhsachcuahang_adapter;
import com.example.mybeamin.adapter.doanadapter;
import com.example.mybeamin.db.cuahangDB;
import com.example.mybeamin.db.cuahangDao;
import com.example.mybeamin.db.menuDB;
import com.example.mybeamin.db.menuDao;
import com.example.mybeamin.db.sanphamDB;
import com.example.mybeamin.db.sanphamDao;
import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;
import com.example.mybeamin.model.sanpham;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.mybeamin.adapter.menu_adapter;
import com.squareup.picasso.Picasso;


public class layoutmonan extends AppCompatActivity {

    GoogleMap googleMapp;
    Toolbar toolbar;
    ImageView imageView;
    TextView textView,tengiohang,giatien,soluong;
    ListView listView;
    Button giam,tang,dathang,truycap;
    doanadapter doanadapter;
    ArrayList<sanpham> sanphams;
    int idch=-1;
    sanphamDao spDao;
    cuahangDao chDao;
    ArrayList<Integer> dsid = new ArrayList<>();
    ArrayList<Integer> dssl = new ArrayList<>();
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    cuahang m = new cuahang();
    int dcchon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutmonan);
        spDao= sanphamDB.getInstance(this).sanphamDao();
        chDao = cuahangDB.getInstance(this).cuahangDao();
        anhxa();
        actiontoolba();
        getidch();
        //seeddoan();
        taolistview();
        actionthem();
        tang();
        giam();
        themgiohang();
        truycapweb();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            Bundle b = data.getBundleExtra("data");
            dsid = b.getIntegerArrayList("nameid");
            dssl=b.getIntegerArrayList("slid");


    }

    private void truycapweb() {

        truycap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(layoutmonan.this,webview.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",m.getEnd());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void themgiohang() {
        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int o=1;
                int y = Integer.parseInt(soluong.getText().toString());
                for(int i =0;i<dsid.size();i++)
                {
                    if(dsid.get(i)==dcchon)
                    {
                        dssl.set(i,dssl.get(i)+y);
                        o=0;
                        break;
                    }
                }
                if(o==1)
                {
               dsid.add(dcchon);
               dssl.add(y);}
               drawerLayout.closeDrawer(GravityCompat.START);
               thongbao();

            }
        });
    }
    private void thongbao()
    {
        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

    }
    private void giam() {
        giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(giatien.getText().toString());
                int y = Integer.parseInt(soluong.getText().toString());
                if(y>1) {
                    x = x - x / y;
                    y = y-1;
                    giatien.setText(x + "");
                    soluong.setText(y + "");
                }
            }
        });
    }
    private void tang() {
        tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(giatien.getText().toString());
                int y = Integer.parseInt(soluong.getText().toString());
                x = x/y+x;
                y=y+1;
                giatien.setText(x+"");
                soluong.setText(y+"");
            }
        });
    }
    private void seeddoan() {
        /*List<sanpham> x = getdulieusanpham();
        for(int i =0;i<x.size();i++)
        {
            spDao.deletesanpham(x.get(i));
        }*/
        String name="Trà xoài tươi kem sữa";
        String gia="51000";
        sanpham k = new sanpham(name,gia,idch);
        spDao.insertsanpham(k);
    }
    private void actionthem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tengiohang.setText(sanphams.get(position).getNamesp());
                giatien.setText(sanphams.get(position).getGiasp());
                soluong.setText("1");
                dcchon=sanphams.get(position).getIdsp();
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private List<cuahang> getdulieucuahang()
    {
        return  chDao.getcuahangs();
    }
    private List<sanpham> getdulieusanpham()
    {
        return spDao.getsanphams();
    }
    private void taolistview() {
        List<sanpham> dulie = getdulieusanpham();
        ArrayList<sanpham> x = new ArrayList<>(dulie.size());
        x.addAll(dulie);
        ArrayList<sanpham> ds = new ArrayList<>();
        for(int i =0;i<x.size();i++)
        {
            if(x.get(i).getIdch()==idch)
            {
                ds.add(x.get(i));
            }
        }

        List<cuahang> dsch =getdulieucuahang();
        for(int i =0;i<dsch.size();i++)
        {
            if(dsch.get(i).getIdch()==idch)
            {
                m=dsch.get(i);
                break;
            }
        }
        textView.setText(m.getNamech());
        Glide.with(this)
                .load(m.getHinhanh())
                .into(imageView);


        sanphams = new ArrayList<>();
        sanphams.addAll(ds);
        doanadapter = new doanadapter(ds,getApplicationContext());
        listView.setAdapter(doanadapter);
    }

    private void getidch() {
        idch=getIntent().getIntExtra("idcuahang",-1);
    }

    private void actiontoolba() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.controlbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.controlbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.map:
                String ggmap="geo:0,0?q="+m.getDiachi();
                Intent intentt = new Intent(Intent.ACTION_VIEW, Uri.parse(ggmap));
                if (intentt.resolveActivity(this.getPackageManager())!=null)
                this.startActivity(intentt);
                break;
            case R.id.gio:
                Intent intent=new Intent(layoutmonan.this,giohang.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("danhsach",dsid);
                bundle.putIntegerArrayList("danhsachsl",dssl);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void anhxa() {

        tengiohang=findViewById(R.id.tengiohang);
        giatien=findViewById(R.id.giatien);
        soluong=findViewById(R.id.soluong);
        toolbar = findViewById(R.id.toolbamonan);
        imageView = findViewById(R.id.anhbiadoan);
        textView=findViewById(R.id.tenquan);
        listView=findViewById(R.id.listviewmonan);
        navigationView= findViewById(R.id.navimonan);
        drawerLayout = findViewById(R.id.drawerdsmonan);
        tang = findViewById(R.id.tang);
        giam = findViewById(R.id.giam);
        dathang = findViewById(R.id.themgiohang);
        truycap = findViewById(R.id.truycaptrangwebmonan);
    }
}
