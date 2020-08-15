package com.example.foodhouse.model;

public class FoodData {

    private String itemName;
    private String itemDescription;
    private String itemImage;

    public FoodData(String itemName, String itemDescription, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }
}
