package com.example.a2019_12_13_tiku6.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class SimpData {
    public static String fetData(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
