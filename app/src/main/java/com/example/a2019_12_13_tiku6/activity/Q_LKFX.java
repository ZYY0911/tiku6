package com.example.a2019_12_13_tiku6.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.lkfx;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Q_LKFX extends AppCompatActivity implements View.OnClickListener {


    private boolean b = true, b1 = true, b2 = true, b3 = true, b4 = true, b5 = true, b6 = true;
    private LinearLayout xyl, lxl, xfl, yyl, hcksl, hcgs, tcc;
    private String[] huqu, s_xyl, s_xfl, s_lxl, s_yyl, s_hcksl, s_hcgs, s_tcc;
    private List<lkfx> mlkfx;
    private BarChart barChart;
    private BarData data;
    private BarDataSet dataSet, dataSet1, dataSet2, dataSet3, dataSet4, dataSet5, dataSet6;
    private List<BarEntry> mY1, mY2, mY3, mY4, mY5, mY6, mY7;
    private List<String> mX;
    private List<Integer> color;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            mlkfx.clear();
            huoqu1();
            return false;
        }
    });
    private ImageView change;
    private TextView title;
    private TextView title1;
    private BarChart barchart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__lkfx);
        initView();
        inviwew();
        huoqu1();
        xyl.setOnClickListener(this);
        yyl.setOnClickListener(this);
        xfl.setOnClickListener(this);
        lxl.setOnClickListener(this);
        hcgs.setOnClickListener(this);
        hcksl.setOnClickListener(this);
        tcc.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }).start();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        overridePendingTransition(R.anim.anim_3, R.anim.anim_2);
