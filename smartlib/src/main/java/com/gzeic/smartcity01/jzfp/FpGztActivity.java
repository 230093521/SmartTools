package com.gzeic.smartcity01.jzfp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class FpGztActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBase;
    private ImageView qiuzu;
    private ImageView bangfu;
    private ImageView ruhu;
    private ImageView fabu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_fp_gzt);
        initView();

    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        qiuzu = (ImageView) findViewById(R.id.qiuzu);
        bangfu = (ImageView) findViewById(R.id.bangfu);
        ruhu = (ImageView) findViewById(R.id.ruhu);
        fabu = (ImageView) findViewById(R.id.fabu);
        ivBase.setOnClickListener(this);
        qiuzu.setOnClickListener(this);
        bangfu.setOnClickListener(this);
        ruhu.setOnClickListener(this);
        fabu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_base) {
            finish();
        } else if (id == R.id.qiuzu) {
            startActivity(new Intent(FpGztActivity.this, FpGzsdqzActivity.class));
        } else if (id == R.id.bangfu) {
            startActivity(new Intent(FpGztActivity.this, FpGzwbfdActivity.class));
        } else if (id == R.id.ruhu) {
            startActivity(new Intent(FpGztActivity.this, FpGzrhzfActivity.class));
        } else if (id == R.id.fabu) {
            startActivity(new Intent(FpGztActivity.this, FpGzalfbActivity.class));
        }
    }
}