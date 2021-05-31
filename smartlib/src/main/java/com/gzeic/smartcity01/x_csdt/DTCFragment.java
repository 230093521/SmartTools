package com.gzeic.smartcity01.x_csdt;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTccmBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DTCFragment extends BaseFragment {


    private ImageView ewmtp;
    private Button btnBtn;
    DTccmBean.DataDTO dTccmBeanData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dt_c, container, false);
        initView(view);
        getCcm();
        btnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = btnBtn.getText().toString();
                if (toString.equals("注销")){
                    ewmtp.setVisibility(View.GONE);
                    zhuxiao(String.valueOf(dTccmBeanData.getId()));
                }else {
                    putSP("ccmxx",new Gson().toJson(dTccmBeanData));
                    startActivity(new Intent(getContext(),CsdtCcmlqActivity.class));
                }
            }
        });
        return view;
    }

    private void zhuxiao(String id) {
        getTools().sendDeleteRequestToken("http://" + getServerIp() + "/prod-api/api/metro/card/"+id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTccmBean dTccmBean = new Gson().fromJson(string, DTccmBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnBtn.setText("领取乘车卡");
                        showToast(dTccmBean.getMsg());
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getCcm();
    }

    private void getCcm() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/card", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: "+string);
                DTccmBean dTccmBean = new Gson().fromJson(string, DTccmBean.class);
                if (dTccmBean.getCode()==200){
                    dTccmBeanData = dTccmBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ewmtp.setVisibility(View.VISIBLE);
                            btnBtn.setText("注销");
                            Log.i(TAG, "run: 二维码生成");
                            Bitmap qrCodeBitmap = getTools().createQRCodeBitmap(dTccmBeanData.getCardNum(), 200, 200, "utf-8", "M", "5", Color.BLACK, Color.WHITE);
                            ewmtp.setImageBitmap(qrCodeBitmap);
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        ewmtp = (ImageView) view.findViewById(R.id.ewmtp);
        btnBtn = (Button) view.findViewById(R.id.btn_btn);
    }
}