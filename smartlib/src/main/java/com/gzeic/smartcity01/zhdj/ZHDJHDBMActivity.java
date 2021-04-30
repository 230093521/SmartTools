package com.gzeic.smartcity01.zhdj;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;


public class ZHDJHDBMActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView name;
    private TextView keshi;
    private TextView zhuanjia;
    private TextView jine;
    private TextView riqi;
    private TextView shijian;
    private TextView quxiao;
    private TextView queding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_hdbm);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("报名成功");
            }
        });

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        name = (TextView) findViewById(R.id.name);
        keshi = (TextView) findViewById(R.id.keshi);
        zhuanjia = (TextView) findViewById(R.id.zhuanjia);
        jine = (TextView) findViewById(R.id.jine);
        riqi = (TextView) findViewById(R.id.riqi);
        shijian = (TextView) findViewById(R.id.shijian);
        quxiao = (TextView) findViewById(R.id.quxiao);
        queding = (TextView) findViewById(R.id.queding);
    }
}