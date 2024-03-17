package com.example.thespoon.Entity;

import com.example.thespoon.Enum.FoodTypeEnum;

public class Restaurant {

    private String name;

    private String description;
    private String address;

    private String image;

    private FoodTypeEnum type;

    private Integer averagePrice;

    private Rate rate;
    
    // CONSTRUCTOR
    public Restaurant(String name, String description, String address, String image, FoodTypeEnum type, Integer averagePrice, Rate rate) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.type = type;
        this.averagePrice = averagePrice;
        this.rate = rate;
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(FoodTypeEnum type) {
        this.type = type;
    }

    public FoodTypeEnum getType() {
        return type;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Rate getRate() {
        return rate;
    }

    public void setAveragePrice(Integer averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Integer getAveragePrice() {
        return averagePrice;
    }
}
