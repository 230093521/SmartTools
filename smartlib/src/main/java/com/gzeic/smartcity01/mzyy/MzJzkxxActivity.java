package com.gzeic.smartcity01.mzyy;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.KaPianBean;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzJzkxxActivity extends BaseActivity {

    private ImageView metroBase;
    private EditText etName;
    private RadioButton rbSex1;
    private RadioButton rbSex2;
    private EditText idcard;
    private TextView chusheng;
    private EditText phone;
    private EditText address;
    private Button quxiao;
    private Button queding;
    KaPianBean.RowsDTO rowsBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_mz_jzxx);
        initView();
        final String jiemian = getSP("jiemian");
        if (jiemian.equals("001")){
            String kapian = getSP("kapian");
            rowsBean = new Gson().fromJson(kapian, KaPianBean.RowsDTO.class);
            etName.setText(rowsBean.getName());
            String sex = rowsBean.getSex();
            if (sex.equals("1")){
                rbSex1.setChecked(true);
            }else {
                rbSex2.setChecked(true);
            }
            idcard.setText(rowsBean.getCardId());
            chusheng.setText(rowsBean.getBirthday());
            phone.setText(rowsBean.getTel());
            address.setText(rowsBean.getAddress());
        }else {
            rbSex1.setChecked(true);
        }

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                if (jiemian.equals("001")){
                    try {
                        jsonObject.put("name",etName.getText().toString());
                        jsonObject.put("id",rowsBean.getId());
                        jsonObject.put("cardId",idcard.getText().toString());
                        jsonObject.put("tel",phone.getText().toString());
                        if (rbSex1.isChecked()){
                            jsonObject.put("sex","1");
                        }
                        if (rbSex2.isChecked()){
                            jsonObject.put("sex","0");
                        }
                        jsonObject.put("birthday",chusheng.getText().toString());
                        jsonObject.put("address",address.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getTools().sendPutRequestToken(jsonObject, "http://"+getServerIp()+"/prod-api/api/hospital/patient", getToken(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            Log.i(TAG, "onResponse: "+json);
                            final KaPianBean kaPianBean = new Gson().fromJson(json, KaPianBean.class);
                            if (kaPianBean.getCode()==200){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast(kaPianBean.getMsg());
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                }else {
                    try {
                        jsonObject.put("name",etName.getText().toString());
                        jsonObject.put("cardId",idcard.getText().toString());
                        jsonObject.put("tel",phone.getText().toString());
                        if (rbSex1.isChecked()){
                            jsonObject.put("sex","1");
                        }
                        if (rbSex2.isChecked()){
                            jsonObject.put("sex","0");
                        }
                        jsonObject.put("birthday",chusheng.getText().toString());
                        jsonObject.put("address",address.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getTools().sendPostRequestToken(jsonObject, "http://"+getServerIp()+"/prod-api/api/hospital/patient", getToken(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            final KaPianBean kaPianBean = new Gson().fromJson(json, KaPianBean.class);
                            if (kaPianBean.getCode()==200){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast(kaPianBean.getMsg());
                                        finish();
                                    }
                                });
                            }
                        }
                    });

                }


            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        etName = (EditText) findViewById(R.id.et_name);
        rbSex1 = (RadioButton) findViewById(R.id.rb_sex1);
        rbSex2 = (RadioButton) findViewById(R.id.rb_sex2);
        idcard = (EditText) findViewById(R.id.idcard);
        chusheng = (TextView) findViewById(R.id.chusheng);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        quxiao = (Button) findViewById(R.id.quxiao);
        queding = (Button) findViewById(R.id.queding);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}