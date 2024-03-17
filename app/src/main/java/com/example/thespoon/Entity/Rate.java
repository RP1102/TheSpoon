package com.example.thespoon.Entity;

import java.util.List;

public class Rate {

    Integer calculatedRate;

    List<Integer> listRates;

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

    public Integer getCalculatedRate() {
        return calculatedRate;
    }
}
