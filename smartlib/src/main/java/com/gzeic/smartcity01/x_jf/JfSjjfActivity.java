package com.gzeic.smartcity01.x_jf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.JfSjczBean;
import com.gzeic.smartcity01.bean.JfSjjlBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfSjjfActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private TextView changyong;
    private TextView shoujihao;
    private RadioButton jine1;
    private RadioButton jine2;
    private RadioButton jine3;
    private Button btnZhifu;
    String shoujihaos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_sjjf);
        initView();
        shoujihaos = getSP("shoujihao");
        String yunyingshangs = getSP("yunyingshang");
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("设置成功");
            }
        });
        shoujihao.setText("手机号码："+shoujihaos);
        btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiaofei();
            }
        });
    }

    private void jiaofei() {
        int jine = 0;
        if (jine1.isChecked()){
            jine = 50;
        }else if (jine2.isChecked()){
            jine = 100;
        }else {
            jine = 200;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("paymentType","电子支付");
            jsonObject.put("phonenumber",shoujihaos);
            jsonObject.put("rechargeAmount",jine);
            jsonObject.put("ruleId",1);
            jsonObject.put("type","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/api/living/phone/recharge", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final JfSjczBean jfSjczBean = new Gson().fromJson(string, JfSjczBean.class);
               if (jfSjczBean.getCode()==200){

               }
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       showToast(jfSjczBean.getMsg());
                   }
               });
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        changyong = (TextView) findViewById(R.id.changyong);
        shoujihao = (TextView) findViewById(R.id.shoujihao);
        jine1 = (RadioButton) findViewById(R.id.jine1);
        jine2 = (RadioButton) findViewById(R.id.jine2);
        jine3 = (RadioButton) findViewById(R.id.jine3);
        btnZhifu = (Button) findViewById(R.id.btn_zhifu);
    }
}