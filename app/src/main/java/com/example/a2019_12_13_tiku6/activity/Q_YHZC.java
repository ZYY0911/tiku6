package com.example.a2019_12_13_tiku6.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.MainActivity;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Q_YHZC_bean;
import com.example.a2019_12_13_tiku6.bean.Q_YHZC_sql;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Q_YHZC extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_yy;
    private EditText et_yhm;
    private EditText et_mm;
    private TextView tv_zcxzh;
    private TextView tv_zhmm;
    private Button bt_dr;
    private List<Q_YHZC_bean> list = new ArrayList<>();
    private Q_YHZC_bean bean;
    private boolean is = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__yhzc);
        if (AppClient.getOgin()==true) {
             startActivity(new Intent(Q_YHZC.this, MainActivity.class));
             finish();
            return;
        }
        initView();
        setonclick();
    }

    private void pandaun() {
        for (int i = 0; i < list.size(); i++) {
            bean = list.get(i);
            Log.i("222222222222", "onClick: " + list.toString());
            if (et_yhm.getText().toString().equals(bean.getYh()) && et_mm.getText().toString().equals(bean.getMima())) {
                Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Q_YHZC.this, MainActivity.class));
                finish();
                return;
            }else {
                is = false;
            }
        }
        if (is == false) {
            Toast.makeText(this, "登入失败", Toast.LENGTH_SHORT).show();

        }
    }

    private void setSQL() {
        List<Q_YHZC_sql> litePalSQL2s = LitePal.findAll(Q_YHZC_sql.class);
        for (Q_YHZC_sql sql2 : litePalSQL2s) {
            Log.i("3333333333333333", "邮箱: " + sql2.getYx());
            Log.i("3333333333333333", "用户名: " + sql2.getMima());
            Log.i("3333333333333333", "密码: " + sql2.getYh());
            list.add(new Q_YHZC_bean(sql2.getYh(), sql2.getMima(), sql2.getYx()));
        }

    }

    private void setonclick() {
        tv_zcxzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q_YHZC.this, Q_YHZC_zc.class);
                startActivity(intent);
                finish();
            }
        });


        tv_zhmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q_YHZC.this, Q_YHZC_zh.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_login")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = new JSONArray(jsonObject.getString("ROWS_DETAIL"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                if (et_yhm.getText().toString().equals(jsonObject1.getString("username")) && et_mm.getText().toString().equals(jsonObject1.getString("password"))) {
                                    Toast.makeText(Q_YHZC.this, "登入成功", Toast.LENGTH_SHORT).show();
                                    AppClient.setOgin(true);
                                    AppClient.addUser(et_yhm.getText().toString());
                                    startActivity(new Intent(Q_YHZC.this, MainActivity.class));
                                    finish();
                                    return;
                                }
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

    private void initView() {
        iv_yy = (ImageView) findViewById(R.id.iv_yy);
        et_yhm = (EditText) findViewById(R.id.et_yhm);
        et_mm = (EditText) findViewById(R.id.et_mm);
        tv_zcxzh = (TextView) findViewById(R.id.tv_zcxzh);
        tv_zhmm = (TextView) findViewById(R.id.tv_zhmm);
        bt_dr = (Button) findViewById(R.id.bt_dr);
        bt_dr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_dr:
                setVolley();
                List<Q_YHZC_sql> q_yhzc_sqls = LitePal.findAll(Q_YHZC_sql.class);
                for (int i = 0; i < q_yhzc_sqls.size(); i++) {
                    Q_YHZC_sql q_yhzc_sql = q_yhzc_sqls.get(i);
                    if (et_yhm.getText().toString().trim().equals(q_yhzc_sql.getYh())){
                        if (et_mm.getText().toString().trim().equals(q_yhzc_sql.getMima())){
                            startActivity(new Intent(this,MainActivity.class));
                            AppClient.addUser(q_yhzc_sql.getYh());
                            AppClient.setOgin(true);
                            finish();
                            return;
                        }
                    }
                }
                Toast.makeText(Q_YHZC.this, "登入失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
