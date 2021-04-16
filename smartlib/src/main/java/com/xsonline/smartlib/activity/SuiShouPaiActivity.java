package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xsonline.smartlib.R;

public class SuiShouPaiActivity extends BaseActivity {

    private ImageView newsBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_sui_shou_pai);

        initView();

        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
    }
}