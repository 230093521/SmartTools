package com.gzeic.smartcity01.zhsq;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.fragment.CFragment_zhsq;

public class SqDtActivity extends BaseActivity implements View.OnClickListener {

    private ImageView newsBase;
    private ImageView newsImage;
    private TextView newsContents;
    private TextView newsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_sq_dt);
        initView();
        String dtxq = getSP("dtxq");
        if (dtxq != null) {
            CFragment_zhsq.ShowTestData showTestData = new Gson().fromJson(dtxq, CFragment_zhsq.ShowTestData.class);
            Glide.with(getApplicationContext()).load(showTestData.getImgid()).error(R.drawable.ceshi).into(newsImage);
            newsTitle.setText(showTestData.getTitle());
            newsContents.setText(showTestData.getContent());
        }
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsContents = (TextView) findViewById(R.id.news_contents);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsBase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.news_base) {
            finish();
        }
    }
}