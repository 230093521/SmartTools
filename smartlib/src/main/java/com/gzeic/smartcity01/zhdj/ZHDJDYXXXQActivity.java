package com.gzeic.smartcity01.zhdj;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;

import java.util.ArrayList;
import java.util.List;

public class ZHDJDYXXXQActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private EditText etPl;
    private Button btnPl;
    private ListViewScrollView plList;
    private List<testZiyuan> ziyuanList;
    private VideoView mVideoView;
    private TextView yuyin;
    MediaPlayer mediaPlayer;
    private TextView zhangjie;
    private LinearLayout llKclb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_dyxxpl);
        String dang = getSP("dang");
        ZHDJActivity.testnews testnews = new Gson().fromJson(dang, ZHDJActivity.testnews.class);
        initView();
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/video01"));
        MediaController mc = new MediaController(ZHDJDYXXXQActivity.this);//Video是我类名，是你当前的类
        mVideoView.setMediaController(mc);//设置VedioView与MediaController相关联
        mVideoView.start();
        yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.start();
            }
        });

        llKclb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showToast("已切换课程");
            }
        });

        zhangjie.setText("学习视频：" + testnews.title);


        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        homeTitle.setText(testnews.title);
        btnPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPl.getText().length() < 1) {
                    showToast("请输入正确内容");
                } else {
                    ziyuanList.add(new testZiyuan(getUserdata().getNickName(), getThisTime(), etPl.getText().toString()));
                    plList.setAdapter(new MyAdapter());
                }
            }
        });
        ziyuanList = new ArrayList<>();
        ziyuanList.add(new testZiyuan("张三", getThisTime(), "党的十八大以来，习近平总书记在地方考察调研时多次到访革命纪念地，强调要从中国革命历史、优良传统和精神中汲取养分。"));
        ziyuanList.add(new testZiyuan("李四", getThisTime(), "《党史故事100讲》以党的发展历程为顺序，以党的重大事件为线索，以不同时期的典型事例、历史人物、精彩故事为主干，全景式回顾党的伟大历程和辉煌成就。"));
        plList.setAdapter(new MyAdapter());
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ziyuanList.size();
        }

        @Override
        public Object getItem(int i) {
            return ziyuanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_xw_pl, null);
            final ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.plx_image.setImageResource(R.drawable.ic_icon);
            viewHolder.plx_nickname.setText(ziyuanList.get(i).getName());
            viewHolder.plx_content.setText(ziyuanList.get(i).getNeirong());
            viewHolder.plx_time.setText(ziyuanList.get(i).getTime());
            viewHolder.dianzanll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.dianzanshu.setText(Integer.valueOf(viewHolder.dianzanshu.getText().toString()) + 1 + "");
                    showToast("点赞成功");
                }
            });
            return view;
        }

        class ViewHolder {
            public View rootView;
            public ImageView plx_image;
            public TextView plx_nickname;
            public TextView plx_time;
            public TextView plx_content;
            public TextView dianzanshu;
            public LinearLayout dianzanll;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.plx_image = (ImageView) rootView.findViewById(R.id.plx_image);
                this.plx_nickname = (TextView) rootView.findViewById(R.id.plx_nickname);
                this.plx_time = (TextView) rootView.findViewById(R.id.plx_time);
                this.plx_content = (TextView) rootView.findViewById(R.id.plx_content);
                this.dianzanshu = (TextView) rootView.findViewById(R.id.dianzanshu);
                this.dianzanll = (LinearLayout) rootView.findViewById(R.id.dianzanll);
            }

        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        etPl = (EditText) findViewById(R.id.et_pl);
        btnPl = (Button) findViewById(R.id.btn_pl);
        plList = (ListViewScrollView) findViewById(R.id.pl_list);
        mVideoView = (VideoView) findViewById(R.id.video);
        yuyin = (TextView) findViewById(R.id.yuyin);
        zhangjie = (TextView) findViewById(R.id.zhangjie);
        llKclb = (LinearLayout) findViewById(R.id.ll_kclb);
    }

    static class testZiyuan {
        String name;
        String time;
        String neirong;

        public testZiyuan(String name, String time, String neirong) {
            this.name = name;
            this.time = time;
            this.neirong = neirong;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNeirong() {
            return neirong;
        }

        public void setNeirong(String neirong) {
            this.neirong = neirong;
        }
    }
}