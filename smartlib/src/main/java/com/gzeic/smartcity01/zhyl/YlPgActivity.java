package com.gzeic.smartcity01.zhyl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class YlPgActivity extends BaseActivity {

    private ImageView ivBase;
    private TextView feiyong;
    private TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yl_pg);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feiyong.setVisibility(View.VISIBLE);
                showToast("您的费用已评估");
            }
        });
    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        feiyong = (TextView) findViewById(R.id.feiyong);
        submit = (TextView) findViewById(R.id.submit);
    }
}