package com.example.a2019_12_13_tiku6.bean;

/**
 * Create by 张瀛煜 on 2019-12-31
 */

public class WDLK {
    String Lk;
    int Lvd;
    int Hud;
    int Hod;
    int Hvalue;
    int Svalue;
    String Hzt;
    String Szt;
    boolean He;
    boolean Sh;
    boolean Qx;

    public WDLK() {
    }

    public WDLK(String lk, int lvd, int hud, int hod, int hvalue, int svalue, String hzt, String szt, boolean he, boolean sh, boolean qx) {
        Lk = lk;
        Lvd = lvd;
        Hud = hud;
        Hod = hod;
        Hvalue = hvalue;
        Svalue = svalue;
        Hzt = hzt;
        Szt = szt;
        He = he;
        Sh = sh;
        Qx = qx;
    }

    public String getLk() {
        return Lk;
    }

    public void setLk(String lk) {
        Lk = lk;
    }

    public int getLvd() {
        return Lvd;
    }

    public void setLvd(int lvd) {
        Lvd = lvd;
    }

    public int getHud() {
        return Hud;
    }

    public void setHud(int hud) {
        Hud = hud;
    }

    public int getHod() {
        return Hod;
    }

    public void setHod(int hod) {
        Hod = hod;
    }

    public int getHvalue() {
        return Hvalue;
    }

    public void setHvalue(int hvalue) {
        Hvalue = hvalue;
    }

    public int getSvalue() {
        return Svalue;
    }

    public void setSvalue(int svalue) {
        Svalue = svalue;
    }

    public String getHzt() {
        return Hzt;
    }

    public void setHzt(String hzt) {
        Hzt = hzt;
    }

    public String getSzt() {
        return Szt;
    }

    public void setSzt(String szt) {
        Szt = szt;
    }

    public boolean isHe() {
        return He;
    }

    public void setHe(boolean he) {
        He = he;
    }

    public boolean isSh() {
        return Sh;
    }

    public void setSh(boolean sh) {
        Sh = sh;
    }

    public boolean isQx() {
        return Qx;
    }

    public void setQx(boolean qx) {
        Qx = qx;
    }
}
