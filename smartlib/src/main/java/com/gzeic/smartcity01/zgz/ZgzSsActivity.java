package com.gzeic.smartcity01.zgz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZwSsBean;
import com.gzeic.smartcity01.bean.ZwrmBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZgzSsActivity extends BaseActivity {

    private ImageView orderBase;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zgz_ss);
        initView();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String zgzss = getSP("zgzss");
        getTools().sendGetRequest("http://" + getServerIp() + " /prod-api/api/job/post/list?name=" + zgzss, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwrmBean zwSsBean = new Gson().fromJson(string, ZwrmBean.class);
                if (zwSsBean.getCode() == 200) {
                    final List<ZwrmBean.RowsDTO> zwSsBeanRows = zwSsBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView zwmc;
                                    public TextView zhizhe;
                                    public TextView didian;
                                    public TextView daiyu;
                                    public LinearLayout news_ll;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.zwmc = (TextView) rootView.findViewById(R.id.zwmc);
                                        this.zhizhe = (TextView) rootView.findViewById(R.id.zhizhe);
                                        this.didian = (TextView) rootView.findViewById(R.id.didian);
                                        this.daiyu = (TextView) rootView.findViewById(R.id.daiyu);
                                        this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                                    }
                                }

                                @Override
                                public int getCount() {
                                    return zwSsBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return zwSsBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(ZgzSsActivity.this).inflate(R.layout.item_zgz_gw, null);
                                    final ZwrmBean.RowsDTO rowsDTO = zwSsBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.daiyu.setText(rowsDTO.getSalary());
                                    viewHolder.didian.setText(rowsDTO.getAddress());
                                    viewHolder.zhizhe.setText(rowsDTO.getObligation());
                                    viewHolder.zwmc.setText(rowsDTO.getName());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            putSP("zwdata",new Gson().toJson(rowsDTO));
                                            startActivity(new Intent(ZgzSsActivity.this,ZgzXqActivity.class));
                                        }
                                    });
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        orderBase = (ImageView) findViewById(R.id.order_base);
        listview = (ListView) findViewById(R.id.listview);
    }
}