package com.xsonline.smartlib.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.FeiYongBean;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PaySelectActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView gs;
    private TextView huhao;
    private TextView huming;
    private TextView shifu;
    private RadioButton weixin;
    private RadioButton zhifubao;
    private Button btnZhifu;
    FeiYongBean.RowsBean  feiyong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_select);
        initView();
        feiyong = new Gson().fromJson(getSP("feiyong"),FeiYongBean.RowsBean.class);
        gs.setText(feiyong.getChargeUnit());
        huhao.setText("123");
        huming.setText(feiyong.getOwnerName());
        shifu.setText(feiyong.getElectricityMoney());
        weixin.setChecked(true);
        if (weixin.isChecked()){

        }else if (zhifubao.isChecked()){

        }
        btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("balance",feiyong.getBalance());
                    jsonObject.put("userId",feiyong.getUserId());
                    jsonObject.put("id",feiyong.getDoorId());
                    if (feiyong.getChargeUnit().contains("电")){
                        jsonObject.put("classifyId","1");
                    }else {
                        jsonObject.put("classifyId","2");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getTools().sendPutRequestToken(jsonObject, "http://"+getServerIp()+"/userinfo/householder", getToken(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        FeiYongBean feiYongBean = new Gson().fromJson(json, FeiYongBean.class);
                        if (feiYongBean.getCode()==200){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("缴费成功");
                                }
                            });
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("缴费失败");
                                }
                            });
                        }
                    }
                });
            }
        });


    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        gs = (TextView) findViewById(R.id.gs);
        huhao = (TextView) findViewById(R.id.huhao);
        huming = (TextView) findViewById(R.id.huming);
        shifu = (TextView) findViewById(R.id.shifu);
        weixin = (RadioButton) findViewById(R.id.weixin);
        zhifubao = (RadioButton) findViewById(R.id.zhifubao);
        btnZhifu = (Button) findViewById(R.id.btn_zhifu);
    }
}