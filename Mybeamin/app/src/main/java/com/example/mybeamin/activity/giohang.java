package com.example.mybeamin.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.mybeamin.adapter.tinhtienadapter;
import com.example.mybeamin.db.cuahangDB;
import com.example.mybeamin.db.cuahangDao;
import com.example.mybeamin.db.menuDB;
import com.example.mybeamin.db.menuDao;
import com.example.mybeamin.db.sanphamDB;
import com.example.mybeamin.db.sanphamDao;
import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;
import com.example.mybeamin.model.modelgiohang;
import com.example.mybeamin.model.sanpham;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class giohang extends AppCompatActivity {
    ListView listView;
    ArrayList<modelgiohang> modelgiohangs;
    ArrayList<sanpham> sanphams;
    ArrayList<Integer>name = new ArrayList<>();
    ArrayList<Integer>gia = new ArrayList<>();
    tinhtienadapter tinhtienadapter;
    Toolbar toolbar;
    sanphamDao spDao;
    Button button;
    TextView thanhtien,textViewtamtinh;
    int tongtien,tamtinh;
    cuahangDao chDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        spDao= sanphamDB.getInstance(this).sanphamDao();
        chDao=cuahangDB.getInstance(this).cuahangDao();
        anhxa();
        toolbargiohang();
        getdanhsach();
        getdulieu();
        taomodelgiohang();
        taolist();
        dathang();
       thanhtien();
    }

    private void thanhtien() {

        for(int i=0;i<modelgiohangs.size();i++)
        {
            tamtinh = tamtinh+Integer.parseInt(modelgiohangs.get(i).getGiasp())*modelgiohangs.get(i).getSl();
        }
        tongtien=tamtinh+15000;
        thanhtien.setText(tongtien+"");
        textViewtamtinh.setText(tamtinh+"");
    }

    private void dathang() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms;
                if(name.size()==0)
                {
                    Toast.makeText(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm",Toast.LENGTH_SHORT).show();
                }
                else {
                    sms = "--đặt hàng cùng beamin--\n" + "nhà ăn :" + chDao.getCuahang(modelgiohangs.get(0).Idch).getNamech() + "\n sản phẩm của bạn bao gồm :";
                    for (int i = 0; i < modelgiohangs.size(); i++) {
                        sms = sms + modelgiohangs.get(i).getNamesp() + " số lượng " + modelgiohangs.get(i).getSl() + ", ";
                    }
                    sms = sms + "\ntổng tiền là " + tongtien + "\n" + "Chúc quý khách một ngày tốt lành";

                   /* SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("0582184058", null, sms, null, null);*/

                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "0582184058"));
                    smsIntent.putExtra("sms_body", sms);
                    startActivity(smsIntent);
                }
            }
        });
    }

    private void taomodelgiohang() {
        modelgiohangs=new ArrayList<>();
        for(int i=0;i<name.size();i++)
        {
            sanpham sp = spDao.getsanpham(name.get(i));
            int idsp = sp.getIdsp();
            String namesp=sp.getNamesp();
            String giasp=sp.getGiasp();
            int idch=sp.getIdch();
            int sl=gia.get(i);
            modelgiohang x = new modelgiohang(idsp,namesp,giasp,idch,sl);
            modelgiohangs.add(x);
        }
    }

    private void getdulieu() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            name = bundle.getIntegerArrayList("danhsach");
            gia = bundle.getIntegerArrayList("danhsachsl");
        }
    }

    private void taolist() {
       tinhtienadapter = new tinhtienadapter(modelgiohangs,getApplicationContext());
        listView.setAdapter(tinhtienadapter);
    }

    private void getdanhsach() {
        List<sanpham> x = spDao.getsanphams();
        sanphams=new ArrayList<>(x.size());
        sanphams.addAll(x);

    }
    private void toolbargiohang() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
                Bundle b = new Bundle();
                b.putIntegerArrayList("nameid", name);
                b.putIntegerArrayList("slid", gia);
                it.putExtra("data", b);
                setResult(1, it);
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbagh);
        listView=findViewById(R.id.listtinhtien);
        button = findViewById(R.id.dathang);
        thanhtien=findViewById(R.id.thanhtien);
        textViewtamtinh=findViewById(R.id.tamtinh);
    }
}
