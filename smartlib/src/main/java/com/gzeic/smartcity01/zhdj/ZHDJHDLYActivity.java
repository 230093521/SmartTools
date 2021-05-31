package com.gzeic.smartcity01.zhdj;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class ZHDJHDLYActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView ivBase;
    private TextView homeTitle;
    private EditText etFeedback;
    private TextView tvZishu;
    private TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_hdly);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = etFeedback.getText().toString();
                if (toString.isEmpty()){
                    showToast("请输入内容");
                }else {
                    showToast("提交成功");
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        ivBase = (ImageView) findViewById(R.id.iv_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        etFeedback = (EditText) findViewById(R.id.et_feedback);
        tvZishu = (TextView) findViewById(R.id.tv_zishu);
        submit = (TextView) findViewById(R.id.submit);
    }
}