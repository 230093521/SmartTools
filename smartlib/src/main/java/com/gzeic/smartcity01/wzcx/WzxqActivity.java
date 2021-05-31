package com.gzeic.smartcity01.wzcx;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzcxBean;

public class WzxqActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView wzsj;
    private TextView wfdd;
    private TextView wfxw;
    private TextView tzsh;
    private TextView wzjf;
    private TextView fkje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wzxq);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String json = getSP("wzcxjg");
        WzcxBean.RowsDTO rowsDTO = new Gson().fromJson(json, WzcxBean.RowsDTO.class);
        wzsj.setText(rowsDTO.getBadTime());
        wfdd.setText(rowsDTO.getIllegalSites());
        wfxw.setText(rowsDTO.getTrafficOffence());
        tzsh.setText(rowsDTO.getNoticeNo());
        wzjf.setText(rowsDTO.getDeductMarks());
        fkje.setText(rowsDTO.getMoney()+"元");
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        wzsj = (TextView) findViewById(R.id.wzsj);
        wfdd = (TextView) findViewById(R.id.wfdd);
        wfxw = (TextView) findViewById(R.id.wfxw);
        tzsh = (TextView) findViewById(R.id.tzsh);
        wzjf = (TextView) findViewById(R.id.wzjf);
        fkje = (TextView) findViewById(R.id.fkje);
    }
}