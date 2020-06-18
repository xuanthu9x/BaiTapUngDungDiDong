package com.example.orderfood1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ListView lst;
    ItemList[] lists;
    CustomAdapter adapter;
    Button btnXacNhan;

    static String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnXacNhan= (Button) findViewById(R.id.btnXacNhan);
        data();
        adapter=new CustomAdapter(this,R.layout.activity_layout_list_item,lists);
        lst=(ListView)findViewById(R.id.lstTour);
        lst.setAdapter(adapter);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btnClick();
            }
        });
    }

    private void data()
    {
        lists = new ItemList[6];
        lists[0]=new ItemList("Bún đậu mắm tôm","https://bundaumamtom.com","geo:0,0?q=14+Đồng+Đen+phường+14+Hồ+Chí+Minh+Bến+Nghé",R.drawable.food0);
        lists[1]=new ItemList("Cơm tấm","https://www.comtamcali.com/","geo:0,0?q=160+Pasteur+Bến+Nghé+Quận+1+Hồ+Chí+Minh",R.drawable.comtam);
        lists[2]=new ItemList("Gà nướng ò ó o","https://ooo.com.vn/","geo:0,0?q=2/12+Cao+Thắng+phường+5+Quận+3+Hồ+Chí+Minh",R.drawable.ganuong);
        lists[3]=new ItemList("Trà sữa TooCha","http://toocha.vn/","geo:0,0?q=477+An+Dương+Vương+phường+3+Quận+5+Hồ+Chí+Minh",R.drawable.trasua);
        lists[4]=new ItemList("Mỳ cay Sasin","http://micaysasin.vn/","geo:0,0?q=121+Trần+Bình+Trọng+phường+2+Quận+5+Hồ+Chí+Minh",R.drawable.micay);
        lists[5]=new ItemList("Gà rán jollibee","https://jollibee.com.vn/","geo:0,0?q=334+Trần+Hưng+Đạo+Quận+5+Hồ+Chí+Minh",R.drawable.jollibee);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos= lst.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION)
        {
            ItemList p = lists[pos];
            p.setSelected(isChecked);
            if(isChecked ==true)
                Toast.makeText(this, "Bạn đã chọn món " + p.getFoodName(), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Bạn đã bỏ chọn món "+p.getFoodName(),Toast.LENGTH_SHORT).show();
        }
    }
    public void btnClick()  {
        String mon = "";
       int sp= lists.length;
        for(int i=0;i<sp;i++)
        {
            if(lists[i].isSlected()==true)
            mon =mon+","+lists[i].getFoodName();
        }
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.putExtra("sms_body","Bạn chọn đặt các món" + mon);
                    intent.setData(Uri.parse("sms:0336745289"));
                    startActivity(intent);
    }

}
