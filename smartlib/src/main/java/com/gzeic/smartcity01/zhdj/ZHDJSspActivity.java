package com.gzeic.smartcity01.zhdj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class ZHDJSspActivity extends BaseActivity {

    private ImageView back;
    private TextView fabu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_ssp);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZHDJSspActivity.this,ZHDJSuiShouPaiActivity.class));
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        fabu = (TextView) findViewById(R.id.fabu);
    }
}