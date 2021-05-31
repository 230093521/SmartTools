package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzjdsxqBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzJdsxqActivity extends BaseActivity {
    private String http = "http://";
    private ImageView metroBase;
    private TextView cfdw;
    private TextView bh;
    private TextView cfr;
    private TextView dabh;
    private TextView jszh;
    private TextView zjcx;
    private TextView lxfs;
    private TextView cph;
    private TextView cllx;
    private TextView fzjg;
    private TextView wzrq;
    private TextView wzxw;
    private TextView wzdd;
    private TextView fkje;
    private TextView jf;
    private TextView cfjg;
    private TextView cfsj;
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_jdsxq);
        initView();
        String cjsh = getSP("cjsh");
        qqsju(cjsh);
    }

    private void qqsju(String id) {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/notice/" + id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final WzjdsxqBean cjsxx = gson.fromJson(string, WzjdsxqBean.class);
                final WzjdsxqBean.DataBean data = cjsxx.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            card.setVisibility(View.VISIBLE);
                            cfdw.setText(data.getPerformOffice());
                            bh.setText(data.getId() + "");
                            cfr.setText(data.getPerformOffice());
                            dabh.setText(data.getFileNo() + "");
                            jszh.setText(data.getLicenseNo() + "");
                            zjcx.setText(data.getLicenseLevel() + "");
                            lxfs.setText(data.getContact() + "");
                            cph.setText(data.getPlateNo() + "");
                            cllx.setText(data.getCarType() + "");
                            fzjg.setText(data.getAuditOffice() + "");
                            wzrq.setText(data.getIllegalDate() + "");
                            wzxw.setText(data.getIllegalEven() + "");
                            wzdd.setText(data.getIllegalAddress() + "");
                            fkje.setText(data.getMoney() + "");
                            jf.setText(data.getDeductMarks() + "");
                            cfjg.setText(data.getPerformOffice() + "");
                            cfsj.setText(data.getPerformDate() + "");
                        } else {
                            showToast("未查询到处罚决定书");
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        metroBase = findViewById(R.id.metro_base);
        cfdw = findViewById(R.id.cfdw);
        bh = findViewById(R.id.bh);
        cfr = findViewById(R.id.cfr);
        dabh = findViewById(R.id.dabh);
        jszh = findViewById(R.id.jszh);
        zjcx = findViewById(R.id.zjcx);
        lxfs = findViewById(R.id.lxfs);
        cph = findViewById(R.id.cph);
        cllx = findViewById(R.id.cllx);
        fzjg = findViewById(R.id.fzjg);
        wzrq = findViewById(R.id.wzrq);
        wzxw = findViewById(R.id.wzxw);
        wzdd = findViewById(R.id.wzdd);
        fkje = findViewById(R.id.fkje);
        jf = findViewById(R.id.jf);
        cfjg = findViewById(R.id.cfjg);
        cfsj = findViewById(R.id.cfsj);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        card = (CardView) findViewById(R.id.card);
    }
}