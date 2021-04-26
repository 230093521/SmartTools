package com.gzeic.smartcity01.zhbs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

import java.util.Calendar;

public class BaShiDinDanActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView riqi;
    private TextView shijian;
    private EditText name;
    private TextView tvPhones;
    private EditText phone;
    private EditText scdd;
    private EditText xcdd;
    private Button btnNext;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_ba_shi_din_dan);
        initView();
        name.setText("宋哥");
        phone.setText("158888888888");
        calendar = Calendar.getInstance();


        riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BaShiDinDanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        riqi.setText(i+"-"+i1+"-"+i2);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(BaShiDinDanActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        shijian.setText(i+":"+i1);
                    }
                },calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND),true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scdd.getText().length()<1){
                    showToast("请输入正确的上车地点");
                }else if(xcdd.getText().length()<1){
                    showToast("请输入正确下车地点");
                }else {
                    startActivity(new Intent(BaShiDinDanActivity.this,BaShiQueRenActivity.class));
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        riqi = (TextView) findViewById(R.id.riqi);
        shijian = (TextView) findViewById(R.id.shijian);
        name = (EditText) findViewById(R.id.name);
        tvPhones = (TextView) findViewById(R.id.tv_phones);
        phone = (EditText) findViewById(R.id.phone);
        scdd = (EditText) findViewById(R.id.scdd);
        xcdd = (EditText) findViewById(R.id.xcdd);
        btnNext = (Button) findViewById(R.id.btn_next);
    }
}