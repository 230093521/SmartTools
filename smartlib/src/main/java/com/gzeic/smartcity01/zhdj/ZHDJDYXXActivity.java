package com.gzeic.smartcity01.zhdj;

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
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ZHDJDYXXActivity extends BaseActivity {

    private ImageView plBase;
    private Banner banner;
    private ListViewScrollView xxlist;
    private List<Integer> viewlist;
    private List<ZHDJActivity.testnews> ziyuanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_xx);
        initView();
        banner.setImageLoader(new BaseActivity.GlideImageLoader());
        viewlist = new ArrayList<>();
        viewlist.add(R.drawable.dyxx1);
        viewlist.add(R.drawable.dyxx2);
        viewlist.add(R.drawable.dyxx3);
        banner.setImages(viewlist);
        banner.start();
        ziyuanlist = new ArrayList<>();
        ziyuanlist.add(new ZHDJActivity.testnews("党员学习课程1", "党员学习课程党员学习课程党员学习课程党员学习课程党员学习课程", R.drawable.dyxx1));
        ziyuanlist.add(new ZHDJActivity.testnews("党员学习课程2", "党员学习课程党员学习课程党员学习课程党员学习课程党员学习课程", R.drawable.dyxx2));
        ziyuanlist.add(new ZHDJActivity.testnews("党员学习课程3", "党员学习课程党员学习课程党员学习课程党员学习课程党员学习课程", R.drawable.dyxx3));
        ziyuanlist.add(new ZHDJActivity.testnews("党员学习课程", "党员学习课程党员学习课程党员学习课程党员学习课程党员学习课程", R.drawable.dyxx3));

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

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("dang",new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(ZHDJDYXXActivity.this, ZHDJDYXXXQActivity.class));
                    }
                });
                return view;
            }
        });
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        banner = (Banner) findViewById(R.id.banner);
        xxlist = (ListViewScrollView) findViewById(R.id.xxlist);
    }
}