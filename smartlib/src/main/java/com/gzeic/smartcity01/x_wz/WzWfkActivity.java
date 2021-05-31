package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzcllbBean;
import com.gzeic.smartcity01.bean.WzjszxxBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzWfkActivity extends BaseActivity {
    private String http = "http://";
    private ImageView metro_base;
    private TextView jszh;
    private TextView jszyxq;
    private TextView sfzh;
    private LinearLayout wdxx;
    private Button btn_sousuo;
    private EditText home_edit_search;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_wzfk);
        jszh = findViewById(R.id.jszh);
        jszyxq = findViewById(R.id.jszyxq);
        metro_base = findViewById(R.id.metro_base);
        sfzh = findViewById(R.id.sfzh);
        wdxx = findViewById(R.id.wdxx);
        btn_sousuo = findViewById(R.id.btn_sousuo);
        home_edit_search = findViewById(R.id.home_edit_search);
        metro_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wdxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WzWfkActivity.this, WzJdslbActivity.class));
            }
        });
        btn_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (home_edit_search.getText().toString() != null) {
                    putSP("cjsh",home_edit_search.getText().toString());
                    Intent intent = new Intent(WzWfkActivity.this, WzJdsxqActivity.class);
                    startActivity(intent);
                }
            }
        });
        hq();
        initView();
    }

    private void hq() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/license/user", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Gson gson = new Gson();
                WzjszxxBean jszxx = gson.fromJson(string, WzjszxxBean.class);
                final List<WzjszxxBean.DataBean> rows = jszxx.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size() > 0) {
                            jszh.setText(rows.get(0).getLicenseNo());
                            if (rows.get(0).getVerifyDate() != null) {
                                jszyxq.setText(rows.get(0).getVerifyDate());
                            } else {
                                jszyxq.setText("2027-11-20");
                            }
                            sfzh.setText(rows.get(0).getIdCard());
                            hqwccl();
                        } else {
                            startActivity(new Intent(WzWfkActivity.this, WzJszbdActivity.class));
                        }
                    }
                });
            }
        });
    }

    private void hqwccl() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/car/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                WzcllbBean clxxlb = gson.fromJson(string, WzcllbBean.class);
                final List<WzcllbBean.RowsDTO> rows = clxxlb.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size() == 0) {
                            showToast("未绑定车辆");
                            return;
                        }
                        listview.setAdapter(new BaseAdapter() {
                            class ViewHolder {
                                public View rootView;
                                public TextView chepai;
                                public TextView wzzt;

                                public ViewHolder(View rootView) {
                                    this.rootView = rootView;
                                    this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                                    this.wzzt = (TextView) rootView.findViewById(R.id.wzzt);
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
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View view = LayoutInflater.from(WzWfkActivity.this).inflate(R.layout.item_wz_cl, null);
                                final WzcllbBean.RowsDTO rowsDTO = rows.get(position);
                                ViewHolder viewHolder = new ViewHolder(view);
                                viewHolder.chepai.setText(rowsDTO.getPlateNo());
                                viewHolder.wzzt.setText("无违章信息");
                                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        putSP("cjsh","2021042110040387379");
                                        Intent intent = new Intent(WzWfkActivity.this, WzJdsxqActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                return view;
                            }
                        });
                    }
                });
            }
        });
    }

    boolean dyc = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (dyc) {
            dyc = false;
        } else {
            hq();
        }
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
    }
}