package com.gzeic.smartcity01.x_csdt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTxltBean;
import com.gzeic.smartcity01.bean.DiTieZdxlxBean;
import com.gzeic.smartcity01.bean.DiTieZdxxBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtXltActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private LinearLayout celalan;
    private ListView listXlbt;
    private PhotoView tupian;
    private LinearLayout tuli;
    private LinearLayout yuyan;
    private LinearLayout xianlu;
    List<DiTieZdxlxBean.DataDTO> diTieXLMCBeanData;
    DiTieZdxlxBean.DataDTO xuanzexianludx;
    int yuyantype = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_xlt);
        initView();
        getxlt();
        getXLLB();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xianlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (celalan.getVisibility() == View.GONE) {
                    celalan.setVisibility(View.VISIBLE);
                } else {
                    celalan.setVisibility(View.GONE);
                }
            }
        });
        tuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CsdtXltActivity.this,CsdtTlActivity.class));
            }
        });
        yuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("切换语言成功");
                if (yuyantype==1){
                    yuyantype=2;
                }else {
                    yuyantype=1;
                }
                getXLLB();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String xuanzexianlu = getSP("xuanzexianlu");
        xuanzexianludx = new Gson().fromJson(xuanzexianlu, DiTieZdxlxBean.DataDTO.class);
        getXLLB();
    }

    private void getXLLB() {
        String url = "http://" + getServerIp() + "/prod-api/api/metro/line/list";

        getTools().sendGetRequestToken(url, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: " + string);
                DiTieZdxlxBean diTieXLMCBean = null;
                diTieXLMCBean = new Gson().fromJson(string, DiTieZdxlxBean.class);
                if (diTieXLMCBean.getCode() == 200) {
                    diTieXLMCBeanData = diTieXLMCBean.getData();
                    HashSet<DiTieZdxlxBean.DataDTO> dataDTOS = new HashSet<>(diTieXLMCBeanData);
                    diTieXLMCBeanData = new ArrayList<>(dataDTOS);
                    Collections.sort(diTieXLMCBeanData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listXlbt.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView xlmc;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.xlmc = (TextView) rootView.findViewById(R.id.xlmc);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return diTieXLMCBeanData.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return diTieXLMCBeanData.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = LayoutInflater.from(CsdtXltActivity.this).inflate(R.layout.item_dt_xlxx, null);
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    final DiTieZdxlxBean.DataDTO dataDTO = diTieXLMCBeanData.get(position);
                                    if (xuanzexianludx!=null){
                                        if (xuanzexianludx.getLineId()==dataDTO.getLineId()){
                                            viewHolder.xlmc.setTextColor(Color.RED);
                                        }
                                    }
                                    //调用翻译
                                    if (yuyantype==1){
                                        viewHolder.xlmc.setText(dataDTO.getLineName());
                                    }else {
                                        viewHolder.xlmc.setText(dataDTO.getLineName());
                                    }
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            putSP("zhandianxq", String.valueOf(dataDTO.getLineId()));
                                            startActivity(new Intent(CsdtXltActivity.this, CsdtXlxzActivity.class));
                                        }
                                    });
                                    return inflate;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void getxlt() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/city", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTxltBean dTxltBean = new Gson().fromJson(string, DTxltBean.class);
                if (dTxltBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(CsdtXltActivity.this).load("http://" + getServerIp() + dTxltBean.getData().getImgUrl()).into(tupian);
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        celalan = (LinearLayout) findViewById(R.id.celalan);
        listXlbt = (ListView) findViewById(R.id.list_xlbt);
        tupian = (PhotoView) findViewById(R.id.tupian);
        tuli = (LinearLayout) findViewById(R.id.tuli);
        yuyan = (LinearLayout) findViewById(R.id.yuyan);
        xianlu = (LinearLayout) findViewById(R.id.xianlu);
    }
}