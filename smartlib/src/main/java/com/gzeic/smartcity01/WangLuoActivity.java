package com.gzeic.smartcity01;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xsonline.smartlib.R;


public class WangLuoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText setAddress;
    private TextView setSave;
    private ImageView setBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wo_wangluo);
        initView();
    }

    private void initView() {
        setAddress = (EditText) findViewById(R.id.set_address);
        setSave = (TextView) findViewById(R.id.set_save);
        setBack = (ImageView) findViewById(R.id.set_back);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        setAddress.setText(add);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.set_save) {
            String s = setAddress.getText().toString();
            if (s.isEmpty()) {
                Toast.makeText(this, "请输入IP地址与端口", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
                sharedPreferences.edit().putString("add", s).apply();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (id == R.id.set_back) {//                startActivity(new Intent(SetNetworkActivity.this,GuideActivity.class));
            finish();
        }
    }
}