package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Duration;
import com.example.a2019_12_13_tiku6.bean.State;
import com.example.a2019_12_13_tiku6.bean.Tcc;
import com.example.a2019_12_13_tiku6.net.HttpClient;
import com.example.a2019_12_13_tiku6.net.HttpclientLo;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_LXDTActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.lx_qd)
    Spinner lxQd;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.lx_zd)
    Spinner lxZd;
    @BindView(R.id.lx_ss)
    TextView lxSs;
    @BindView(R.id.location)
    ImageButton location;
    @BindView(R.id.change_layer)
    ImageButton changeLayer;
    @BindView(R.id.marker)
    ImageButton marker;
    @BindView(R.id.line_distance)
    ImageButton lineDistance;
    @BindView(R.id.lx_xq)
    TextView lxXq;
    private Tcc tccs;
    private AMap aMap;
    private List<State> states;
    private List<Duration> durations;
    private String distanceString, durationString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxdt_layout);
        ButterKnife.bind(this);
        initView();
        mapView.onCreate(savedInstanceState);
        if (aMap == null) aMap = mapView.getMap();
        setData();
        setVolley();
        initClick();

    }

    private void initClick() {
        lxQd.setSelection(0);
        lxZd.setSelection(1);
        lxQd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (lxQd.getSelectedItem().toString().equals(lxZd.getSelectedItem().toString())) {
                    Toast.makeText(Z_LXDTActivity.this, "起点和终点不能选择同一站点", Toast.LENGTH_SHORT).show();
                    if (position + 1 > lxZd.getChildCount()) {
                        lxZd.setSelection(0);
                    } else {
                        lxZd.setSelection(position + 1);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lxZd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (lxQd.getSelectedItem().toString().equals(lxZd.getSelectedItem().toString())) {
                    Toast.makeText(Z_LXDTActivity.this, "起点和终点不能选择同一站点", Toast.LENGTH_SHORT).show();
                    if (position + 1 > lxZd.getChildCount()) {
                        lxZd.setSelection(0);
                    } else {
                        lxZd.setSelection(position + 1);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_site_data")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        Gson gson = new Gson();
                        states = gson.fromJson(jsonArray.toString(), new TypeToken<List<State>>() {
                        }.getType());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setData() {
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(tccs.getLongitude(), tccs.getLatitude())));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        LatLng latLng = new LatLng(tccs.getLongitude(), tccs.getLatitude());
        MarkerOptions myCarOption = new MarkerOptions();
        myCarOption.position(latLng);
        myCarOption.title(tccs.getParking_name()).snippet("");
        myCarOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
        myCarOption.setFlat(true);
        aMap.addMarker(myCarOption);
        Toast.makeText(this, "已为您定位到" + tccs.getParking_name() + "停车场", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    private void initView() {
        title.setText("离线地图");
        tccs = (Tcc) getIntent().getSerializableExtra("infos");
        AppClient appClient = (AppClient) getApplication();
        durations = appClient.getDurations();
        change.setImageResource(R.mipmap.arrow_left_);
    }

    @OnClick({R.id.change, R.id.lx_ss, R.id.location, R.id.change_layer, R.id.marker, R.id.line_distance, R.id.lx_xq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.lx_ss:
                durations.clear();
                aMap.clear();
                String qd = lxQd.getSelectedItem().toString().trim();
                String zd = lxZd.getSelectedItem().toString().trim();
                String qdString = null, zdString = null;
                for (int i = 0; i < states.size(); i++) {
                    State state = states.get(i);
                    if (state.getSite().equals(qd)) {
                        qdString = state.getLatitude() + "," + state.getLongitude();
                    }
                    if (state.getSite().equals(zd)) {
                        zdString = state.getLatitude() + "," + state.getLongitude();
                    }
                }
                HttpClient httpClient = new HttpClient();
                httpClient.setOrigin(qdString)
                        .setDestination(zdString)
                        .setHttpclientLo(new HttpclientLo() {
                            @Override
                            public void Susscss(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("route").optJSONArray("paths").getJSONObject(0);
                                    JSONArray jsonArray = jsonObject1.optJSONArray("steps");
                                    distanceString = jsonObject1.optString("distance");
                                    durationString = jsonObject1.optString("duration");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                        String action = jsonObject2.optString("action");
                                        if (action.equals("[]")) {
                                            action = "到达目的地";
                                        }
                                        Log.i("ggg", "getJson: " + action);
                                        durations.add(new Duration(jsonObject2.optString("instruction")
                                                , jsonObject2.optString("orientation")
                                                , jsonObject2.optString("distance")
                                                , jsonObject2.optString("duration")
                                                , jsonObject2.optString("polyline")
                                                , action));
                                    }
                                    setMap();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                break;

            case R.id.location:
                break;
            case R.id.change_layer:
                break;
            case R.id.marker:
                break;
            case R.id.line_distance:

                break;
            case R.id.lx_xq:
                Intent intent = new Intent(this, Z_XLZNActivity.class);
                intent.putExtra("distanceString", distanceString);
                intent.putExtra("durationString", durationString);
                intent.putExtra("start", lxQd.getSelectedItem().toString());
                intent.putExtra("end", lxZd.getSelectedItem().toString());
                startActivity(intent);
                break;
        }
    }

    private void setMap() {
        List<LatLng> latLngs = new ArrayList<>();
        for (int i = 0; i < durations.size(); i++) {
            Duration duration = durations.get(i);
            String polyline[] = duration.getPolyline().split(";");
            for (int j = 0; j < polyline.length; j++) {
                String polt[] = polyline[j].split(",");
                latLngs.add(new LatLng(Double.parseDouble(polt[1]), Double.parseDouble(polt[0])));
            }
        }
        aMap.addPolyline(new PolylineOptions().addAll(latLngs).width(10f).color(Color.RED));
    }
}