//    }

    private void addData() {
        System.out.println("----" + mlkfx);
        for (int i = 0; i < mlkfx.size(); i++) {
            lkfx l = mlkfx.get(i);
            s_xyl[i] = l.getXyl();

            s_yyl[i] = l.getYyl();
            s_lxl[i] = l.getLxl();
            s_xfl[i] = l.getXfl();
            s_hcgs[i] = l.getHcksl();
            s_hcksl[i] = l.getHcgs();
            s_tcc[i] = l.getTcc();
        }
        mX.add("周一");
        mX.add("周二");
        mX.add("周三");
        mX.add("周四");
        mX.add("周五");
        mX.add("周六");
        mX.add("周七");


        mY1.add(new BarEntry(0, Integer.parseInt(s_xyl[0])));
        mY1.add(new BarEntry(1, Integer.parseInt(s_xyl[1])));
        mY1.add(new BarEntry(2, Integer.parseInt(s_xyl[2])));
        mY1.add(new BarEntry(3, Integer.parseInt(s_xyl[3])));
        mY1.add(new BarEntry(4, Integer.parseInt(s_xyl[4])));
        mY1.add(new BarEntry(5, Integer.parseInt(s_xyl[5])));
        mY1.add(new BarEntry(6, Integer.parseInt(s_xyl[6])));

        mY2.add(new BarEntry(0, Integer.parseInt(s_lxl[0])));
        mY2.add(new BarEntry(1, Integer.parseInt(s_lxl[1])));
        mY2.add(new BarEntry(2, Integer.parseInt(s_lxl[2])));
        mY2.add(new BarEntry(3, Integer.parseInt(s_lxl[3])));
        mY2.add(new BarEntry(4, Integer.parseInt(s_lxl[4])));
        mY2.add(new BarEntry(5, Integer.parseInt(s_lxl[5])));
        mY2.add(new BarEntry(6, Integer.parseInt(s_lxl[6])));

        mY3.add(new BarEntry(0, Integer.parseInt(s_yyl[0])));
        mY3.add(new BarEntry(1, Integer.parseInt(s_yyl[1])));
        mY3.add(new BarEntry(2, Integer.parseInt(s_yyl[2])));
        mY3.add(new BarEntry(3, Integer.parseInt(s_yyl[3])));
        mY3.add(new BarEntry(4, Integer.parseInt(s_yyl[4])));
        mY3.add(new BarEntry(5, Integer.parseInt(s_yyl[5])));
        mY3.add(new BarEntry(6, Integer.parseInt(s_yyl[6])));

        mY4.add(new BarEntry(0, Integer.parseInt(s_xfl[0])));
        mY4.add(new BarEntry(1, Integer.parseInt(s_xfl[1])));
        mY4.add(new BarEntry(2, Integer.parseInt(s_xfl[2])));
        mY4.add(new BarEntry(3, Integer.parseInt(s_xfl[3])));
        mY4.add(new BarEntry(4, Integer.parseInt(s_xfl[4])));
        mY4.add(new BarEntry(5, Integer.parseInt(s_xfl[5])));
        mY4.add(new BarEntry(6, Integer.parseInt(s_xfl[6])));

        mY5.add(new BarEntry(0, Integer.parseInt(s_hcksl[0])));
        mY5.add(new BarEntry(1, Integer.parseInt(s_hcksl[1])));
        mY5.add(new BarEntry(2, Integer.parseInt(s_hcksl[2])));
        mY5.add(new BarEntry(3, Integer.parseInt(s_hcksl[3])));
        mY5.add(new BarEntry(4, Integer.parseInt(s_hcksl[4])));
        mY5.add(new BarEntry(5, Integer.parseInt(s_hcksl[5])));
        mY5.add(new BarEntry(6, Integer.parseInt(s_hcksl[6])));

        mY6.add(new BarEntry(0, Integer.parseInt(s_hcgs[0])));
        mY6.add(new BarEntry(1, Integer.parseInt(s_hcgs[1])));
        mY6.add(new BarEntry(2, Integer.parseInt(s_hcgs[2])));
        mY6.add(new BarEntry(3, Integer.parseInt(s_hcgs[3])));
        mY6.add(new BarEntry(4, Integer.parseInt(s_hcgs[4])));
        mY6.add(new BarEntry(5, Integer.parseInt(s_hcgs[5])));
        mY6.add(new BarEntry(6, Integer.parseInt(s_hcgs[6])));

        mY7.add(new BarEntry(0, Integer.parseInt(s_tcc[0])));
        mY7.add(new BarEntry(1, Integer.parseInt(s_tcc[1])));
        mY7.add(new BarEntry(2, Integer.parseInt(s_tcc[2])));
        mY7.add(new BarEntry(3, Integer.parseInt(s_tcc[3])));
        mY7.add(new BarEntry(4, Integer.parseInt(s_tcc[4])));
        mY7.add(new BarEntry(5, Integer.parseInt(s_tcc[5])));
        mY7.add(new BarEntry(6, Integer.parseInt(s_tcc[6])));


    }

    private void setdata() {
        mX = new ArrayList<>();
        mY1 = new ArrayList<>();
        mY2 = new ArrayList<>();
        mY3 = new ArrayList<>();
        mY4 = new ArrayList<>();
        mY5 = new ArrayList<>();
        mY6 = new ArrayList<>();
        mY7 = new ArrayList<>();
        color = new ArrayList<>();
        addData();

        if (b) {
            dataSet = new BarDataSet(mY1, "");
            dataSet.setColors(Color.parseColor("#c13531"));
        }
        if (b1) {
            dataSet1 = new BarDataSet(mY2, "");
            dataSet1.setColors(Color.parseColor("#FF2F4554"));
        }

        if (b2) {
            dataSet2 = new BarDataSet(mY3, "");
            dataSet2.setColors(Color.parseColor("#FF65A1A9"));
        }
        if (b3) {
            dataSet3 = new BarDataSet(mY4, "");
            dataSet3.setColors(Color.parseColor("#FFD38165"));
        }
        if (b4) {
            dataSet4 = new BarDataSet(mY5, "");
            dataSet4.setColors(Color.parseColor("#FF90C6AD"));

        }
        if (b5) {
            dataSet5 = new BarDataSet(mY6, "");
            dataSet5.setColors(Color.parseColor("#FF749E82"));
        }
        if (b6) {
            dataSet6 = new BarDataSet(mY7, "");
            dataSet6.setColors(Color.parseColor("#FFC98522"));
        }
        dataSet.setDrawValues(false);
        dataSet1.setDrawValues(false);
        dataSet2.setDrawValues(false);
        dataSet3.setDrawValues(false);
        dataSet4.setDrawValues(false);
        dataSet5.setDrawValues(false);
        dataSet6.setDrawValues(false);

        data = new BarData(dataSet, dataSet1, dataSet2, dataSet3, dataSet4, dataSet5, dataSet6);
        data.setBarWidth(0.08f);
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setValueFormatter(new PercentFormatter());
        List<String> left = new ArrayList<>();
        left.add("");
        left.add("畅通");
        left.add("缓行");
        left.add("一般拥堵");
        left.add("中度拥堵");
        left.add("严重拥堵");

        yAxis.setValueFormatter(new IndexAxisValueFormatter(left));
        yAxis.setLabelCount(5);
        yAxis.setStartAtZero(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(mX));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7);

        // xAxis.setLabelRotationAngle(-60);
        xAxis.setDrawGridLines(false);

        YAxis yAxis1 = barChart.getAxisRight();
        yAxis1.setLabelCount(5);
        yAxis1.setStartAtZero(true);

        barChart.setData(data);
        barChart.getAxisLeft().setStartAtZero(true);
        barChart.groupBars(-0.5f, 0.3f, 0.02f);
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDescription(null);
    }

    private void huoqu1() {
        for (int i = 0; i < 7; i++) {
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("get_road_status").setJsonObject("UserName", "user1").setJsonObject("RoadId", "0").setVolleyLo(new VolleyLo() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {

                        String arr = jsonObject.getString("ROWS_DETAIL");
                        JSONArray jsonArray = new JSONArray(arr);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String zhi = jsonObject1.getString("state");
                            huqu[i] = zhi;
                        }
                        mlkfx.add(new lkfx(huqu[0] + "", huqu[1] + "", huqu[2] + "", huqu[3] + "", huqu[4] + "", huqu[5] + "", huqu[6] + ""));
                        if (mlkfx.size() == 7) {
                            setdata();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }).start();
        }
    }

    private void inviwew() {
        barChart = findViewById(R.id.barchart1);
        mlkfx = new ArrayList<>();
        xyl = findViewById(R.id.xyl);
        yyl = findViewById(R.id.yyl);
        xfl = findViewById(R.id.xfl);
        lxl = findViewById(R.id.lxl);
        hcksl = findViewById(R.id.hcksl);
        hcgs = findViewById(R.id.hcgs);
        tcc = findViewById(R.id.tcc);
        huqu = new String[7];
        s_hcgs = new String[7];
        s_yyl = new String[7];
        s_hcksl = new String[7];
        s_tcc = new String[7];
        s_xfl = new String[7];
        s_xyl = new String[7];
        s_lxl = new String[7];
//        left();
//        addActivity(this);
        setTitle("路况分析");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.xyl:
                if (b) {
                    dataSet.setVisible(false);
                    barChart.invalidate();
                    b = false;
                } else {
                    dataSet.setVisible(true);
                    barChart.invalidate();
                    b = true;
                }
                break;
            case R.id.lxl:
                if (b1) {
                    dataSet1.setVisible(false);
                    barChart.invalidate();
                    b1 = false;
                } else {
                    dataSet1.setVisible(true);
                    barChart.invalidate();
                    b1 = true;
                }
                break;
            case R.id.yyl:
                if (b2) {
                    dataSet2.setVisible(false);
                    barChart.invalidate();
                    b2 = false;
                } else {
                    dataSet2.setVisible(true);
                    barChart.invalidate();
                    b2 = true;
                }
                break;
            case R.id.xfl:
                if (b3) {
                    dataSet3.setVisible(false);
                    barChart.invalidate();
                    b3 = false;
                } else {
                    dataSet3.setVisible(true);
                    barChart.invalidate();
                    b3 = true;
                }
                break;
            case R.id.hcksl:
                if (b4) {
                    dataSet4.setVisible(false);
                    barChart.invalidate();
                    b4 = false;
                } else {
                    dataSet4.setVisible(true);
                    barChart.invalidate();
                    b4 = true;
                }
                break;
            case R.id.hcgs:
                if (b5) {
                    dataSet5.setVisible(false);
                    barChart.invalidate();
                    b5 = false;
                } else {
                    dataSet5.setVisible(true);
                    barChart.invalidate();
                    b5 = true;
                }
                break;
            case R.id.tcc:
                if (b6) {
                    dataSet6.setVisible(false);
                    barChart.invalidate();
                    b6 = false;
                } else {
                    dataSet6.setVisible(true);
                    barChart.invalidate();
                    b6 = true;
                }
                break;
        }
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
        title.setText("路况分析");
        title1 = (TextView) findViewById(R.id.title1);
        barchart1 = (BarChart) findViewById(R.id.barchart1);
    }
}
