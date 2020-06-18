package com.example.listcountries;

public class Country {
    private  String name,image,countryCode,population, area;
    public  Country() {}

    public Country(String name, String countryCode, String population,String area) {
        this.name = name;
        //this.image = image;
        this.countryCode = countryCode;
        this.population = population;
        this.area=area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
