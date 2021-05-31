package com.gzeic.smartcity01.hd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.zhdj.ZHDJHDBMActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HdXqActivity extends BaseActivity {


    private RelativeLayout homeToprl;
    private ImageView newsBase;
    private TextView homeTitle;
    private TextView newsTitle;
    private ImageView newsImage;
    private TextView newsContents;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private TextView homeSearchBase;
    private ListViewScrollView pinglunlist;
    private ListViewScrollView huodonglist;
    private Button baomin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hd_xq);
        initView();
        String huodong = getSP("huodong");
//        ZHDJActivity.testnews testnews = new Gson().fromJson(huodong, ZHDJActivity.testnews.class);
//        newsBase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//        newsTitle.setText(testnews.getTitle());
//        newsContents.setText(testnews.getNeirong());
//        newsImage.setImageResource(testnews.getZiyuan());
        baomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HdXqActivity.this, HdBmActivity.class));
            }
        });
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPlList();

        getHdList();

    }

    //活动评论
    private void getPlList() {
        getTools().sendGetRequest("", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
            }
        });
    }

    //活动推荐
    private void getHdList() {
        getTools().sendGetRequest("", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
            }
        });
    }


    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        newsBase = (ImageView) findViewById(R.id.news_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsContents = (TextView) findViewById(R.id.news_contents);
        ivMei = (ImageView) findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) findViewById(R.id.home_edit_search);
        btnSousuo = (Button) findViewById(R.id.btn_sousuo);
        homeSearchBase = (TextView) findViewById(R.id.home_search_base);
        pinglunlist = (ListViewScrollView) findViewById(R.id.pinglunlist);
        huodonglist = (ListViewScrollView) findViewById(R.id.huodonglist);
        baomin = (Button) findViewById(R.id.baomin);
    }
}