package com.gzeic.smartcity01.jzfp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class FpAlxqActivity extends BaseActivity {

    private ImageView newsBase;
    private ImageView zan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_fp_alxq);
        initView();
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点赞成功");
            }
        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        zan = (ImageView) findViewById(R.id.zan);
    }
}