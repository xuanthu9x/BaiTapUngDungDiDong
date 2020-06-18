package com.example.networkjson;

public class QuocGia {
    private  String name;
    private String code;
    private  String danso;
    private String area;

    public QuocGia(String name, String code, String danso, String area) {
        this.name = name;
        this.code = code;
        this.danso = danso;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDanso() {
        return danso;
    }

    public void setDanso(String danso) {
        this.danso = danso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
