package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WzWzCxsqActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView ivBase;
    private EditText etFeedback;
    private TextView tvZishu;
    private TextView submit;
    private String http = "http://";
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_cxsq);
        id = getIntent().getIntExtra("id", -1);
        initView();
        etFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvZishu.setText(s.length()+"/500");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initView() {
        homeToprl = findViewById(R.id.home_toprl);
        ivBase = findViewById(R.id.iv_base);
        etFeedback = findViewById(R.id.et_feedback);
        tvZishu = findViewById(R.id.tv_zishu);
        submit = findViewById(R.id.submit);
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etFeedback.getText().toString();
                if (s.length()>0){
                    tj(s);
                }else{
                    Toast.makeText(WzWzCxsqActivity.this, "收起理由不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void tj(String s) {
        HashMap hashMap = new HashMap();
        hashMap.put("illegalId",id);
        hashMap.put("reason",s);
        getTools().sendPostJsonToken(http+getServerIp()+"/prod-api/api/traffic/illegal/cancel",hashMap).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(WzWzCxsqActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call call, Throwable throwable) {
            }
        });
    }
}