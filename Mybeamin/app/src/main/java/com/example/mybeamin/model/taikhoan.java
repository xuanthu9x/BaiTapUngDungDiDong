package com.example.mybeamin.model;

public class taikhoan {
    public int Idtk;
    public String TK;
    public String MK;
    public String Name;
    public String Sdt;
    public String Diachi;
    public void taikhoan(int xIdtk,String xTK,String xMK,String xName,String xSdt,String xDiachi)
    {
       Idtk=xIdtk;
       TK=xTK;
       MK=xMK;
       Name=xName;
       Sdt=xSdt;
       Diachi=xDiachi;
    }
    public int getIdtk() {
        return Idtk;
    }
    public String getTK() {
        return TK;
    }
    public String getMK() {
        return MK;
    }
    public String getName() {
        return Name;
    }
    public String getSdt() {
        return Sdt;
    }
    public String getDiachi() {
        return Diachi;
    }
    public void setIdtk(int Id) {
        Idtk=Id;
    }
    public void setTK(String Id) {
        TK=Id;
    }
    public void setMK(String Id) {
        MK=Id;
    }
    public void setName(String Id) {
        Name=Id;
    }
    public void setSdt(String Id) {
        Sdt=Id;
    }
    public void setDiachi(String Id) {
        Diachi=Id;
    }
}
