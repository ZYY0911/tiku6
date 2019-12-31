package com.example.a2019_12_13_tiku6.bean;

public class SSHJ {
    private int co2,temperature,humidity;
    private String time;

    public SSHJ(int co2, int temperature, int humidity, String time) {
        this.co2 = co2;
        this.temperature = temperature;
        this.humidity = humidity;
        this.time = time;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
