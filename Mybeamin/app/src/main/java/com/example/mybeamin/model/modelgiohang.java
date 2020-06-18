package com.example.mybeamin.model;

public class modelgiohang {
    public int Idsp;
    public String Namesp;
    public String Giasp;
    public int Idch;
    public int sl;

    public modelgiohang(int idsp, String namesp, String giasp, int idch, int sl) {
        Idsp = idsp;
        Namesp = namesp;
        Giasp = giasp;
        Idch = idch;
        this.sl = sl;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
