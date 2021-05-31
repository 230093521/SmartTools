package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
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
import com.gzeic.smartcity01.bean.WzcllbBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzWzcllbActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_wzcllb);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCllb();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCllb();
    }

    private void getCllb() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/traffic/car/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final WzcllbBean wzcllbBean = new Gson().fromJson(string, WzcllbBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final List<WzcllbBean.RowsDTO> rows = wzcllbBean.getRows();
                        if (rows.size() == 0) {
                            showToast("请先绑定车辆");
                            startActivity(new Intent(WzWzcllbActivity.this, WzWzbdActivity.class));
                            return;
                        }
                        list.setAdapter(new BaseAdapter() {
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
                                View view = LayoutInflater.from(WzWzcllbActivity.this).inflate(R.layout.item_wz_cl, null);
                                final WzcllbBean.RowsDTO rowsDTO = rows.get(position);
                                ViewHolder viewHolder = new ViewHolder(view);
                                viewHolder.chepai.setText(rowsDTO.getPlateNo());
                                viewHolder.wzzt.setText("无违章信息");
                                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        putSP("clxx",new Gson().toJson(rowsDTO));
                                        startActivity(new Intent(WzWzcllbActivity.this, WzWzclActivity.class));
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

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        list = (ListView) findViewById(R.id.list);
    }
}