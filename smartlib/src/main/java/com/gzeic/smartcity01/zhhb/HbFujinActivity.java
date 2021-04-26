package com.gzeic.smartcity01.zhhb;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class HbFujinActivity extends BaseActivity {

    private ImageView metroBase;
    private Button btnGengduo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_hb_fujin);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnGengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("没有更多信息了");
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        btnGengduo = (Button) findViewById(R.id.btn_gengduo);
    }
}