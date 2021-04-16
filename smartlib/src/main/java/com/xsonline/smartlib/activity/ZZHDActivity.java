package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.ui.ListViewScrollView;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

public class ZZHDActivity extends BaseActivity {

    private ImageView plBase;
    private Banner banner;
    private ListViewScrollView xxlist;
    private List<Integer> integerList;
    private List<ZHDJActivity.testnews> ziyuanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_z_z_h_d);
        initView();
        integerList = new ArrayList<>();
        integerList.add(R.drawable.zzhd1);
        integerList.add(R.drawable.zzhd2);
        integerList.add(R.drawable.zzhd3);
        integerList.add(R.drawable.zzhd4);
        integerList.add(R.drawable.zzhd5);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(integerList);
        banner.start();
        ziyuanlist = new ArrayList<>();
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd1));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd2));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd3));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd4));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd5));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd1));
        ziyuanlist.add(new ZHDJActivity.testnews("活动测试标题", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.zzhd2));
        xxlist.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView news_image;
                public TextView news_title;
                public TextView news_content;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                    this.news_title = (TextView) rootView.findViewById(R.id.news_title);
                    this.news_content = (TextView) rootView.findViewById(R.id.news_content);
                }

            }

            @Override
            public int getCount() {
                return ziyuanlist.size();
            }

            @Override
            public Object getItem(int i) {
                return ziyuanlist.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_news, null);
                ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.news_image.setImageResource(ziyuanlist.get(i).getZiyuan());
                viewHolder.news_title.setText(ziyuanlist.get(i).title);
                viewHolder.news_content.setText(ziyuanlist.get(i).neirong);
//                ZHDJActivity.testnews testnews = ziyuanlist.get(i);

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("huodong", new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(ZZHDActivity.this, ZZHDXQActivity.class));
                    }
                });
                return view;
            }
        });

    }

    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        banner = (Banner) findViewById(R.id.banner);
        xxlist = (ListViewScrollView) findViewById(R.id.xxlist);
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}