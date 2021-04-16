package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.LoginBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBase;
    private EditText etFeedback;
    private TextView submit;
    private TextView tvZishu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_feed_back);
        initView();
    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        etFeedback = (EditText) findViewById(R.id.et_feedback);
        submit = (TextView) findViewById(R.id.submit);
        tvZishu = (TextView) findViewById(R.id.tv_zishu);
        submit.setOnClickListener(this);
        ivBase.setOnClickListener(this);
        etFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Editable text = etFeedback.getText();
                int len = text.length();
                tvZishu.setText(len+""+"/150");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_base) {
            finish();
        } else if (id == R.id.submit) {
            if (etFeedback.getText().toString().isEmpty()) {
                return;
            }
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", etFeedback.getText().toString());
                jsonObject.put("userId", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/userinfo/feedback", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i(TAG, "onResponse: " + json);
                    LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    if (loginBean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("提交成功");
                            }
                        });
                    }
                }
            });
        }
    }
}