package com.example.a2019_12_13_tiku6.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class BaseActivity extends AppCompatActivity {
    private Thread thread;
    private boolean isLoop = true;
    private boolean isSuccess = true;


    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Dialog dialog;
    private AlertDialog.Builder builder;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            builder = new AlertDialog.Builder(BaseActivity.this);
            View view = LayoutInflater.from(BaseActivity.this).inflate(R.layout.wlts_dialog, null, false);
            builder.setView(view);
            TextView setting = view.findViewById(R.id.setting);
            TextView exiting = view.findViewById(R.id.exiting);
            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            exiting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    setSuccess(true);
                    isLoop = true;
                }
            });
            dialog = builder.show();
            return false;
        }
    });

    @Override
    protected void onResume() {
        super.onResume();
        isLoop = true;
        isSuccess = true;
        setNet();
        Log.e("aaaaaa", "onResume: ");
        TextView textView = findViewById(R.id.title1);
        textView.setText("当前用户："+AppClient.getUser());
    }


    public void setNet() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                do {
                    Log.e("aaaaaa", "myTherad");
                    if (!isNetwork(BaseActivity.this)) {
                        if (isSuccess) {
                            if (dialog == null) {
                                Log.e("aaaaaaaaaaaaaa", "222222");
                                handler.sendEmptyMessage(0);
                            } else {
                                if (!dialog.isShowing()) {
                                    Log.e("aaaaaaaaaaaa", "11111111111");
                                    handler.sendEmptyMessage(0);
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        };
        thread.start();
    }

    public boolean isNetwork(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


    @Override
    protected void onStop() {
        super.onStop();
        isLoop = false;
        Log.e("aaaaaaa", "onStop: ");
    }


}
