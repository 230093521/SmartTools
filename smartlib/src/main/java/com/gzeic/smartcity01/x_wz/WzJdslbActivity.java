package com.gzeic.smartcity01.x_wz;

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
import com.gzeic.smartcity01.bean.WzjdslbBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzJdslbActivity extends BaseActivity {
    private String http = "http://";
    private ImageView metroBase;
    private TextView homeTitle;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_jdslb);
        initView();
    }
    private void initView() {
        metroBase = findViewById(R.id.metro_base);
        homeTitle = findViewById(R.id.home_title);
        list = findViewById(R.id.list);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qq();
    }

    private void qq() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/notice/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                WzjdslbBean cjslb = gson.fromJson(string, WzjdslbBean.class);
                final List<WzjdslbBean.RowsBean> rows = cjslb.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size()==0){
                            showToast("未获取到处罚决定书");
                            return;
                        }
                        list.setAdapter(new BaseAdapter() {
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
                                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_cjs, parent, false);
                                TextView cfdw;
                                TextView bh;
                                TextView cfr;
                                TextView dabh;
                                TextView jszh;
                                TextView zjcx;
                                TextView lxfs;
                                TextView cph;
                                TextView cllx;
                                TextView fzjg;
                                TextView wzrq;
                                TextView wzxw;
                                TextView wzdd;
                                TextView fkje;
                                TextView jf;
                                TextView cfjg;
                                TextView cfsj;
                                cfdw = inflate.findViewById(R.id.cfdw);
                                bh = inflate.findViewById(R.id.bh);
                                cfr = inflate.findViewById(R.id.cfr);
                                dabh = inflate.findViewById(R.id.dabh);
                                jszh = inflate.findViewById(R.id.jszh);
                                zjcx = inflate.findViewById(R.id.zjcx);
                                lxfs = inflate.findViewById(R.id.lxfs);
                                cph = inflate.findViewById(R.id.cph);
                                cllx = inflate.findViewById(R.id.cllx);
                                fzjg = inflate.findViewById(R.id.fzjg);
                                wzrq = inflate.findViewById(R.id.wzrq);
                                wzxw = inflate.findViewById(R.id.wzxw);
                                wzdd = inflate.findViewById(R.id.wzdd);
                                fkje = inflate.findViewById(R.id.fkje);
                                jf = inflate.findViewById(R.id.jf);
                                cfjg = inflate.findViewById(R.id.cfjg);
                                cfsj = inflate.findViewById(R.id.cfsj);
                                cfdw.setText(rows.get(position).getPerformOffice());
                                bh.setText(rows.get(position).getId()+"");
                                cfr.setText(rows.get(position).getPerformOffice());
                                dabh.setText(rows.get(position).getFileNo()+"");
                                jszh.setText(rows.get(position).getLicenseNo()+"");
                                zjcx.setText(rows.get(position).getLicenseLevel()+"");
                                lxfs.setText(rows.get(position).getContact()+"");
                                cph.setText(rows.get(position).getPlateNo()+"");
                                cllx.setText(rows.get(position).getCarType()+"");
                                fzjg.setText(rows.get(position).getAuditOffice()+"");
                                wzrq.setText(rows.get(position).getIllegalDate()+"");
                                wzxw.setText(rows.get(position).getIllegalEven()+"");
                                wzdd.setText(rows.get(position).getIllegalAddress()+"");
                                fkje.setText(rows.get(position).getMoney()+"");
                                jf.setText(rows.get(position).getDeductMarks()+"");
                                cfjg.setText(rows.get(position).getPerformOffice()+"");
                                cfsj.setText(rows.get(position).getPerformDate()+"");
                                return inflate;
                            }
                        });
                    }
                });
            }
        });
    }
}