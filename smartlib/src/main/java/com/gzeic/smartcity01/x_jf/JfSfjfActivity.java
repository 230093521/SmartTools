package com.gzeic.smartcity01.x_jf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.JfSfzdBean;
import com.gzeic.smartcity01.bean.JfZhBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfSfjfActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private TextView lishi;
    private TextView jfdq;
    private TextView sfh;
    private TextView qfje;
    private Button btnZhifu;
    String shuifeihao;
    String jiaofeidanhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_sfjf);
        initView();
        String chengqu = getSP("chengqu");
        shuifeihao = getSP("shuifeihao");
        jfdq.setText("交费地区：" + chengqu);
        sfh.setText("水费号：" + shuifeihao);
        getQfje();
        btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qfje.getText().toString().equals("未查询到欠费信息")) {
                    showToast("未查询到欠费信息");
                    return;
                }else {
                    faqizhifu();
                }

            }
        });
        lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JfSfjfActivity.this,JfSfjflsActivity.class));
            }
        });
    }

    private void faqizhifu() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("billNo",jiaofeidanhao);
            jsonObject.put("paymentNo",shuifeihao);
            jsonObject.put("paymentType","电子支付");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/api/living/recharge", getToken(), new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String string = response.body().string();
            final JfZhBean jfZhBean = new Gson().fromJson(string, JfZhBean.class);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(jfZhBean.getMsg());
                }
            });
        }
    });
    }

    private void getQfje() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/bill?paymentNo="+shuifeihao+"&categoryId=2", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final JfSfzdBean jfSfzdBean = new Gson().fromJson(string, JfSfzdBean.class);
                if (jfSfzdBean.getCode()==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JfSfzdBean.DataDTO jfSfzdBeanData = jfSfzdBean.getData();
                            if (jfSfzdBeanData==null){
                                qfje.setText("未查询到欠费信息");
                            }else {
                                jiaofeidanhao = jfSfzdBeanData.getBillNo();
                                qfje.setText("欠费金额："+jfSfzdBeanData.getAmount()+"元");
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        lishi = (TextView) findViewById(R.id.lishi);
        jfdq = (TextView) findViewById(R.id.jfdq);
        sfh = (TextView) findViewById(R.id.sfh);
        qfje = (TextView) findViewById(R.id.qfje);
        btnZhifu = (Button) findViewById(R.id.btn_zhifu);
    }
}