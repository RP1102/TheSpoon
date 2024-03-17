package com.example.thespoon.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Rate implements Parcelable {

    private Integer calculatedRate;
    private List<Integer> listRates;

    // CONSTRUCTOR

    public Rate (List<Integer> listRates){
        this.listRates = listRates;
        calculateAverage();
    }

    // GETTERS & SETTERS
    public Integer getCalculatedRate() {
        return calculatedRate;
    }

    // GETTERS & SETTERS
    public void addRate(int rate) {
        listRates.add(rate);
    }

    public void calculateAverage() {
        if (this.listRates.isEmpty()) {
            this.calculatedRate = 0;
        } else {
            int sum = 0;
            for (int rate : this.listRates) {
                sum += rate;
            }
            this.calculatedRate = sum / this.listRates.size();
        }
    }

    // IMPLEMENT PARCELABLE
    protected Rate(Parcel in) {
        if (in.readByte() == 0) {
            calculatedRate = null;
        } else {
            calculatedRate = in.readInt();
        }
        listRates = new ArrayList<>();
        in.readList(listRates, Integer.class.getClassLoader());
    }

    public static final Creator<Rate> CREATOR = new Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (calculatedRate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(calculatedRate);
        }
        dest.writeList(listRates);
    }
}
