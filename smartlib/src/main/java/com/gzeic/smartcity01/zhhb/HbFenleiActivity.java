package com.gzeic.smartcity01.zhhb;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class HbFenleiActivity extends BaseActivity {

    private ImageView metroBase;
    private LinearLayout fenlei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_hb_fenlei);
        initView();

        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HbFenleiActivity.this, HbXinxiActivity.class));
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        fenlei = (LinearLayout) findViewById(R.id.fenlei);
    }
}