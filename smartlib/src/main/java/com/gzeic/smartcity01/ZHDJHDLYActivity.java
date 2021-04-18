package com.gzeic.smartcity01;

import android.graphics.Color;
import android.os.Bundle;

import com.xsonline.smartlib.R;

public class ZHDJHDLYActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_hdly);
    }
}