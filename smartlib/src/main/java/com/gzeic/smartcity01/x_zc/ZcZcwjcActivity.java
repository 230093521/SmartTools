package com.gzeic.smartcity01.x_zc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoZiLiaoActivity;
import com.gzeic.smartcity01.bean.ZcTccBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcZcwjcActivity extends BaseActivity {

    private ImageView newsBase;
    private TextView homeTitle;
    private EditText cheweishu;
    private EditText shuru;
    private ImageView zhaopian;
    private Button paizhao;
    ZcTccBean.RowsDTO rowsDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_zcwjc);
        initView();
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zhaopian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开图库
                if (ActivityCompat.checkSelfPermission(ZcZcwjcActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ZcZcwjcActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 200);
                }
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tijiao();
            }
        });
        String tingchebean = getSP("tingchebean");
        rowsDTO = new Gson().fromJson(tingchebean, ZcTccBean.RowsDTO.class);
    }

    private void tijiao() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content",shuru.getText().toString());
            jsonObject.put("name",rowsDTO.getParkName());
            jsonObject.put("remark"," ");
            jsonObject.put("spotCount",Integer.valueOf(cheweishu.getText().toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/api/park/correct", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final ZcTccBean zcTccBean = new Gson().fromJson(string, ZcTccBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(zcTccBean.getMsg());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                Log.i(TAG, "onActivityResult: " + data.getData() + "    " + requestCode + "    " + resultCode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getApplicationContext()).load(data.getData()).error(R.mipmap.ic_launcher).into(zhaopian);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        cheweishu = (EditText) findViewById(R.id.cheweishu);
        shuru = (EditText) findViewById(R.id.shuru);
        zhaopian = (ImageView) findViewById(R.id.zhaopian);
        paizhao = (Button) findViewById(R.id.paizhao);
    }
}