package com.example.a2019_12_13_tiku6.fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.Sense;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class DLHJ_Framgent extends Fragment {
    @BindView(R.id.dl_pm)
    TextView dlPm;
    @BindView(R.id.dl_ts)
    TextView dlTs;
    @BindView(R.id.dl_video)
    VideoView dlVideo;
    @BindView(R.id.dl_gzdq)
    TextView dlGzdq;
    @BindView(R.id.dl_ts2)
    TextView dlTs2;
    @BindView(R.id.dl_value)
    TextView dlValue;
    @BindView(R.id.dl_value2)
    TextView dlValue2;
    Unbinder unbinder;
    private Sense sense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlhj_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setVolley();
    }

    private void initData() {
        dlVideo.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.car1));
        dlVideo.start();
        dlVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                dlVideo.start();
            }
        });
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(3000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        sense = gson.fromJson(jsonObject.toString(), Sense.class);
                        int pm = sense.getPm25();
                        if (pm > 200) {
                            dlTs.setText("友情提示：PM2.5大于80，不适合出行。");
                            showNotificationView("PM2.5大于80，不适合出行。", 1);
                            dlVideo.setVisibility(View.VISIBLE);
                            dlVideo.start();
                        } else {
                            dlTs.setText("友情提示：适合出行");
                            dlVideo.setVisibility(View.GONE);
                        }
                        int gz = sense.getIllumination();
                        if (gz > 4000) {
                            dlTs2.setText("友情提示：光照较强，出行请戴墨镜");
                            showNotificationView("光照较强，出行请戴墨镜。", 2);
                        } else if (gz<1000) {
                            dlTs2.setText("友情提示：光照较弱，出行请开灯");
                            showNotificationView("光照较弱，出行请开灯。", 3);
                        } else {
                            dlTs2.setText("友情提示：适合出行");
                        }
                        dlValue.setPadding((gz/10),0,0,0);
                        dlValue2.setPadding(((gz/10)-50),0,0,0);
                        dlValue2.setText(gz+"");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).

                start();

    }


    private void showNotificationView(String message, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("智能交通管理系统")
                .setAutoCancel(true)
                .setContentText(message)
                .setWhen(System.currentTimeMillis());
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
