package com.example.a2019_12_13_tiku6.bean;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class WDLK {
    private int red,yellow,green,allred,allyellow,allgreen;
    private String now;

    public WDLK(int red, int yellow, int green, int allred, int allyellow, int allgreen, String now) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
        this.allred = allred;
        this.allyellow = allyellow;
        this.allgreen = allgreen;
        this.now = now;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getAllred() {
        return allred;
    }

    public void setAllred(int allred) {
        this.allred = allred;
    }

    public int getAllyellow() {
        return allyellow;
    }

    public void setAllyellow(int allyellow) {
        this.allyellow = allyellow;
    }

    public int getAllgreen() {
        return allgreen;
    }

    public void setAllgreen(int allgreen) {
        this.allgreen = allgreen;
    }
}
