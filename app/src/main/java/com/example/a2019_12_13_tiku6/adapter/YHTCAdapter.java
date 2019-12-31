package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Tcc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2019-12-31
 */

public class YHTCAdapter extends ArrayAdapter<Tcc> {

    private int mLayout;

    public YHTCAdapter(Context context, int resource, List<Tcc> objects) {
        super(context, resource, objects);
        mLayout = resource;
    }

    public interface MyClick{
        void myClick(int psotion);
    }
    private  MyClick click;

    public void setClick(MyClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Tcc tcc = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tccId.setText(tcc.getId()+"");
        viewHolder.tccName.setText(tcc.getParking_name());
        viewHolder.tccKw.setText("空车位" + tcc.getCarport() + "个，停车费" + tcc.getPrice());
        viewHolder.tccJl.setText(tcc.getLocation() + "," + tcc.getDistance() + "米");
        if (tcc.getRoot().equals("不对外开放")) {
            viewHolder.tccTv.setText("关闭");
            viewHolder.tccLayout.setBackgroundColor(Color.parseColor("#cccccc"));
            viewHolder.tccJt.setVisibility(View.GONE);
            viewHolder.tccTv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tccJt.setVisibility(View.VISIBLE);
            viewHolder.tccTv.setVisibility(View.GONE);
            viewHolder.tccJt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.myClick(position);
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tcc_id)
        TextView tccId;
        @BindView(R.id.tcc_name)
        TextView tccName;
        @BindView(R.id.tcc_kw)
        TextView tccKw;
        @BindView(R.id.tcc_jl)
        TextView tccJl;
        @BindView(R.id.tcc_jt)
        ImageView tccJt;
        @BindView(R.id.tcc_tv)
        TextView tccTv;
        @BindView(R.id.tcc_layout)
        LinearLayout tccLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
