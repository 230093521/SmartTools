package com.xsonline.smartlib.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.YiYuanBean;
import com.xsonline.smartlib.bean.YiYuanTuBean;
import com.xsonline.smartlib.bean.YiYuanXqBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YiYuanXiangQingActivity extends BaseActivity {
    List<YiYuanTuBean.RowsBean> rows;
    private ImageView metroBase;
    private Banner banner;
    private TextView mainNeirong;
    private Button btnYuyue;
    List<String> imageViewslist;
    YiYuanBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yi_yuan_xiang_qing);
        String json = getSP("yiyuan");
        rowsBean = new Gson().fromJson(json, YiYuanBean.RowsBean.class);
        initView();
        //轮播图
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/img/list?hospitalId=" + rowsBean.getId(), getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                YiYuanTuBean yiYuanBean = new Gson().fromJson(json, YiYuanTuBean.class);
                if (yiYuanBean.getCode() == 200) {
                    imageViewslist=new ArrayList<>();
                    rows = yiYuanBean.getRows();
                    for (YiYuanTuBean.RowsBean row : rows) {
                        imageViewslist.add("http://"+getServerIp()+row.getImgUrl());
                    }
                    initTu();

                }
            }
        });

    }

    private void initTu() {
        //详细信息
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/registration/" + rowsBean.getId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final YiYuanXqBean yiYuanXqBean = new Gson().fromJson(json, YiYuanXqBean.class);
                if (yiYuanXqBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(imageViewslist);
                            banner.start();
                            mainNeirong.setText(yiYuanXqBean.getData().getBrief());
                        }
                    });
                }
            }
        });
    }


    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        banner = (Banner) findViewById(R.id.banner);
        mainNeirong = (TextView) findViewById(R.id.main_neirong);
        btnYuyue = (Button) findViewById(R.id.btn_yuyue);
        btnYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YiYuanXiangQingActivity.this,JiuZhengRenKaPianActivity.class));
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}