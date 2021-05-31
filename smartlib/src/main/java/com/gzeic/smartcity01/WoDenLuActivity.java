package com.gzeic.smartcity01;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gzeic.smartcity01.bean.LoginBean;
import com.gzeic.smartcity01.bean.UsersBean;
import com.gzeic.smartcity01.mzyy.MzActivity;
import com.gzeic.smartcity01.x_csdt.CsdtSyActivity;
import com.gzeic.smartcity01.x_jf.JfSyActivity;
import com.gzeic.smartcity01.x_wz.WzSyActivity;
import com.gzeic.smartcity01.x_yy.YySyActivity;
import com.gzeic.smartcity01.x_zc.ZcSyActivity;
import com.gzeic.smartcity01.zfz.ZfzActivity;
import com.gzeic.smartcity01.zgz.ZgzActivity;
import com.gzeic.smartcity01.zhbs.BaShiActivity;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoDenLuActivity extends BaseActivity implements View.OnClickListener {
    String serverIp;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnReg;
    private Button btnLogin;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#5F8BFA"));
        setContentView(R.layout.activity_wo_denglu);
        serverIp = getServerIp();
        initView();
        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        verifyStoragePermissions(this);
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

    //申请读写权限
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_reg) {
            startActivity(new Intent(WoDenLuActivity.this, WoZhuCeActivity.class));
        } else if (id == R.id.btn_login) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            if (username.isEmpty()) {
                showToast("请输入账号");
                return;
            }
            if (password.isEmpty()) {
                showToast("请输入密码");
                return;
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
            getTools().sendPostRequest(jsonObject, "http://" + serverIp + "/prod-api/api/login", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "run: "+"http://" + serverIp + "/prod-api/api/login");
                            showToast("请求失败");
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i("ceshi", "onResponse: " + json);
                    try {
                        final LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                        if (loginBean.getCode() == 200) {
                            SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                            sharedPreferences.edit().putString("token", loginBean.getToken()).apply();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                  startActivity(new Intent(WoDenLuActivity.this, MainActivity.class));
                                    getUserdata1();
                                    showToast(loginBean.getMsg());
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
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public void getUserdata1(){
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/common/user/getInfo", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                try {
                    UsersBean userDataBean = new Gson().fromJson(json, UsersBean.class);
                    if (userDataBean.getCode()==200){
                        putSP("userdata",json);
                        startActivity(new Intent(WoDenLuActivity.this, MainActivity.class));
                        finish();
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("网络错误,获取用户信息失败");
                        }
                    });
                }
            }
        });
    }

}