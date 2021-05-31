package com.gzeic.smartcity01.x_hdgl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.FwBean;
import com.gzeic.smartcity01.bean.HdlbBean;
import com.gzeic.smartcity01.bean.HdlbxqBean;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.gzeic.smartcity01.bean.NewsFlBean;
import com.gzeic.smartcity01.bean.XwlblbBean;
import com.gzeic.smartcity01.x_csdt.CsdtSyActivity;
import com.gzeic.smartcity01.xw.XinWenActivity;
import com.gzeic.smartcity01.xw.XinWenZiXunActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HdSyActivity extends BaseActivity implements View.OnClickListener {
    private List<String> imageViews;
    private Banner banner;
    private List<FwBean.RowsDTO> fwlist;
    private List<XwlblbBean.RowsDTO> ztlist;
    private List<NewsFlBean.DataDTO> newFllist;
    private List<HdlbBean.RowsDTO> newsList;
    List<LunBoBean.RowsDTO> lbrows;
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
    private ImageView metroBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hd_sy);
        initView();
        //      recyclerView = view.findViewById(R.id.fw_rl);
        recyclerView2 = findViewById(R.id.rec_newsfl);
        newsListView = findViewById(R.id.news_list);
        home_edit_search = findViewById(R.id.home_edit_search);
        home_edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String s = home_edit_search.getText().toString();
                    putSP("xwmc", s);
                    home_edit_search.setText("");
                    Log.i(TAG, "onKey: 111");
                    startActivity(new Intent(HdSyActivity.this, XinWenZiXunActivity.class));
                    return true;
                }
                return false;
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HdSyActivity.this,LinearLayoutManager.HORIZONTAL,true);
//        recyclerView.setLayoutManager(linearLayoutManager);
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = home_edit_search.getText().toString();
                putSP("xwmc", s);
                home_edit_search.setText("");
                Log.i(TAG, "onKey: 111");
                startActivity(new Intent(HdSyActivity.this, XinWenZiXunActivity.class));
            }
        });
        //轮播图
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/activity/rotation/list?pageNum=1&pageSize=8&type=2", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "XXXXXXXXXXXXXXXXXXXXXXXXX " + json);
                final LunBoBean rowsListBean = new Gson().fromJson(json, LunBoBean.class);
                if (rowsListBean.getCode() == 200) {
                    imageViews = new ArrayList<>();
                    lbrows = rowsListBean.getRows();
                    for (LunBoBean.RowsDTO rs : lbrows) {
                        imageViews.add("http://" + getServerIp() + rs.getAdvImg());
                    }
                    try {
                        runOnUiThread(new Runnable() {
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
                        runOnUiThread(new Runnable() {
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
        //服务列表
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/service/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                FwBean fwBean = new Gson().fromJson(json, FwBean.class);
                if (fwBean.getCode() == 200) {
                    fwlist = fwBean.getRows();
                    initFw();
                    FwBean.RowsDTO rowsBean = fwlist.get(0);
                    String s = new Gson().toJson(rowsBean);
//                    //新增模块:更多服务
                    FwBean.RowsDTO rowsBean1 = new Gson().fromJson(s, FwBean.RowsDTO.class);
                    rowsBean1.setId(99);
                    rowsBean1.setServiceName("更多服务");
                    rowsBean1.setImgUrl("001");
                    fwlist.add(rowsBean1);
                    //新增模块:减掉多余模块
                    fwlist.remove(fwlist.size() - 2);
                    fwlist.remove(fwlist.size() - 6);
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
        //推荐主题
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/press/press/list?hot=Y", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                XwlblbBean ztBean = new Gson().fromJson(json, XwlblbBean.class);
                if (ztBean.getCode() == 200) {
                    ztlist = ztBean.getRows();
                    initZt();
                }
            }
        });
        //新闻分类
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/activity/category/list", getToken(), new Callback() {
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
        //新闻列表
        getNewsList(1);
        //轮播图点击
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                LunBoBean.RowsDTO rowsBean = lbrows.get(i);
                getHdxq(rowsBean.getTargetId());
            }
        });
    }

    private void getHdxq(int id) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/activity/activity/"+id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final HdlbxqBean hdlbxqBean = new Gson().fromJson(string, HdlbxqBean.class);
                String json = new Gson().toJson(hdlbxqBean.getData());
                SharedPreferences sharedPreferences = getSharedPreferences("hdxq", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("hdxq", json).apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hdlbxqBean.getData()==null){
                            showToast("该轮播图暂无跳转主题");
                            return;
                        }
                        startActivity(new Intent(HdSyActivity.this, HdXqyActivity.class));
                    }
                });
            }
        });
    }

    private void initView() {
        faZtLl1 = (LinearLayout) findViewById(R.id.fa_zt_ll1);
        faZtImage1 = (ImageView) findViewById(R.id.fa_zt_image1);
        faZtText1 = (TextView) findViewById(R.id.fa_zt_text1);
        faZtLl2 = (LinearLayout) findViewById(R.id.fa_zt_ll2);
        faZtImage2 = (ImageView) findViewById(R.id.fa_zt_image2);
        faZtText2 = (TextView) findViewById(R.id.fa_zt_text2);
        faZtLl3 = (LinearLayout) findViewById(R.id.fa_zt_ll3);
        faZtImage3 = (ImageView) findViewById(R.id.fa_zt_image3);
        faZtText3 = (TextView) findViewById(R.id.fa_zt_text3);
        faZtLl4 = (LinearLayout) findViewById(R.id.fa_zt_ll4);
        faZtImage4 = (ImageView) findViewById(R.id.fa_zt_image4);
        faZtText4 = (TextView) findViewById(R.id.fa_zt_text4);
        banner = findViewById(R.id.a_banner);
        faZtLl1.setOnClickListener(this);
        faZtLl2.setOnClickListener(this);
        faZtLl3.setOnClickListener(this);
        faZtLl4.setOnClickListener(this);
        gridview = findViewById(R.id.gridview);
        btnSousuo = findViewById(R.id.btn_sousuo);
        metroBase = (ImageView) findViewById(R.id.metro_base);
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
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //                recyclerView.setAdapter(new MyAdapter(fwlist));
                    //                recyclerView.scrollToPosition(1);
                    boolean tablet = isTablet(HdSyActivity.this);
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
                            view = LayoutInflater.from(HdSyActivity.this).inflate(R.layout.item_fw_tb, null);
                            ViewHolder viewHolder = new ViewHolder(view);
                            viewHolder.fw_text.setText(fwlist.get(i).getServiceName());
                            if (fwlist.get(i).getImgUrl().contains("/")) {
                                Glide.with(HdSyActivity.this).load("http://" + getServerIp() + fwlist.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(viewHolder.fw_image);
                            } else {
                                switch (fwlist.get(i).getId()) {
                                    case 99://更多服务
                                        viewHolder.fw_image.setImageResource(R.drawable.ic_gengduo);
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
                                    FwBean.RowsDTO rowsBean = fwlist.get(i);
                                    //根据ID跳转对应服务
                                    switch (rowsBean.getId()) {
                                        case 7://生活缴费
//                                            startActivity(new Intent(HdSyActivity.this, ShActivity.class));
                                            break;
                                        case 2://城市地铁
                                            startActivity(new Intent(HdSyActivity.this, CsdtSyActivity.class));
                                            break;
                                        case 25://预约检车
//                                            startActivity(new Intent(HdSyActivity.this, YyjcActivity.class));
                                            break;
                                        case 24://堵车移车
//                                            startActivity(new Intent(HdSyActivity.this, DcycActivity.class));
                                            break;
                                        case 20://找房子
//                                            startActivity(new Intent(HdSyActivity.this, ZfzActivity.class));
                                            break;
                                        case 4://找工作
//                                            startActivity(new Intent(HdSyActivity.this, ZgzActivity.class));
                                            break;
                                        case 5://门诊预约
//                                            startActivity(new Intent(HdSyActivity.this, MzActivity.class));
                                            break;
                                        case 3://智慧巴士
//                                            startActivity(new Intent(HdSyActivity.this, BaShiActivity.class));
                                            break;
                                        case 16://停车场
//                                            startActivity(new Intent(HdSyActivity.this, TccActivity.class));
                                            break;
                                        case 9://违章查询
//                                            startActivity(new Intent(HdSyActivity.this, WzActivity.class));
                                            break;
                                        case 99://更多服务
//                                            startActivity(new Intent(HdSyActivity.this, ZHDJActivity.class));
                                            break;
                                        //                                    case 21://智慧养老
                                        //                                        startActivity(new Intent(HdSyActivity.this, YangLaoActivity.class));
                                        //                                        break;
                                        //                                    case 22://智慧环保
                                        //                                        startActivity(new Intent(HdSyActivity.this, WeiZhangActivity.class));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void initZt() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Glide.with(HdSyActivity.this).load("http://" + getServerIp() + ztlist.get(0).getCover()).error(R.drawable.ceshi).into(faZtImage1);
                        Glide.with(HdSyActivity.this).load("http://" + getServerIp() + ztlist.get(1).getCover()).error(R.drawable.ceshi).into(faZtImage2);
                        //Glide.with(HdSyActivity.this).load("http://" + getServerIp() + ztlist.get(2).getCover()).error(R.drawable.ceshi).into(faZtImage3);
                        //Glide.with(HdSyActivity.this).load("http://" + getServerIp() + ztlist.get(3).getCover()).error(R.drawable.ceshi).into(faZtImage4);
                        faZtText1.setText(Html.fromHtml(ztlist.get(0).getContent()));
                        faZtText2.setText(Html.fromHtml(ztlist.get(1).getContent()));
                        //faZtText3.setText(Html.fromHtml(ztlist.get(2).getContent()));
                        //faZtText4.setText(Html.fromHtml(ztlist.get(3).getContent()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initNewsFl() {
        try {
            runOnUiThread(new Runnable() {
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
                            convertView = LayoutInflater.from(HdSyActivity.this).inflate(R.layout.item_xw_fl, null);
                            final NewsFlBean.DataDTO dataBean = newFllist.get(position);
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/activity/activity/list?categoryId=" + dictCode, getToken(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            Log.i("ceshi", "onResponse:测试 " + json);
                            HdlbBean newsBean = new Gson().fromJson(json, HdlbBean.class);
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    newsListView.setAdapter(new NewsAdapter(newsList));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fa_zt_ll1) {
            String json = new Gson().toJson(ztlist.get(0));
            SharedPreferences sharedPreferences = getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("news", json).apply();
            startActivity(new Intent(HdSyActivity.this, XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll2) {
            String json1 = new Gson().toJson(ztlist.get(1));
            SharedPreferences sharedPreferences1 = getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences1.edit().putString("news", json1).apply();
            startActivity(new Intent(HdSyActivity.this, XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll3) {
            String json2 = new Gson().toJson(ztlist.get(2));
            SharedPreferences sharedPreferences2 = getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences2.edit().putString("news", json2).apply();
            startActivity(new Intent(HdSyActivity.this, XinWenActivity.class));
        } else if (id == R.id.fa_zt_ll4) {
            String json3 = new Gson().toJson(ztlist.get(3));
            SharedPreferences sharedPreferences3 = getSharedPreferences("news", Context.MODE_PRIVATE);
            sharedPreferences3.edit().putString("news", json3).apply();
            startActivity(new Intent(HdSyActivity.this, XinWenActivity.class));
        }
    }

    class NewsAdapter extends BaseAdapter {
        List<HdlbBean.RowsDTO> newsList;
        LayoutInflater inflater;

        public NewsAdapter(List<HdlbBean.RowsDTO> newsList) {
            this.newsList = newsList;
            inflater = LayoutInflater.from(HdSyActivity.this);
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
            View views = inflater.inflate(R.layout.item_hd_dz, null);
            final HdlbBean.RowsDTO rowsBean = (HdlbBean.RowsDTO) getItem(i);
            final ViewHolder viewHolder = new ViewHolder(views);
            Glide.with(HdSyActivity.this).load("http://" + getServerIp() + rowsBean.getImgUrl()).error(R.drawable.icon2).into(viewHolder.news_image);
            viewHolder.news_title.setText(rowsBean.getName());
            viewHolder.dianzanshu.setText(rowsBean.getLikeNum() + "");
            viewHolder.fabushijian.setText(rowsBean.getPublishTime());
            viewHolder.yuedushu.setText(rowsBean.getSignupNum() + "");
            viewHolder.zdimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.dianzanshu.setText(rowsBean.getLikeNum() + 1 + "");
                    showToast("点赞成功");
                }
            });
            viewHolder.dianzanshu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.dianzanshu.setText(rowsBean.getLikeNum() + 1 + "");
                    showToast("点赞成功");
                }
            });
            viewHolder.news_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    putSP("hdxq", json);
                    putSP("yemian", "");
                    startActivity(new Intent(HdSyActivity.this, HdXqyActivity.class));
                }
            });
            return views;
        }

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
    }

}