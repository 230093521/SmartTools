package com.gzeic.smartcity01.zhbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.BashiBean;
import com.gzeic.smartcity01.bean.BsXqBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaShiActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listBanchexianlu;
    List<BashiBean.RowsDTO> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_ba_shi);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/bus/line/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                BashiBean bashiBean = new Gson().fromJson(json, BashiBean.class);
                if (bashiBean.getCode() == 200) {
                    rows = bashiBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listBanchexianlu.setAdapter(new BaseAdapter() {

                                class ViewHolder {
                                    public View rootView;
                                    public Button btn_xianlu;
                                    public TextView xianlu1;
                                    public TextView xianlu2;
                                    public TextView qian;
                                    public TextView chufashijian;
                                    public TextView locheng;
                                    public TextView daodashijian;
                                    public ImageView shangjian;
                                    public ImageView xiajian;
                                    public ListView listxxxl;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.btn_xianlu = (Button) rootView.findViewById(R.id.btn_xianlu);
                                        this.xianlu1 = (TextView) rootView.findViewById(R.id.xianlu1);
                                        this.xianlu2 = (TextView) rootView.findViewById(R.id.xianlu2);
                                        this.qian = (TextView) rootView.findViewById(R.id.qian);
                                        this.chufashijian = (TextView) rootView.findViewById(R.id.chufashijian);
                                        this.locheng = (TextView) rootView.findViewById(R.id.locheng);
                                        this.daodashijian = (TextView) rootView.findViewById(R.id.daodashijian);
                                        this.shangjian = (ImageView) rootView.findViewById(R.id.shangjian);
                                        this.xiajian = (ImageView) rootView.findViewById(R.id.xiajian);
                                        this.listxxxl = (ListView) rootView.findViewById(R.id.listxxxl);
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
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_bs_xl, null);
                                    final ViewHolder viewHolder = new ViewHolder(view);
                                    final BashiBean.RowsDTO rowsBean = rows.get(i);
                                    viewHolder.btn_xianlu.setText(rowsBean.getName());
                                    viewHolder.xianlu1.setText(rowsBean.getFirst());
                                    viewHolder.xianlu2.setText(rowsBean.getEnd());
                                    viewHolder.locheng.setText(rowsBean.getMileage() + "km");
                                    viewHolder.qian.setText("￥" + rowsBean.getPrice());
                                    viewHolder.chufashijian.setText(rowsBean.getStartTime());
                                    viewHolder.daodashijian.setText(rowsBean.getEndTime());
                                    viewHolder.btn_xianlu.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("xianlu", new Gson().toJson(rowsBean));
                                            startActivity(new Intent(BaShiActivity.this, BaShiXqActivity.class));
                                        }
                                    });
                                    viewHolder.xiajian.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getBashi(viewHolder.listxxxl, rowsBean.getId() + "");
                                            viewHolder.xiajian.setVisibility(View.GONE);
                                            viewHolder.shangjian.setVisibility(View.VISIBLE);

                                        }
                                    });
                                    viewHolder.shangjian.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            viewHolder.listxxxl.setVisibility(View.GONE);
                                            viewHolder.shangjian.setVisibility(View.GONE);
                                            viewHolder.xiajian.setVisibility(View.VISIBLE);
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

    public void getBashi(final ListView listView, String id) {
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/bus/stop/list?linesId=" + id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                BsXqBean bsXqBean = new Gson().fromJson(string, BsXqBean.class);
                if (bsXqBean.getCode() == 200) {
                    final List<BsXqBean.RowsDTO> bsXqBeanRows = bsXqBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setVisibility(View.VISIBLE);
                            listView.setAdapter(new BaseAdapter() {

                                class ViewHolder {
                                    public View rootView;
                                    public ImageView qidian;
                                    public ImageView zhongdian;
                                    public TextView zhandian;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.qidian = (ImageView) rootView.findViewById(R.id.qidian);
                                        this.zhongdian = (ImageView) rootView.findViewById(R.id.zhongdian);
                                        this.zhandian = (TextView) rootView.findViewById(R.id.zhandian);
                                    }

                                }


                                @Override
                                public int getCount() {
                                    return bsXqBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return bsXqBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(BaShiActivity.this).inflate(R.layout.item_bs_xlxq, null);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    BsXqBean.RowsDTO rowsDTO = bsXqBeanRows.get(position);
                                    viewHolder.zhandian.setText(rowsDTO.getName());
                                    if (position==0){
                                        viewHolder.qidian.setVisibility(View.VISIBLE);
                                    }
                                    if (position==bsXqBeanRows.size()-1){
                                        viewHolder.zhongdian.setVisibility(View.VISIBLE);
                                    }
                                    return convertView;
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
        listBanchexianlu = (ListView) findViewById(R.id.list_banchexianlu);
    }
}