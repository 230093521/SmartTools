package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.LoginBean;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    String serverIp;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnReg;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#2196F3"));
        setContentView(R.layout.activity_login);
        serverIp = getServerIp();
        initView();
        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
    }

    private void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnReg = (Button) findViewById(R.id.btn_reg);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etUsername.setText(sp.getString("username", null));
        etPassword.setText(sp.getString("password", null));
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_reg) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        } else if (id == R.id.btn_login) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            if (username.isEmpty()) {
                showToast("请输入账号");
            }
            if (password.isEmpty()) {
                showToast("请输入密码");
            }
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            sp.edit().putString("username", username).putString("password", password).apply();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", username);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequest(jsonObject, "http://" + serverIp + "/login", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showToast("请求失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i("ceshi", "onResponse: " + json);
                    final LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    if (loginBean.getCode() == 200) {
                        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                        sharedPreferences.edit().putString("token", loginBean.getToken()).apply();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                showToast(loginBean.getMsg());
                                finish();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(loginBean.getMsg());
                            }
                        });

                    }
                }
            });
        }
    }

}