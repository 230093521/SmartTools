package com.gzeic.smartcity01.jzfp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class FpCqcmActivity extends BaseActivity {

    private ImageView metroBase;
    private LinearLayout liebiao;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_fp_cqcm);
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
                startActivity(new Intent(FpCqcmActivity.this, FpCqcmxqActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("没有更多啦");
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        liebiao = (LinearLayout) findViewById(R.id.liebiao);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}