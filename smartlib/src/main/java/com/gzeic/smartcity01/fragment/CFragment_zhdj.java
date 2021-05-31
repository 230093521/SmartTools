package com.gzeic.smartcity01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.HdlbBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.XwlblbBean;
import com.gzeic.smartcity01.x_hdgl.HdSyActivity;
import com.gzeic.smartcity01.x_hdgl.HdXqyActivity;
import com.gzeic.smartcity01.xw.XinWenActivity;
import com.gzeic.smartcity01.zhdj.ZHDJDYXXActivity;
import com.gzeic.smartcity01.zhdj.ZHDJJYXCActivity;
import com.gzeic.smartcity01.zhdj.ZHDJSspActivity;
import com.gzeic.smartcity01.zhdj.ZZDJHDActivity;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CFragment_zhdj extends BaseFragment {

    private List<Integer> viewlist;
    private Banner banner;
    private ImageView xuexi;
    private ImageView huodong;
    private ImageView liuyan;
    private ImageView paizhao;
    private GridView recNewsfl;
    private ListViewScrollView newsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_c_zhdj, container, false);
        initView(root);
        banner.setImageLoader(new GlideImageLoader());
        viewlist = new ArrayList<>();
        viewlist.add(R.drawable.dangjian1);
        viewlist.add(R.drawable.dangjian2);
        viewlist.add(R.drawable.dangjian3);
        viewlist.add(R.drawable.dangjian4);
        banner.setImages(viewlist);
        banner.start();
        xuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJDYXXActivity.class));
            }
        });
        liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJJYXCActivity.class));
            }
        });
        huodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZZDJHDActivity.class));
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJSspActivity.class));
            }
        });
        getNewsList(26);
        //新闻分类
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/press/category/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                NewsFlBean newsFlBean = new Gson().fromJson(json, NewsFlBean.class);
                if (newsFlBean.getCode() == 200) {
                    final List<NewsFlBean.DataDTO> newsFlBeanData = newsFlBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recNewsfl.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView newsfl_text;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.newsfl_text = rootView.findViewById(R.id.newsfl_text);
                                    }
                                }

                                @Override
                                public int getCount() {
                                    return newsFlBeanData.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return newsFlBeanData.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_xw_fl, null);
                                    final NewsFlBean.DataDTO dataBean = newsFlBeanData.get(position);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.newsfl_text.setText(dataBean.getName());
                                    viewHolder.newsfl_text.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getNewsList(dataBean.getId());
                                        }
                                    });
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });

        return root;
    }

    private void getNewsList(int dictCode) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/press/press/list?type=" + dictCode, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("ceshi", "onResponse:测试 " + json);
                XwlblbBean newsBean = new Gson().fromJson(json, XwlblbBean.class);
                if (newsBean.getCode() == 200) {
                    final List<XwlblbBean.RowsDTO> newsBeanRows = newsBean.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            newsList.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public ImageView news_image;
                                    public TextView news_title;
                                    public TextView fabushijian;
                                    public TextView yuedushu;
                                    public ImageView zdimg;
                                    public TextView dianzanshu;
                                    public LinearLayout news_ll;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                                        this.news_title = (TextView) rootView.findViewById(R.id.news_title);
                                        this.fabushijian = (TextView) rootView.findViewById(R.id.fabushijian);
                                        this.yuedushu = (TextView) rootView.findViewById(R.id.yuedushu);
                                        this.zdimg = (ImageView) rootView.findViewById(R.id.zdimg);
                                        this.dianzanshu = (TextView) rootView.findViewById(R.id.dianzanshu);
                                        this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return newsBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return newsBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_xw_dz, null);
                                    final XwlblbBean.RowsDTO rowsDTO = newsBeanRows.get(position);
                                    final ViewHolder viewHolder = new ViewHolder(view);
                                    Glide.with(getContext()).load("http://" + getServerIp() + rowsDTO.getCover()).error(R.drawable.icon2).into(viewHolder.news_image);
                                    viewHolder.news_title.setText(rowsDTO.getTitle());
                                    viewHolder.dianzanshu.setText(rowsDTO.getLikeNum() + "");
                                    viewHolder.fabushijian.setText(rowsDTO.getPublishDate());
                                    viewHolder.yuedushu.setText(rowsDTO.getReadNum() + "");
                                    viewHolder.zdimg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            viewHolder.dianzanshu.setText(rowsDTO.getLikeNum() + 1 + "");
                                            showToast("点赞成功");
                                        }
                                    });
                                    viewHolder.dianzanshu.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            viewHolder.dianzanshu.setText(rowsDTO.getLikeNum() + 1 + "");
                                            showToast("点赞成功");
                                        }
                                    });
                                    viewHolder.news_ll.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String json = new Gson().toJson(rowsDTO);
                                            putSP("xwzx", json);
                                            putSP("yemian", "");
                                            startActivity(new Intent(getContext(), XinWenActivity.class));
                                        }
                                    });
                                    return view;
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        xuexi = (ImageView) view.findViewById(R.id.xuexi);
        huodong = (ImageView) view.findViewById(R.id.huodong);
        liuyan = (ImageView) view.findViewById(R.id.liuyan);
        paizhao = (ImageView) view.findViewById(R.id.paizhao);
        recNewsfl = (GridView) view.findViewById(R.id.rec_newsfl);
        newsList = (ListViewScrollView) view.findViewById(R.id.news_list);
    }
}