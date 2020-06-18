package com.example.orderfood1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.Serializable;

import static android.content.Intent.*;

public class CustomAdapter extends ArrayAdapter<ItemList> {
    Context context;
    ItemList[] lists;
    String Data[];

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ItemList[] items) {
        super(context, R.layout.activity_layout_list_item, items);
        this.context=context;
        this.lists=items;
    }
    private View.OnClickListener itemClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
 //               case R.id.FoodName:
//                    Intent intent= new Intent(LayoutListItem.this, WebView.class);
//                    ItemList item= new ItemList();
//                    intent.putExtra("dulieu",(Serializable) item);
//                    String fl = (String) v.getTag();
//                    Toast.makeText(context, fl.toString(), Toast.LENGTH_SHORT).show();
//                    showView(fl.toString());
//                    return;
                case R.id.FoodImages:
                    ImageView fi = (ImageView) v;
                    String floc = (String) fi.getTag();
                    Toast.makeText(context, fi.toString(), Toast.LENGTH_SHORT).show();
                    showView(floc.toString());
                    return;
            }
        }
    };

        private void showView(String v) {
            Intent intent = new Intent(ACTION_VIEW, Uri.parse(v));
            if (intent.resolveActivity(context.getPackageManager()) != null)
                context.startActivity(intent);
        }
        @Override
    public View getView (final int position, final View convertView, ViewGroup parent)
        {
            LayoutInflater inflater= ((Activity)context).getLayoutInflater();
            View row = inflater.inflate(R.layout.activity_layout_list_item,null);
            final TextView FoodName = (TextView) row.findViewById(R.id.FoodName);
            ImageView FoodImages =(ImageView) row.findViewById(R.id.FoodImages);
            TextView FoodLink= (TextView) row.findViewById(R.id.FoodLink);
            TextView FoodLocation= (TextView) row.findViewById(R.id.FoodLocation);
            final CheckBox checkbox=(CheckBox) row.findViewById(R.id.checkbox);
            final android.webkit.WebView webV = row.findViewById(R.id.webview);

            FoodName.setText(lists[position].getFoodName().toString());
            FoodLink.setText(lists[position].getFoodLink().toString());
            FoodName.setTag(FoodLink.getText().toString());

            FoodImages.setImageResource(lists[position].getFoodImages());
            FoodLocation.setText(lists[position].getFoodLocation().toString());
            FoodImages.setTag(FoodLocation.getText().toString());

            checkbox.setOnCheckedChangeListener((MainActivity) context);
            checkbox.setChecked(lists[position].isSlected());
            checkbox.setTag(lists[position]);



            FoodImages.setOnClickListener(itemClickListener);
            FoodName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(convertView.getContext(), WebViewActivity.class);
                    intent.putExtra("dulieu", lists[position]);
                    if (intent.resolveActivity(context.getPackageManager()) != null)
                        context.startActivity(intent);
                }
            });
            return row;
        }
}
