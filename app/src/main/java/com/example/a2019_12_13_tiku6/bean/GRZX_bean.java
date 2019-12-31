package com.example.a2019_12_13_tiku6.bean;

public class GRZX_bean {
    private String brand,plate,balance;

    public GRZX_bean(String brand, String plate, String balance) {
        this.brand = brand;
        this.plate = plate;
        this.balance = balance;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
