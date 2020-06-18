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
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.example.mybeamin.db.cuahangDB;
import com.example.mybeamin.db.cuahangDao;
import com.example.mybeamin.db.menuDB;
import com.example.mybeamin.db.menuDao;
import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;
import com.example.mybeamin.until.logweb;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.mybeamin.adapter.menu_adapter;
import com.squareup.picasso.Picasso;

public class webview extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;
    EditText addressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        anhxa();
        getdulieu();
        actiontoolba();
        goUrl();
    }

    private void getdulieu() {
        String urlx="";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
           urlx = bundle.getString("url","");
        }
        addressBar.setText(urlx);
        webView.setWebViewClient(new logweb(addressBar));
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbawb);
        webView =(WebView)findViewById(R.id.webView);
        addressBar=findViewById(R.id.addressBar);

    }

    private void actiontoolba() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void goUrl()  {
        String url = addressBar.getText().toString().trim();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
}
