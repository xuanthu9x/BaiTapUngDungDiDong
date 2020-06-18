package com.example.listcountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayName;
    ArrayList<Country> listCountries;
    Spinner spinnerName;
    ArrayAdapter arrayAdapter;
    EditText txtSearch;
    TextView QuocGia, quocgia;
    TextView DanSo, sodan;
    TextView DienTich, area;
    Button btnTim;
    ImageView flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listCountries= new ArrayList<>();
        arrayName= new ArrayList<String>();
        spinnerName= (Spinner) findViewById(R.id.spinner);
        AnhXa();

        String url = "http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&style=full&username=caoth&fbclid=IwAR2Wh2XxUkeoaMpIs8btsHX_2z-A0g7lrwKkSMb0k56HfbojTuC_nVto8L8";
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerName.setAdapter(arrayAdapter);
        SpinClick();
        ButtonClick();
        traJSONvemang(url);

    }
    private  void AnhXa()
    {
        txtSearch=(EditText) findViewById(R.id.txtSearch);
        btnTim=(Button)findViewById(R.id.btnTim);
        QuocGia=(TextView)findViewById(R.id.txtQuocGia);
        quocgia=(TextView)findViewById(R.id.txtName);
        DanSo=(TextView)findViewById(R.id.txtDanSo);
        sodan=(TextView)findViewById(R.id.txtSoDan);
        DienTich=(TextView)findViewById(R.id.txtDienTich);
        area=(TextView)findViewById(R.id.txtArea);
        flag=(ImageView)findViewById(R.id.flag);
    }
    private  void ButtonClick()
    {
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= txtSearch.getText().toString();
                int flag=0;
                for(Country c : listCountries)
                {
                    if(name.equalsIgnoreCase(c.getName()))
                    {
                        flag=1;
                        quocgia.setText(c.getName());
                        sodan.setText(c.getPopulation());
                        area.setText(c.getArea());
                        String url1 ="https://img.geonames.org/flags/x/"+c.getCountryCode().toLowerCase()+".gif";
                        loadhinh(url1);
                        break;
                    }
                }
                if(flag==0)
                    Toast.makeText(MainActivity.this,"Quoc gia"+txtSearch.getText().toString()+" khong ton tai" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SpinClick()
    {
        spinnerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this, arrayName.get(i),Toast.LENGTH_SHORT).show();
                quocgia.setText(listCountries.get(i).getName());
                sodan.setText(listCountries.get(i).getPopulation());
                area.setText(listCountries.get(i).getArea());
                txtSearch.setText("");
                String url1 ="https://img.geonames.org/flags/x/"+listCountries.get(i).getCountryCode().toLowerCase()+".gif";
                loadhinh(url1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                quocgia.setText("");
                sodan.setText("");
                area.setText("");
            }
        });
    }
    public  void loadhinh(String url)
    {
        ImageRequest imageRequest= new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                flag.setImageBitmap(response);
            }
        },0,0,null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                flag.setImageResource(R.mipmap.ic_launcher);
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
                                listCountries.add(new Country(name,code,danso,area));
                                arrayName.add(name);
                            }
                            arrayAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi" +error);
                    }
                }
        );

        jsonArrayRequest.setShouldCache(false);
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }


}
