package com.example.a2019_12_13_tiku6;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a2019_12_13_tiku6.bean.SSHJ;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;
    private List<SSHJ> sshjs;

    public List<SSHJ> getSshjs() {
        return sshjs;
    }

    public static boolean isIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        AppClient.isAdmin = isAdmin;
    }

    public static boolean isAdmin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sshjs = new ArrayList<>();
        LitePal.initialize(this);
        requestQueue = Volley.newRequestQueue(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static String getIp() {
        return preferences.getString("IP", "10.172.176.54");
    }

    public static void setIp(String ip) {
        preferences.edit().putString("IP", ip).apply();
    }

    public static void addRe(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    public static void addUser(String username) {
        preferences.edit().putString("username", username).apply();
    }
    public static void  setOgin(boolean x){
        preferences.edit().putBoolean("x",x).apply();
    }
    public  static boolean getOgin(){
        return preferences.getBoolean("x",false);
    }


}
