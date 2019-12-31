package com.example.a2019_12_13_tiku6.activity;

import android.graphics.Color;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.TQYB_Adapter1;
import com.example.a2019_12_13_tiku6.adapter.TQYB_Adapter2;
import com.example.a2019_12_13_tiku6.bean.Sense;
import com.example.a2019_12_13_tiku6.bean.Weather;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.example.a2019_12_13_tiku6.util.SimpData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_TQYBActivity extends BaseActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.now_day)
    TextView nowDay;
    @BindView(R.id.weather_image)
    ImageView weatherImage;
    @BindView(R.id.gridView1)
    GridView gridView1;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.gridView2)
    GridView gridView2;
    @BindView(R.id.today_info)
    TextView todayInfo;
    private List<Weather> weathers;
    private List<Sense> senses;
    private VolleyTo volleyTo;
    private TQYB_Adapter2 adapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tqyb_layout);
        ButterKnife.bind(this);
        initData();
        setVolley_Weather();
        setVolley_Sense();
    }

    private void setVolley_Sense() {
        volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(3000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        senses.add(gson.fromJson(jsonObject.toString(), Sense.class));
                        if (senses.size() == 5) {
                            senses.remove(0);
                        }
                        setAtapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setAtapter() {
        if (adapter2 == null) {
            adapter2 = new TQYB_Adapter2(this, R.layout.tqyb_item2, senses);
            gridView2.setAdapter(adapter2);
        } else {
            adapter2.notifyDataSetChanged();
        }
    }

    private void initData() {
        title.setText("天气预报");
        nowDay.setText(SimpData.fetData("yyyy年MM月dd日   EE ", new Date()));
        senses = new ArrayList<>();
    }

    private void setVolley_Weather() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_weather_info")
                .setJsonObject("UserName", "user1")
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String todayString = jsonObject.optString("weather");
                        todayInfo.setText(jsonObject.optString("temperature") + "度     " + todayString);
                        setImage(todayString, weatherImage);
                        Gson gson = new Gson();
                        weathers = gson.fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<Weather>>() {
                        }.getType());
                        setGridView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setGridView() {
        gridView1.setAdapter(new TQYB_Adapter1(this, R.layout.tqyb_item1, weathers));
        List<Entry> entries = new ArrayList<>();
        List<Entry> entries1 = new ArrayList<>();
        for (int i = 0; i < weathers.size(); i++) {
            Weather weather = weathers.get(i);
            String arr[] = weather.getInterval().split("~");
            entries.add(new Entry(i + 0.5f, Integer.parseInt(arr[0])));
            entries1.add(new Entry(i + 0.5f, Integer.parseInt(arr[1])));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setCircleRadius(7f);
        dataSet.setLineWidth(3f);
        LineDataSet dataSet1 = new LineDataSet(entries1, "");
        dataSet1.setColor(Color.YELLOW);
        dataSet1.setCircleColor(Color.YELLOW);
        dataSet1.setCircleRadius(7f);
        dataSet1.setLineWidth(3f);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(value) + "˚";
            }
        });
        dataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(value) + "˚";
            }
        });
        dataSet.setValueTextSize(25);
        dataSet1.setValueTextSize(25);
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet1);
        LineData data = new LineData(iLineDataSets);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(6);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.TRANSPARENT);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setData(data);
        lineChart.invalidate();

    }

    private void setImage(String todayString, ImageView weatherImage) {
        switch (todayString) {
            case "晴":
                weatherImage.setImageResource(R.mipmap.weather);
                break;
            case "小雨":
                weatherImage.setImageResource(R.mipmap.rain);
                break;
            case "阴":
                weatherImage.setImageResource(R.mipmap.cloudy);
                break;
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}

