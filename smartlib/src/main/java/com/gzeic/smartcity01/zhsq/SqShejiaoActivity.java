package com.gzeic.smartcity01.zhsq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class SqShejiaoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView metroBase;
    private LinearLayout llDt1;
    private LinearLayout llDt2;
    private LinearLayout llDt3;
    private LinearLayout llDt4;
    private LinearLayout llDt5;
    private Button btnFabiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_sq_shejiao);
        initView();

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        llDt1 = (LinearLayout) findViewById(R.id.ll_dt1);
        llDt2 = (LinearLayout) findViewById(R.id.ll_dt2);
        llDt3 = (LinearLayout) findViewById(R.id.ll_dt3);
        llDt4 = (LinearLayout) findViewById(R.id.ll_dt4);
        llDt5 = (LinearLayout) findViewById(R.id.ll_dt5);
        btnFabiao = (Button) findViewById(R.id.btn_fabiao);
        llDt1.setOnClickListener(this);
        llDt2.setOnClickListener(this);
        llDt3.setOnClickListener(this);
        llDt4.setOnClickListener(this);
        llDt5.setOnClickListener(this);
        btnFabiao.setOnClickListener(this);
        metroBase.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.metro_base) {
            finish();
        } else if (id == R.id.ll_dt1 || id == R.id.ll_dt2 || id == R.id.ll_dt3 || id == R.id.ll_dt4 || id == R.id.ll_dt5) {
            startActivity(new Intent(SqShejiaoActivity.this, SqSjXqActivity.class));
        } else if (id == R.id.btn_fabiao) {
            startActivity(new Intent(SqShejiaoActivity.this, SqSjFdtActivity.class));
        }
    }

}