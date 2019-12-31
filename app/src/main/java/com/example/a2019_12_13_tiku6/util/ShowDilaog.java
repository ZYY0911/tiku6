package com.example.a2019_12_13_tiku6.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class ShowDilaog {
    public static void ShowDialog(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定",null);
        builder.show();
    }
}
