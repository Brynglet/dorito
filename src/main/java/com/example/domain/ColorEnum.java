package com.example.domain;

/*
           return 1;
            //return "black";
        }
        if (i % 2 == 0 && k % 2 == 0) {
            return 2;
            //return "grey"; //beslutsruta
        }
        return 3;
        //return "coral"; //faktiska strecken
 */
public enum ColorEnum {

    BLACK(1),
    GREY(2),
    CORAL(3),
    GREEN(4);

    private int colorInt;
    private int colorString;
    ColorEnum (int colorInt) {
        this.colorInt = colorInt;
    }

}
