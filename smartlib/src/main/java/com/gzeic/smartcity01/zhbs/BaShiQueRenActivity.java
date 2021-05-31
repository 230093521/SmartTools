package com.gzeic.smartcity01.zhbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoDinDanActivity;
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
    BashiBean.RowsDTO rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_ba_shi_que_ren);
        initView();
        String xianlu = getSP("xianlu");
        name.setText(getSP("ccrxm"));
        phone.setText(getSP("ccrsjh"));
        scdd.setText(getSP("scdd"));
        xcdd.setText(getSP("xcdd"));
        rowsBean = new Gson().fromJson(xianlu, BashiBean.RowsDTO.class);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("start",getSP("scdd"));
                    jsonObject.put("end",getSP("xcdd"));
                    jsonObject.put("userName",name.getText().toString());
                    jsonObject.put("userTel",phone.getText().toString());
                    jsonObject.put("price",rowsBean.getPrice());
                    jsonObject.put("path",rowsBean.getName());
                    jsonObject.put("status",0);
                    jsonObject.put("userId",getUserdata().getUserId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getTools().sendPostRequestToken(jsonObject, "http://"+getServerIp()+"/prod-api/api/bus/order", getToken(), new Callback() {
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
                                    startActivity(new Intent(BaShiQueRenActivity.this, WoDinDanActivity.class));
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