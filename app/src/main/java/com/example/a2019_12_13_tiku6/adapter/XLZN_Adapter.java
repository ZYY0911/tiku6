package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Duration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class XLZN_Adapter extends ArrayAdapter<Duration> {
    private int layout;
    private String start, end;

    public XLZN_Adapter(Context context, int resource, List<Duration> objects, String start, String end) {
        super(context, resource, objects);
        layout = resource;
        this.start = start;
        this.end = end;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Duration duration = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.xlzn_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemAction.setText(duration.getAction());
        holder.itemInfo.setText(duration.getInstruction());
        if (position == 0) {
            holder.itemName.setText(start);
        } else if (position == getCount() - 1) {
            holder.itemName.setText(end);
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_action)
        TextView itemAction;
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_info)
        TextView itemInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

