package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.YHTCAdapter;
import com.example.a2019_12_13_tiku6.bean.Tcc;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_YHTCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.location)
    ImageButton location;
    @BindView(R.id.change_layer)
    ImageButton changeLayer;
    @BindView(R.id.marker)
    ImageButton marker;
    @BindView(R.id.line_distance)
    ImageButton lineDistance;
    @BindView(R.id.yhtc_list)
    ListView yhtcList;
    private AMap aMap;
    private List<Tcc> tccs;
    private YHTCAdapter adapter;
    private MarkerOptions firstOption, secondOption, thirdOption;
    private List<Marker> markers;
    private List<LatLng> latLngs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yhtc_layout);
        ButterKnife.bind(this);
        initView();
        mapView.onCreate(savedInstanceState);
        if (aMap == null) aMap = mapView.getMap();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_park_data")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        Gson gson = new Gson();
                        tccs = gson.fromJson(jsonArray.toString(), new TypeToken<List<Tcc>>() {
                        }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        Collections.sort(tccs, new Comparator<Tcc>() {
            @Override
            public int compare(Tcc o1, Tcc o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });
        adapter = new YHTCAdapter(this, R.layout.tcc_item, tccs);
        adapter.setClick(new YHTCAdapter.MyClick() {
            @Override
            public void myClick(int psotion) {
                Intent intent = new Intent(Z_YHTCActivity.this, Z_TCCXQActivity.class);
                intent.putExtra("infos", tccs.get(psotion));
                startActivity(intent);
            }
        });
        yhtcList.setAdapter(adapter);
        if (latLngs == null) latLngs = new ArrayList<>();
        latLngs.clear();
        for (int i = 0; i < tccs.size(); i++) {
            Tcc tcc = tccs.get(i);
            latLngs.add(new LatLng(tcc.getLongitude(), tcc.getLatitude()));
        }
        removeMarkers();
        firstOption = new MarkerOptions();
        firstOption.position(latLngs.get(0));
        firstOption.title("南京学院停车场").snippet("栖霞区洋山北路1号");
        firstOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_one)));
        firstOption.setFlat(true);
        secondOption = new MarkerOptions();
        secondOption.position(latLngs.get(1));
        secondOption.title("德州百货停车场").snippet("德城区三八东路7号");
        secondOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_second)));
        secondOption.setFlat(true);
        thirdOption = new MarkerOptions();
        thirdOption.position(latLngs.get(2));
        thirdOption.title("德州职业停车场").snippet("德城区大学东路15号");
        thirdOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_thrid)));
        thirdOption.setFlat(true);
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
        title.setText("用户停车");
        change.setImageResource(R.mipmap.arrow_left_);
    }

    private void removeMarkers() {
        List<Marker> markersa = aMap.getMapScreenMarkers();
        for (Marker marker : markersa) {
            marker.remove();
        }
    }

    @OnClick({R.id.change, R.id.location, R.id.change_layer, R.id.marker, R.id.line_distance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.location:
                removeMarkers();
                aMap.clear();
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(39.942209, 116.384467)));
                aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
                LatLng latLng = new LatLng(39.942209, 116.384467);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("什刹海").snippet("");
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.marker_self)));
                markerOptions.setFlat(true);
                aMap.addMarker(markerOptions);
                yhtcList.setVisibility(View.GONE);
                break;
            case R.id.change_layer:
                yhtcList.setVisibility(View.GONE);
                break;
            case R.id.marker:
                Marker marker = aMap.addMarker(firstOption);
                Marker marker1 = aMap.addMarker(secondOption);
                Marker marker2 = aMap.addMarker(thirdOption);
                if (markers == null) markers = new ArrayList<>();
                markers.clear();
                markers.add(marker);
                markers.add(marker1);
                markers.add(marker2);
                yhtcList.setVisibility(View.VISIBLE);
                break;
            case R.id.line_distance:
                yhtcList.setVisibility(View.GONE);
                break;
        }
    }
}
