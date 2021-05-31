package com.gzeic.smartcity01.zhdj;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;

import java.util.ArrayList;
import java.util.List;

public class ZHDJPLActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private ImageView image;
    private TextView neirong;
    private EditText etPl;
    private Button btnPl;
    private ListViewScrollView plList;
    private List<testZiyuan> ziyuanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_pl);
        String dang = getSP("dang");
        ZHDJActivity.testnews testnews = new Gson().fromJson(dang, ZHDJActivity.testnews.class);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        homeTitle.setText(testnews.title);
        image.setImageResource(testnews.getZiyuan());
        neirong.setText(testnews.neirong);
        btnPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPl.getText().length()<1) {
                    showToast("请输入正确内容");
                }else {
                    ziyuanList.add(new testZiyuan("张三"+Math.random(), "2020-12-22 12:30", etPl.getText().toString()));
                    plList.setAdapter(new MyAdapter());
                }
            }
        });
        ziyuanList = new ArrayList<>();
        ziyuanList.add(new testZiyuan("张三", "2020-12-22 12:30", "测试评论内容"));
        ziyuanList.add(new testZiyuan("李四", "2020-12-22 12:30", "测试评论内容"));
        plList.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{
        class ViewHolder {
            public View rootView;
            public ImageView plx_image;
            public TextView plx_nickname;
            public TextView plx_time;
            public TextView plx_content;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.plx_image = (ImageView) rootView.findViewById(R.id.plx_image);
                this.plx_nickname = (TextView) rootView.findViewById(R.id.plx_nickname);
                this.plx_time = (TextView) rootView.findViewById(R.id.plx_time);
                this.plx_content = (TextView) rootView.findViewById(R.id.plx_content);
            }

        }

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
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.plx_image.setImageResource(R.drawable.ic_icon);
            viewHolder.plx_nickname.setText(ziyuanList.get(i).getName());
            viewHolder.plx_content.setText(ziyuanList.get(i).getNeirong());
            viewHolder.plx_time.setText(ziyuanList.get(i).getTime());
            return view;
        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        image = (ImageView) findViewById(R.id.image);
        neirong = (TextView) findViewById(R.id.neirong);
        etPl = (EditText) findViewById(R.id.et_pl);
        btnPl = (Button) findViewById(R.id.btn_pl);
        plList = (ListViewScrollView) findViewById(R.id.pl_list);
    }

    class testZiyuan {
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