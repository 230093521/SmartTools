package com.gzeic.smartcity01.ditie;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class DiTieXianLuTuActivity extends BaseActivity {

    private ImageView metroBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_di_tie_xl);
        initView();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}