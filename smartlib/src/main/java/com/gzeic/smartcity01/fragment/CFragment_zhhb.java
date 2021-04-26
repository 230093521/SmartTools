package com.gzeic.smartcity01.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.zhhb.HbFenleiActivity;
import com.gzeic.smartcity01.zhhb.HbFujinActivity;
import com.gzeic.smartcity01.zhhb.HbLishiActivity;
import com.gzeic.smartcity01.zhsq.SqDtActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class CFragment_zhhb extends BaseFragment implements View.OnClickListener {

    private Banner banner;
    private ImageView wuye;
    private ImageView kuaijian;
    private ImageView lajifl;
    private ImageView tuiguang;
    private ListViewScrollView listDongtai;
    List<ShowTestData> showTestDataList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_c_zhhb, container, false);
        initView(root);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(getContext()).load(o).into(imageView);
            }
        });
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.drawable.huanbao1);
        integerList.add(R.drawable.huanbao2);
        integerList.add(R.drawable.huanbao3);
        integerList.add(R.drawable.huanbao4);
        integerList.add(R.drawable.huanbao5);
        integerList.add(R.drawable.huanbao6);
        banner.setImages(integerList);
        banner.start();
        showTestDataList = new ArrayList<>();
        showTestDataList.add(new ShowTestData(R.drawable.huanbao1, "测试环境宣传标题1", "测试环境宣传内容", "2020-02-02 14:00"));
        showTestDataList.add(new ShowTestData(R.drawable.huanbao2, "测试环境宣传标题2", "测试环境宣传内容", "2020-02-02 14:00"));
        showTestDataList.add(new ShowTestData(R.drawable.huanbao3, "测试环境宣传标题3", "测试环境宣传内容", "2020-02-02 14:00"));
        showTestDataList.add(new ShowTestData(R.drawable.huanbao4, "测试环境宣传标题4", "测试环境宣传内容", "2020-02-02 14:00"));
        showTestDataList.add(new ShowTestData(R.drawable.huanbao5, "测试环境宣传标题5", "测试环境宣传内容", "2020-02-02 14:00"));
        showTestDataList.add(new ShowTestData(R.drawable.huanbao6, "测试环境宣传标题6", "测试环境宣传内容", "2020-02-02 14:00"));
        listDongtai.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView news_image;
                public TextView news_title;
                public TextView news_content;
                public TextView time;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                    this.news_title = (TextView) rootView.findViewById(R.id.news_title);
                    this.news_content = (TextView) rootView.findViewById(R.id.news_content);
                    this.time = (TextView) rootView.findViewById(R.id.time);
                }

            }

            @Override
            public int getCount() {
                return showTestDataList.size();
            }

            @Override
            public Object getItem(int position) {
                return showTestDataList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news_time, null);
                final ShowTestData showTestData = showTestDataList.get(position);
                ViewHolder viewHolder = new ViewHolder(convertView);
                viewHolder.news_title.setText(showTestData.title);
                viewHolder.news_content.setText(showTestData.content);
                viewHolder.time.setText(showTestData.time);
                Glide.with(getContext()).load(showTestData.imgid).into(viewHolder.news_image);
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String toJson = new Gson().toJson(showTestData);
                        putSP("dtxq",toJson);
                        startActivity(new Intent(getContext(), SqDtActivity.class));
                    }
                });
                return convertView;
            }
        });
        return root;
    }


    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        wuye = (ImageView) view.findViewById(R.id.wuye);
        kuaijian = (ImageView) view.findViewById(R.id.kuaijian);
        lajifl = (ImageView) view.findViewById(R.id.lajifl);
        tuiguang = (ImageView) view.findViewById(R.id.tuiguang);
        listDongtai = (ListViewScrollView) view.findViewById(R.id.list_dongtai);
        wuye.setOnClickListener(this);
        kuaijian.setOnClickListener(this);
        lajifl.setOnClickListener(this);
        tuiguang.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wuye || id == R.id.lajifl) {
            startActivity(new Intent(getContext(), HbFenleiActivity.class));
        } else if (id == R.id.kuaijian) {
            startActivity(new Intent(getContext(), HbLishiActivity.class));
        } else if (id == R.id.tuiguang) {
            startActivity(new Intent(getContext(), HbFujinActivity.class));
        }
    }

    public static class ShowTestData {
        int imgid;
        String title;
        String content;
        String time;

        public ShowTestData(int imgid, String title, String content, String time) {
            this.imgid = imgid;
            this.title = title;
            this.content = content;
            this.time = time;
        }

        public int getImgid() {
            return imgid;
        }

        public void setImgid(int imgid) {
            this.imgid = imgid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}