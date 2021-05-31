package com.gzeic.smartcity01.zhsq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class SqTuiguangActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBase;
    private LinearLayout llDt1;
    private LinearLayout llDt2;
    private LinearLayout llDt3;
    private LinearLayout llDt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_sq_tuiguang);
        initView();

    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        llDt1 = (LinearLayout) findViewById(R.id.ll_dt1);
        llDt2 = (LinearLayout) findViewById(R.id.ll_dt2);
        llDt3 = (LinearLayout) findViewById(R.id.ll_dt3);
        llDt4 = (LinearLayout) findViewById(R.id.ll_dt4);
        llDt1.setOnClickListener(this);
        llDt2.setOnClickListener(this);
        llDt3.setOnClickListener(this);
        llDt4.setOnClickListener(this);
        ivBase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_base) {
            finish();
        } else if (id == R.id.ll_dt1 || id == R.id.ll_dt2 || id == R.id.ll_dt3 || id == R.id.ll_dt4) {
            startActivity(new Intent(SqTuiguangActivity.this, SqTgzxActivity.class));
        }
    }
}