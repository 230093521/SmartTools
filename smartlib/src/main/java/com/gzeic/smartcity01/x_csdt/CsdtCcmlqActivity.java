package com.gzeic.smartcity01.x_csdt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTccmBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtCcmlqActivity extends BaseActivity {
    DTccmBean.DataDTO dataDTO;
    private RelativeLayout homeToprl;
    private ImageView infoBase;
    private TextView homeTitle;
    private EditText etName;
    private EditText etZjh;
    private EditText etPhone;
    private Button tvSetSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_ccmlq);
        initView();
        String ccmxx = getSP("ccmxx");
        dataDTO = new Gson().fromJson(ccmxx, DTccmBean.DataDTO.class);
        infoBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            etName.setText(dataDTO.getUserName());
            etPhone.setText(dataDTO.getPhonenumber());
            etZjh.setText(dataDTO.getIdCard());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvSetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getCck();
            }
        });
    }

    private void getCck() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idCard",etZjh.getText().toString());
            jsonObject.put("phonenumber",etPhone.getText().toString());
            jsonObject.put("userName",etName.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/metro/card", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTccmBean dTccmBean = new Gson().fromJson(string, DTccmBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(dTccmBean.getMsg());
                        finish();
                    }
                });
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        infoBase = (ImageView) findViewById(R.id.info_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        etName = (EditText) findViewById(R.id.et_name);
        etZjh = (EditText) findViewById(R.id.et_zjh);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvSetSave = (Button) findViewById(R.id.tv_set_save);
    }
}