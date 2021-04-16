package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.RegBean;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText regUsername;
    private EditText regNickname;
    private EditText regPhone;
    private EditText regPassword;
    private Button regLoginBtn;
    private Button regRegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#2196F3"));
        setContentView(R.layout.activity_register);
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
            if (userName.isEmpty()) {
                showToast("用户名不能为空");
            }
            if (nickName.isEmpty()) {
                showToast("昵称不能为空");
            }
            if (phoneNumber.isEmpty()) {
                showToast("手机号不能为空");
            }
            if (password.isEmpty()) {
                showToast("密码不能为空");
            }
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userName", userName);
                jsonObject.put("nickName", nickName);
                jsonObject.put("phoneNumber", phoneNumber);
                jsonObject.put("sex", "1");
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequest(jsonObject, "http://" + getServerIp() + "/system/user/register", new Callback() {
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