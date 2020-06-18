package com.example.orderfood1;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String FoodName, FoodLink, FoodLocation;
    private int FoodImages;
    private boolean isSelected;
    public ItemList(){}
    public  ItemList(String FoodName, String FoosLink, String FoodLocation, int FoodImages)
    {
        this.FoodImages=FoodImages;
        this.FoodLink=FoosLink;
        this.FoodLocation=FoodLocation;
        this.FoodName=FoodName;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodLink() {
        return FoodLink;
    }

    public void setFoodLink(String foodLink) {
        FoodLink = foodLink;
    }

    public String getFoodLocation() {
        return FoodLocation;
    }

    public void setFoodLocation(String foodLocation) {
        FoodLocation = foodLocation;
    }

    public int getFoodImages() {
        return FoodImages;
    }

    public void setFoodImages(int foodImages) {
        FoodImages = foodImages;
    }

    public boolean isSlected() {
        return isSelected;
    }

    public void setSelected(boolean Selected) {
        this.isSelected=Selected;
    }
}
