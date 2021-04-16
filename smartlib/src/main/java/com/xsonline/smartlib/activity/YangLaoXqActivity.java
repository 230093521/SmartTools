package com.xsonline.smartlib.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xsonline.smartlib.R;

public class YangLaoXqActivity extends BaseActivity {

    private ImageView metroBase;
    private ImageView img;
    private TextView neirong;
    private Button btnYuyue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yang_lao_xq);
        String json = getSP("yanglao");
        YangLaoActivity.yanglaobean yanglaobean = new Gson().fromJson(json, YangLaoActivity.yanglaobean.class);
        initView();
        img.setImageResource(yanglaobean.getImg());
        neirong.setText(yanglaobean.getContent());
        btnYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("预约成功");
                startActivity(new Intent(YangLaoXqActivity.this,YangLaoZhuYeActivity.class));
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        img = (ImageView) findViewById(R.id.img);
        neirong = (TextView) findViewById(R.id.neirong);
        btnYuyue = (Button) findViewById(R.id.btn_yuyue);
    }
}