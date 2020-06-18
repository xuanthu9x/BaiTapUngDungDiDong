package com.example.mybeamin.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity(tableName="cuahangs")
public class cuahang {
    @PrimaryKey(autoGenerate = true)
    public int Idch;
    @ColumnInfo(name = "Namech")
    public String Namech;
    @ColumnInfo(name = "Gioithieu")
    public String Gioithieu;
    @ColumnInfo(name = "Sdt")
    public String Sdt;
    @ColumnInfo(name = "Diachi")
    public String Diachi;
    @ColumnInfo(name = "Strart")
    public String Strart;
    @ColumnInfo(name = "End")
    public String End;
    @ColumnInfo(name = "Hinhanh")
    public String Hinhanh;
    @ColumnInfo(name = "theloai")
    public int Theloai;

    public cuahang() {
    }

    public cuahang(int idch, String namech, String gioithieu, String sdt, String diachi, String strart, String end, String hinhanh) {
        Idch = idch;
        Namech = namech;
        Gioithieu = gioithieu;
        Sdt = sdt;
        Diachi = diachi;
        Strart = strart;
        End = end;
        Hinhanh = hinhanh;
    }


    @Ignore
    public cuahang(int idch, String namech, String gioithieu, String sdt, String diachi, String strart, String end, String hinhanh,int theloai) {
        Idch = idch;
        Namech = namech;
        Gioithieu = gioithieu;
        Sdt = sdt;
        Diachi = diachi;
        Strart = strart;
        End = end;
        Hinhanh = hinhanh;
        Theloai=theloai;
    }
    @Ignore
    public cuahang(String namech, String gioithieu, String sdt, String diachi, String strart, String end, String hinhanh,int theloai) {
        Namech = namech;
        Gioithieu = gioithieu;
        Sdt = sdt;
        Diachi = diachi;
        Strart = strart;
        End = end;
        Hinhanh = hinhanh;
        Theloai=theloai;

    }
    public int getIdch() {
        return Idch;
    }
    public String getNamech() {
        return Namech;
    }
    public String getGioithieu() {
        return Gioithieu;
    }
    public String getHinhanh() {
        return Hinhanh;
    }
    public String getSdt() {
        return Sdt;
    }
    public String getDiachi() {
        return Diachi;
    }
    public String getStrart() {
        return Strart;
    }
    public String getEnd() {
        return End;
    }
    public void setGioithieu(String Id) {
        Gioithieu=Id;
    }
    public void setHinhanh(String Id) { Hinhanh=Id; }
    public void setNamech(String Id) {
        Namech=Id;
    }
    public void setSdt(String Id) {
        Sdt=Id;
    }
    public void setDiachi(String Id) {
        Diachi=Id;
    }
    public void setStrart(String Id) {
        Strart=Id;
    }

    public int getTheloai() {
        return Theloai;
    }

    public void setTheloai(int theloai) {
        Theloai = theloai;
    }

    public void setEnd(String Id) {
        End=Id;
    }
}
