package com.gzeic.smartcity01.zhsq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class SqCheliangActivity extends BaseActivity {
    private ImageView metroBase;
    private RelativeLayout addche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_sq_cheliang);
        initView();
        addche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SqCheliangActivity.this,SqTjClActivity.class));
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        addche = (RelativeLayout) findViewById(R.id.addche);
    }
}