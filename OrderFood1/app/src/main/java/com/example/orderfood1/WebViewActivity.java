package com.example.orderfood1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {


    WebView webview;
    EditText edt;
    ImageButton btnBack;
    ImageButton btnNext;
    ImageButton btnReload;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = (WebView) findViewById(R.id.webview);
        edt= (EditText) findViewById(R.id.edtText);
        webview.setWebViewClient(new WebViewClient());
        btnBack= (ImageButton) findViewById( R.id.ibtnBack);
        btnNext=(ImageButton)findViewById(R.id.ibtnNext);
        btnReload=(ImageButton) findViewById(R.id.ibtnReload);

       // ViewGroup.LayoutParams params = main.getLay
        final Intent intent= getIntent();
        ItemList item = (ItemList) intent.getSerializableExtra("dulieu");
      //  String url = intent.getStringExtra("dulieu");
         url=item.getFoodLink();
        edt.setText(url);
        webview.loadUrl(url);
        url=webview.getUrl();
         btnBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(webview.canGoBack()) {
                     webview.goBack();
                     edt.setText(webview.getUrl());
                 }
                 else {
                     Intent intent1 = new Intent(WebViewActivity.this, MainActivity.class);
                     startActivity(intent1);
                     //Toast.makeText(WebViewActivity.this, "Không tồn tại trang trước để trở về", Toast.LENGTH_SHORT);
                 }
             }
         });
         btnNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (webview.canGoForward()) {
                     webview.goForward();
                     edt.setText(webview.getUrl());
                 } else
                     Toast.makeText(WebViewActivity.this, "Không tồn tại trang sau để tiến tới", Toast.LENGTH_SHORT).show();
             }
         });
         btnReload.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 webview.reload();
                 edt.setText(webview.getUrl());
             }
         });
        WebSettings webSettings = webview.getSettings();
        webSettings.setBuiltInZoomControls(true);

    }
}
