package com.example.thespoon.Entity;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.thespoon.Enum.FoodTypeEnum;

public class Restaurant implements Parcelable {

    private String id;
    private String name;

    private String description;
    private String address;

    private String image;

    private FoodTypeEnum type;

    private Integer averagePrice;

    private Integer rate;

    
    // CONSTRUCTOR
    public Restaurant(String id, String name, String description, String address, String image, FoodTypeEnum type, Integer averagePrice, Integer rate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.type = type;
        this.averagePrice = averagePrice;
        this.rate = rate;
    }

    public Restaurant() {
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setAveragePrice(Integer averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Integer getAveragePrice() {
        return averagePrice;
    }

    // IMPLEMENT PARCELABLE
    protected Restaurant(Parcel in) {
        name = in.readString();
        description = in.readString();
        address = in.readString();
        image = in.readString();
        id = in.readString();
        type = FoodTypeEnum.valueOf(in.readString());
        if (in.readByte() == 0) {
            averagePrice = null;
        } else {
            averagePrice = in.readInt();
        }
        rate = in.readInt();

    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeString(id);
        dest.writeString(type.name());
        if (averagePrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(averagePrice);
        }
        dest.writeInt(rate);
    }

}
