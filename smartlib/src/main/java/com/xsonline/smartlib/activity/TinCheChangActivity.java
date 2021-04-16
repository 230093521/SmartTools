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
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.TinCheBean;

import java.io.IOException;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TinCheChangActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listTinche;
    List<TinCheBean.RowsBean> rows;
    private TextView tinchejilu;
    private TextView chakangenduo;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_tin_che_chang);
        initView();
        num = 1;
        initjilu();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        chakangenduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initjilu();
            }
        });
        tinchejilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinCheChangActivity.this,TinCheJiLuActivity.class));
            }
        });



    }

    private void initjilu() {
        getTools().sendGetRequest("http://"+getServerIp()+"/userinfo/parklot/list?pageNum="+num+"&pageSize=10", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                TinCheBean tinCheBean = new Gson().fromJson(json, TinCheBean.class);
                if (tinCheBean.getCode() == 200) {
                    if (num==1){
                        rows = tinCheBean.getRows();
                    }else {
                        rows.addAll(tinCheBean.getRows());
                    }
                    num=num+1;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listTinche.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView tinchechang;
                                    public TextView gongli;
                                    public TextView kongwei;
                                    public TextView weizhi;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tinchechang = (TextView) rootView.findViewById(R.id.tinchechang);
                                        this.gongli = (TextView) rootView.findViewById(R.id.gongli);
                                        this.kongwei = (TextView) rootView.findViewById(R.id.kongwei);
                                        this.weizhi = (TextView) rootView.findViewById(R.id.weizhi);
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
                                public View getView(int i, View view, ViewGroup viewGroup) {
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tinche, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    final TinCheBean.RowsBean rowsBean = rows.get(i);
                                    viewHolder.weizhi.setText(rowsBean.getAddress());
                                    viewHolder.gongli.setText(rowsBean.getDistance() + "公里");
                                    viewHolder.kongwei.setText("空位" + rowsBean.getVacancy() + "个|停车场" + rowsBean.getRates() + "元/小时");
                                    viewHolder.tinchechang.setText(rowsBean.getParkName());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("tinche", new Gson().toJson(rowsBean));
                                            startActivity(new Intent(TinCheChangActivity.this, TinCheXqActivity.class));
                                        }
                                    });
                                    return view;
                                }
                            });
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("没有更多了");
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listTinche = (ListView) findViewById(R.id.list_tinche);
        tinchejilu = (TextView) findViewById(R.id.tinchejilu);
        chakangenduo = (TextView) findViewById(R.id.chakangenduo);
    }
}