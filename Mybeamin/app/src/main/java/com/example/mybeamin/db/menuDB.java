package com.example.mybeamin.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mybeamin.model.menu;

@Database(entities = menu.class, version = 1)
public abstract class menuDB extends RoomDatabase {
    public abstract menuDao menuDao();
    public static final String DATABSE_NAME ="menuDB";
    private static menuDB instance;
    public  static menuDB getInstance(Context context)
    {
        if(instance==null)
            instance= Room.databaseBuilder(context,menuDB.class,DATABSE_NAME)
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}
