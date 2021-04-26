package com.gzeic.smartcity01.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoDenLuActivity;
import com.gzeic.smartcity01.WoDinDanActivity;
import com.gzeic.smartcity01.WoFanKuiActivity;
import com.gzeic.smartcity01.WoMiMaActivity;
import com.gzeic.smartcity01.WoZiLiaoActivity;
import com.gzeic.smartcity01.bean.MyDataBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EFragment extends BaseFragment implements View.OnClickListener{

    private ImageView ivIcon;
    private TextView tvNickname;
    private RelativeLayout rlSetting;
    private RelativeLayout rlDindan;
    private RelativeLayout rlSetpass;
    private RelativeLayout rlYijian;
    private TextView tvExit;
    private MyDataBean.DataBean dataBean;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e, container, false);
        initView(view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/system/user/1", token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                SharedPreferences userdata = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
                userdata.edit().putString("userdata",json).apply();
                MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
                if (myDataBean.getCode() == 200) {
                    dataBean = myDataBean.getData();
                    showData();
                } else {

                }
            }
        });

        return view;
    }

    public void showData() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(getContext()).load("http://" + getServerIp() + dataBean.getAvatar()).error(R.mipmap.ic_launcher).into(ivIcon);
                tvNickname.setText(dataBean.getNickName());
            }
        });
    }

    private void initView(View view) {
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvNickname = (TextView) view.findViewById(R.id.tv_nickname);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        rlDindan = (RelativeLayout) view.findViewById(R.id.rl_dindan);
        rlSetpass = (RelativeLayout) view.findViewById(R.id.rl_setpass);
        rlYijian = (RelativeLayout) view.findViewById(R.id.rl_yijian);
        tvExit = (TextView) view.findViewById(R.id.tv_exit);
        rlSetting.setOnClickListener(this);
        rlDindan.setOnClickListener(this);
        rlSetpass.setOnClickListener(this);
        rlYijian.setOnClickListener(this);
        tvExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_setting) {
            startActivity(new Intent(getContext(), WoZiLiaoActivity.class));
        } else if (id == R.id.rl_dindan) {
            startActivity(new Intent(getContext(), WoDinDanActivity.class));
        } else if (id == R.id.rl_setpass) {
            startActivity(new Intent(getContext(), WoMiMaActivity.class));
        } else if (id == R.id.rl_yijian) {
            startActivity(new Intent(getContext(), WoFanKuiActivity.class));
        } else if (id == R.id.tv_exit) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("token", "").apply();
            startActivity(new Intent(getContext(), WoDenLuActivity.class));
            getActivity().finish();
        }
    }
}