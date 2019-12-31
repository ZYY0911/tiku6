package com.example.a2019_12_13_tiku6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.activity.BaseActivity;
import com.example.a2019_12_13_tiku6.activity.Q_GJCX;
import com.example.a2019_12_13_tiku6.activity.Z_IPSzActivity;
import com.example.a2019_12_13_tiku6.activity.Z_TQYBActivity;
import com.example.a2019_12_13_tiku6.bean.Sense;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.dlhj_text)
    TextView dlhjText;
    @BindView(R.id.gjjl_text1)
    TextView gjjlText1;
    @BindView(R.id.gjjl_text2)
    TextView gjjlText2;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setVolley();
        setVolley_Bus();
        initClick();
    }

    private void initView() {
        title.setText("主页面");
    }

    private void initClick() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class myClass = null;
                switch (menuItem.getItemId()){
                    case R.id.setting:
                        myClass = Z_IPSzActivity.class;
                        finish();
                        break;
                    case R.id.weather:
                        myClass = Z_TQYBActivity.class;
                        finish();
                        break;
                    case R.id.gjcx:
                        myClass= Q_GJCX.class;
                        finish();
                        break;
                }
                startActivity(new Intent(MainActivity.this,myClass));
                return true;
            }
        });
    }

    private void setVolley_Bus() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_bus_stop_distance")
                .setJsonObject("UserName","user1")
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("中医院站");
                        JSONArray jsonArray1 = jsonObject.optJSONArray("联想大厦站");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        JSONObject jsonObject2 = jsonArray.optJSONObject(1);
                        JSONObject jsonObject3 = jsonArray1.optJSONObject(0);
                        JSONObject jsonObject4 = jsonArray1.optJSONObject(1);
                        gjjlText1.setText("1号公交"+jsonObject1.optInt("distance")+"m,2号公交:"+jsonObject2.optInt("distance")+"m");
                        gjjlText2.setText("1号公交"+jsonObject3.optInt("distance")+"m,2号公交:"+jsonObject4.optInt("distance")+"m");

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley() {
        VolleyTo  volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        Sense sense = gson.fromJson(jsonObject.toString(), Sense.class);
                        dlhjText.setText("PM2.5:"+sense.getPm25()+"µg/m3,温度:"+sense.getTemperature()+"˚C\n湿度："
                                +sense.getHumidity()+"˚C,CO2:"+sense.getCo2()+"µg/m3");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        drawer.openDrawer(GravityCompat.START);
    }
}



