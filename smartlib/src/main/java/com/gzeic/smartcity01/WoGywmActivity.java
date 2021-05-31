package com.gzeic.smartcity01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xsonline.smartlib.R;

public class WoGywmActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView ivBase;
    private TextView homeTitle;
    private ImageView mzsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_gywm);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mzsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoGywmActivity.this,WoMzsmActivity.class));
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        ivBase = (ImageView) findViewById(R.id.iv_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        mzsm = (ImageView) findViewById(R.id.mzsm);
    }
}