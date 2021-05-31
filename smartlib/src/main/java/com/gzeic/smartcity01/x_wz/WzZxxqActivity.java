package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class WzZxxqActivity extends BaseActivity {
    private ImageView news_base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_zxxq);
        news_base = findViewById(R.id.news_base);
        news_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}