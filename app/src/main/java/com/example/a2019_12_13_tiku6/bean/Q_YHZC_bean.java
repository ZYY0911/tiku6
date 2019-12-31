package com.example.a2019_12_13_tiku6.bean;

import org.litepal.crud.LitePalSupport;

public class Q_YHZC_bean {
    private String yh;
    private String yx;

    public Q_YHZC_bean(String yh, String yx, String mima) {
        this.yh = yh;
        this.yx = yx;
        this.mima = mima;
    }

    public String getYh() {
        return yh;
    }

    public void setYh(String yh) {
        this.yh = yh;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    private String mima;
}
