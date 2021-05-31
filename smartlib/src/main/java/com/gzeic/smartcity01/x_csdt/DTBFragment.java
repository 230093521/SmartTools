package com.gzeic.smartcity01.x_csdt;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.XwZxActivity;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.gzeic.smartcity01.bean.NewsBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.XwlbBean;
import com.gzeic.smartcity01.bean.XwlblbBean;
import com.gzeic.smartcity01.fragment.AFragment;
import com.gzeic.smartcity01.xw.XinWenActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DTBFragment extends BaseFragment {
    private Banner banner;
    private GridView recNewsfl;
    private ListViewScrollView viewScrollView;
    private List<XwlbBean.DataDTO> newFllist;
    private List<XwlblbBean.RowsDTO> newsList;
    List<String> imglist;
    LunBoBean lunBoBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dt_b, container, false);
        initView(view);
        getLunbo();
        getXwfl();
        getNewsList(4);
        return view;
    }

    private void getXwfl() {
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/metro/press/category/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                XwlbBean newsFlBean = new Gson().fromJson(json, XwlbBean.class);
                if (newsFlBean.getCode() == 200) {
                    newFllist = newsFlBean.getData();
                    initNewsFl();
                }
            }
        });
    }

    public void initNewsFl() {
        try {
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
                            return newFllist.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return newFllist.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return position;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_xw_fl, null);
                            final XwlbBean.DataDTO dataBean = newFllist.get(position);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据分类口令动态加载新闻列表
    public void getNewsList(final int dictCode) {
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/metro/press/press/list?pageNum=1&pageSize=6&type=" + dictCode, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            Log.i("ceshi", "onResponse:测试 " + json);
                            XwlblbBean newsBean = new Gson().fromJson(json, XwlblbBean.class);
                            if (newsBean.getCode() == 200) {
                                newsList = newsBean.getRows();
                                initNewList();
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initNewList() {
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewScrollView.setAdapter(new NewsAdapter(newsList));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getLunbo() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/rotation/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                lunBoBean = new Gson().fromJson(string, LunBoBean.class);
                if (lunBoBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imglist = new ArrayList<>();
                            for (LunBoBean.RowsDTO lunBoBeanRow : lunBoBean.getRows()) {
                                imglist.add("http://" + getServerIp() + lunBoBeanRow.getAdvImg());
                            }
                            banner.setImages(imglist);
                            banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object o, ImageView imageView) {
                                    Glide.with(context).load(o).into(imageView);
                                }
                            });
                            banner.start();
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        recNewsfl = (GridView) view.findViewById(R.id.rec_newsfl);
        viewScrollView = (ListViewScrollView) view.findViewById(R.id.news_list);
    }

    class NewsAdapter extends BaseAdapter {
        List<XwlblbBean.RowsDTO> newsList;
        LayoutInflater inflater;

        public NewsAdapter(List<XwlblbBean.RowsDTO> newsList) {
            this.newsList = newsList;
            inflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int i) {
            return newsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View views = inflater.inflate(R.layout.item_xw, null);
            final XwlblbBean.RowsDTO rowsBean = (XwlblbBean.RowsDTO) getItem(i);
            Log.i("ceshi", "getView: " + rowsBean.getContent());
            ImageView imageView = views.findViewById(R.id.news_image);
            TextView title = views.findViewById(R.id.news_title);
            TextView content = views.findViewById(R.id.news_content);
            LinearLayout linearLayout = views.findViewById(R.id.news_ll);
            Glide.with(getContext()).load("http://" + getServerIp() + rowsBean.getCover()).error(R.mipmap.ic_launcher).into(imageView);
            title.setText(rowsBean.getTitle());
            String content1 = rowsBean.getContent();
            String replace = content1.replace("<p>", " ");
            content.setText(replace);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    putSP("xwzx",json);
                    putSP("yemian","shzt");
                    getActivity().startActivity(new Intent(getContext(), XwZxActivity.class));
                }
            });
            return views;
        }
    }
}