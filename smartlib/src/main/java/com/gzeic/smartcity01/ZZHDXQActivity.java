package com.gzeic.smartcity01;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xsonline.smartlib.R;

public class ZZHDXQActivity extends BaseActivity {

    private ImageView newsBase;
    private TextView newsTitle;
    private ImageView newsImage;
    private TextView newsContents;
    private TextView baomin;
    private TextView liuyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_xq);
        initView();
        String huodong = getSP("huodong");
        ZHDJActivity.testnews testnews = new Gson().fromJson(huodong, ZHDJActivity.testnews.class);
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        newsTitle.setText(testnews.getTitle());
        newsContents.setText(testnews.getNeirong());
        newsImage.setImageResource(testnews.getZiyuan());
        baomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZZHDXQActivity.this, ZHDJHDBMActivity.class));
            }
        });
        liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZZHDXQActivity.this, ZHDJHDLYActivity.class));
            }
        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsContents = (TextView) findViewById(R.id.news_contents);
        baomin = (TextView) findViewById(R.id.baomin);
        liuyan = (TextView) findViewById(R.id.liuyan);
    }
}