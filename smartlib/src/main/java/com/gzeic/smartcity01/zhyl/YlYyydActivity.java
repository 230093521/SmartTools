package com.gzeic.smartcity01.zhyl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class YlYyydActivity extends BaseActivity {

    private ImageView infoBase;
    private TextView tvSetSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_yl_yyyd);
        initView();
        infoBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("提交成功");
            }
        });
    }

    private void initView() {
        infoBase = (ImageView) findViewById(R.id.info_base);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
    }
}