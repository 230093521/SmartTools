package com.gzeic.smartcity01.qianbao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.UsersBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoQianbaoActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private TextView shouzhiminxi;
    private TextView yonghuming;
    private TextView yueshu;
    private RadioButton jine1;
    private RadioButton jine2;
    private RadioButton jine3;
    private RadioButton jine4;
    private RadioButton jine5;
    private RadioButton weixin;
    private RadioButton zhifubao;
    private Button btnZhifu;
    UsersBean.UserDTO userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_qianbao);
        initView();
        userdata = getUserdata();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yonghuming.setText(userdata.getNickName());
        yueshu.setText(userdata.getBalance() + "元");
        btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jine = 0;
                String zflx = null;
                if (jine1.isChecked()) {
                    jine = 50;
                }else
                if (jine2.isChecked()) {
                    jine = 100;
                }else
                if (jine3.isChecked()) {
                    jine = 200;
                }else
                if (jine4.isChecked()) {
                    jine = 500;
                }else
                if (jine5.isChecked()) {
                    jine = 1000;
                }
                if (weixin.isChecked()) {
                    zflx = "微信";
                }else
                if (zhifubao.isChecked()) {
                    zflx = "支付宝";
                }
                sendChongzhi(jine,zflx);
            }
        });
        shouzhiminxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoQianbaoActivity.this,WoQianbaomxActivity.class));
            }
        });
    }

    private void sendChongzhi(final int jine, String type) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("money", jine);
            jsonObject.put("payType", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/park/recharge/pay", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: "+string);
                final UsersBean usersBean = new Gson().fromJson(string, UsersBean.class);
                if (usersBean.getCode()==200){
                    userdata.setBalance(userdata.getBalance()+jine);
                    putSP("userdata",new Gson().toJson(userdata));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            yueshu.setText(userdata.getBalance()+jine+"元");
                            showToast(usersBean.getMsg()
                            );
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("服务器错误："+usersBean.getMsg()
                            );
                        }
                    });
                }

            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        shouzhiminxi = (TextView) findViewById(R.id.shouzhiminxi);
        yonghuming = (TextView) findViewById(R.id.yonghuming);
        yueshu = (TextView) findViewById(R.id.yueshu);
        jine1 = (RadioButton) findViewById(R.id.jine1);
        jine2 = (RadioButton) findViewById(R.id.jine2);
        jine3 = (RadioButton) findViewById(R.id.jine3);
        jine4 = (RadioButton) findViewById(R.id.jine4);
        jine5 = (RadioButton) findViewById(R.id.jine5);
        weixin = (RadioButton) findViewById(R.id.weixin);
        zhifubao = (RadioButton) findViewById(R.id.zhifubao);
        btnZhifu = (Button) findViewById(R.id.btn_zhifu);
    }
}