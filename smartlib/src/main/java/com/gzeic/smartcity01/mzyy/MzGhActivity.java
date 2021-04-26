package com.gzeic.smartcity01.mzyy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.gzeic.smartcity01.bean.KeShiBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzGhActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView zhuanjia;
    private TextView putong;
    private ListView lists;
    List<KeShiBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_mz_gh);
        initView();
        getList(1);
    }

    public void getList(final int bianhao){
        getTools().sendGetRequestToken("http://"+getServerIp()+"/userinfo/types/list?pageNum=1&pageSize=10&did="+bianhao, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                KeShiBean keShiBean = new Gson().fromJson(json, KeShiBean.class);
                if (keShiBean.getCode()==200){
                    rows = keShiBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lists.setAdapter(new BaseAdapter() {
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
                                    view =  LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guahao,null);
                                    TextView textView = view.findViewById(R.id.keshi);

                                    textView.setText(rows.get(i).getCategoryName());
                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("leixin",String.valueOf(bianhao));
                                            putSP("keshi",new Gson().toJson(rows.get(i)));
                                            startActivity(new Intent(MzGhActivity.this, MzYydActivity.class));
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
        zhuanjia = (TextView) findViewById(R.id.zhuanjia);
        putong = (TextView) findViewById(R.id.putong);
        lists = (ListView) findViewById(R.id.lists);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        zhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList(1);
            }
        });
        putong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList(2);
            }
        });
    }
}