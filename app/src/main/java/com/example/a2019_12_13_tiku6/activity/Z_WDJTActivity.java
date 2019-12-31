package com.example.a2019_12_13_tiku6.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.fragment.DLHJ_Framgent;
import com.example.a2019_12_13_tiku6.fragment.WDLK_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_WDJTActivity extends BaseActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.tv_wdlk)
    TextView tvWdlk;
    @BindView(R.id.tv_dlhj)
    TextView tvDlhj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdjt_layout);
        ButterKnife.bind(this);
        initView();
        replace(new WDLK_Fragment());
    }

    private void initView() {
        title.setText("我的交通");
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, fragment).commit();
    }

    @OnClick({R.id.change, R.id.tv_wdlk, R.id.tv_dlhj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.tv_wdlk:
                replace(new WDLK_Fragment());
                tvWdlk.setTextSize(35);
                tvDlhj.setTextSize(30);
                break;
            case R.id.tv_dlhj:
                replace(new DLHJ_Framgent());
                tvDlhj.setTextSize(35);
                tvWdlk.setTextSize(30);
                break;
        }
    }
}
