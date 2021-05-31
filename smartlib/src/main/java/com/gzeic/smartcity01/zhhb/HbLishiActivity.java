package com.gzeic.smartcity01.zhhb;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class HbLishiActivity extends BaseActivity {

    private ImageView metroBase;
    private Button btnSaoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_hb_lishi);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSaoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("没有更多信息了");
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        btnSaoma = (Button) findViewById(R.id.btn_saoma);
    }
}