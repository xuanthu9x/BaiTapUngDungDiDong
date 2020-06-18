package com.example.mybeamin.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="menus")
public  class menu {
    @PrimaryKey(autoGenerate = true)
    public int Idmenu;
    @ColumnInfo(name = "Tenmenu")
    public String Tenmenu;
    @ColumnInfo(name = "anhmenu")
    public String anhmenu;

    public menu() {

    }
    public menu(int idmenu, String tenmenu, String anhmenu) {
        Idmenu = idmenu;
        Tenmenu = tenmenu;
        this.anhmenu = anhmenu;
    }

    @Ignore
    public menu(String Tenmenu, String anhmenu) {
        this.Tenmenu = Tenmenu;
        this.anhmenu = anhmenu;
    }

    public int getIdmenu() {
        return Idmenu;
    }
    public void setIdmenu(int id) {
        this.Idmenu=id;
    }

    public String getAnhmenu() {
        return anhmenu;
    }

    public void setAnhmenu(String anhmenu) {
        this.anhmenu = anhmenu;
    }

    public String getTenmenu() {
        return Tenmenu;
    }

    public void setTenmenu(String Tenmenu) {
        this.Tenmenu = Tenmenu;
    }

}