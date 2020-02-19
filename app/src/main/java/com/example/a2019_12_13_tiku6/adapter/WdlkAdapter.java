package com.example.a2019_12_13_tiku6.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.WDLK;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/10/30 0030.
 */

public class WdlkAdapter extends ArrayAdapter<WDLK> {

    private int mLayout;

    public WdlkAdapter(@NonNull Context context, int resource, @NonNull List<WDLK> objects) {
        super(context, resource, objects);
        mLayout = resource;
    }

    public interface SetData{
        void setdata(int index, int lx);
    }

    private SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WDLK wdlk = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(mLayout, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.lkName.setText(wdlk.getLk());
        viewHolder.lkLvd.setText(wdlk.getLvd()+"");
        viewHolder.lkHud.setText(wdlk.getHud()+"");
        viewHolder.lkHod.setText(wdlk.getHod()+"");
        viewHolder.lkPz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setdata(position,1);
            }
        });
        viewHolder.lkHeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setdata(position,2);
            }
        });
        viewHolder.lkShBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setdata(position,3);
            }
        });
        viewHolder.lkHeValue.setText(wdlk.getHvalue()+"");
        viewHolder.lkHeValue2.setText(wdlk.getHvalue()+"");
        viewHolder.lkHeZt.setText(wdlk.getHzt());
        if (wdlk.getHzt().equals("绿灯")){
            viewHolder.lkHeValue.setBackgroundResource(R.drawable.lvdeng);
        }else if (wdlk.getHzt().equals("黄灯")){
            viewHolder.lkHeValue.setBackgroundResource(R.drawable.huangdeng);
        }else if (wdlk.getHzt().equals("红灯")){
            viewHolder.lkHeValue.setBackgroundResource(R.drawable.hongdeng);
        }
        viewHolder.lkShValue.setText(wdlk.getSvalue()+"");
        viewHolder.lkShValue2.setText(wdlk.getSvalue()+"");
        viewHolder.lkShZt.setText(wdlk.getSzt());
        if (wdlk.getSzt().equals("绿灯")){
            viewHolder.lkShValue.setBackgroundResource(R.drawable.lvdeng);
        }else if (wdlk.getSzt().equals("黄灯")){
            viewHolder.lkShValue.setBackgroundResource(R.drawable.huangdeng);
        }else if (wdlk.getSzt().equals("红灯")){
            viewHolder.lkShValue.setBackgroundResource(R.drawable.hongdeng);
        }
        if (wdlk.isQx()){
            viewHolder.lkPz.setVisibility(View.VISIBLE);
        }else {
            viewHolder.lkPz.setVisibility(View.GONE);
        }
        viewHolder.lkHeBt.setEnabled(wdlk.isHe());
        viewHolder.lkShBt.setEnabled(wdlk.isSh());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.lk_name)
        TextView lkName;
        @BindView(R.id.lk_lvd)
        TextView lkLvd;
        @BindView(R.id.lk_hud)
        TextView lkHud;
        @BindView(R.id.lk_hod)
        TextView lkHod;
        @BindView(R.id.lk_pz)
        Button lkPz;
        @BindView(R.id.lk_he_zt)
        TextView lkHeZt;
        @BindView(R.id.lk_he_value2)
        TextView lkHeValue2;
        @BindView(R.id.lk_he_value)
        TextView lkHeValue;
        @BindView(R.id.lk_sh_zt)
        TextView lkShZt;
        @BindView(R.id.lk_sh_value2)
        TextView lkShValue2;
        @BindView(R.id.lk_sh_value)
        TextView lkShValue;
        @BindView(R.id.lk_he_bt)
        Button lkHeBt;
        @BindView(R.id.lk_sh_bt)
        Button lkShBt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
