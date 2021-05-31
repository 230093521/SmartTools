package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzclxqlbBean;
import com.gzeic.smartcity01.bean.WzjszxxBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzWzCxActivity extends BaseActivity {
    private TextView sfzh;
    private TextView jf;
    private ImageView metro_base;
    private ListView list;
    private BaseAdapter baseAdapter;
    private String http = "http://";
    private List<WzclxqlbBean.RowsBean> rowsxs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_cx);
        sfzh = findViewById(R.id.sfzh);
        jf = findViewById(R.id.jf);
        metro_base = findViewById(R.id.metro_base);
        list = findViewById(R.id.list);
        rowsxs = new ArrayList<>();
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return rowsxs.size();
            }

            @Override
            public Object getItem(int position) {
                return rowsxs.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_cx, parent, false);
                TextView lzlx = inflate.findViewById(R.id.lzlx);
                TextView fsrq = inflate.findViewById(R.id.fsrq);
                TextView fsdd = inflate.findViewById(R.id.fsdd);
                TextView cfdw = inflate.findViewById(R.id.cfdw);
                final Button bxsq = inflate.findViewById(R.id.bxsq);
                lzlx.setText(rowsxs.get(position).getTrafficOffence());
                fsrq.setText(rowsxs.get(position).getBadTime());
                fsdd.setText(rowsxs.get(position).getIllegalSites());
                cfdw.setText(rowsxs.get(position).getPerformOffice());
                bxsq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WzWzCxActivity.this, WzWzCxsqActivity.class);
                        intent.putExtra("id",rowsxs.get(position).getId());
                        startActivity(intent);
                    }
                });
                return inflate;
            }
        };

        list.setAdapter(baseAdapter);
        metro_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qq();
    }
    private void qq() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/license/user", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Gson gson= new Gson();
                WzjszxxBean jszxx = gson.fromJson(string, WzjszxxBean.class);
                final List<WzjszxxBean.DataBean> rows = jszxx.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size()>0){
                            jf.setText(rows.get(0).getScore()+"");
                            sfzh.setText(rows.get(0).getIdCard()+"");
                            hqwccl();
                        }else{
                            startActivity(new Intent(WzWzCxActivity.this, WzJszbdActivity.class));
                        }
                    }
                });
            }
        });
    }

    private void hqwccl() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/list?disposeState=未处理", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                WzclxqlbBean wzcllb = gson.fromJson(string, WzclxqlbBean.class);
                final List<WzclxqlbBean.RowsBean> rows = wzcllb.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size()>0){
                            xs(rows);
                        }else{
                            Toast.makeText(WzWzCxActivity.this, "暂无未处理信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void xs(List<WzclxqlbBean.RowsBean> rows) {
        this.rowsxs = rows;
        baseAdapter.notifyDataSetChanged();
    }

    boolean dyc = true;
    @Override
    protected void onResume() {
        super.onResume();
        if (dyc){
            dyc = false;
        }else{
            qq();
        }
    }
}