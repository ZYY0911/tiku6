package com.example.a2019_12_13_tiku6.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shijian {
    public static String simp(String type , Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
