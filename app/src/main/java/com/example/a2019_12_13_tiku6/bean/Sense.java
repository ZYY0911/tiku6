package com.example.a2019_12_13_tiku6.bean;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Sense {

    /**
     * temperature : 23
     * humidity : 1
     * illumination : 4721
     * co2 : 3601
     * pm25 : 82
     * RESULT : S
     */

    private int temperature;
    private int humidity;
    private int illumination;
    private int co2;
    private int pm25;
    private String RESULT;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }
}
