package com.gzeic.smartcity01.mzyy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YiYuanBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listYiyuan;
    List<YiYuanBean.RowsDTO> rows;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private TextView homeSearchBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_mz);
        initView();
        getYiyuanList();
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = homeEditSearch.getText().toString();
                if (toString.isEmpty()){
                    showToast("输入不能为空");
                    return;
                }
                getYySousuoList(toString);
            }
        });
    }

    private void getYiyuanList() {
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/hospital/hospital/list", new Callback() {
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
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_mz_yym, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    Glide.with(getApplicationContext()).load("http://" + getServerIp() + rows.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(viewHolder.yiyuan_img);
                                    viewHolder.yiyuan_name.setText(rows.get(i).getHospitalName());
                                    viewHolder.yiyuanpingfen.setRating(rows.get(i).getLevel());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("yiyuan", new Gson().toJson(rows.get(i)));
                                            startActivity(new Intent(MzActivity.this, MzYyxqActivity.class));
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

    public void getYySousuoList(String name){
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/hospital/hospital/list?hospitalName="+name, new Callback() {
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
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_mz_yym, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    Glide.with(getApplicationContext()).load("http://" + getServerIp() + rows.get(i).getImgUrl()).error(R.mipmap.ic_launcher).into(viewHolder.yiyuan_img);
                                    viewHolder.yiyuan_name.setText(rows.get(i).getHospitalName());
                                    viewHolder.yiyuanpingfen.setRating(rows.get(i).getLevel());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("yiyuan", new Gson().toJson(rows.get(i)));
                                            startActivity(new Intent(MzActivity.this, MzYyxqActivity.class));
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
        ivMei = (ImageView) findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) findViewById(R.id.home_edit_search);
        btnSousuo = (Button) findViewById(R.id.btn_sousuo);
        homeSearchBase = (TextView) findViewById(R.id.home_search_base);
    }
}