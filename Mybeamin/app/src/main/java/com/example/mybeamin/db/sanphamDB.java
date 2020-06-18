package com.example.mybeamin.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mybeamin.model.sanpham;

@Database(entities = sanpham.class, version = 1)
public abstract class sanphamDB extends RoomDatabase {
    public abstract sanphamDao sanphamDao();
    public static final String DATABSE_NAME ="sanphamDB";
    private static sanphamDB instance;
    public  static sanphamDB getInstance(Context context)
    {
        if(instance==null)
            instance= Room.databaseBuilder(context,sanphamDB.class,DATABSE_NAME)
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}