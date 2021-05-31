package com.gzeic.smartcity01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.AllServiceBean;
import com.gzeic.smartcity01.bean.FwBean;
import com.gzeic.smartcity01.ditie.DiTieActivity;
import com.gzeic.smartcity01.mzyy.MzActivity;
import com.gzeic.smartcity01.shjf.ShActivity;
import com.gzeic.smartcity01.wzcx.WzActivity;
import com.gzeic.smartcity01.x_csdt.CsdtSyActivity;
import com.gzeic.smartcity01.x_hdgl.HdSyActivity;
import com.gzeic.smartcity01.x_jf.JfSyActivity;
import com.gzeic.smartcity01.x_wz.WzSyActivity;
import com.gzeic.smartcity01.x_yy.YySyActivity;
import com.gzeic.smartcity01.x_zc.ZcSyActivity;
import com.gzeic.smartcity01.zfz.ZfzActivity;
import com.gzeic.smartcity01.zgz.ZgzActivity;
import com.gzeic.smartcity01.zhbs.BaShiActivity;
import com.xsonline.smartlib.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FuWuJieGuoActivity extends BaseActivity {

    private ImageView ivBase;
    private GridView gridview;
    FwBean allServiceBean;
    List<FwBean.RowsDTO> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_fwjg);
        initView();
        String fwjg = getSP("fwjg");
        getAllService(fwjg);
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getAllService(final String fwjg) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/service/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                allServiceBean = new Gson().fromJson(json, FwBean.class);
                rows = allServiceBean.getRows();
                List<FwBean.RowsDTO> rows2 = new ArrayList<>();
                for (FwBean.RowsDTO rowsBean : rows) {
                    if (rowsBean.getServiceName().contains(fwjg)) {
                        rows2.add(rowsBean);
                    }
                }
                rows = rows2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gridview.setAdapter(new BaseAdapter() {
                            class ViewHolder {
                                public View rootView;
                                public ImageView fw_image;
                                public TextView fw_text;

                                public ViewHolder(View rootView) {
                                    this.rootView = rootView;
                                    this.fw_image = (ImageView) rootView.findViewById(R.id.fw_image);
                                    this.fw_text = (TextView) rootView.findViewById(R.id.fw_text);
                                }

                            }

                            @Override
                            public int getCount() {
                                return rows.size();
                            }

                            @Override
                            public Object getItem(int position) {
                                return rows.get(position);
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(final int position, View convertView, ViewGroup parent) {
                                convertView = LayoutInflater.from(FuWuJieGuoActivity.this).inflate(R.layout.item_fw_x, null);
                                final FwBean.RowsDTO rowsBean = rows.get(position);
                                ViewHolder viewHolder = new ViewHolder(convertView);
                                viewHolder.fw_text.setText(rowsBean.getServiceName());
                                Glide.with(FuWuJieGuoActivity.this).load("http://" + getServerIp() + rowsBean.getImgUrl()).into(viewHolder.fw_image);
                                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
//                    showToast(bean.getServiceName());
                                        //根据ID跳转对应服务
                                        switch (rowsBean.getId()) {
                                            case 17://停哪儿
                                                startActivity(new Intent(FuWuJieGuoActivity.this, ZcSyActivity.class));
                                                break;
                                            case 2://城市地铁
                                                startActivity(new Intent(FuWuJieGuoActivity.this, CsdtSyActivity.class));
                                                break;
                                            case 3://智慧巴士
                                                startActivity(new Intent(FuWuJieGuoActivity.this, BaShiActivity.class));
                                                break;
                                            case 5://门诊预约
                                                startActivity(new Intent(FuWuJieGuoActivity.this, MzActivity.class));
                                                break;
                                            case 9://智慧交管
                                                startActivity(new Intent(FuWuJieGuoActivity.this, WzSyActivity.class));
                                                break;
                                            case 7://生活缴费
                                                startActivity(new Intent(FuWuJieGuoActivity.this, JfSyActivity.class));
                                                break;
                                            case 19://外卖订餐
                                                startActivity(new Intent(FuWuJieGuoActivity.this, JfSyActivity.class));
                                                break;
                                            case 20://找房子
                                                startActivity(new Intent(FuWuJieGuoActivity.this, ZfzActivity.class));
                                                break;
                                            case 18://看电影
                                                startActivity(new Intent(FuWuJieGuoActivity.this, YySyActivity.class));
                                                break;
                                            case 21://找工作
                                                startActivity(new Intent(FuWuJieGuoActivity.this, ZgzActivity.class));
                                                break;
                                            case 22://活动管理
                                                startActivity(new Intent(FuWuJieGuoActivity.this, HdSyActivity.class));
                                                break;
                                            default:
                                                break;
                                        }
                                        Log.i(TAG, rows.get(position).toString());
                                    }
                                });
                                return convertView;
                            }
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        gridview = (GridView) findViewById(R.id.gridview);
    }


}