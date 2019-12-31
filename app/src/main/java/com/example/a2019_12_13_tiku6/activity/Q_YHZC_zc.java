package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Q_YHZC_sql;

public class Q_YHZC_zc extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_jt;
    private EditText zc_yhm;
    private EditText zc_yx;
    private EditText zc_mima;
    private EditText zc_mima2;
    private Button bt_tj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__yhzc_zc);
        initView();
    }

    private boolean cheankYHM(String s) {
        return s.matches("(.*[A-Za-z].*){4,}");
    }

    private boolean cheanksz(String s) {
        return s.matches("(.*[0-9].*){6,}");
    }

    private void setData() {
        if (!cheankYHM(zc_yhm.getText().toString())) {
            Toast.makeText(this, "不少于4位字母", Toast.LENGTH_SHORT).show();
        }

         if (!cheanksz(zc_yx.getText().toString())) {
             Toast.makeText(this, "邮箱不少于6位数字", Toast.LENGTH_SHORT).show();
             return;
         }
        if (!cheanksz(zc_mima.getText().toString())) {
            Toast.makeText(this, "密码不少于6位数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (zc_mima.getText().toString().length()!=zc_mima2.getText().toString().length()){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }else {
            Q_YHZC_sql bean = new Q_YHZC_sql(zc_yhm.getText().toString(), zc_yx.getText().toString(), zc_mima.getText().toString());
            bean.save();
        }

    }

    private void initView() {
        iv_jt = (ImageView) findViewById(R.id.iv_jt);
        iv_jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Q_YHZC_zc.this,Q_YHZC.class);
                startActivity(intent);
            }
        });
        zc_yhm = (EditText) findViewById(R.id.zc_yhm);
        zc_yx = (EditText) findViewById(R.id.zc_yx);
        zc_mima = (EditText) findViewById(R.id.zc_mima);
        zc_mima2 = (EditText) findViewById(R.id.zc_mima2);
        bt_tj = (Button) findViewById(R.id.bt_tj);
        bt_tj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tj:
                setData();
                break;
        }
    }
}
