package com.finplapp.model;

public enum CostType {
    RENT,
    FOOD,
    CLOTHES,
    TRANSPORT,
    HOBBY,
    HOLIDAY,
    SOMETHING_OTHER;

    @Override
    public String toString() {
        return this.name();
    }
}
