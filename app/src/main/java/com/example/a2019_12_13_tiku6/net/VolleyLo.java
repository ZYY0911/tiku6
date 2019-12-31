package com.example.a2019_12_13_tiku6.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public interface VolleyLo {
    void onResponse(JSONObject jsonObject);
    void onErrorResponse(VolleyError volleyError);
}
