package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.FeiYongBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PayFeesActivity extends BaShiActivity {

    private ImageView metroBase;
    private TextView gongsi;
    private TextView huhaos;
    private EditText jine;
    private TextView humin;
    private TextView yue;
    private Button btnLogin;
    private TextView homeTitle;
    FeiYongBean.RowsBean shuifeis;
    FeiYongBean.RowsBean dianfeis;
    private ImageView feitu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_pay_fees);
        initView();
        final String fy = getSP("fy");
        final String huhao = getSP("huhao");
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/electricity/list?doorNo=" + "125" + "&userId=3", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                FeiYongBean feiYongBean = new Gson().fromJson(json, FeiYongBean.class);
                if (feiYongBean.getCode() == 200) {
                    List<FeiYongBean.RowsBean> rows = feiYongBean.getRows();
                    for (FeiYongBean.RowsBean row : rows) {
                        if (row.getLiveName().equals("水费")) {
                            shuifeis = row;
                        } else {
                            dianfeis = row;
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (fy.equals("001")) {
                                gongsi.setText(shuifeis.getChargeUnit());
                                huhaos.setText(huhao);
                                jine.setText(shuifeis.getElectricityMoney());
                                humin.setText(shuifeis.getOwnerName());
                                yue.setText(shuifeis.getBalance());
                                feitu.setImageResource(R.drawable.ic_shui);
                            } else {
                                homeTitle.setText("电费缴费");
                                gongsi.setText(dianfeis.getChargeUnit());
                                huhaos.setText(huhao);
                                jine.setText(dianfeis.getElectricityMoney());
                                humin.setText(dianfeis.getOwnerName());
                                yue.setText(dianfeis.getBalance());
                                feitu.setImageResource(R.drawable.ic_dian);
                            }
                        }
                    });
                }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fy.equals("001")) {
                    shuifeis.setElectricityMoney(jine.getText().toString());
                    putSP("feiyong", new Gson().toJson(shuifeis));
                } else {
                    dianfeis.setElectricityMoney(jine.getText().toString());
                    putSP("feiyong", new Gson().toJson(dianfeis));
                }
                startActivity(new Intent(PayFeesActivity.this, PaySelectActivity.class));
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        gongsi = (TextView) findViewById(R.id.gongsi);
        huhaos = (TextView) findViewById(R.id.huhao);
        jine = (EditText) findViewById(R.id.jine);
        humin = (TextView) findViewById(R.id.humin);
        yue = (TextView) findViewById(R.id.yue);
        btnLogin = (Button) findViewById(R.id.btn_login);
        homeTitle = (TextView) findViewById(R.id.home_title);
        feitu = (ImageView) findViewById(R.id.feitu);



    }
}