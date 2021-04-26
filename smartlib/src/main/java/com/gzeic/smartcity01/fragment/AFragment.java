package com.gzeic.smartcity01.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.FwBean;
import com.gzeic.smartcity01.bean.NewsBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.RowsBean;
import com.gzeic.smartcity01.bean.RowsListBean;
import com.gzeic.smartcity01.bean.ZtBean;
import com.gzeic.smartcity01.dcyc.DcycActivity;
import com.gzeic.smartcity01.ditie.DiTieActivity;
import com.gzeic.smartcity01.mzyy.MzActivity;
import com.gzeic.smartcity01.shjf.ShActivity;
import com.gzeic.smartcity01.tcc.TccActivity;
import com.gzeic.smartcity01.wzcx.WzActivity;
import com.gzeic.smartcity01.xinwen.XinWenActivity;
import com.gzeic.smartcity01.xinwen.XinWenZiXunActivity;
import com.gzeic.smartcity01.yyjc.YyjcActivity;
import com.gzeic.smartcity01.zfz.ZfzActivity;
import com.gzeic.smartcity01.zgz.ZgzActivity;
import com.gzeic.smartcity01.zhbs.BaShiActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AFragment extends BaseFragment implements View.OnClickListener {
    private List<String> imageViews;
    private Banner banner;
    private List<FwBean.RowsBean> fwlist;
    private List<ZtBean.RowsBean> ztlist;
    private List<NewsFlBean.DataBean> newFllist;
    private List<NewsBean.RowsBean> newsList;
    List<RowsBean> lbrows;
    //    private  RecyclerView recyclerView;
    private GridView recyclerView2;
    ListView newsListView;
    private LinearLayout faZtLl1;
    private ImageView faZtImage1;
    private TextView faZtText1;
    private LinearLayout faZtLl2;
    private ImageView faZtImage2;
    private TextView faZtText2;
    private LinearLayout faZtLl3;
    private ImageView faZtImage3;
    private TextView faZtText3;
    private LinearLayout faZtLl4;
    private ImageView faZtImage4;
    private TextView faZtText4;
    private GridView gridview;
    private EditText home_edit_search;
    private Button btnSousuo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView(view);
//        recyclerView = view.findViewById(R.id.fw_rl);
        recyclerView2 = view.findViewById(R.id.rec_newsfl);
        newsListView = view.findViewById(R.id.news_list);
        home_edit_search = view.findViewById(R.id.home_edit_search);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
//        recyclerView.setLayoutManager(linearLayoutManager);

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = home_edit_search.getText().toString();
                putSP("xwmc", s);
                home_edit_search.setText("");
                Log.i(TAG, "onKey: 111");
                startActivity(new Intent(getContext(), XinWenZiXunActivity.class));
            }
        });

        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/rotation/list?pageNum=1&pageSize=10&type=45", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final RowsListBean rowsListBean = new Gson().fromJson(json, RowsListBean.class);
                if (rowsListBean.getCode() == 200) {
                    imageViews = new ArrayList<>();
                    lbrows = rowsListBean.getRows();
                    for (RowsBean rs : lbrows) {
                        imageViews.add("http://" + getServerIp() + rs.getImgUrl());
                        Log.e("ceshi", "http://" + getServerIp() + rs.getImgUrl());
                    }
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initBanner();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(rowsListBean.getMsg());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getTools().sendGetRequest("http://" + getServerIp() + "/service/service/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                FwBean fwBean = new Gson().fromJson(json, FwBean.class);
                if (fwBean.getCode() == 200) {
                    fwlist = fwBean.getRows();
                    initFw();
//                    FwBean.RowsBean rowsBean = fwlist.get(0);
//                    String s = new Gson().toJson(rowsBean);
//                    //新增模块:智慧党建
//                    FwBean.RowsBean rowsBean1 = new Gson().fromJson(s, FwBean.RowsBean.class);
//                    rowsBean1.setId(20);
//                    rowsBean1.setServiceName("智慧党建");
//                    rowsBean1.setImgUrl("001");
//                    fwlist.add(rowsBean1);
//                    //新增模块:智慧养老
//                    FwBean.RowsBean rowsBean2 = new Gson().fromJson(s, FwBean.RowsBean.class);
//                    rowsBean2.setId(21);
//                    rowsBean2.setServiceName("智慧养老");
//                    rowsBean2.setImgUrl("002");
//                    fwlist.add(rowsBean2);
//                    //新增模块:智慧环保
//                    FwBean.RowsBean rowsBean3 = new Gson().fromJson(s, FwBean.RowsBean.class);
//                    rowsBean3.setId(22);
//                    rowsBean3.setServiceName("智慧环保");
//                    rowsBean3.setImgUrl("003");
//                    fwlist.add(rowsBean3);

                } else {

                }
            }
        });
        getTools().sendGetRequest("http://" + getServerIp() + "/press/press/list?pageNum=1&pageSize=10", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                ZtBean ztBean = new Gson().fromJson(json, ZtBean.class);
                if (ztBean.getCode() == 200) {
                    ztlist = ztBean.getRowsss();
                    Collections.sort(ztlist);
                    initZt();
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
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                RowsBean rowsBean = lbrows.get(i);
                String json = new Gson().toJson(rowsBean);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("news", json).apply();
                getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
            }
        });
        return view;
    }

    public void initBanner() {
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(final Context context, final Object o, final ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        });
        banner.setImages(imageViews);
        banner.start();
    }


    public void initFw() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                recyclerView.setAdapter(new MyAdapter(fwlist));
