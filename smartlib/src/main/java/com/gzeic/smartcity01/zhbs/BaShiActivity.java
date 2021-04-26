package com.gzeic.smartcity01.zhbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.BashiBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaShiActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listBanchexianlu;
    List<BashiBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_ba_shi);
        initView();
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/lines/list?pageNum=1&pageSize=10", new Callback() {
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
                                    public TextView btn_xianlu;
                                    public TextView xianlu1;
                                    public TextView xianlu2;
                                    public TextView qian;
                                    public TextView chufashijian;
                                    public TextView locheng;
                                    public TextView daodashijian;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.btn_xianlu = (TextView) rootView.findViewById(R.id.btn_xianlu);
                                        this.xianlu1 = (TextView) rootView.findViewById(R.id.xianlu1);
                                        this.xianlu2 = (TextView) rootView.findViewById(R.id.xianlu2);
                                        this.qian = (TextView) rootView.findViewById(R.id.qian);
                                        this.chufashijian = (TextView) rootView.findViewById(R.id.chufashijian);
                                        this.locheng = (TextView) rootView.findViewById(R.id.locheng);
                                        this.daodashijian = (TextView) rootView.findViewById(R.id.daodashijian);
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
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_banchexian, null);
                                    ViewHolder viewHolder =  new ViewHolder(view);
                                    final BashiBean.RowsBean rowsBean = rows.get(i);
                                    viewHolder.btn_xianlu.setText(rowsBean.getName());
                                    viewHolder.xianlu1.setText(rowsBean.getFirst());
                                    viewHolder.xianlu2.setText(rowsBean.getEnd());
                                    viewHolder.locheng.setText(rowsBean.getMileage()+"km");
                                    viewHolder.qian.setText("￥"+rowsBean.getPrice());
                                    viewHolder.chufashijian.setText(rowsBean.getStartTime());
                                    viewHolder.daodashijian.setText(rowsBean.getEndTime());
                                    viewHolder.btn_xianlu.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("xianlu",new Gson().toJson(rowsBean));
                                            startActivity(new Intent(BaShiActivity.this,BaShiXqActivity.class));
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
        listBanchexianlu = (ListView) findViewById(R.id.list_banchexianlu);
    }
}