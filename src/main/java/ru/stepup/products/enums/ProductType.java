package ru.stepup.products.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {
    ACCOUNT("account"),
    CARD("card");

    private String type;

    ProductType (String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return this.type;
    }
}
