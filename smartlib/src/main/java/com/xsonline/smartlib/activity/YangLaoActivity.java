package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xsonline.smartlib.R;

import java.util.ArrayList;
import java.util.List;

public class YangLaoActivity extends BaseActivity {

    private ImageView infoBase;
    private ListView yanglaolist;
    List<yanglaobean> yanglaobeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yang_lao);
        initView();
        infoBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        yanglaobeans = new ArrayList<>();
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd1, "测试养老院1", 3.5f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd2, "测试养老院2", 4.5f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd3, "测试养老院3", 2.5f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd4, "测试养老院4", 1.5f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd5, "测试养老院5", 5.0f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd2, "测试养老院6", 2.0f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaobeans.add(new yanglaobean(R.drawable.zzhd3, "测试养老院7", 3.5f,"养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容养老院详细设施内容"));
        yanglaolist.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView yiyuan_img;
                public TextView yiyuan_name;
                public RatingBar yiyuanpingfen;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.yiyuan_img = (ImageView) rootView.findViewById(R.id.yiyuan_img);
                    this.yiyuan_name = (TextView) rootView.findViewById(R.id.yiyuan_name);
                    this.yiyuanpingfen = (RatingBar) rootView.findViewById(R.id.yiyuanpingfen);
                }

            }

            @Override
            public int getCount() {
                return yanglaobeans.size();
            }

            @Override
            public Object getItem(int i) {
                return yanglaobeans.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_yiyuanming, null);
                ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.yiyuan_img.setImageResource(yanglaobeans.get(i).getImg());
                viewHolder.yiyuan_name.setText(yanglaobeans.get(i).getName());
                viewHolder.yiyuanpingfen.setRating(yanglaobeans.get(i).getDengji());
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("yanglao",new Gson().toJson(yanglaobeans.get(i)));
                        startActivity(new Intent(YangLaoActivity.this,YangLaoXqActivity.class));
                    }
                });
                return view;
            }
        });


    }

    private void initView() {
        infoBase = (ImageView) findViewById(R.id.info_base);
        yanglaolist = (ListView) findViewById(R.id.yanglaolist);
    }

    static class yanglaobean {
        int img;
        String name;
        Float dengji;
        String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public yanglaobean(int img, String name, Float dengji, String content) {
            this.img = img;
            this.name = name;
            this.dengji = dengji;
            this.content = content;
        }

        public yanglaobean(int img, String name, Float dengji) {
            this.img = img;
            this.name = name;
            this.dengji = dengji;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getDengji() {
            return dengji;
        }

        public void setDengji(Float dengji) {
            this.dengji = dengji;
        }
    }
}