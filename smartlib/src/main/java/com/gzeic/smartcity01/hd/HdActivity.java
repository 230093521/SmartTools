package com.gzeic.smartcity01.hd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.gzeic.smartcity01.zhdj.ZHDJZZHDXQActivity;
import com.youth.banner.Banner;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HdActivity extends BaseActivity {

    private ImageView plBase;
    private Banner banner;
    private GridView hdfenlei;
    private ListViewScrollView huodonglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hd);
        initView();
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getHdFL();

        getHdxq("1");

    }



    //活动分类
    private void getHdFL() {
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
    //活动列表
    private void getHdxq(final String num) {
        getTools().sendGetRequest(""+num, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
                if (myDataBean.getCode()==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            huodonglist.setAdapter(new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return 0;
                                }

                                @Override
                                public Object getItem(int position) {
                                    return null;
                                }

                                @Override
                                public long getItemId(int position) {
                                    return 0;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                   startActivity(new Intent(HdActivity.this, HdXqActivity.class));
                                    return null;
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        banner = (Banner) findViewById(R.id.banner);
        hdfenlei = (GridView) findViewById(R.id.hdfenlei);
        huodonglist = (ListViewScrollView) findViewById(R.id.huodonglist);
    }
}