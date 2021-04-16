package com.xsonline.smartlib.activity;

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
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.BaShiDdBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OrderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView orderBase;
    private ListView orderList;
    private TextView tvWzfBtn;
    private TextView tvYzfBtn;
    private MyAdapter myAdapter;
    List<BaShiDdBean.RowsBean> rows;
    List<BaShiDdBean.RowsBean> yzfRows;
    List<BaShiDdBean.RowsBean> wzfRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_order);
        initView();

        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/busOrders/list?pageNum=1&pageSize=10", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                BaShiDdBean baShiDdBean = new Gson().fromJson(json, BaShiDdBean.class);
                if (baShiDdBean.getCode()==200){
                    rows = baShiDdBean.getRows();
                    yzfRows = new ArrayList<>();
                    wzfRows = new ArrayList<>();
                    for (BaShiDdBean.RowsBean dataBean : rows) {
                        if (dataBean.getStatus() == 1) {
                            yzfRows.add(dataBean);
                        } else {
                            wzfRows.add(dataBean);
                        }
                    }
                    Log.e(TAG, "onResponse: "+yzfRows.size()+" "+wzfRows.size() );
                }
                showDindan(1);
            }
        });
    }

    private void showDindan(int i) {
        if (i==1){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myAdapter = new MyAdapter(wzfRows,1);
                    orderList.setAdapter(myAdapter);
                }
            });
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myAdapter = new MyAdapter(yzfRows,0);
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
        tvYzfBtn.setBackgroundColor(Color.parseColor("#03A9F4"));
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
            tvYzfBtn.setBackgroundColor(Color.parseColor("#03A9F4"));
        } else if (id == R.id.tv_yzf_btn) {
            showDindan(2);
//                myAdapter.setData(yzfRows);
            myAdapter.setState(0);
            myAdapter.notifyDataSetChanged();
            tvYzfBtn.setBackgroundColor(Color.parseColor("#DDDDDD"));
            tvWzfBtn.setBackgroundColor(Color.parseColor("#03A9F4"));
        }
    }

    class MyAdapter extends BaseAdapter {
        List<BaShiDdBean.RowsBean> data;
        private int state;

        public MyAdapter(List<BaShiDdBean.RowsBean> data, int state) {
            this.data = data;
            this.state = state;
        }

        public void setData(List<BaShiDdBean.RowsBean> data) {
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
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_order, null);
            ViewHolder viewHolder = new ViewHolder(view);
            final BaShiDdBean.RowsBean dataBean = data.get(i);
            viewHolder.tv_danhao.setText("订单" + dataBean.getOrderNum());
            viewHolder.tv_je.setText("￥" + dataBean.getPrice() + "");
            viewHolder.tv_cfz.setText(dataBean.getStart());
            viewHolder.tv_mdz.setText(dataBean.getEnd());
            viewHolder.tv_xlm.setText(dataBean.getPath());
            viewHolder.tv_username.setText(dataBean.getUserName());
            viewHolder.tv_phone.setText(dataBean.getUserTel());
            if (state == 1) {
                viewHolder.tv_zhifu.setVisibility(View.VISIBLE);
                viewHolder.tv_zhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("支付成功");
                    }
                });
            }
            return view;
        }

        class ViewHolder {
            public View rootView;
            public TextView tv_danhao;
            public TextView tv_je;
            public TextView tv_cfz;
            public TextView tv_mdz;
            public TextView tv_xlm;
            public TextView tv_username;
            public TextView tv_phone;
            public TextView tv_zhifu;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_danhao = (TextView) rootView.findViewById(R.id.tv_danhao);
                this.tv_je = (TextView) rootView.findViewById(R.id.tv_je);
                this.tv_cfz = (TextView) rootView.findViewById(R.id.tv_cfz);
                this.tv_mdz = (TextView) rootView.findViewById(R.id.tv_mdz);
                this.tv_xlm = (TextView) rootView.findViewById(R.id.tv_xlm);
                this.tv_username = (TextView) rootView.findViewById(R.id.tv_username);
                this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
                this.tv_zhifu = (TextView) rootView.findViewById(R.id.tv_zhifu);
            }

        }
    }


}