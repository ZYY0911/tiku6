package com.example.a2019_12_13_tiku6.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.GRAX_ADapter;
import com.example.a2019_12_13_tiku6.bean.GRZX_bean;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Q_GRZX extends BaseActivity {

    private ImageView change;
    private TextView title;
    private TextView title1;
    private TextView tv_yhm;
    private TextView tv_xm;
    private TextView tv_xb;
    private TextView tv_tel;
    private TextView tv_sfz;
    private TextView tv_sj;
    private ListView lv;
    private List<GRZX_bean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__grzx);
        initView();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_root")
                .setJsonObject("UserName", "user1")
                .setJsonObject("Password", "1234")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            tv_yhm.setText(jsonObject.getString("username"));
                            tv_xm.setText(jsonObject.getString("name"));
                            tv_xb.setText(jsonObject.getString("sex"));
                            tv_tel.setText(jsonObject.getString("tel"));
                            tv_sj.setText(jsonObject.getString("registered_time"));
                            StringBuilder builder = new StringBuilder(jsonObject.getString("idnumber"));
                            tv_sfz.setText(builder.replace(6, 15, "*********"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setVolley_car();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setVolley_car() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_vehicle")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray jsonArray = new JSONArray(jsonObject.getString("ROWS_DETAIL"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                list.add(new GRZX_bean(jsonObject1.getString("brand")
                                        , jsonObject1.getString("plate")
                                        , jsonObject1.getString("balance")));
                            }
                            lv.setAdapter(new GRAX_ADapter(Q_GRZX.this, R.layout.grzx_listtview, list));
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
        change = (ImageView) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = (TextView) findViewById(R.id.title);
        title.setText("个人中心");
        title1 = (TextView) findViewById(R.id.title1);
        tv_yhm = (TextView) findViewById(R.id.tv_yhm);
        tv_xm = (TextView) findViewById(R.id.tv_xm);
        tv_xb = (TextView) findViewById(R.id.tv_xb);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_sfz = (TextView) findViewById(R.id.tv_sfz);
        tv_sj = (TextView) findViewById(R.id.tv_sj);
        lv = (ListView) findViewById(R.id.lv);
    }
}
