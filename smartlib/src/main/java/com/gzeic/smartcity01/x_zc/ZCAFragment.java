package com.gzeic.smartcity01.x_zc;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.Tools.SwitchTextView;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.gzeic.smartcity01.bean.NewsBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.TianQiBean;
import com.gzeic.smartcity01.bean.ZcJfBean;
import com.gzeic.smartcity01.xw.XinWenActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZCAFragment extends BaseFragment {

    private EditText homeEditSearch;
    private Button btnSousuo;
    private Banner banner;
    private ImageView tqtb;
    private SwitchTextView tqgd;
    private SwitchTextView jfgd;
    private ImageView nuoche;
    private ImageView zhaoche;
    private ImageView chewei;
    private GridView recNewsfl;
    private ListViewScrollView newsList;
    private TextView gengduo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zc_a, container, false);
        initView(view);
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSP("tingchechang", homeEditSearch.getText().toString());
                startActivity(new Intent(getContext(), ZcTccssActivity.class));
            }
        });
        getLunbo();
        getTianQi();
        getJiFen();
        getXwfl();
        getNewsList(13);
        nuoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ZcNcActivity.class));
            }
        });
        zhaoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ZcCnzcActivity.class));
            }
        });
        chewei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ZcZcwActivity.class));
            }
        });
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                ZcSyActivity activity = (ZcSyActivity) getActivity();
                activity.setSelect(4);
            }
        });
        return view;
    }

    private void getLunbo() {
        //轮播图
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/rotation/list?pageNum=1&pageSize=8&type=2",getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final LunBoBean rowsListBean = new Gson().fromJson(json, LunBoBean.class);
                if (rowsListBean.getCode() == 200) {
                    final List<String> imageViews = new ArrayList<>();
                    List<LunBoBean.RowsDTO> rows = rowsListBean.getRows();
                    for (LunBoBean.RowsDTO rs : rows) {
                        imageViews.add("http://" + getServerIp() + rs.getAdvImg());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(final Context context, final Object o, final ImageView imageView) {
                                    Glide.with(context).load(o).into(imageView);
                                }
                            });
                            banner.setImages(imageViews);
                            banner.start();
                        }
                    });
                }
            }
        });
    }

    private void getXwfl() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/press/category/list", getToken(), new Callback() {
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
    }

    private void getNewsList(int id) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/press/press/list?type=" + id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("ceshi", "onResponse:测试 " + json);
                NewsBean newsBean = new Gson().fromJson(json, NewsBean.class);
                if (newsBean.getCode() == 200) {
                    List<NewsBean.RowsDTO> newsBeanRows = newsBean.getRows();
                    List<NewsBean.RowsDTO> newsBeanRows2 = new ArrayList<>();
                    newsBeanRows2.add(newsBeanRows.get(0));
                    newsBeanRows2.add(newsBeanRows.get(1));
                    newsBeanRows2.add(newsBeanRows.get(2));
                    newsBeanRows = newsBeanRows2;
                    final List<NewsBean.RowsDTO> finalNewsBeanRows = newsBeanRows;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            newsList.setAdapter(new BaseAdapter() {

                                @Override
                                public int getCount() {
                                    return finalNewsBeanRows.size();
                                }

                                @Override
                                public Object getItem(int i) {
                                    return finalNewsBeanRows.get(i);
                                }

                                @Override
                                public long getItemId(int i) {
                                    return i;
                                }

                                @Override
                                public View getView(int i, View view, ViewGroup viewGroup) {
                                    View views = LayoutInflater.from(getContext()).inflate(R.layout.item_xw, null);
                                    final NewsBean.RowsDTO rowsBean = (NewsBean.RowsDTO) getItem(i);
                                    Log.i("ceshi", "getView: " + rowsBean.getContent());
                                    ImageView imageView = views.findViewById(R.id.news_image);
                                    TextView title = views.findViewById(R.id.news_title);
                                    TextView content = views.findViewById(R.id.news_content);
                                    LinearLayout linearLayout = views.findViewById(R.id.news_ll);

                                    Glide.with(getContext()).load("http://" + getServerIp() + rowsBean.getCover()).error(R.drawable.icon2).into(imageView);
                                    title.setText(rowsBean.getTitle());
                                    content.setText(Html.fromHtml(rowsBean.getContent()));

                                    linearLayout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String json = new Gson().toJson(rowsBean);
                                            putSP("xwzx", json);
                                            putSP("yemian", "");
                                            getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
                                        }
                                    });
                                    return views;
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void getJiFen() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/score/notice/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final ZcJfBean zcJfBean = new Gson().fromJson(string, ZcJfBean.class);
                if (zcJfBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<ZcJfBean.RowsDTO> rows = zcJfBean.getRows();
                            List<String> texts = new ArrayList<>();
                            for (ZcJfBean.RowsDTO row : rows) {
                                texts.add("用户" + row.getUserName() + "通过" + row.getEvent() + row.getType() + "了" + row.getScore() + "积分！");
                            }
//                        jfgd.setText(stringBuffer.toString());
                            jfgd.startPlay(texts);
                        }
                    });
                }
            }
        });
    }

    private void getTianQi() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/common/weather/today", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final TianQiBean tianQiBean = new Gson().fromJson(string, TianQiBean.class);
                if (tianQiBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TianQiBean.DataDTO tianQiBeanData = tianQiBean.getData();
                            if (tianQiBeanData.getWeather().equals("晴")) {
                                tqtb.setImageResource(R.drawable.qingtian);
                            } else if (tianQiBeanData.getWeather().equals("雨")) {
                                tqtb.setImageResource(R.drawable.yutian);
                            } else {
                                tqtb.setImageResource(R.drawable.yingtian);
                            }
                            List<String> texts = new ArrayList<>();
                            texts.add("当前温度：" + tianQiBeanData.getTemperature() + "℃ 湿度：" + tianQiBeanData.getHumidity() + "  空气质量：" + tianQiBeanData.getAir());
                            tqgd.startPlay(texts);
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
        banner = (Banner) view.findViewById(R.id.banner);
        tqtb = (ImageView) view.findViewById(R.id.tqtb);
        tqgd = (SwitchTextView) view.findViewById(R.id.tqgd);
        jfgd = (SwitchTextView) view.findViewById(R.id.jfgd);
        nuoche = (ImageView) view.findViewById(R.id.shoujifei);
        zhaoche = (ImageView) view.findViewById(R.id.shuifei);
        chewei = (ImageView) view.findViewById(R.id.dianfei);
        recNewsfl = (GridView) view.findViewById(R.id.rec_newsfl);
        newsList = (ListViewScrollView) view.findViewById(R.id.news_list);
        gengduo = (TextView) view.findViewById(R.id.gengduo);
    }
}