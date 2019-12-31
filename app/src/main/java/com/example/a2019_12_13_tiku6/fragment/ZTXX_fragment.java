package com.example.a2019_12_13_tiku6.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZTXX_fragment extends Fragment {
    private BarChart barChart;
    private List<BarEntry> barEntries1, barEntries2;
    private BarData data;
    private BarDataSet dataSet1,dataSet2;
    private List<Integer> color1, color2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ztxx_item, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        barChart = getView().findViewById(R.id.barchart1);
        barEntries1 = new ArrayList<>();
        barEntries2 = new ArrayList<>();
        color1 = new ArrayList<>();
        color2 = new ArrayList<>();

        setVolley();
    }
    private void setVolley() {

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_bus_stop_distance")
                .setJsonObject("UserName", "user1")
                .setTime(3000)
                .setLoop(true)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            barEntries1.clear();
                            barEntries2.clear();
                            color2.clear();
                            color1.clear();
                            JSONArray jsonArray = new JSONArray(jsonObject.getString("中医院站"));
                            JSONArray jsonArray1 = new JSONArray(jsonObject.getString("联想大厦站"));
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                            JSONObject jsonObject3 = jsonArray1.getJSONObject(0);
                            JSONObject jsonObject4 = jsonArray1.getJSONObject(1);
                            int z = jsonObject1.getInt("distance");
                            int x = jsonObject2.getInt("distance");
                            int c = jsonObject3.getInt("distance");
                            int v = jsonObject4.getInt("distance");
                            if (z > c) {
//                                color1.clear();
//                                color2.clear();
                                color1.add(Color.YELLOW);
                                color2.add(Color.GREEN);
                            } else {
                                color2.add(Color.YELLOW);
                                color1.add(Color.GREEN);
                            }
                            if (x > v) {
                                color1.add(Color.YELLOW);
                                color2.add(Color.GREEN);
                            } else {
                                color2.add(Color.YELLOW);
                                color1.add(Color.GREEN);
                            }
                            barEntries1.add(new BarEntry(0, z));
                            barEntries1.add(new BarEntry(1, x));
                            barEntries2.add(new BarEntry(0, c));
                            barEntries2.add(new BarEntry(1, v));
                            setData();
                            barChart.postInvalidate();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setData() {
        dataSet1=new BarDataSet(barEntries1,"");
        dataSet1.setColors(color1);
        dataSet2 = new BarDataSet(barEntries2, "");
        dataSet2.setColors(color2);
        data = new BarData(dataSet1, dataSet2);
        float groupSpace = 1f; //柱状图组之间的间距
        float barSpace = 0f;
        data.setBarWidth(0.1f);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setTextColor(Color.WHITE);
        setY();
        data.groupBars(-0.7f, groupSpace, barSpace);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.invalidate();
    }

    private void setY() {
        YAxis yAxisR = barChart.getAxisRight();
        yAxisR.setEnabled(false);
        YAxis yAxisL = barChart.getAxisLeft();
        yAxisL.setDrawAxisLine(true);
        yAxisL.setTextColor(Color.BLACK);
    }
}
