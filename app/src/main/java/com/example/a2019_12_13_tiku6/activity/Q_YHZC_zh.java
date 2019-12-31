package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Q_YHZC_sql;

import org.litepal.LitePal;

import java.util.List;

public class Q_YHZC_zh extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_jt;
    private EditText et_yhm;
    private EditText et_yx;
    private Button bt_zh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__yhzc_zh);
        initView();
    }
    private void setSQl() {
        List<Q_YHZC_sql> litePalSQL2s = LitePal.findAll(Q_YHZC_sql.class);
        for (Q_YHZC_sql sql2 : litePalSQL2s) {
            if (et_yhm.getText().toString().equals(sql2.getYh())&&et_yx.getText().toString().equals(sql2.getYx())){
                Toast.makeText(this, sql2.getMima(), Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
    private void initView() {
        iv_jt = (ImageView) findViewById(R.id.iv_jt);
        iv_jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Q_YHZC_zh.this,Q_YHZC.class);
                startActivity(intent);
            }
        });
        et_yhm = (EditText) findViewById(R.id.et_yhm);
        et_yx = (EditText) findViewById(R.id.et_yx);
        bt_zh = (Button) findViewById(R.id.bt_zh);

        bt_zh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zh:
                setSQl();
                break;
        }
    }

}
