package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xsonline.smartlib.R;

public class YangLaoZhuYeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView metroBase;
    private ImageView hjjc;
    private ImageView jkjc;
    private ImageView xjjl;
    private ImageView cyjl;
    private ImageView rzqk;
    private ImageView stzk;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yang_lao_zhu_ye);
        initView();
        String json = getSP("yanglao");
        YangLaoActivity.yanglaobean yanglaobean = new Gson().fromJson(json, YangLaoActivity.yanglaobean.class);
        name.setText(yanglaobean.getName()+"功能管理");

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        hjjc = (ImageView) findViewById(R.id.hjjc);
        jkjc = (ImageView) findViewById(R.id.jkjc);
        xjjl = (ImageView) findViewById(R.id.xjjl);
        cyjl = (ImageView) findViewById(R.id.cyjl);
        rzqk = (ImageView) findViewById(R.id.rzqk);
        stzk = (ImageView) findViewById(R.id.stzk);
        metroBase.setOnClickListener(this);
        hjjc.setOnClickListener(this);
        jkjc.setOnClickListener(this);
        xjjl.setOnClickListener(this);
        cyjl.setOnClickListener(this);
        rzqk.setOnClickListener(this);
        stzk.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.metro_base) {
            finish();
        } else if (id == R.id.hjjc) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoJcActivity.class));
        } else if (id == R.id.jkjc) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoJkActivity.class));
        } else if (id == R.id.xjjl) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoXjActivity.class));
        } else if (id == R.id.cyjl) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoCyActivity.class));
        } else if (id == R.id.rzqk) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoRzActivity.class));
        } else if (id == R.id.stzk) {
            startActivity(new Intent(YangLaoZhuYeActivity.this, YangLaoStActivity.class));
        }
    }
}