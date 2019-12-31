package com.example.a2019_12_13_tiku6;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void addRe(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }
}
