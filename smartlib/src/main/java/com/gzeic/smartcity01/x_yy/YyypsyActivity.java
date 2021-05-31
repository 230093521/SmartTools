package com.gzeic.smartcity01.x_yy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyYpygBean;

public class YyypsyActivity extends BaseActivity {
    YyYpygBean.RowsBean rowsBean;
    String add;
    private RelativeLayout toplan;
    private ImageView back;
    private ImageView tupian;
    private TextView name;
    private TextView neirong;
    private TextView tvZhankai;
    private TextView tvShouqi;
    private Button btnGoupiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_j_s_y);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String stringExtra = getIntent().getStringExtra("jjsy");
        Gson gson = new Gson();
        rowsBean = gson.fromJson(stringExtra, YyYpygBean.RowsBean.class);
        if (rowsBean != null) {
            name.setText(rowsBean.getName());
            Glide.with(this).load("http://" + add + rowsBean.getCover()).into(tupian);
            neirong.setText(Html.fromHtml(rowsBean.getIntroduction()));
        }
        tvZhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxLines = neirong.getMaxLines();
                if (maxLines == 3) {
                    neirong.setMaxLines(99999999);
                    tvZhankai.setText("收起");
                } else {
                    neirong.setMaxLines(3);
                    tvZhankai.setText("展开");
                }
            }
        });
        btnGoupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YyypsyActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
            }
        });
//        leixin.setText("类型：" + yplxs[Integer.valueOf(ypxx.getCategory()) - 1]);
//        xk.setText("想看：" + ypxx.getLikeNum() + "人");
//        kg.setText("看过：" + ypxx.getFavoriteNum() + "人");
//        sysj.setText("上映时间：" + ypxx.getPlayDate());
//        pf.setText("评分：" + ypxx.getScore() * 2 + "分");
//        Glide.with(YyYpxqActivity.this).load("http://" + add + ypxx.getCover()).error(R.drawable.icon2).into(tupian);
//        neirong.setText(Html.fromHtml(ypxx.getIntroduction()) + "");
    }

    private void initView() {
        toplan = findViewById(R.id.toplan);
        back = findViewById(R.id.back);
        tupian = findViewById(R.id.tupian);
        name = findViewById(R.id.name);
        neirong = findViewById(R.id.neirong);
        tvZhankai = findViewById(R.id.tv_zhankai);
        tvShouqi = findViewById(R.id.tv_shouqi);
        btnGoupiao = findViewById(R.id.btn_goupiao);
    }
}