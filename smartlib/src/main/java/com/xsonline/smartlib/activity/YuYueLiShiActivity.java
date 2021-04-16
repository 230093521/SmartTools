package com.xsonline.smartlib.activity;

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
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.YuYueBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YuYueLiShiActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView list;
    List<YuYueBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yu_yue_li_shi);
        initView();
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/order/list?pageNum=1&pageSize=10&userId=1", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                YuYueBean yuYueBean = new Gson().fromJson(json, YuYueBean.class);
                if (yuYueBean.getCode() == 200) {
                    rows = yuYueBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView danhao;
                                    public TextView jine;
                                    public TextView name;
                                    public TextView keshilei;
                                    public TextView menzhenglei;
                                    public TextView shijian;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.danhao = (TextView) rootView.findViewById(R.id.danhao);
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.name = (TextView) rootView.findViewById(R.id.name);
                                        this.keshilei = (TextView) rootView.findViewById(R.id.keshilei);
                                        this.menzhenglei = (TextView) rootView.findViewById(R.id.menzhenglei);
                                        this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                                    }

                                }

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
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_lishi, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    YuYueBean.RowsBean rowsBean = rows.get(i);
                                    viewHolder.name.setText(rowsBean.getPatientName());
                                    viewHolder.danhao.setText(rowsBean.getOrderNo());
                                    viewHolder.jine.setText(rowsBean.getMoeny());
                                    viewHolder.keshilei.setText(rowsBean.getCategoryName());
                                    if (rowsBean.getTypesId().equals("1")){
                                        viewHolder.menzhenglei.setText("专家号");
                                    }else {
                                        viewHolder.menzhenglei.setText("普通号");
                                    }
                                    viewHolder.shijian.setText(rowsBean.getStartime());
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
        list = (ListView) findViewById(R.id.list);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}