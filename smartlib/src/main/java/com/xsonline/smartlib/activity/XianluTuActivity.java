package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xsonline.smartlib.R;

public class XianluTuActivity extends BaseActivity {

    private ImageView metroBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_xianlu_tu);
        initView();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}