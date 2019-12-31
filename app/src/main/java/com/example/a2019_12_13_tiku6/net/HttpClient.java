package com.example.a2019_12_13_tiku6.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class HttpClient extends Thread {
    private String origin, destination;
    private HttpclientLo httpclientLo;

    public HttpClient setHttpclientLo(HttpclientLo httpclientLo) {
        this.httpclientLo = httpclientLo;
        return this;
    }

    public HttpClient setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public HttpClient setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public void run() {
        super.run();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://restapi.amap.com/v3/direction/driving?origin=" + origin + "&destination=" + destination + "&output=json&key=012e80f56fd55b0015e779a5417be981")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            httpclientLo.Susscss(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
