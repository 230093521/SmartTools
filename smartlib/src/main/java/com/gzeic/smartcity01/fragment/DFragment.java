package com.gzeic.smartcity01.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.gzeic.smartcity01.XinWenActivity;
import com.gzeic.smartcity01.bean.NewsBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.RowsBean;
import com.gzeic.smartcity01.bean.RowsListBean;
import com.xsonline.smartlib.R;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DFragment extends BaseFragment {
    List<String> imageViews;
    Banner banner;
    List<NewsFlBean.DataBean> newFllist;
    List<NewsBean.RowsBean> newsList;
    ListView newsListView;
    GridView recyclerView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        banner = view.findViewById(R.id.c_banner);
        recyclerView2 = view.findViewById(R.id.rec_newsfl);
        newsListView = view.findViewById(R.id.news_list);
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/rotation/list?pageNum=1&pageSize=10&type=45", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final RowsListBean rowsListBean = new Gson().fromJson(json, RowsListBean.class);
                if (rowsListBean.getCode()==200){
                    imageViews = new ArrayList<>();
                    List<RowsBean> rows = rowsListBean.getRows();
                    for (RowsBean rs : rows) {
                        imageViews.add("http://"+getServerIp()+rs.getImgUrl());
                        Log.e("ceshi", "http://"+getServerIp()+rs.getImgUrl());
                    }
                    initBanner();
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(rowsListBean.getMsg());
                        }
                    });
                }
            }
        });
        getTools().sendGetRequest("http://" + getServerIp() + "/system/dict/data/type/press_category", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                NewsFlBean newsFlBean = new Gson().fromJson(json, NewsFlBean.class);
                if (newsFlBean.getCode() == 200) {
                    newFllist = newsFlBean.getData();
                    initNewsFl();
                }
            }
        });
        getNewsList(37);
        return view;
    }
    public void initBanner(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(imageViews);
                banner.start();
            }
        });
    }

    //根据分类口令动态加载新闻列表
    public void initNewsFl() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView2.setAdapter(new BaseAdapter() {
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
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_newsfl, null);
                        final NewsFlBean.DataBean dataBean = newFllist.get(position);
                        ViewHolder viewHolder = new ViewHolder(convertView);
                        viewHolder.newsfl_text.setText(dataBean.getDictLabel());
                        viewHolder.newsfl_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getNewsList(dataBean.getDictCode());
                            }
                        });
                        return convertView;
                    }
                });
            }
        });
    }

    //根据分类口令动态加载新闻列表
    public void getNewsList(final int dictCode) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("ceshi", "http://" + getServerIp() + "/press/press/list?pageNum=1&pageSize=10&pressCategory=" + dictCode);
                getTools().sendGetRequest("http://" + getServerIp() + "/press/press/list?pageNum=1&pageSize=10&pressCategory=" + dictCode, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        Log.i("ceshi", "onResponse:测试 " + json);
                        NewsBean newsBean = new Gson().fromJson(json, NewsBean.class);
                        if (newsBean.getCode() == 200) {
                            newsList = newsBean.getRows();
                            Log.i("ceshi", "onResponse:测试 " + newsList.get(0).getContent());
                            initNewList();
                        }
                    }
                });
            }
        });
    }

    public void initNewList() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newsListView.setAdapter(new NewsAdapter(newsList));
            }
        });
    }

    class NewsFlAdapter extends RecyclerView.Adapter<NewsFlAdapter.ViewHolder> {
        List<NewsFlBean.DataBean> newFllist;
        private int selectedPosition = 0;

        public NewsFlAdapter(List<NewsFlBean.DataBean> newFllist) {
            this.newFllist = newFllist;
            Collections.sort(newFllist);
        }

        public void setSelection(int position) {
            selectedPosition = position;
        }

        @NonNull
        @Override
        public NewsFlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NewsFlAdapter.ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_newsfl, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final NewsFlAdapter.ViewHolder holder, final int position) {
            holder.textView.setText(newFllist.get(position).getDictLabel());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int code = newFllist.get(position).getDictCode();
                    getNewsList(code);
                    setSelection(position);
                    notifyDataSetChanged();
                }
            });
            if (position == selectedPosition) {
                holder.textView.setTextColor(Color.parseColor("#FF0000"));
            } else {
                holder.textView.setTextColor(Color.parseColor("#000000"));
            }

        }

        @Override
        public int getItemCount() {
            return newFllist.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.newsfl_text);
            }
        }
    }

    class NewsAdapter extends BaseAdapter {
        List<NewsBean.RowsBean> newsList;
        LayoutInflater inflater;

        public NewsAdapter(List<NewsBean.RowsBean> newsList) {
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
            View views = inflater.inflate(R.layout.item_news, null);
            final NewsBean.RowsBean rowsBean = (NewsBean.RowsBean) getItem(i);
            Log.i("ceshi", "getView: " + rowsBean.getContent());
            ImageView imageView = views.findViewById(R.id.news_image);
            TextView title = views.findViewById(R.id.news_title);
            TextView content = views.findViewById(R.id.news_content);
            LinearLayout linearLayout = views.findViewById(R.id.news_ll);

            Glide.with(getContext()).load("http://" + getServerIp() + rowsBean.getImgUrl()).error(R.mipmap.ic_launcher).into(imageView);
            title.setText(rowsBean.getTitle());
            content.setText(rowsBean.getContent());

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("news", json).apply();
                    getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
                }
            });
            return views;
        }
    }
}