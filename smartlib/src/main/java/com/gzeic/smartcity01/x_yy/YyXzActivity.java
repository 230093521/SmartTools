package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyZwLbBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyXzActivity extends BaseActivity {
    Integer id;
    String add;
    GridLayout gridview;
    Button btn_goupiao;
    ImageView back;
    List<CheckBox> checkBoxList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#5F8BFA"));
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        setContentView(R.layout.activity_yy_xz);
        gridview = findViewById(R.id.gridview);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_goupiao = findViewById(R.id.btn_goupiao);
        id = getIntent().getIntExtra("id", -1);
        btn_goupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(YyXzActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(YyXzActivity.this, YyGpjgActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        zwlb();
    }

    private void zwlb() {
        getTools().sendGet("http://"+add+"/prod-api/api/movie/theatre/seat/list?roomId="+id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                    Log.i(TAG, "onResponse: 座位"+string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyZwLbBean zwlb = gson.fromJson(string, YyZwLbBean.class);
                final List<YyZwLbBean.RowsBean> rows = zwlb.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size()==0){
                            showToast("该场次暂无座位可选");
                            return;
                        }
                        for (int i = 0; i < rows.size(); i++) {
                            final CheckBox inflate = (CheckBox) LayoutInflater.from(YyXzActivity.this).inflate(R.layout.itme_yy_xz, gridview, false);
                            gridview.addView(inflate);
                            inflate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked){
                                        if (checkBoxList.size()<4){
                                            checkBoxList.add(inflate);
                                        }else {
                                            showToast("每人最多购买4张噢~");
                                            inflate.setChecked(false);
                                        }
                                    }else {
                                        checkBoxList.remove(inflate);
                                    }
                                }
                            });
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
}