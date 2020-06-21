package com.example.doitien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1,spinner2;
    ArrayList<String> arrayName;
    ArrayList<String> arrayLichSu;
    ArrayAdapter arrayAdapter,adapterLichSu;
    String from,from2, end,end2,end3,tile="";
    TextView txt;
    EditText edt;
    Button btn;
    ListView lvLS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        txt=(TextView)findViewById(R.id.txt);
        edt=(EditText)findViewById(R.id.edt);
        btn=(Button)findViewById(R.id.btn);
        lvLS=(ListView)findViewById(R.id.listV);
        arrayName=new ArrayList<String>();
        arrayLichSu=new ArrayList<String>();



        new ReadRSS().execute("https://usd.fxexchangerate.com/rss.xml");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayName);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);
        SpinnerClick();
        ButtonClick();

        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText("");
                txt.setText("");
            }
        });
    }
    private void ButtonClick()
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeEnd=from.substring((from.indexOf("(")+1),from.indexOf(")"));
                from2=from.substring(from.indexOf("("),from.indexOf(")")+1);
                codeEnd=codeEnd.toLowerCase();
                end2=end.substring(0,end.indexOf("("));
                end3=end.substring(end.indexOf("("),end.indexOf(")")+1);
                String url="https://"+codeEnd+".fxexchangerate.com/rss.xml";
                new RSS().execute(url);

            }
        });
    }
    private void SpinnerClick()
    {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     from=(String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                end=(String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        }));
    }
    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content= new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader= new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=""; //biến line chứa các dòng dữ liệu đọc được
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        @Override
        protected void  onPostExecute(String s)
        {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document= parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title="",link="";
            String name="",name1="";
            String description="";
            for(int i=0;i<nodeList.getLength();i++)
            {
                Element element= (Element) nodeList.item(i);
                title=parser.getValue(element,"title");
                name=title.substring((title.indexOf("/")+1));
                arrayName.add(name);
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }
    private class RSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content= new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader= new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=""; //biến line chứa các dòng dữ liệu đọc được
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        @Override
        protected void  onPostExecute(String s)
        {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document= parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title="",link="";
            String name="",name1="";
            String description="";
            for (int i =0;i<nodeList.getLength();i++)
            {
                Element element = (Element) nodeList.item(i);
                description = parser.getValue(element,"description");
                description=description.substring(description.indexOf("=")+2,description.length());
                String x=description.substring(description.indexOf(" ")+1,description.length());
                if(end2.indexOf(x)!=-1)
                {
                    tile=description.substring(0,description.indexOf(" "));
                }
            }
            if(edt.length()==0)
            {
                Toast.makeText(MainActivity.this,"Vui lòng nhập số tiền cần đổi", Toast.LENGTH_SHORT).show();
            }
            else
            {
            String a = edt.getText()+"";
            Pattern p = Pattern.compile("^\\d+((\\.(\\d+))|(\\d+))$");
            Matcher m = p.matcher(a);
                if(m.matches())
                {
                    float y=Float.parseFloat(tile);
                    float CanDoi =Float.parseFloat(a);
                    float x = CanDoi*Float.parseFloat(tile);
                    String c=x+"";
                    txt.setText(c);
                    arrayLichSu.add(from+" to "+end+":\n"+CanDoi+" "+from2+" -->"+x+" "+end3);
                    adapterLichSu= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayLichSu);
                    lvLS.setAdapter(adapterLichSu);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Nhập số tiền là số nguyên dương",Toast.LENGTH_SHORT).show();
                    edt.setText("");
                }
            }
        }
    }
}
