package com.example.a2019_12_13_tiku6.net;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.dialog.MyDialog;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class VolleyTo extends Thread {
    private String Url = "http://" + AppClient.getIp() + ":8080/traffic/";
    private boolean isLoop;
    private int time;
    private JSONObject jsonObject = new JSONObject();
    private VolleyLo volleyLo;
    private MyDialog dialog;
    private boolean is  = true;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (dialog != null && dialog.isVisible()) {
                dialog.dismiss();
            }
            return false;
        }
    });

    public VolleyTo setDialog(AppCompatActivity context) {
        dialog = new MyDialog();
        dialog.show(context.getSupportFragmentManager(), "aaa");
        return this;
    }

    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    public VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public VolleyTo setTime(int time) {
        this.time = time;
        return this;
    }

    public VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }


    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                    if (is) {
                        handler.sendEmptyMessageDelayed(0, 1500);
                        is= false;
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                    if (is) {
                        handler.sendEmptyMessageDelayed(0, 1500);
                        is= false;
                    }
                }
            });
            AppClient.addRe(jsonObjectRequest);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isLoop);
    }
}
