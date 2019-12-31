package com.example.a2019_12_13_tiku6.activity;

import android.content.Intent;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.MainActivity;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.util.ShowDilaog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class Z_IPSzActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.ip_1)
    EditText ip1;
    @BindView(R.id.ip_2)
    EditText ip2;
    @BindView(R.id.ip_3)
    EditText ip3;
    @BindView(R.id.ip_4)
    EditText ip4;
    @BindView(R.id.setting)
    Button setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kipsz_layout);
        ButterKnife.bind(this);
        initView();
        ininLisent();
        add(ip2);
        add(ip3);
        add(ip4);
    }

    private void ininLisent() {
        ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    if (Integer.parseInt(s.toString())>255&&Integer.parseInt(s.toString())>0){
                        ip1.setText("255");
                        ip1.setSelection(3);
                        Toast.makeText(Z_IPSzActivity.this, "只能输入1～255", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void add(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    if (Integer.parseInt(s.toString())>255){
                        editText.setText("255");
                        editText.setSelection(3);
                        Toast.makeText(Z_IPSzActivity.this, "只能输入0～255", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        title.setText("IP设置");
    }

    @OnClick({R.id.change, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.setting:
                String ip_1 = ip1.getText().toString().trim();
                String ip_2 = ip2.getText().toString().trim();
                String ip_3 = ip3.getText().toString().trim();
                String ip_4 = ip4.getText().toString().trim();
                if (ip_1.equals("")||ip_2.equals("")||ip_3.equals("")||ip_4.equals("")){
                    ShowDilaog.ShowDialog("Ip地址不正确",this);
                    return;
                }
                AppClient.setIp(ip_1+"."+ip_2+"."+ip_3+"."+ip_4);
                Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

