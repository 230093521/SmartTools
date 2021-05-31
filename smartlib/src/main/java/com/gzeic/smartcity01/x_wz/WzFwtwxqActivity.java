package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.TestWtBean;

public class WzFwtwxqActivity extends BaseActivity {

    TestWtBean testWtBean;
    private ImageView metroBase;
    private ImageView img;
    private TextView name;
    private TextView wenti;
    private TextView jieda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_fwwt);
        String fwwt = getSP("fwwt");
        testWtBean = new Gson().fromJson(fwwt, TestWtBean.class);
        initView();
        initWt();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWt() {
        Glide.with(WzFwtwxqActivity.this).load(HTTP_HEAD+getServerIp()+getUserdata().getAvatar()).error(R.drawable.icon2).into(img);
        name.setText("欢迎您，用户"+getUserdata().getNickName()+"!");
        wenti.setText(testWtBean.getName());
        jieda.setText(testWtBean.getContent());
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        img = (ImageView) findViewById(R.id.img);
        name = (TextView) findViewById(R.id.name);
        wenti = (TextView) findViewById(R.id.wenti);
        jieda = (TextView) findViewById(R.id.jieda);
    }
}