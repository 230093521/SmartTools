package com.gzeic.smartcity01.mzyy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.KaPianBean;
import com.gzeic.smartcity01.bean.KeShiBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzYydActivity extends BaseActivity implements View.OnClickListener {

    private ImageView metroBase;
    private TextView name;
    private TextView keshi;
    private TextView zhuanjia;
    private TextView jine;
    private TextView riqi;
    private TextView shijian;
    private TextView quxiao;
    private TextView queding;
    SimpleDateFormat simpleDateFormat;
    //获取日期格式器对象
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    KaPianBean.RowsDTO rowsBean;
    KeShiBean.RowsDTO rowsBean1;
    String leixin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_mz_yyd);
        String kapian = getSP("kapian");
        String keshis = getSP("keshi");
        leixin = getSP("leixin");
        rowsBean = new Gson().fromJson(kapian, KaPianBean.RowsDTO.class);
        rowsBean1 = new Gson().fromJson(keshis, KeShiBean.RowsDTO.class);
        initView();
        simpleDateFormat  = new SimpleDateFormat("HH:mm");
        name.setText(rowsBean.getName());
        keshi.setText(rowsBean1.getCategoryName());
        if (leixin.equals("1")){
            zhuanjia.setText("专家号");
        }else {
            zhuanjia.setText("普通号");
        }
        jine.setText(rowsBean1.getMoney()+"元");

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        name = (TextView) findViewById(R.id.name);
        keshi = (TextView) findViewById(R.id.keshi);
        zhuanjia = (TextView) findViewById(R.id.zhuanjia);
        jine = (TextView) findViewById(R.id.jine);
        riqi = (TextView) findViewById(R.id.riqi);
        shijian = (TextView) findViewById(R.id.shijian);
        quxiao = (TextView) findViewById(R.id.quxiao);
        queding = (TextView) findViewById(R.id.queding);
        metroBase.setOnClickListener(this);
        riqi.setOnClickListener(this);
        shijian.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.metro_base || id == R.id.quxiao) {
            finish();
        } else if (id == R.id.riqi) {
            showDatePickDlg();
        } else if (id == R.id.shijian) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(MzYydActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //同DatePickerDialog控件
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                }

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
            shijian.setText(simpleDateFormat.format(calendar.getTime()));
            //将页面TextView的显示更新为最新时间
        } else if (id == R.id.queding) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("categoryId", rowsBean1.getId());
                jsonObject.put("money", rowsBean1.getMoney());
                jsonObject.put("patientName", rowsBean.getName());
                jsonObject.put("type", rowsBean1.getType());
                jsonObject.put("reserveTime", riqi.getText().toString() + " " + shijian.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/hospital", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.e(TAG, "onResponse: " + json);
                    KaPianBean kaPianBean = new Gson().fromJson(json, KaPianBean.class);
                    if (kaPianBean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("预约成功");
                                startActivity(new Intent(MzYydActivity.this, MzYylsActivity.class));
                            }
                        });
                    }
                }
            });
        }
    }

    public void showDatePickDlg () {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MzYydActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                riqi.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }


}