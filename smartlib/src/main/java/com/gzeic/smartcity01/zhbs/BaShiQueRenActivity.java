package com.gzeic.smartcity01.zhbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.gzeic.smartcity01.DinDanActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.BashiBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaShiQueRenActivity extends BaseActivity {

    private ImageView metroBase;
    private EditText name;
    private EditText phone;
    private EditText scdd;
    private EditText xcdd;
    private Button btnNext;
    BashiBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_ba_shi_que_ren);
        initView();
        String xianlu = getSP("xianlu");
        rowsBean = new Gson().fromJson(xianlu, BashiBean.RowsBean.class);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("start",rowsBean.getFirst());
                    jsonObject.put("end",rowsBean.getEnd());
                    jsonObject.put("userName",name.getText().toString());
                    jsonObject.put("userTel",phone.getText().toString());
                    jsonObject.put("price",rowsBean.getPrice());
                    jsonObject.put("path",rowsBean.getName());
                    jsonObject.put("status","1");
                    jsonObject.put("userId","1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getTools().sendPostRequestToken(jsonObject, "http://"+getServerIp()+"/userinfo/busOrders", getToken(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        BashiBean bashiBean = new Gson().fromJson(json, BashiBean.class);
                        if (bashiBean.getCode()==200){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("提交成功");
                                    startActivity(new Intent(BaShiQueRenActivity.this, DinDanActivity.class));
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        scdd = (EditText) findViewById(R.id.scdd);
        xcdd = (EditText) findViewById(R.id.xcdd);
        btnNext = (Button) findViewById(R.id.btn_next);
    }
}