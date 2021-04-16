package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.NewsBean;


public class NewsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView newsBase;
    private ImageView newsImage;
    private TextView newsContents;
    private RelativeLayout newsRlPl;
    private TextView newsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_news);
        initView();

        SharedPreferences sharedPreferences = getSharedPreferences("news", MODE_PRIVATE);
        String news = sharedPreferences.getString("news", null);
        if (news != null) {
            NewsBean.RowsBean rowsBean = new Gson().fromJson(news, NewsBean.RowsBean.class);
            Glide.with(getApplicationContext()).load("http://" + getServerIp() + rowsBean.getImgUrl()).error(R.drawable.ceshi).into(newsImage);
            newsTitle.setText(rowsBean.getTitle());
            newsContents.setText(rowsBean.getContent());
        }
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsContents = (TextView) findViewById(R.id.news_contents);
        newsRlPl = (RelativeLayout) findViewById(R.id.news_rl_pl);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsBase.setOnClickListener(this);
        newsRlPl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.news_base) {
            finish();
        } else if (id == R.id.news_rl_pl) {
            startActivity(new Intent(NewsActivity.this, CommentsActivity.class));
        }
    }
}