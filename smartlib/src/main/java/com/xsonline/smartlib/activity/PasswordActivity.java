package com.xsonline.smartlib.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.MyDataBean;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PasswordActivity extends BaseActivity implements View.OnClickListener {

    private ImageView passBase;
    private EditText newPass1;
    private EditText newPass2;
    private TextView tvSetSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_password);
        initView();
    }

    private void initView() {
        passBase = (ImageView) findViewById(R.id.pass_base);
        newPass1 = (EditText) findViewById(R.id.new_pass1);
        newPass2 = (EditText) findViewById(R.id.new_pass2);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
        tvSetSave.setOnClickListener(this);
        passBase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.pass_base) {
            finish();
        } else if (id == R.id.tv_set_save) {
            SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
            String userdata = sharedPreferences.getString("userdata", null);
            MyDataBean myDataBean = new Gson().fromJson(userdata, MyDataBean.class);
            String name = myDataBean.getData().getUserName();
            String pass1 = newPass1.getText().toString();
            String pass2 = newPass2.getText().toString();
            if (!pass1.equals(pass2)) {
                showToast("两次输入密码不一致");
                return;
            }
            putPass("2", name, pass2);
        }
    }

    public void putPass(String userId,String username,String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("userName",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        getTools().sendPutRequestToken(jsonObject, "http://"+getServerIp()+"/system/user/resetPwd", token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);

            }
        });

    }
}