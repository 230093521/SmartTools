package com.gzeic.smartcity01.shjf;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DianFeiBean;
import com.gzeic.smartcity01.bean.ShuiFeiBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llShuifei;
    private LinearLayout llDianfei;
    private Button btnHuhao;
    private ImageView metroBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_sh);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        llShuifei = (LinearLayout) findViewById(R.id.ll_shuifei);
        llDianfei = (LinearLayout) findViewById(R.id.ll_dianfei);
        btnHuhao = (Button) findViewById(R.id.btn_huhao);
        llShuifei.setOnClickListener(this);
        llDianfei.setOnClickListener(this);
        btnHuhao.setOnClickListener(this);
        metroBase = (ImageView) findViewById(R.id.metro_base);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_shuifei) {
            getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/feeslist/list? userId=2&classifyId=2&pageNum=1&pageSize=10", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    ShuiFeiBean shuiFeiBean = new Gson().fromJson(json, ShuiFeiBean.class);
                    List<ShuiFeiBean.RosBean> ros = shuiFeiBean.getRos();
                    putSP("huhao", String.valueOf(ros.get(0).getDoorNo()));
                    Log.i(TAG, "onResponse:shuifei " + json);
                    putSP("fy", "001");
                    startActivity(new Intent(ShActivity.this, ShJfActivity.class));
                }
            });
        } else if (id == R.id.ll_dianfei) {
            getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/feeslist/list? userId=2&classifyId=1&pageNum=1&pageSize=10", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    DianFeiBean shuiFeiBean = new Gson().fromJson(json, DianFeiBean.class);
                    List<DianFeiBean.RowsBean> ros = shuiFeiBean.getRows();
                    putSP("huhao", String.valueOf(ros.get(0).getDoorNo()));
                    Log.i(TAG, "onResponse:dianfei " + json);
                    putSP("fy", "002");
                    startActivity(new Intent(ShActivity.this, ShJfActivity.class));
                }
            });
        } else if (id == R.id.btn_huhao) {
            startActivity(new Intent(ShActivity.this, ShHhglActivity.class));
        }
    }
}