//                recyclerView.scrollToPosition(1);
                boolean tablet = isTablet(getContext());
                if (tablet) {
                    ViewGroup.LayoutParams layoutParams = gridview.getLayoutParams();
                    layoutParams.height = 200;
                    gridview.setLayoutParams(layoutParams);
                    gridview.setNumColumns(6);

                }
                gridview.setAdapter(new BaseAdapter() {
                    class ViewHolder {
                        public View rootView;
                        public ImageView fw_image;
                        public TextView fw_text;

                        public ViewHolder(View rootView) {
                            this.rootView = rootView;
                            this.fw_image = (ImageView) rootView.findViewById(R.id.fw_image);
                            this.fw_text = (TextView) rootView.findViewById(R.id.fw_text);
                        }

                    }

                    @Override
                    public int getCount() {
                        return fwlist.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return fwlist.get(i);
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        view = LayoutInflater.from(getContext()).inflate(R.layout.item_fuwu, null);
                        ViewHolder viewHolder = new ViewHolder(view);
                        viewHolder.fw_text.setText(fwlist.get(i).getServiceName());
                        if (fwlist.get(i).getImgUrl().contains("/")) {
                            Glide.with(getContext()).load("http://" + getServerIp() + fwlist.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(viewHolder.fw_image);
                        } else {
                            switch (fwlist.get(i).getId()) {
                                case 20://智慧党建
                                    viewHolder.fw_image.setImageResource(R.drawable.zhdj);
                                    break;
                                case 21://智慧养老
                                    viewHolder.fw_image.setImageResource(R.drawable.zhyl);
                                    break;
                                case 22://智慧环保
                                    viewHolder.fw_image.setImageResource(R.drawable.zhhb);
                                    break;
                                default:
                                    break;
                            }
                        }
                        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FwBean.RowsBean rowsBean = fwlist.get(i);
                                //根据ID跳转对应服务
                                switch (rowsBean.getId()) {
                                    case 7://生活缴费
                                        startActivity(new Intent(getContext(), ShActivity.class));
                                        break;
                                    case 2://城市地铁
                                        startActivity(new Intent(getContext(), DiTieActivity.class));
                                        break;
                                    case 25://预约检车
                                        startActivity(new Intent(getContext(), YyjcActivity.class));
                                        break;
                                    case 24://堵车移车
                                        startActivity(new Intent(getContext(), DcycActivity.class));
                                        break;
                                    case 23://找房子
                                        startActivity(new Intent(getContext(), ZfzActivity.class));
                                        break;
                                    case 4://找工作
                                        startActivity(new Intent(getContext(), ZgzActivity.class));
                                        break;
                                    case 5://门诊预约
                                        startActivity(new Intent(getContext(), MzActivity.class));
                                        break;
                                    case 3://智慧巴士
                                        startActivity(new Intent(getContext(), BaShiActivity.class));
                                        break;
                                    case 16://停车场
                                        startActivity(new Intent(getContext(), TccActivity.class));
                                        break;
                                    case 9://违章查询
                                        startActivity(new Intent(getContext(), WzActivity.class));
                                        break;
//                                    case 20://智慧党建
//                                        startActivity(new Intent(getContext(), ZHDJActivity.class));
//                                        break;
//                                    case 21://智慧养老
//                                        startActivity(new Intent(getContext(), YangLaoActivity.class));
//                                        break;
//                                    case 22://智慧环保
//                                        startActivity(new Intent(getContext(), WeiZhangActivity.class));
//                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                        return view;
                    }
                });
            }
        });
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public void initZt() {
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getContext()).load("http://" + getServerIp() + ztlist.get(0).getImgUrl()).error(R.drawable.ceshi).into(faZtImage1);
                    Glide.with(getContext()).load("http://" + getServerIp() + ztlist.get(1).getImgUrl()).error(R.drawable.ceshi).into(faZtImage2);
                    Glide.with(getContext()).load("http://" + getServerIp() + ztlist.get(2).getImgUrl()).error(R.drawable.ceshi).into(faZtImage3);
                    Glide.with(getContext()).load("http://" + getServerIp() + ztlist.get(3).getImgUrl()).error(R.drawable.ceshi).into(faZtImage4);
                    faZtText1.setText(ztlist.get(0).getContent());
                    faZtText2.setText(ztlist.get(1).getContent());
                    faZtText3.setText(ztlist.get(2).getContent());
                    faZtText4.setText(ztlist.get(3).getContent());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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


    private void initView(View view) {
        faZtLl1 = (LinearLayout) view.findViewById(R.id.fa_zt_ll1);
        faZtImage1 = (ImageView) view.findViewById(R.id.fa_zt_image1);
        faZtText1 = (TextView) view.findViewById(R.id.fa_zt_text1);
        faZtLl2 = (LinearLayout) view.findViewById(R.id.fa_zt_ll2);
        faZtImage2 = (ImageView) view.findViewById(R.id.fa_zt_image2);
        faZtText2 = (TextView) view.findViewById(R.id.fa_zt_text2);
        faZtLl3 = (LinearLayout) view.findViewById(R.id.fa_zt_ll3);
        faZtImage3 = (ImageView) view.findViewById(R.id.fa_zt_image3);
        faZtText3 = (TextView) view.findViewById(R.id.fa_zt_text3);
        faZtLl4 = (LinearLayout) view.findViewById(R.id.fa_zt_ll4);
        faZtImage4 = (ImageView) view.findViewById(R.id.fa_zt_image4);
        faZtText4 = (TextView) view.findViewById(R.id.fa_zt_text4);
        banner = view.findViewById(R.id.a_banner);
        faZtLl1.setOnClickListener(this);
        faZtLl2.setOnClickListener(this);
        faZtLl3.setOnClickListener(this);
        faZtLl4.setOnClickListener(this);
        gridview = view.findViewById(R.id.gridview);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fa_zt_ll1) {
            String json = new Gson().toJson(ztlist.get(0));
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("news", json).apply();
            getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll2) {
            String json1 = new Gson().toJson(ztlist.get(1));
            SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences1.edit().putString("news", json1).apply();
            getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll3) {
            String json2 = new Gson().toJson(ztlist.get(2));
            SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences2.edit().putString("news", json2).apply();
            getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll4) {
            String json3 = new Gson().toJson(ztlist.get(3));
            SharedPreferences sharedPreferences3 = getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences3.edit().putString("news", json3).apply();
            getActivity().startActivity(new Intent(getContext(), XinWenActivity.class));
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