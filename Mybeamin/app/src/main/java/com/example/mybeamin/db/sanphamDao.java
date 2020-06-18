package com.example.mybeamin.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybeamin.model.cuahang;
import com.example.mybeamin.model.sanpham;

import java.util.List;

@Dao
public interface sanphamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertsanpham(sanpham sanpham);
    @Delete
    void deletesanpham(sanpham sanpham);
    @Update
    void updatesanpham(sanpham sanpham);
    @Query("SELECT * FROM sanphams")
    List<sanpham> getsanphams();
    @Query("SELECT * FROM sanphams WHERE  Idsp =:Idd")
    sanpham getsanpham(int Idd);
}
