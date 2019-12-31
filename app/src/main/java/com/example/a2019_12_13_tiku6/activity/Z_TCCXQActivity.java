package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Tcc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_TCCXQActivity extends BaseActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xq_dd)
    TextView xqDd;
    @BindView(R.id.xq_jl)
    TextView xqJl;
    @BindView(R.id.xq_layout)
    LinearLayout xqLayout;
    @BindView(R.id.xq_fl)
    TextView xqFl;
    @BindView(R.id.xq_cw)
    TextView xqCw;
    @BindView(R.id.xq_ck)
    TextView xqCk;
    @BindView(R.id.root)
    TextView root;
    private Tcc tccs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tccxq_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        xqName.setText(tccs.getParking_name());
        root.setText(tccs.getRoot());
        xqDd.setText(tccs.getLocation());
        xqJl.setText(tccs.getDistance() + "米");
        xqCw.setText(tccs.getCarport() + "/600");
        xqCk.setText(tccs.getReference());
        xqFl.setText(tccs.getPrice() + "元/" + tccs.getRate_type());
    }

    private void initView() {
        title.setText("停车场详情");
        tccs = (Tcc) getIntent().getSerializableExtra("infos");
        change.setImageResource(R.mipmap.arrow_left_);
    }


    @OnClick({R.id.change, R.id.xq_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.xq_layout:
                Intent intent = new Intent(this, Z_LXDTActivity.class);
                intent.putExtra("infos", tccs);
                startActivity(intent);
                break;
        }
    }
}
