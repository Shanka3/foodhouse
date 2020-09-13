package com.example.foodhouse.model;

public class FoodData {

    private String itemName;
    private String itemDescription;
    private String itemMethod;
    private String itemImage;
    private String publisher;

    public FoodData() {
    }

    public FoodData(String itemName, String itemDescription, String itemMethod, String itemImage, String publisher) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemMethod = itemMethod;
        this.itemImage = itemImage;
        this.publisher = publisher;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    public String getItemMethod() {
        return itemMethod;
    }
    public String getItemImage() {
        return itemImage;
    }
    public String getPublisher() {
        return publisher;
    }
}
