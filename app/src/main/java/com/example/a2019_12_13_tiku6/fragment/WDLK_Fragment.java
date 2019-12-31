package com.example.a2019_12_13_tiku6.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.bean.HLD;
import com.example.a2019_12_13_tiku6.bean.WDLK;
import com.example.a2019_12_13_tiku6.net.VolleyLo;
import com.example.a2019_12_13_tiku6.net.VolleyTo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
public class WDLK_Fragment extends Fragment {
    @BindView(R.id.wdlk_list)
    ListView wdlkList;
    Unbinder unbinder;
    private List<HLD> hlds;
    private List<WDLK> wdlks,wdlks1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wdlk_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
    }

    private void setData() {
        if (hlds == null) hlds = new ArrayList<>();
        hlds.clear();
        for (int i = 1; i <= 5; i++) {
            setVolley(i);
        }
    }

    private void setVolley(int i) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_traffic_light")
                .setJsonObject("UserName", "user1")
                .setJsonObject("number", i)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        Gson gson = new Gson();
                        hlds.add(gson.fromJson(jsonArray.optJSONObject(0).toString(), HLD.class));
                        if (hlds.size() == 5) {
                            setListView();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        if (wdlks==null) wdlks = new ArrayList<>();
        wdlks.clear();
        if (wdlks1==null) wdlks1 = new ArrayList<>();
        wdlks1.clear();
        for (int i = 0; i < hlds.size(); i++) {
            HLD hld  =hlds.get(i);
            wdlks.add(new WDLK(hld.getRed(),hld.getYellow(),hld.getGreen(),hld.getRed(),hld.getYellow(),hld.getGreen(),"红"));
            wdlks1.add(new WDLK(hld.getRed(),hld.getYellow(),hld.getGreen(),hld.getRed(),hld.getYellow(),hld.getGreen(),"绿"));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
