package com.gzeic.smartcity01.x_jf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class JfRqfActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private RadioButton cq1;
    private RadioButton cq2;
    private RadioButton cq3;
    private EditText etShuifei;
    private Button btnChaxun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_rqf);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cq = null;
                if (cq1.isChecked()){
                    cq = cq1.getText().toString();
                }else if (cq2.isChecked()){
                    cq = cq2.getText().toString();
                }else {
                    cq = cq3.getText().toString();
                }
                if (etShuifei.getText().toString().isEmpty()){
                    showToast("燃气费号不能为空");
                    return;
                }
                putSP("chengqu",cq);
                putSP("shuifeihao",etShuifei.getText().toString());
                startActivity(new Intent(JfRqfActivity.this,JfRqfjfActivity.class));
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        cq1 = (RadioButton) findViewById(R.id.cq1);
        cq2 = (RadioButton) findViewById(R.id.cq2);
        cq3 = (RadioButton) findViewById(R.id.cq3);
        etShuifei = (EditText) findViewById(R.id.et_shuifei);
        btnChaxun = (Button) findViewById(R.id.btn_chaxun);
    }
}