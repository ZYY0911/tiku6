package com.example.a2019_12_13_tiku6.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.SSHJ;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class SD_Fragment extends Fragment {
    private LineChart lineChart;
    private List<SSHJ> sshjs;
    private List<Entry> entries;
    private List<Integer> circle;
    private LineDataSet dataSet;
    private LineData data;
    private List<String> xValue;
    private AppClient appClient;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            setData();
           return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ssxs_fragment3, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lineChart = getView().findViewById(R.id.lineChart);
        appClient = (AppClient) getActivity().getApplication();
        entries = new ArrayList<>();
        circle = new ArrayList<>();
        xValue = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (true);

            }
        }.start();

    }

    private void setData() {
        entries.clear();
        circle.clear();
        xValue.clear();
        circle.clear();
        sshjs = appClient.getSshjs();
        if (sshjs.size() == 0) {
            return;
        }
        int m = 3;
        for (int i = 0; i < sshjs.size(); i++) {
            SSHJ sshj = sshjs.get(i);
            int a = sshj.getHumidity();
            if (xValue.size() == 0) {
                xValue.add("");
                xValue.add("");
                xValue.add("");
            }
            circle.add(Color.BLACK);
            entries.add(new Entry(m, a));
            xValue.add(sshj.getTime());
            xValue.add("");
            xValue.add("");
            m += 3;
        }
        dataSet = new LineDataSet(entries, "");
        dataSet.setCircleColors(circle);
        dataSet.setColor(Color.BLACK);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleSize(7f);
        dataSet.setValueTextSize(25);
        dataSet.setLineWidth(3f);
        data = new LineData(dataSet);
        setX();
        setY();
        lineChart.setData(data);
        lineChart.getDescription().setText("");
        lineChart.getDescription().setTextSize(200);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.invalidate();
    }


    private void setY() {
        YAxis yAxisR = lineChart.getAxisRight();
        yAxisR.setStartAtZero(true);
        yAxisR.setEnabled(false);
        YAxis yAxis1 = lineChart.getAxisLeft();
        yAxis1.setTextSize(25f);
        yAxis1.setStartAtZero(true);
    }

    private void setX() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMaximum(17);
        xAxis.setLabelRotationAngle(-50);
        xAxis.setTextSize(25f);
    }
}
