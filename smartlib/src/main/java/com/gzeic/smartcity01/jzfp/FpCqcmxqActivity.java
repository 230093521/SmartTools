package com.gzeic.smartcity01.jzfp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xsonline.smartlib.R;

public class FpCqcmxqActivity extends AppCompatActivity {

    private ImageView newsBase;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_fp_cqcmxq);
        initView();
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FpCqcmxqActivity.this,FpCqcmtjActivity.class));
            }
        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}