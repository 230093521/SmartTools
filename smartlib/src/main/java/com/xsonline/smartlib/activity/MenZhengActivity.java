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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.YiYuanBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MenZhengActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listYiyuan;
    List<YiYuanBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_men_zheng);
        initView();
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/registration/list?pageNum=1&pageSize=10", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                YiYuanBean yiYuanBean = new Gson().fromJson(json, YiYuanBean.class);
                rows = yiYuanBean.getRows();
                if (yiYuanBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listYiyuan.setAdapter(new BaseAdapter() {
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
                                    return rows.size();
                                }

                                @Override
                                public Object getItem(int i) {
                                    return rows.get(i);
                                }

                                @Override
                                public long getItemId(int i) {
                                    return i;
                                }

                                @Override
                                public View getView(final int i, View view, ViewGroup viewGroup) {
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_yiyuanming, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    Glide.with(getApplicationContext()).load("http://"+getServerIp()+rows.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(viewHolder.yiyuan_img);
                                    viewHolder.yiyuan_name.setText(rows.get(i).getHospitalName());
                                    viewHolder.yiyuanpingfen.setRating(Float.parseFloat(rows.get(i).getLevel()));
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("yiyuan",new Gson().toJson(rows.get(i)));
                                            startActivity(new Intent(MenZhengActivity.this,YiYuanXiangQingActivity.class));
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


    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listYiyuan = (ListView) findViewById(R.id.list_yiyuan);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}