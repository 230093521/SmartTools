package com.gzeic.smartcity01;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.gzeic.smartcity01.bean.UsersBean;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoMiMaActivity extends BaseActivity implements View.OnClickListener {
    UsersBean.UserDTO dataBean;
    private ImageView passBase;
    private EditText newPass1;
    private EditText newPass2;
    private TextView tvSetSave;
    private EditText yuanmima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wo_mima);
        initView();
        dataBean = getUserdata();
    }

    private void initView() {
        passBase = (ImageView) findViewById(R.id.pass_base);
        newPass1 = (EditText) findViewById(R.id.new_pass1);
        newPass2 = (EditText) findViewById(R.id.new_pass2);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
        tvSetSave.setOnClickListener(this);
        passBase.setOnClickListener(this);
        yuanmima = (EditText) findViewById(R.id.yuanmima);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.pass_base) {
            finish();
        } else if (id == R.id.tv_set_save) {
            String pass1 = newPass1.getText().toString();
            String pass2 = newPass2.getText().toString();
            if (yuanmima.getText().toString().isEmpty()) {
                showToast("原密码不能为空");
                return;
            }
            if (!pass1.equals(pass2)) {
                showToast("两次输入密码不一致");
                return;
            }
            putPass(yuanmima.getText().toString(), pass2);
        }
    }

    public void putPass(String oldpwd, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newPassword", password);
            jsonObject.put("oldPassword", oldpwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPutRequestToken(jsonObject, "http://" + getServerIp() + "/system/user/resetPwd", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                try {
                    final MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
                    if (myDataBean.getCode()==200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("修改完成");
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(myDataBean.getMsg());
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