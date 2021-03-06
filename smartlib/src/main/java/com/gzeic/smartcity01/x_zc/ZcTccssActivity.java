package com.gzeic.smartcity01.x_zc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.ZcTccBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcTccssActivity extends BaseActivity {

    private ImageView metroBase;
    private ListViewScrollView listTinche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_tccss);
        initView();
        String tingchechang = getSP("tingchechang");
        getTcc(tingchechang);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getTcc(String name) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/lot/list?parkName=" + name, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZcTccBean zcTccBean = new Gson().fromJson(string, ZcTccBean.class);
                if (zcTccBean.getCode() == 200) {
                    final List<ZcTccBean.RowsDTO> zcTccBeanRows = zcTccBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listTinche.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView tinchechang;
                                    public TextView gongli;
                                    public TextView kongwei;
                                    public TextView weizhi;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tinchechang = (TextView) rootView.findViewById(R.id.yonghuming);
                                        this.gongli = (TextView) rootView.findViewById(R.id.gongli);
                                        this.kongwei = (TextView) rootView.findViewById(R.id.kongwei);
                                        this.weizhi = (TextView) rootView.findViewById(R.id.weizhi);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return zcTccBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return zcTccBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(ZcTccssActivity.this).inflate(R.layout.item_tc_tcc, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    final ZcTccBean.RowsDTO rowsDTO = zcTccBeanRows.get(position);
                                    viewHolder.tinchechang.setText(rowsDTO.getParkName());
                                    viewHolder.gongli.setText(rowsDTO.getDistance()+"??????");
                                    viewHolder.kongwei.setText(rowsDTO.getVacancy()+"?????????|??????"+rowsDTO.getRates()+"???/??????");
                                    viewHolder.weizhi.setText(rowsDTO.getAddress());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            putSP("tingchebean",new Gson().toJson(rowsDTO));
                                            startActivity(new Intent(ZcTccssActivity.this,ZcZcwxqActivity.class));
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
        listTinche = (ListViewScrollView) findViewById(R.id.list_tinche);
    }
}