package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.GRZX_bean;

import java.util.List;

public class GRAX_ADapter extends ArrayAdapter<GRZX_bean> {
    private int layout;

    public GRAX_ADapter(Context context, int resource, List<GRZX_bean> objects) {
        super(context, resource, objects);
        layout = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GRZX_bean grzx = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        ImageView my_iamge = view.findViewById(R.id.my_iamge);
        TextView my_info = view.findViewById(R.id.my_info);
        switch (grzx.getPlate()){
            case "鲁A10001":
                my_iamge.setImageResource(R.drawable.benchi);
                break;
            case "鲁A10004":
                my_iamge.setImageResource(R.drawable.voervo);
                break;
            case "鲁A10003":
                my_iamge.setImageResource(R.drawable.zhonghua);
                break;
        }
        my_info.setText(grzx.getPlate()+"       余额:"+grzx.getBalance());
        return view;
    }
}
