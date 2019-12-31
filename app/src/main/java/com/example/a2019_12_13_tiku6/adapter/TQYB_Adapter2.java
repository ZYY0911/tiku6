package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Sense;
import com.example.a2019_12_13_tiku6.bean.Weather;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class TQYB_Adapter2 extends ArrayAdapter<Sense> {
    private int layout;
    private int iamge[] = {R.mipmap.light_index, R.mipmap.air_index, R.mipmap.sport_index, R.mipmap.dress_index
            , R.mipmap.cold_index, R.mipmap.clear_car_index};
    private List<Sense> senses;
    private int num;
    private String arr[] = {"紫外线指数", "空气污染指数", "运动指数", "穿衣指数", "感冒指数", "洗车指数"};

    public TQYB_Adapter2(Context context, int resource, List<Sense> objects) {
        super(context, resource, objects);
        layout = resource;
        senses = objects;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sense sense = senses.get(senses.size() - 1);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tqyb_item2, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemTitle.setText(arr[position]);
        holder.itemImage.setImageResource(iamge[position]);
        switch (position) {
            case 0:
                num = sense.getIllumination();
                if (num < 1000) {
                    holder.itemMessage.setText("弱");
                    holder.itemMessage.setTextColor(Color.parseColor("#4472c4"));
                } else if (num > 3000) {
                    holder.itemMessage.setText("强");
                    holder.itemMessage.setTextColor(Color.parseColor("#ff0000"));
                } else {
                    holder.itemMessage.setText("中等");
                    holder.itemMessage.setTextColor(Color.parseColor("#00b050"));
                }
                break;
            case 1:
                num = sense.getPm25();
                if (num < 35) {
                    holder.itemMessage.setText("优");
                    holder.itemMessage.setTextColor(Color.parseColor("#44bc68"));
                } else if (num > 35 && num < 75) {
                    holder.itemMessage.setText("良");
                    holder.itemMessage.setTextColor(Color.parseColor("#92d050"));
                } else if (num >= 75 && num < 115) {
                    holder.itemMessage.setText("轻度污染");
                    holder.itemMessage.setTextColor(Color.parseColor("#ff4400"));
                } else if (num >= 150) {
                    holder.itemMessage.setText("重度污染");
                    holder.itemMessage.setTextColor(Color.parseColor("#993300"));
                } else {
                    holder.itemMessage.setText("中度污染");
                    holder.itemMessage.setTextColor(Color.parseColor("#bf9000"));
                }
                break;
            case 2:
                num = sense.getCo2();
                if (num < 3000) {
                    holder.itemMessage.setText("适宜");
                    holder.itemMessage.setTextColor(Color.parseColor("#44bc68"));
                } else if (num > 6000) {
                    holder.itemMessage.setText("较不宜");
                    holder.itemMessage.setTextColor(Color.parseColor("#8149ac"));
                } else {
                    holder.itemMessage.setText("中");
                    holder.itemMessage.setTextColor(Color.parseColor("#ffc000"));
                }
                break;
            case 3:
                num = sense.getTemperature();
                if (num < 12) {
                    holder.itemMessage.setText("冷");
                    holder.itemMessage.setTextColor(Color.parseColor("#3462f4"));
                } else if (num > 12 && num < 21) {
                    holder.itemMessage.setText("舒适");
                    holder.itemMessage.setTextColor(Color.parseColor("#92d050"));
                } else if (num >= 21 && num < 35) {
                    holder.itemMessage.setText("温暖");
                    holder.itemMessage.setTextColor(Color.parseColor("#44bc68"));
                } else {
                    holder.itemMessage.setText("热");
                    holder.itemMessage.setTextColor(Color.parseColor("#ff0000"));
                }
                break;
            case 4:
                num = sense.getTemperature();
                if (num < 50) {
                    holder.itemMessage.setText("较易发");
                    holder.itemMessage.setTextColor(Color.parseColor("#ff0000"));
                } else {
                    holder.itemMessage.setText("少发");
                    holder.itemMessage.setTextColor(Color.parseColor("#ff4400"));
                }
                break;
            case 5:
                Random random = new Random();
                switch (random.nextInt(3)) {
                    case 1:
                        holder.itemMessage.setText("不适宜");
                        break;
                    case 2:
                        holder.itemMessage.setText("不太适宜");
                        break;
                    case 0:
                        holder.itemMessage.setText("适宜");
                        break;

                }
                holder.itemMessage.setTextColor(Color.BLACK);
                holder.itemNum.setTextColor(Color.TRANSPARENT);
                break;
        }
        holder.itemNum.setText(num + "");
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_num)
        TextView itemNum;
        @BindView(R.id.item_message)
        TextView itemMessage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
