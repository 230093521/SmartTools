package com.gzeic.smartcity01.x_wz;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.gzeic.smartcity01.FuWuJieGuoActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.WoFanKuiActivity;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.gzeic.smartcity01.bean.NewsBean;
import com.gzeic.smartcity01.bean.TestWtBean;
import com.gzeic.smartcity01.bean.XwxqBean;
import com.gzeic.smartcity01.xw.XinWenActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WZAFragment extends BaseFragment {
    private String http = "http://";
    private Banner banner;
    private ListViewScrollView fw;
    private LinearLayout wzcl;
    private LinearLayout jnfk;
    private LinearLayout czbx;
    private ListViewScrollView zxlist;
    private RelativeLayout rlYjfk;
    List<LunBoBean.RowsDTO> rows;
    private ImageView clba;
    private ImageView fwzx;
    private TextView gengduofuwu;
    private Button btnSousuo;
    private EditText homeEditSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_a, container, false);
        initView(view);
        init();
        rlYjfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoFanKuiActivity.class));
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                LunBoBean.RowsDTO rowsBean = rows.get(i);
                getxwxq(rowsBean.getId());
            }
        });
        wzcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WzWzcllbActivity.class);
                startActivity(intent);
            }
        });
        jnfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WzWfkActivity.class);
                startActivity(intent);
            }
        });
        czbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WzWzCxActivity.class);
                startActivity(intent);
            }
        });
        clba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WzWzbdActivity.class);
                startActivity(intent);
            }
        });

        fwzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WzSyActivity activity = (WzSyActivity) getActivity();
                activity.setSelect(4);
            }
        });
        gengduofuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WzSyActivity activity = (WzSyActivity) getActivity();
                activity.setSelect(4);
            }
        });

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = homeEditSearch.getText().toString();
                putSP("fwjg", s);
                homeEditSearch.setText("");
                startActivity(new Intent(getContext(), FuWuJieGuoActivity.class));
            }
        });

        return view;
    }

    private void init() {
        //??????
        banner.setImageLoader(new GlideImageLoader());
        getTools().sendGet(http + getServerIp() + "/prod-api/api/traffic/rotation/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                LunBoBean lb = gson.fromJson(string, LunBoBean.class);
                rows = lb.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(rows);
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object o, ImageView imageView) {
                                LunBoBean.RowsDTO rowsBean = (LunBoBean.RowsDTO) o;
                                Glide.with(getContext()).load(http + getServerIp() + rowsBean.getAdvImg()).into(imageView);
                            }
                        });
                        banner.start();
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
        final List<TestWtBean> testWtBeans = new ArrayList<>();
        testWtBeans.add(new TestWtBean("?????????????????????????????????", "????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 1));
        testWtBeans.add(new TestWtBean("????????????????????????", "??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????11???????????????????????????????????????????????????????????????????????????11???????????????1???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 2));
        testWtBeans.add(new TestWtBean("???????????????????????????", "???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????", 3));
        //????????????
        fw.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public TextView name;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.name = (TextView) rootView.findViewById(R.id.name);
                }

            }

            @Override
            public int getCount() {
                return testWtBeans.size();
            }

            @Override
            public Object getItem(int position) {
                return testWtBeans.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.itme_fwzx, parent, false);
                ViewHolder viewHolder = new ViewHolder(inflate1);
                viewHolder.name.setText(testWtBeans.get(position).getName());
                inflate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        putSP("fwwt", new Gson().toJson(testWtBeans.get(position)));
                        startActivity(new Intent(getActivity(), WzFwtwxqActivity.class));
                    }
                });
                return inflate1;
            }
        });
        getNewsList();
    }

    private void getNewsList() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/press/press/list?pageNum=3", getToken(), new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String json = response.body().string();
                Log.i("ceshi", "onResponse:?????? " + json);
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
                            zxlist.setAdapter(new BaseAdapter() {

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

    private void getxwxq(int id) {
        Log.i(TAG, "getxwxq: " + id);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/press/press/" + id, getToken(), new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                final XwxqBean xwxqBean = new Gson().fromJson(string, XwxqBean.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (xwxqBean.getData() == null) {
                            showToast("??????????????????????????????");
                            return;
                        }
                        putSP("xwzx", new Gson().toJson(xwxqBean.getData()));
                        startActivity(new Intent(getContext(), XinWenActivity.class));
                    }
                });
            }
        });
    }

    private void initView(View view) {
        banner = view.findViewById(R.id.a_banner);
        fw = view.findViewById(R.id.fw);
        wzcl = view.findViewById(R.id.wzcl);
        jnfk = view.findViewById(R.id.jnfk);
        czbx = view.findViewById(R.id.czbx);
        zxlist = (ListViewScrollView) view.findViewById(R.id.zxlist);
        rlYjfk = (RelativeLayout) view.findViewById(R.id.rl_yjfk);
        clba = (ImageView) view.findViewById(R.id.clba);
        fwzx = (ImageView) view.findViewById(R.id.fwzx);
        gengduofuwu = (TextView) view.findViewById(R.id.gengduofuwu);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
    }
}