package com.gzeic.smartcity01.zhsq;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.app.ActivityCompat;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoFanKuiActivity;

public class SqWuYeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView metroBase;
    private LinearLayout llDh1;
    private LinearLayout llDh2;
    private LinearLayout llDh3;
    private LinearLayout llDh4;
    private LinearLayout llDh5;
    private LinearLayout llFankui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_sq_wuye);
        initView();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        llDh1 = (LinearLayout) findViewById(R.id.ll_dh1);
        llDh2 = (LinearLayout) findViewById(R.id.ll_dh2);
        llDh3 = (LinearLayout) findViewById(R.id.ll_dh3);
        llDh4 = (LinearLayout) findViewById(R.id.ll_dh4);
        llDh5 = (LinearLayout) findViewById(R.id.ll_dh5);
        llFankui = (LinearLayout) findViewById(R.id.ll_fankui);
        metroBase.setOnClickListener(this);
        llDh1.setOnClickListener(this);
        llDh2.setOnClickListener(this);
        llDh3.setOnClickListener(this);
        llDh4.setOnClickListener(this);
        llDh5.setOnClickListener(this);
        llFankui.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.metro_base) {
            finish();
        } else if (id == R.id.ll_dh1 || id == R.id.ll_dh2 || id == R.id.ll_dh3 || id == R.id.ll_dh4 || id == R.id.ll_dh5) {
            if (ActivityCompat.checkSelfPermission(SqWuYeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SqWuYeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "110"));
                startActivity(intent);
            }
        } else if (id == R.id.ll_fankui) {
            startActivity(new Intent(SqWuYeActivity.this, WoFanKuiActivity.class));
        }
    }


}