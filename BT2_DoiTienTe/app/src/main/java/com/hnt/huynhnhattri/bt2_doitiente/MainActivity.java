package com.hnt.huynhnhattri.bt2_doitiente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Spinner ListNationalFirst;
    public Spinner ListNationalSecond;
    public Button Exchange;
    public EditText MoneyStart;
    public TextView MoneyEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute("https://www.fxexchangerate.com/currency-converter-rss-feed.html");
            }
        });

    }
    class ReadData extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            return ReadContentFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            String[] cutStartSelect = s.split("<select  name=\"currencyfrom\" aria-label=\"Currency From\">");
            String[] cutEndSelect = cutStartSelect[1].split("</select>");
            ganDuLieu(bienDoiDuLieuXML(cutEndSelect[0]));
            super.onPostExecute(s);
        }
    }
    class ReadDataExchangeRate extends AsyncTask<String, Integer, String> {
        double moneyFirst;
        String codeFirst;
        String codeSeconde;
        ReadDataExchangeRate(double moneyFirst,String codeFirst, String codeSeconde){
            this.moneyFirst = moneyFirst;
            this.codeFirst = codeFirst.toUpperCase();
            this.codeSeconde = codeSeconde.toUpperCase();
        }
        @Override
        protected String doInBackground(String... strings) {
            return ReadContentFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("In",s);
            String catChuoi = s.substring(s.indexOf("1.00 " + this.codeFirst + " = "));
            String[] chiaChuoi = catChuoi.split(" "+this.codeSeconde+"<br/>");
            String[] layMoney = chiaChuoi[0].split(" = ");
            double tyGia = Double.parseDouble(layMoney[1]);
            MoneyEnd.setText((this.moneyFirst * tyGia) + "");
            super.onPostExecute(s);
        }
    }

    private String congChuoiNational(String[] chuoi){
        String s = chuoi[1];
        int length = chuoi.length;
        for (int i = 2 ; i < length; i++) {
            s += " " + chuoi[i];
        }
        return s;
    }
    private void ganDanhSach(ArrayList<String> nationall){
        this.ListNationalFirst = findViewById(R.id.NationalFirst);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nationall);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.ListNationalFirst.setAdapter(arrayAdapter);
        this.ListNationalSecond = findViewById(R.id.NationalSecond);
        this.ListNationalSecond.setAdapter(arrayAdapter);
    }

    private void ganDuLieu(String duLieuXMLBiBienDoi){
        String[] catChuoi = duLieuXMLBiBienDoi.split("@");
        String[] value;
        final ArrayList<String> nationall = new ArrayList();
        int lengthcatChuoi = catChuoi.length;
        String[] national  = new String[lengthcatChuoi];
        final String[] theValueCurrency = new String[lengthcatChuoi];
        int LocationListNationalFirst;

        for(int i = 0; i < lengthcatChuoi; i++){
            value = catChuoi[i].split(" ");
            theValueCurrency[i] = value[0];
            national[i] = this.congChuoiNational(value);
            nationall.add(national[i]);
        }

        this.Exchange = findViewById(R.id.Exchange);
       // this.Exchange.setText("Chuyển đổi");
        this.MoneyStart = findViewById(R.id.MoneyStart);
        this.MoneyEnd = findViewById(R.id.MoneyEnd);

        ganDanhSach(nationall);
        this.Exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeNationalFirst = theValueCurrency[ListNationalFirst.getSelectedItemPosition()].toLowerCase();
                String codeNationalSecond = theValueCurrency[ListNationalSecond.getSelectedItemPosition()].toLowerCase();
                try {
                    double moneyFirst = Double.parseDouble(MoneyStart.getText() + "");
                    if(codeNationalFirst.contains(codeNationalSecond)){
                        MoneyEnd.setText(MoneyStart.getText());
                    }else{
                        ReadDataExchangeRate b = new ReadDataExchangeRate(moneyFirst,codeNationalFirst,codeNationalSecond);
                        b.execute("https://" + codeNationalFirst + ".fxexchangerate.com/" + codeNationalSecond + ".xml");
                    }
                }catch(Exception ex){
                    Toast.makeText(MainActivity.this,"Làm ơn nhập số tiền vào!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String bienDoiDuLieuXML(String XML){
        String[] loaibo = new String[]{
                "<option disabled='disabled'>-----------------------------</option>",
                " selected='selected'",
                "<option value='",
                "'>",
                "</option>",
                "@@"
        };
        String[] thayThe = new String[]{
            "", "", "@", " ", "@", "@"
        };
        int length = loaibo.length;
        for(int i = 0; i < length; i++){
            XML = XML.replaceAll(loaibo[i],thayThe[i]);
        }
        XML = XML.substring(1);
        return XML.substring(0,XML.length() - 1);
    }
    private String ReadContentFromURL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            URLConnection urlConnection = new URL(theUrl).openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}

