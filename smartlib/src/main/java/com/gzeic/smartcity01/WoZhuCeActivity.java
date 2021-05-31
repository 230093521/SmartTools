package com.gzeic.smartcity01;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.RegBean;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoZhuCeActivity extends BaseActivity implements View.OnClickListener {

    private EditText regUsername;
    private EditText regNickname;
    private EditText regPhone;
    private EditText regPassword;
    private Button regLoginBtn;
    private Button regRegBtn;
    private RelativeLayout rlRl1;
    private EditText regEmail;
    private EditText regShenfen;
    private RadioButton nan;
    private RadioButton nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wo_zhuce);
        initView();
    }

    private void initView() {
        regUsername = (EditText) findViewById(R.id.reg_username);
        regNickname = (EditText) findViewById(R.id.reg_nickname);
        regPhone = (EditText) findViewById(R.id.reg_phone);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regLoginBtn = (Button) findViewById(R.id.reg_login_btn);
        regRegBtn = (Button) findViewById(R.id.reg_reg_btn);
        regLoginBtn.setOnClickListener(this);
        regRegBtn.setOnClickListener(this);
        rlRl1 = (RelativeLayout) findViewById(R.id.rl_rl1);
        regEmail = (EditText) findViewById(R.id.reg_email);
        regShenfen = (EditText) findViewById(R.id.reg_shenfen);
        nan = (RadioButton) findViewById(R.id.nan);
        nv = (RadioButton) findViewById(R.id.nv);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.reg_login_btn) {
            finish();
        } else if (id == R.id.reg_reg_btn) {
            String userName = regUsername.getText().toString();
            String nickName = regNickname.getText().toString();
            String phoneNumber = regPhone.getText().toString();
            String password = regPassword.getText().toString();
            String email = regEmail.getText().toString();
            String shenfen = regShenfen.getText().toString();
            String sex = "0";
            if (nv.isChecked()){
                sex = "1";
            }
            if (userName.isEmpty()) {
                showToast("用户名不能为空");
                return;
            }
            if (nickName.isEmpty()) {
                showToast("昵称不能为空");
                return;
            }
            if (phoneNumber.isEmpty()) {
                showToast("手机号不能为空");
                return;
            }
            if (password.isEmpty()) {
                showToast("密码不能为空");
                return;
            }
            if (email.isEmpty()) {
                showToast("邮箱不能为空");
                return;
            }
            if (shenfen.isEmpty()) {
                showToast("身份证号不能为空");
                return;
            }
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userName", userName);
                jsonObject.put("nickName", nickName);
                jsonObject.put("password", password);
                jsonObject.put("phonenumber", phoneNumber);
                jsonObject.put("sex", "1");
                jsonObject.put("email", email);
                jsonObject.put("idCard", shenfen);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequest(jsonObject, "http://" + getServerIp() + "/prod-api/api/register", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showToast("请求失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i(TAG, "onResponse: " + json);
                    final RegBean regBean = new Gson().fromJson(json, RegBean.class);
                    if (regBean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(regBean.getMsg());
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(regBean.getMsg());
                            }
                        });
                    }
                }
            });
        }
    }

}