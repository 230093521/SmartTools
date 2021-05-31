package com.gzeic.smartcity01.x_jf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.JfDjBean;
import com.gzeic.smartcity01.bean.JfJFjlBean;
import com.gzeic.smartcity01.bean.JfJlBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfJlActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private RadioButton btnShuifei;
    private RadioButton btnDianfei;
    private RadioButton btnRanqifei;
    private RadioButton btnShoujifei;
    private ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_jl);
        initView();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnShoujifei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getList(1);
                }
            }
        });
        btnDianfei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getList(2);
                }
            }
        });
        btnShuifei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getList(3);
                }
            }
        });
        btnRanqifei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getList(4);
                }
            }
        });
    }

    private void getList(int type) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/recharge/record/list?categoryId=" + type + "&pageNum=1&pageSize=8", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final JfJFjlBean jfJlBean = new Gson().fromJson(string, JfJFjlBean.class);
                if (jfJlBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jfJlBean.getRows().size() == 0) {
                                showToast("未查询到缴费记录");
                                return;
                            }
                            final List<JfJFjlBean.RowsDTO> jfJlBeanRows = jfJlBean.getRows();
                            orderList.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView jine;
                                    public TextView dfh;
                                    public TextView jfsj;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.dfh = (TextView) rootView.findViewById(R.id.dfh);
                                        this.jfsj = (TextView) rootView.findViewById(R.id.jfsj);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return jfJlBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return jfJlBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(JfJlActivity.this).inflate(R.layout.item_jf_df_jl, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    JfJFjlBean.RowsDTO rowsDTO = jfJlBeanRows.get(position);
                                    viewHolder.jfsj.setText("缴费时间："+rowsDTO.getRechargeTime());
                                    viewHolder.jine.setText("缴费金额："+rowsDTO.getAmount()+"元");
                                    viewHolder.dfh.setText("缴费单号："+rowsDTO.getBillNo());

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
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        btnShuifei = (RadioButton) findViewById(R.id.btn_shuifei);
        btnDianfei = (RadioButton) findViewById(R.id.btn_dianfei);
        btnRanqifei = (RadioButton) findViewById(R.id.btn_ranqifei);
        btnShoujifei = (RadioButton) findViewById(R.id.btn_shoujifei);
        orderList = (ListView) findViewById(R.id.order_list);
    }
}