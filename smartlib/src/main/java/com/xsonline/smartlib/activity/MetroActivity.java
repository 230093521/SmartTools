package com.xsonline.smartlib.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xsonline.smartlib.R;

public class MetroActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView metroName;
    private ListView metroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_metro);
        initView();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        metroName = (TextView) findViewById(R.id.metro_name);
        metroList = (ListView) findViewById(R.id.metro_list);
    }
}