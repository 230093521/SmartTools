package com.gzeic.smartcity01.zhyl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xsonline.smartlib.R;

public class YlXjjlActivity extends AppCompatActivity {

    private ImageView metroBase;
    private LinearLayout liebiao;
    private TextView pingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yl_xjjl);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        liebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YlXjjlActivity.this,YlFwpjActivity.class));
            }
        });

        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YlXjjlActivity.this,YlFwpjActivity.class));
            }
        });

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        liebiao = (LinearLayout) findViewById(R.id.liebiao);
        pingjia = (TextView) findViewById(R.id.pingjia);
    }
}