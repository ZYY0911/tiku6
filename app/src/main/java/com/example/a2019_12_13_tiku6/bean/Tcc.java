package com.example.a2019_12_13_tiku6.bean;

import java.io.Serializable;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Tcc implements Serializable {

    /**
     * id : 1
     * parking_name : 南京学院停车场
     * root : 对外开放
     * location : 栖霞区洋山北路1号
     * rate_type : 小时
     * price : 7
     * reference : 每小时5元，最高40元每天
     * carport : 178
     * distance : 472
     * longitude : 39.940294
     * latitude : 116.377787
     */

    private int id;
    private String parking_name;
    private String root;
    private String location;
    private String rate_type;
    private int price;
    private String reference;
    private int carport;
    private int distance;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getCarport() {
        return carport;
    }

    public void setCarport(int carport) {
        this.carport = carport;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
