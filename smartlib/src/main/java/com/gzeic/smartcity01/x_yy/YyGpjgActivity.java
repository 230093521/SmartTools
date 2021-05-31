package com.gzeic.smartcity01.x_yy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class YyGpjgActivity extends BaseActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#5F8BFA"));

        setContentView(R.layout.activity_yy_gpjg);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}