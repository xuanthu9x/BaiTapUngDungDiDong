package com.example.mybeamin.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mybeamin.model.cuahang;

@Database(entities = cuahang.class, version = 1)
public abstract class cuahangDB extends RoomDatabase {
    public abstract cuahangDao cuahangDao();
    public static final String DATABSE_NAME ="cuahangDBx";
    private static cuahangDB instance;
    public  static cuahangDB getInstance(Context context)
    {
        if(instance==null)
            instance= Room.databaseBuilder(context,cuahangDB.class,DATABSE_NAME)
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}
