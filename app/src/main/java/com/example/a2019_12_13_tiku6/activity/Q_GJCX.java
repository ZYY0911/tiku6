package com.example.a2019_12_13_tiku6.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.SSHJ;
import com.example.a2019_12_13_tiku6.fragment.HCHJ_fragment;
import com.example.a2019_12_13_tiku6.fragment.ZTXX_fragment;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.example.a2019_12_13_tiku6.util.Shijian;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Q_GJCX extends BaseActivity {

    private ImageView change;
    private TextView title;
    private TextView title1;
    private FrameLayout vp;
    private TextView tv_zt;
    private TextView tv_hj;
    private LinearLayout linear1;
    private List<SSHJ> sshjs = new ArrayList<>();
    private AppClient appClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__gjcx);
        initView();
        replace(new ZTXX_fragment());
        setOnclick();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setTime(3000)
                .setLoop(true)
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getString("RESULT").equals("S")) ;
                            sshjs.add(new SSHJ(jsonObject.getInt("co2")
                                    , jsonObject.getInt("temperature")
                                    , jsonObject.getInt("humidity")
                                    , Shijian.simp("HH:mm:ss", new Date())));
                            if (sshjs.size() == 6) {
                                sshjs.remove(0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setOnclick() {
        tv_zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new ZTXX_fragment());
                tv_zt.setTextSize(30);
                tv_hj.setTextSize(20);
            }
        });
        tv_hj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HCHJ_fragment());
                tv_hj.setTextSize(30);
                tv_zt.setTextSize(20);
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.vp, fragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        change = (ImageView) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = (TextView) findViewById(R.id.title);
        title.setText("公交查询");
        title1 = (TextView) findViewById(R.id.title1);
        vp = (FrameLayout) findViewById(R.id.vp);
        tv_zt = (TextView) findViewById(R.id.tv_zt);
        tv_hj = (TextView) findViewById(R.id.tv_hj);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        appClient= (AppClient) getApplication();

        sshjs = appClient.getSshjs();
    }
}
