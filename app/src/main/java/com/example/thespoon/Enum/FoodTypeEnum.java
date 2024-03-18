package com.example.thespoon.Enum;

import java.io.Serializable;

public enum FoodTypeEnum implements Serializable {
    FRENCH ("Francaise"),
    ENGLISH ("Anglaise"),
    ASIA ("Asiatique"),
    AFRICA ("Africaine");

    private String label;

    FoodTypeEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
