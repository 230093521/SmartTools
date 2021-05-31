package com.gzeic.smartcity01.zhdj;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

import java.util.Calendar;
import java.util.Locale;

public class ZHDJHDBMActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView name;
    private TextView keshi;
    private TextView zhuanjia;
    private TextView jine;
    private TextView riqi;
    private TextView shijian;
    private TextView quxiao;
    private TextView queding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_hdbm);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("报名成功");
            }
        });
        riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ZHDJHDBMActivity.this,Calendar.getInstance(Locale.CHINA));
            }
        });
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(ZHDJHDBMActivity.this,Calendar.getInstance(Locale.CHINA));
            }
        });

    }

    /**
     * 日期选择
     *
     * @param activity
     * @param calendar
     */
    public void showDatePickerDialog(final Activity activity, final Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                riqi.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     *
     * @param activity
     * @param calendar
     */
    public void showTimePickerDialog(Activity activity, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog(activity,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        shijian.setText(hourOfDay + ":" + minute+":00");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                , true).show();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        name = (TextView) findViewById(R.id.name);
        keshi = (TextView) findViewById(R.id.keshi);
        zhuanjia = (TextView) findViewById(R.id.zhuanjia);
        jine = (TextView) findViewById(R.id.jine);
        riqi = (TextView) findViewById(R.id.riqi);
        shijian = (TextView) findViewById(R.id.shijian);
        quxiao = (TextView) findViewById(R.id.quxiao);
        queding = (TextView) findViewById(R.id.queding);
    }
}