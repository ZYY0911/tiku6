package com.example.a2019_12_13_tiku6.bean;

public class lkfx {
    private String xyl,lxl,yyl,xfl,hcksl,hcgs,tcc;

    public lkfx(String xyl, String lxl, String yyl, String xfl, String hcksl, String hcgs, String tcc) {
        this.xyl = xyl;
        this.lxl = lxl;
        this.yyl = yyl;
        this.xfl = xfl;
        this.hcksl = hcksl;
        this.hcgs = hcgs;
        this.tcc = tcc;
    }


    @Override
    public String toString() {
        return "lkfx{" +
                "xyl='" + xyl + '\'' +
                ", lxl='" + lxl + '\'' +
                ", yyl='" + yyl + '\'' +
                ", xfl='" + xfl + '\'' +
                ", hcksl='" + hcksl + '\'' +
                ", hcgs='" + hcgs + '\'' +
                ", tcc='" + tcc + '\'' +
                '}';
    }

    public String getXyl() {
        return xyl;
    }

    public void setXyl(String xyl) {
        this.xyl = xyl;
    }

    public String getLxl() {
        return lxl;
    }

    public void setLxl(String lxl) {
        this.lxl = lxl;
    }

    public String getYyl() {
        return yyl;
    }

    public void setYyl(String yyl) {
        this.yyl = yyl;
    }

    public String getXfl() {
        return xfl;
    }

    public void setXfl(String xfl) {
        this.xfl = xfl;
    }

    public String getHcksl() {
        return hcksl;
    }

    public void setHcksl(String hcksl) {
        this.hcksl = hcksl;
    }

    public String getHcgs() {
        return hcgs;
    }

    public void setHcgs(String hcgs) {
        this.hcgs = hcgs;
    }

    public String getTcc() {
        return tcc;
    }

    public void setTcc(String tcc) {
        this.tcc = tcc;
    }
}
