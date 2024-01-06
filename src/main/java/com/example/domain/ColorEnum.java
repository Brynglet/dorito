package com.example.domain;

public enum ColorEnum {

    BLACK("black"),
    GREY("grey"),
    CORAL("coral"),
    GREEN("green");

    private final String colorStr;

    ColorEnum(String colorStr) {
        this.colorStr = colorStr;
    }

    public String getColorEnumStr () {
        return this.colorStr;
    }
}
