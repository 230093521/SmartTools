package com.gzeic.smartcity01.x_zc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZcLcxxBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcJytjjlActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView baocun;
    private TextView riqi;
    private EditText licheng;
    private EditText jine;
    private EditText youjia;
    private EditText youliang;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_jytjjl);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        riqi.setText(getThisTime());
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (riqi.getText().toString().equals("请选择加油日期")) {
//                    showToast("请选择日期");
//                    return;
//                }
                if (licheng.getText().toString().isEmpty()) {
                    showToast("请输入里程");
                    return;
                }
                if (jine.getText().toString().isEmpty()) {
                    showToast("请输入金额");
                    return;
                }
                if (youjia.getText().toString().isEmpty()) {
                    showToast("请输入油价");
                    return;
                }
                if (youliang.getText().toString().isEmpty()) {
                    showToast("请输入油量");
                    return;
                }
                getTianJia();
            }
        });
        riqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showriqi1();
            }
        });

    }
    public void showriqi1() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ZcJytjjlActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                riqi.setText(i + "-" + i1 + "-" + i2);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getTianJia() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("amount",Integer.valueOf(jine.getText().toString()));
            jsonObject.put("gasFilling",Integer.valueOf(youliang.getText().toString()));
            jsonObject.put("plateNo","辽 B444444");
            jsonObject.put("travelDate",riqi.getText().toString());
            jsonObject.put("travelDistance",Integer.valueOf(licheng.getText().toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/api/park/car/consumption", getToken(), new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String string = response.body().string();
            final ZcLcxxBean zcLcxxBean = new Gson().fromJson(string, ZcLcxxBean.class);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(zcLcxxBean.getMsg());
                }
            });
        }
    });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        baocun = (TextView) findViewById(R.id.baocun);
        riqi = (TextView) findViewById(R.id.riqi);
        licheng = (EditText) findViewById(R.id.licheng);
        jine = (EditText) findViewById(R.id.jine);
        youjia = (EditText) findViewById(R.id.youjia);
        youliang = (EditText) findViewById(R.id.youliang);
    }
}