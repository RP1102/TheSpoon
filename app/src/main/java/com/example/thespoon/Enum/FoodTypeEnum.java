package com.example.thespoon.Enum;

public enum FoodTypeEnum {
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
