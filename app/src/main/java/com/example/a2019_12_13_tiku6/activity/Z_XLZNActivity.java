package com.example.a2019_12_13_tiku6.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.XLZN_Adapter;
import com.example.a2019_12_13_tiku6.bean.Duration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_XLZNActivity extends BaseActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.zn_qd)
    TextView znQd;
    @BindView(R.id.zn_zd)
    TextView znZd;
    @BindView(R.id.zn_fz)
    TextView znFz;
    @BindView(R.id.zn_lc)
    TextView znLc;
    @BindView(R.id.lxzn_list)
    ListView lxznList;
    private float distance, myTime;
    private List<Duration> durations;
    private XLZN_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xlzn_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        adapter = new XLZN_Adapter(this, R.layout.xlzn_item, durations, znQd.getText().toString(), znZd.getText().toString());
        lxznList.setAdapter(adapter);
        change.setImageResource(R.mipmap.arrow_left_);
    }


    private void initView() {
        title.setText("路线指南");

        distance = Float.parseFloat(getIntent().getStringExtra("distanceString"));
        myTime = Float.parseFloat(getIntent().getStringExtra("durationString"));
        znQd.setText(getIntent().getStringExtra("start"));
        znZd.setText(getIntent().getStringExtra("end"));
        AppClient appClient = (AppClient) getApplication();
        durations = appClient.getDurations();
        znFz.setText((int) (myTime / 60) + "分钟");
        znLc.setText((int) distance / 1000 + "公里");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
