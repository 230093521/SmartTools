package com.gzeic.smartcity01.ditie;

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
import com.gzeic.smartcity01.bean.DiTieBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiTieActivity extends BaseActivity implements View.OnClickListener {

    private ImageView metroBase;
    private ListView listDitie;
    private List<DiTieBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_di_tie);
        initView();

        getTools().sendGetRequest("http://"+getServerIp()+"/metro/list?pageNum=1&pageSize=10&currentName=建国门", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("加载数据失败1");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                DiTieBean diTieBean = new Gson().fromJson(json, DiTieBean.class);
                if (diTieBean.getCode() == 200) {
                    rows = diTieBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listDitie.setAdapter(new MyAdapter());
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("加载数据失败2");
                        }
                    });
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {
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
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_ditie, null);
            ViewHolder viewHolder = new ViewHolder(view);
            final DiTieBean.RowsBean rowsBean = rows.get(i);
            viewHolder.ditie_name.setText(rowsBean.getLineName());
            viewHolder.ditie_next.setText("下一站："+rowsBean.getLastName());
            viewHolder.ditie_time.setText(rowsBean.getReachTime()+"分钟后");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    putSP("ditie", json);
                    startActivity(new Intent(DiTieActivity.this,DiTieXQActivity.class));
                }
            });
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ditie_name;
            public TextView ditie_time;
            public TextView ditie_next;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ditie_name = (TextView) rootView.findViewById(R.id.ditie_name);
                this.ditie_time = (TextView) rootView.findViewById(R.id.ditie_time);
                this.ditie_next = (TextView) rootView.findViewById(R.id.ditie_next);
            }

        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listDitie = (ListView) findViewById(R.id.list_ditie);
        metroBase.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.metro_base) {
            finish();
        }
    }
}