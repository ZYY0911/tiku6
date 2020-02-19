package com.example.a2019_12_13_tiku6.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.a2019_12_13_tiku6.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2019-12-31
 */

@SuppressLint("ValidFragment")
public class HldDialog extends DialogFragment {
    @BindView(R.id.hld_cha)
    ImageView hldCha;
    @BindView(R.id.hld_hod)
    EditText hldHod;
    @BindView(R.id.hld_hud)
    EditText hldHud;
    @BindView(R.id.hld_lvd)
    EditText hldLvd;
    @BindView(R.id.hld_ok)
    Button hldOk;
    @BindView(R.id.hld_closs)
    Button hldCloss;
    Unbinder unbinder;
    private Context context;
    private String Lk;

    public HldDialog(Context context, String lk) {
        this.context = context;
        Lk = lk;
    }

    public interface SetData{
        void setdata(int Hod, int Hud, int Lvd);
    }

    private SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hld_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void showAlertDialog(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定",null);
        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.hld_cha, R.id.hld_ok, R.id.hld_closs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hld_cha:
                HldDialog.this.dismiss();
                break;
            case R.id.hld_ok:
                String ho=hldHod.getText().toString().trim();
                String hu=hldHud.getText().toString().trim();
                String lv=hldLvd.getText().toString().trim();
                if (ho.equals("")||hu.equals("")||lv.equals("")){
                    showAlertDialog("红绿灯时间不能为空");
                    return;
                }
                int hod=Integer.parseInt(ho);
                int hud=Integer.parseInt(hu);
                int lvd=Integer.parseInt(lv);
                if (!(hod>0&&hod<31)){
                    showAlertDialog("红灯时间只能为1-30");
                    return;
                }
                if (!(hud>0&&hud<31)){
                    showAlertDialog("黄灯时间只能为1-30");
                    return;
                }
                if (!(lvd>0&&lvd<31)){
                    showAlertDialog("绿灯时间只能为1-30");
                    return;
                }
                showAlertDialog(Lk+"号路口的红绿灯配置信息成功");
                data.setdata(hod,hud,lvd);
                HldDialog.this.dismiss();
                break;
            case R.id.hld_closs:
                HldDialog.this.dismiss();
                break;
        }
    }
}
