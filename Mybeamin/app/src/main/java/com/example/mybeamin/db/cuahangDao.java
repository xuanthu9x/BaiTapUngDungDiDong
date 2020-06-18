package com.example.mybeamin.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.menu;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface cuahangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertcuahang(cuahang cuahang);
    @Delete
    void deletecuahang(cuahang cuahang);
    @Update
    void updatecuahang(cuahang cuahang);
    @Query("SELECT * FROM cuahangs")
    List<cuahang> getcuahangs();
    @Query("SELECT * FROM cuahangs WHERE  Idch =:Idd")
    cuahang getCuahang(int Idd);
}
