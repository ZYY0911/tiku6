package com.example.a2019_12_13_tiku6.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.VP_adapter;
import com.example.a2019_12_13_tiku6.bean.SSHJ;

import java.util.ArrayList;
import java.util.List;

public class HCHJ_fragment extends Fragment {
    private ViewPager viewPager;
    private List<SSHJ>sshjs;
    private AppClient appClient;
    private LinearLayout layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.hchj_item,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intView();
        setViewPager();
        setShow();

    }
    private List<Fragment> fragments;
    private void setShow() {


    }

    private void setViewPager() {
        fragments = new ArrayList<>();
        fragments.add(new CO_Fragment());
        fragments.add(new WD_Fragment());
        fragments.add(new SD_Fragment());
        viewPager.setAdapter(new VP_adapter(getChildFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0 ; j<layout.getChildCount();j++){
                    ImageView imageView = (ImageView) layout.getChildAt(j);
                    if (j==i){
                        imageView.setImageResource(R.drawable.page_indicator_focused);
                    }else {
                        imageView.setImageResource(R.drawable.page_indicator_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        layout.removeAllViews();
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                imageView.setImageResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setImageResource(R.drawable.page_indicator_unfocused);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            layout.addView(imageView);
        }
    }

    private void intView() {
        viewPager=getView().findViewById(R.id.vp1);
        layout=getView().findViewById(R.id.linear1);
        appClient= (AppClient) getActivity().getApplication();
        sshjs = appClient.getSshjs();
    }
}
