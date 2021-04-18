package com.gzeic.smartcity01;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.ZtBean;
import com.xsonline.smartlib.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XinWenZiXunActivity extends BaseActivity {
    private List<ZtBean.RowsBean> ztlist;
    private ListView news_list;
    private ImageView setBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_xw_zixun);
        initView();
        getTools().sendGetRequest("http://" + getServerIp() + "/press/press/list?pageNum=1&pageSize=10", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                ZtBean ztBean = new Gson().fromJson(json, ZtBean.class);
                if (ztBean.getCode() == 200) {
                    ztlist = new ArrayList<>();
                    ztlist = ztBean.getRowsss();
                    List<ZtBean.RowsBean> ztlist2 = new ArrayList<>();
                    for (ZtBean.RowsBean rowsBean : ztlist) {
                        Log.i(TAG, "onResponse: "+getSP("xwmc")+"  "+rowsBean.getContent());
                        if ((rowsBean.getContent()).contains(getSP("xwmc"))) {
                            Log.i(TAG, "XXXXXXXXXXXXXXXXXXXX: "+getSP("xwmc")+"  "+rowsBean.getContent());
                            ztlist2.add(rowsBean);
                        }
                    }
                    ztlist = ztlist2;
                    Collections.sort(ztlist);
                    initNewList();
                }
            }
        });
        setBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initNewList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                news_list.setAdapter(new NewsAdapter(ztlist));
            }
        });
    }

    private void initView() {
        news_list = findViewById(R.id.news_list);
        setBack = (ImageView) findViewById(R.id.set_back);
    }

    class NewsAdapter extends BaseAdapter {
        private List<ZtBean.RowsBean> ztlist;

        public NewsAdapter(List<ZtBean.RowsBean> ztlist) {
            this.ztlist = ztlist;
        }

        @Override
        public int getCount() {
            return ztlist.size();
        }

        @Override
        public Object getItem(int i) {
            return ztlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View views = LayoutInflater.from(XinWenZiXunActivity.this).inflate(R.layout.item_news, null);
            final ZtBean.RowsBean rowsBean = (ZtBean.RowsBean) getItem(i);
            Log.i("ceshi", "getView: " + rowsBean.getContent());
            ImageView imageView = views.findViewById(R.id.news_image);
            TextView title = views.findViewById(R.id.news_title);
            TextView content = views.findViewById(R.id.news_content);
            LinearLayout linearLayout = views.findViewById(R.id.news_ll);

            Glide.with(XinWenZiXunActivity.this).load("http://" + getServerIp() + rowsBean.getImgUrl()).error(R.mipmap.ic_launcher).into(imageView);
            title.setText(rowsBean.getTitle());
            content.setText(rowsBean.getContent());

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    putSP("news", json);
                    startActivity(new Intent(XinWenZiXunActivity.this, XinWenActivity.class));
                }
            });
            return views;
        }
    }
}