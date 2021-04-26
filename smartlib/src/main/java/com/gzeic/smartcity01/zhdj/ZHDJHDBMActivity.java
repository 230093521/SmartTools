package com.gzeic.smartcity01.zhdj;

import android.graphics.Color;
import android.os.Bundle;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class ZHDJHDBMActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_hdbm);
    }
}