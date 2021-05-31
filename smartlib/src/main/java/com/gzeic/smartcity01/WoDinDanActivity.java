package com.gzeic.smartcity01;

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
import com.gzeic.smartcity01.bean.BaShiDdBean;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoDinDanActivity extends BaseActivity implements View.OnClickListener {

    private ImageView orderBase;
    private ListView orderList;
    private TextView tvWzfBtn;
    private TextView tvYzfBtn;
    private MyAdapter myAdapter;
    List<BaShiDdBean.RowsDTO> rows;
    List<BaShiDdBean.RowsDTO> yzfRows;
    List<BaShiDdBean.RowsDTO> wzfRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wo_dingdan);
        initView();
        getLieBiao();
        tvWzfBtn.setBackgroundColor(Color.parseColor("#DDDDDD"));
        tvYzfBtn.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    private void getLieBiao() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/bus/order/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                BaShiDdBean baShiDdBean = new Gson().fromJson(json, BaShiDdBean.class);
                if (baShiDdBean.getCode() == 200) {
                    rows = baShiDdBean.getRows();
                    if (rows.size() == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("暂无订单数据");
                            }
                        });
                    }
                    yzfRows = new ArrayList<>();
                    wzfRows = new ArrayList<>();
                    for (BaShiDdBean.RowsDTO dataBean : rows) {
                        if (dataBean.getStatus() == 1) {
                            yzfRows.add(dataBean);
                        } else {
                            wzfRows.add(dataBean);
                        }
                    }
                    Log.e(TAG, "onResponse: " + yzfRows.size() + " " + wzfRows.size());
                }
                showDindan(1);
            }
        });
    }

    private void zhifuDin(String num, String paymentType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderNum", num);
            jsonObject.put("paymentType", paymentType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/bus/pay", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final BaShiDdBean baShiDdBean = new Gson().fromJson(string, BaShiDdBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(baShiDdBean.getMsg());
                        getLieBiao();
                    }
                });
            }
        });
    }

    private void showDindan(int i) {
        if (i == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myAdapter = new MyAdapter(wzfRows, 1);
                    orderList.setAdapter(myAdapter);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myAdapter = new MyAdapter(yzfRows, 0);
                    orderList.setAdapter(myAdapter);
                }
            });
        }

    }

    private void initView() {
        orderBase = (ImageView) findViewById(R.id.order_base);
        orderList = (ListView) findViewById(R.id.order_list);
        tvWzfBtn = (TextView) findViewById(R.id.tv_wzf_btn);
        tvYzfBtn = (TextView) findViewById(R.id.tv_yzf_btn);
        orderBase.setOnClickListener(this);
        tvWzfBtn.setOnClickListener(this);
        tvYzfBtn.setOnClickListener(this);
        tvYzfBtn.setBackgroundColor(getColor(R.color.colorPrimary));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.order_base) {
            finish();
        } else if (id == R.id.tv_wzf_btn) {//                myAdapter.setData(wzfRows);
            showDindan(1);
            myAdapter.setState(1);
            myAdapter.notifyDataSetChanged();
            tvWzfBtn.setBackgroundColor(Color.parseColor("#DDDDDD"));
            tvYzfBtn.setBackgroundColor(getColor(R.color.colorPrimary));
        } else if (id == R.id.tv_yzf_btn) {
            showDindan(2);
//               myAdapter.setData(yzfRows);
            myAdapter.setState(0);
            myAdapter.notifyDataSetChanged();
            tvYzfBtn.setBackgroundColor(Color.parseColor("#DDDDDD"));
            tvWzfBtn.setBackgroundColor(getColor(R.color.colorPrimary));
        }
    }

    class MyAdapter extends BaseAdapter {
        List<BaShiDdBean.RowsDTO> data;
        private int state;

        public MyAdapter(List<BaShiDdBean.RowsDTO> data, int state) {
            this.data = data;
            this.state = state;
        }

        public void setData(List<BaShiDdBean.RowsDTO> data) {
            this.data = data;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_bs_dd, null);
            ViewHolder viewHolder = new ViewHolder(view);
            final BaShiDdBean.RowsDTO dataBean = data.get(i);
            viewHolder.tv_danhao.setText("订单" + dataBean.getOrderNum());
            viewHolder.tv_je.setText("￥" + dataBean.getPrice() + "");
            viewHolder.tv_sjmc.setText(dataBean.getPath());
            viewHolder.tv_qizhongdian.setText(dataBean.getStart()+"——"+dataBean.getEnd());
            viewHolder.tv_riqi.setText(dataBean.getCreateTime());
            if (state == 1) {
                viewHolder.tv_zhifu.setVisibility(View.VISIBLE);
                viewHolder.tv_zhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        zhifuDin(dataBean.getOrderNum(), "电子支付");
                    }
                });

            }
            return view;
        }

        class ViewHolder {
            public View rootView;
            public TextView tv_danhao;
            public TextView tv_je;
            public TextView tv_sjmc;
            public TextView tv_qizhongdian;
            public TextView tv_riqi;
            public TextView tv_zhifu;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_danhao = (TextView) rootView.findViewById(R.id.tv_danhao);
                this.tv_je = (TextView) rootView.findViewById(R.id.tv_je);
                this.tv_sjmc = (TextView) rootView.findViewById(R.id.tv_sjmc);
                this.tv_qizhongdian = (TextView) rootView.findViewById(R.id.tv_qizhongdian);
                this.tv_riqi = (TextView) rootView.findViewById(R.id.tv_riqi);
                this.tv_zhifu = (TextView) rootView.findViewById(R.id.tv_zhifu);
            }

        }
    }


}