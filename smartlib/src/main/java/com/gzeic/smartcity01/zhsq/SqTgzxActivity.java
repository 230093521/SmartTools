package com.gzeic.smartcity01.zhsq;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class SqTgzxActivity extends BaseActivity {


    private ImageView ivBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_sq_tgzx);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
    }
}