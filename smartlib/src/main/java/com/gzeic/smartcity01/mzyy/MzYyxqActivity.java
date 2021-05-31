package com.gzeic.smartcity01.mzyy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YiYuanBean;
import com.gzeic.smartcity01.bean.YiYuanTuBean;
import com.gzeic.smartcity01.bean.YiYuanXqBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzYyxqActivity extends BaseActivity {
    List<YiYuanTuBean.DataDTO> rows;
    private ImageView metroBase;
    private Banner banner;
    private TextView mainNeirong;
    private Button btnYuyue;
    List<String> imageViewslist;
    YiYuanBean.RowsDTO rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_mz_yyxq);
        String json = getSP("yiyuan");
        rowsBean = new Gson().fromJson(json, YiYuanBean.RowsDTO.class);
        initView();
        getLunbo();

    }

    private void getLunbo() {
        //轮播图
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/hospital/banner/list?hospitalId=" + rowsBean.getId(), getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                YiYuanTuBean yiYuanBean = new Gson().fromJson(json, YiYuanTuBean.class);
                if (yiYuanBean.getCode() == 200) {
                    imageViewslist=new ArrayList<>();
                    rows = yiYuanBean.getData();
                    for (YiYuanTuBean.DataDTO row : rows) {
                        imageViewslist.add("http://"+getServerIp()+row.getImgUrl());
                    }
                    getyyxq();

                }
            }
        });
    }

    private void getyyxq() {
        //详细信息
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/hospital/hospital/" + rowsBean.getId(), new Callback() {
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
                            mainNeirong.setText(Html.fromHtml(yiYuanXqBean.getData().getBrief()));
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
                startActivity(new Intent(MzYyxqActivity.this, MzJzkpActivity.class));
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