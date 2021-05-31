package com.gzeic.smartcity01.x_zc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.TinCheJiLuBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcCnzcActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private TextView homeSearchBase;
    private TextView name;
    private TextView cheweihao;
    private TextView weizhi;
    private LinearLayout cnzc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_cnzc);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClwz(homeEditSearch.getText().toString());
            }
        });
    }

    private void getClwz(String chepai) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/lot/record/list?plateNumber=" + chepai, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final TinCheJiLuBean tinCheJiLuBean = new Gson().fromJson(string, TinCheJiLuBean.class);
                if (tinCheJiLuBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tinCheJiLuBean.getTotal()>0){
                                cnzc.setVisibility(View.VISIBLE);
                                TinCheJiLuBean.RowsDTO rowsDTO = tinCheJiLuBean.getRows().get(0);
                                name.setText(rowsDTO.getParkName());
                                cheweihao.setText(rowsDTO.getParkNo()+"号车位");
                                weizhi.setText(rowsDTO.getAddress());
                            }else {
                                showToast("没有查询到您的车位置");
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        ivMei = (ImageView) findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) findViewById(R.id.home_edit_search);
        btnSousuo = (Button) findViewById(R.id.btn_sousuo);
        homeSearchBase = (TextView) findViewById(R.id.home_search_base);
        name = (TextView) findViewById(R.id.name);
        cheweihao = (TextView) findViewById(R.id.cheweihao);
        weizhi = (TextView) findViewById(R.id.weizhi);
        cnzc = (LinearLayout) findViewById(R.id.cnzc);
    }
}