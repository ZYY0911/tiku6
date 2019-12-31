package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.MyData;
import com.example.a2019_12_13_tiku6.bean.Weather;
import com.example.a2019_12_13_tiku6.util.SimpData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class TQYB_Adapter1 extends ArrayAdapter<Weather> {
    private List<MyData> myData = new ArrayList<>();
    private String weekarr[] = {"周天","周一", "周二","周三", "周四", "周五","周六"};

    private int layout;

    public TQYB_Adapter1(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
        layout = resource;
        int day = Integer.parseInt(SimpData.fetData("dd", new Date()));
        Calendar calendar  =Calendar.getInstance();
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        Log.i("aa", "TQYB_Adapter1: "+week);
        int month = Integer.parseInt(SimpData.fetData("MM", new Date()));
        for (int i = 0; i < 6; i++) {
            myData.add(new MyData(month + "月" + day + "日", weekarr[week]));
            day++;
            week++;
            if (day == 32) {
                day = 1;
                month++;
            }
            if (week == 7) {
                week = 0;
            }
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Weather weather = getItem(position);
        MyData data = myData.get(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemDay.setText(data.getDat());
        holder.itemWeek.setText(data.getWeek());
        holder.itemInfo.setText(weather.getWeather());
        switch (weather.getWeather()) {
            case "晴":
                holder.itemImage.setImageResource(R.mipmap.weather);
                break;
            case "小雨":
                holder.itemImage.setImageResource(R.mipmap.rain);
                break;
            case "阴":
                holder.itemImage.setImageResource(R.mipmap.cloudy);
                break;
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_day)
        TextView itemDay;
        @BindView(R.id.item_week)
        TextView itemWeek;
        @BindView(R.id.item_info)
        TextView itemInfo;
        @BindView(R.id.item_image)
        ImageView itemImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
