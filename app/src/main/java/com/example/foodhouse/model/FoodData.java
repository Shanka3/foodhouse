package com.example.foodhouse.model;

public class FoodData {

    private String itemName;
    private String itemDescription;
    private String itemImage;
    private String publisher;

    public FoodData() {
    }

    public FoodData(String itemName, String itemDescription, String itemImage, String publisher) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.publisher = publisher;
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
    public String getPublisher() {
        return publisher;
    }
}
