package com.example.networkjson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgNuoc;
    TextView txtTen;
    TextView txtDanso;
    TextView txtArea;


    ArrayList<String> arrayTen;
    ArrayList<QuocGia> arrayQuocgia;
    Spinner spinTen;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayQuocgia = new ArrayList<>();
        arrayTen = new ArrayList<>();
        AnhXa();
        spinTen = (Spinner) findViewById(R.id.spinner);

        String url = "http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&style=full&username=caoth&fbclid=IwAR2Wh2XxUkeoaMpIs8btsHX_2z-A0g7lrwKkSMb0k56HfbojTuC_nVto8L8";
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayTen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTen.setAdapter(adapter);
        SpinClick();
        traJSONvemang(url);

    }

    private void SpinClick()
    {
        spinTen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this, arrayTen.get(i),Toast.LENGTH_SHORT).show();
                txtTen.setText(arrayQuocgia.get(i).getName());
                txtDanso.setText(arrayQuocgia.get(i).getDanso());
                txtArea.setText(arrayQuocgia.get(i).getArea());
                loadHinh("https://img.geonames.org/flags/x/"+arrayQuocgia.get(i).getCode().toLowerCase()+".gif");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtTen.setText("");
                txtDanso.setText("");
                txtArea.setText("");
            }
        });
    }

    private void AnhXa()
    {
        txtTen = (TextView) findViewById(R.id.txtTen1);
        txtDanso = (TextView) findViewById(R.id.txtDanso1);
        txtArea = (TextView) findViewById(R.id.txtArea1);
    }

    public void loadHinh(String url)
    {
        imgNuoc = (ImageView) findViewById(R.id.imgNuoc);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgNuoc.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imgNuoc.setImageResource(R.mipmap.ic_launcher);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(imageRequest);
    }

    public void traJSONvemang(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray geo = response.getJSONArray("geonames");
                            for(int i=0; i<geo.length();i++)
                            {
                                JSONObject a = geo.getJSONObject(i);
                                String name = a.getString("countryName");
                                String code = a.getString("countryCode");
                                String danso = a.getString("population");
                                String area = a.getString("areaInSqKm");
                                arrayQuocgia.add(new QuocGia(name,code,danso,area));
                                arrayTen.add(name);
                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Lỗi!",Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi" +error);
                    }
                }
        );

        jsonArrayRequest.setShouldCache(false);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }

}
