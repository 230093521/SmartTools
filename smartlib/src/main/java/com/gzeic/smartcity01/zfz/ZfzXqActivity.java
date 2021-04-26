package com.gzeic.smartcity01.zfz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZfzflBean;

public class ZfzXqActivity extends BaseActivity {

    private ImageView newsBase;
    private TextView newsTitle;
    private ImageView newsImage;
    private TextView mianji;
    private TextView jiage;
    private TextView leixin;
    private TextView jianjie;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zfz_xq);
        initView();
        String zfzxq = getSP("zfzxq");
        final ZfzflBean.RowsDTO rowsDTO = new Gson().fromJson(zfzxq, ZfzflBean.RowsDTO.class);
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsTitle.setText(rowsDTO.getSourceName());
        Glide.with(ZfzXqActivity.this).load("http://"+getServerIp()+rowsDTO.getPic()).into(newsImage);
        mianji.setText(String.valueOf(rowsDTO.getAreaSize()));
        jiage.setText(rowsDTO.getPrice());
        jianjie.setText(rowsDTO.getDesc());
        switch (rowsDTO.getHouseType()) {
            case "1":
                leixin.setText("二手");
                break;
            case "2":
                leixin.setText("租房");
                break;
            case "3":
                leixin.setText("楼盘");
                break;
            case "4":
                leixin.setText("中介");
                break;
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ZfzXqActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ZfzXqActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + rowsDTO.getTel());
                    intent.setData(data);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsImage = (ImageView) findViewById(R.id.news_image);
        mianji = (TextView) findViewById(R.id.mianji);
        jiage = (TextView) findViewById(R.id.jiage);
        leixin = (TextView) findViewById(R.id.leixin);
        jianjie = (TextView) findViewById(R.id.jianjie);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }
}