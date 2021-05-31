package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoDenLuActivity;
import com.gzeic.smartcity01.bean.YyXwBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YyXwxqActivity extends BaseActivity {
    YyXwBean.RowsBean xw;
    private ImageView back;
    private TextView zbt;
    private TextView fbsj;
    private TextView fbt;
    private TextView dzs;
    private TextView content;
    private ImageView dz;
    private ImageView img;
    private String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_xwxq);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        String stringExtra = getIntent().getStringExtra("xw");
        Gson gson = new Gson();
        xw = gson.fromJson(stringExtra, YyXwBean.RowsBean.class);
        initView();
        zbt.setText(xw.getTitle());
        Object subTitle = xw.getSubTitle();
        if (subTitle!=null){
            fbt.setText(subTitle.toString());
        }
        fbsj.setText(xw.getPublishDate());
        dzs.setText(xw.getLikeNum()+"");
        Glide.with(this).load("http://"+add+xw.getCover()).into(img);
        content.setText(Html.fromHtml(xw.getContent()));
    }

    private void initView() {
        back = findViewById(R.id.back);
        img = findViewById(R.id.img);
        content = findViewById(R.id.content);
        zbt = findViewById(R.id.zbt);
        fbsj = findViewById(R.id.fbsj);
        fbt = findViewById(R.id.fbt);
        dzs = findViewById(R.id.dzs);
        dz = findViewById(R.id.dz);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = Integer.valueOf(dzs.getText().toString());
                dzs.setText(integer+1+"");
                String token = getToken();
                if (token==null){
                    startActivity(new Intent(YyXwxqActivity.this, WoDenLuActivity.class));
                }else{
                    Toast.makeText(YyXwxqActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
                    getTools().sendPutRequestToken(null, "http://" + add + "/prod-api/api/movie/press/press/like/" + xw.getId(), "Bearer " + token, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                        }
                    });
                }
            }
        });
    }
}