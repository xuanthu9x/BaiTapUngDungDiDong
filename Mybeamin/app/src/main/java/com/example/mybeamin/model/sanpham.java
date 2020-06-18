package com.example.mybeamin.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName="sanphams")
public class sanpham {
    @PrimaryKey(autoGenerate = true)
    public int Idsp;
    @ColumnInfo(name = "Namesp")
    public String Namesp;
    @ColumnInfo(name = "Giasp")
    public String Giasp;
    @ColumnInfo(name = "Idch")
    public int Idch;

    public sanpham() {
    }
    @Ignore
    public sanpham(int idsp, String namesp, String giasp, int idch) {
        Idsp = idsp;
        Namesp = namesp;
        Giasp = giasp;
        Idch = idch;
    }
    public sanpham(String namesp, String giasp, int idch) {
        Namesp = namesp;
        Giasp = giasp;
        Idch = idch;
    }

    public int getIdsp() {
        return Idsp;
    }

    public void setIdsp(int idsp) {
        Idsp = idsp;
    }

    public String getNamesp() {
        return Namesp;
    }

    public void setNamesp(String namesp) {
        Namesp = namesp;
    }

    public String getGiasp() {
        return Giasp;
    }

    public void setGiasp(String giasp) {
        Giasp = giasp;
    }

    public int getIdch() {
        return Idch;
    }

    public void setIdch(int idch) {
        Idch = idch;
    }
}
