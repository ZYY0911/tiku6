package com.example.a2019_12_13_tiku6.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a2019_12_13_tiku6.AppClient;
import com.example.a2019_12_13_tiku6.R;
import com.example.a2019_12_13_tiku6.adapter.WdlkAdapter;
import com.example.a2019_12_13_tiku6.bean.WDLK;
import com.example.a2019_12_13_tiku6.dialog.HldDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-31
 */
@SuppressLint("ValidFragment")
public class WdlkFragment extends Fragment {
    @BindView(R.id.wdlk_list)
    ListView wdlkList;
    Unbinder unbinder;
    private List<WDLK> mList=new ArrayList<>();
    private WdlkAdapter mAdapter;
    private Context context;
    private FragmentManager fragmentManager;
    private Dialog dialog;
    private boolean isLoop=true;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what==0x001){
                mAdapter.notifyDataSetChanged();
            }else {
                if (dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
            return false;
        }
    });

    public WdlkFragment(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wdlk_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.ts_dialog,null,false);
        builder.setView(view);
        dialog=builder.show();
        initData();
    }

    private void initData() {
        if (AppClient.getUser().equals("user1")){
            mList.add(new WDLK("路口1",23,3,20,20,23,"绿灯","红灯",true,true,true));
            mList.add(new WDLK("路口2",27,5,22,27,22,"红灯","绿灯",true,true,true));
            mList.add(new WDLK("路口3",33,4,29,4,3,"黄灯","绿灯",true,true,true));
            mList.add(new WDLK("路口4",26,7,19,6,6,"红灯","黄灯",true,true,true));
            mList.add(new WDLK("路口5",17,3,14,17,14,"红灯","绿灯",true,true,true));
        }else {
            mList.add(new WDLK("路口1",23,3,20,20,23,"绿灯","红灯",true,true,false));
            mList.add(new WDLK("路口2",27,5,22,27,22,"红灯","绿灯",true,true,false));
            mList.add(new WDLK("路口3",33,4,29,4,3,"黄灯","绿灯",true,true,false));
            mList.add(new WDLK("路口4",26,7,19,6,6,"红灯","黄灯",true,true,false));
            mList.add(new WDLK("路口5",17,3,14,17,14,"红灯","绿灯",true,true,false));
        }

        mAdapter=new WdlkAdapter(context,R.layout.wdlk_item,mList);
        wdlkList.setAdapter(mAdapter);
        mAdapter.setData(new WdlkAdapter.SetData() {
            @Override
            public void setdata(final int index, int lx) {
                if (lx==2){
                    WDLK wdlk=mList.get(index);
                    wdlk.setHvalue(30);
                    wdlk.setHzt("绿灯");
                    wdlk.setHe(false);
                    wdlk.setSvalue(30);
                    wdlk.setSzt("红灯");
                    wdlk.setSh(false);
                    mList.set(index,wdlk);
                }else if (lx==3){
                    WDLK wdlk=mList.get(index);
                    wdlk.setHvalue(30);
                    wdlk.setHzt("红灯");
                    wdlk.setHe(false);
                    wdlk.setSvalue(30);
                    wdlk.setSzt("绿灯");
                    wdlk.setSh(false);
                    mList.set(index,wdlk);
                }else if (lx==1){
                    HldDialog dialog=new HldDialog(context,(index+1)+"");
                    dialog.setData(new HldDialog.SetData() {
                        @Override
                        public void setdata(int Hod, int Hud, int Lvd) {
                            WDLK wdlk=mList.get(index);
                            wdlk.setHod(Hod);
                            wdlk.setHud(Hud);
                            wdlk.setLvd(Lvd);
                            mList.set(index,wdlk);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                    dialog.show(fragmentManager,"dialog");
                }
            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                do {
                    for (int i=0;i<mList.size();i++){
                        WDLK wdlk= mList.get(i);
                        String hed=wdlk.getHzt();
                        if (hed.equals("红灯")){
                            int value=wdlk.getHvalue();
                            if (value>0){
                                value--;
                                wdlk.setHvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setHzt("黄灯");
                                wdlk.setHvalue(wdlk.getHud());
                                wdlk.setHe(true);
                                mList.set(i,wdlk);
                            }
                        }else if (hed.equals("黄灯")){
                            int value=wdlk.getHvalue();
                            if (value>0){
                                value--;
                                wdlk.setHvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setHe(true);
                                wdlk.setHzt("绿灯");
                                wdlk.setHvalue(wdlk.getLvd());
                                mList.set(i,wdlk);
                            }
                        }else if (hed.equals("绿灯")){
                            int value=wdlk.getHvalue();
                            if (value>0){
                                value--;
                                wdlk.setHvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setHe(true);
                                wdlk.setHzt("红灯");
                                wdlk.setHvalue(wdlk.getHod());
                                mList.set(i,wdlk);
                            }
                        }
                        String shd=wdlk.getSzt();
                        if (shd.equals("红灯")){
                            int value=wdlk.getSvalue();
                            if (value>0){
                                value--;
                                wdlk.setSvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setSh(true);
                                wdlk.setSzt("黄灯");
                                wdlk.setSvalue(wdlk.getHud());
                                mList.set(i,wdlk);
                            }
                        }else if (shd.equals("黄灯")){
                            int value=wdlk.getSvalue();
                            if (value>0){
                                value--;
                                wdlk.setSvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setSh(true);
                                wdlk.setSzt("绿灯");
                                wdlk.setSvalue(wdlk.getLvd());
                                mList.set(i,wdlk);
                            }
                        }else if (shd.equals("绿灯")){
                            int value=wdlk.getSvalue();
                            if (value>0){
                                value--;
                                wdlk.setSvalue(value);
                                mList.set(i,wdlk);
                            }else {
                                wdlk.setSh(true);
                                wdlk.setSzt("红灯");
                                wdlk.setSvalue(wdlk.getHod());
                                mList.set(i,wdlk);
                            }
                        }
                    }
                    handler.sendEmptyMessage(0x001);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (isLoop);
            }
        }.start();
        handler.sendEmptyMessageDelayed(0x002,1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoop=false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
