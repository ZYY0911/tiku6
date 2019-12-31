package com.example.a2019_12_13_tiku6.bean;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Duration {

    /**
     * instruction : 向西行驶41米左转
     * orientation : 西
     * distance : 41
     * tolls : 0
     * toll_distance : 0
     * toll_road : []
     * duration : 35
     * polyline : 116.459251,39.909805;116.458763,39.909805
     * action : 左转
     */

    private String instruction;
    private String orientation;
    private String distance;
    private String duration;
    private String polyline;
    private String action;

    public Duration(String instruction, String orientation, String distance, String duration, String polyline, String action) {
        this.instruction = instruction;
        this.orientation = orientation;
        this.distance = distance;
        this.duration = duration;
        this.polyline = polyline;
        this.action = action;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }




    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
