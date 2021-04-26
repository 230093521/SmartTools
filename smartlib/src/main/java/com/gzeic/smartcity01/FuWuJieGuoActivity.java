package com.gzeic.smartcity01;

import android.content.Intent;
import android.graphics.Color;
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
import com.gzeic.smartcity01.ditie.DiTieActivity;
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
    AllServiceBean allServiceBean;
    List<AllServiceBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
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
        getTools().sendGetRequest("http://" + getServerIp() + "/service/service/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                allServiceBean = new Gson().fromJson(json, AllServiceBean.class);
                rows = allServiceBean.getRows();
                List<AllServiceBean.RowsBean> rows2 = new ArrayList<>();
                for (AllServiceBean.RowsBean rowsBean : rows) {
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
                                convertView = LayoutInflater.from(FuWuJieGuoActivity.this).inflate(R.layout.item_fuwu2, null);
                                final AllServiceBean.RowsBean rowsBean = rows.get(position);
                                ViewHolder viewHolder = new ViewHolder(convertView);
                                viewHolder.fw_text.setText(rowsBean.getServiceName());
                                Glide.with(FuWuJieGuoActivity.this).load("http://" + getServerIp() + rowsBean.getImgUrl()).into(viewHolder.fw_image);
                                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
//                    showToast(bean.getServiceName());
                                        //根据ID跳转对应服务
                                        switch (rowsBean.getId()) {
//                        case 7://生活缴费
//                            startActivity(new Intent(getContext(), LivePayActivity.class));
//                            break;
                                            case 2://城市地铁
                                                startActivity(new Intent(FuWuJieGuoActivity.this, DiTieActivity.class));
                                                break;
//                        case 5://门诊预约
//                            startActivity(new Intent(getContext(), MenZhengActivity.class));
//                            break;
//                        case 3://智慧巴士
//                            startActivity(new Intent(getContext(), BaShiActivity.class));
//                            break;
//                        case 17://停车场
//                            startActivity(new Intent(getContext(), TinCheChangActivity.class));
//                            break;
//                        case 9://违章查询
//                            startActivity(new Intent(getContext(), WeiZhangActivity.class));
//                            break;
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