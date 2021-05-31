package com.gzeic.smartcity01.x_zc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZcTccBean;

public class ZcZcwxqActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView name;
    private TextView juli;
    private TextView dwkf;
    private TextView weizhi;
    private TextView tcf;
    private TextView kongwei;
    private TextView zs;
    private TextView sfck;
    private ImageView xinxijiucuo;
    ZcTccBean.RowsDTO rowsDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_zcwxq);
        initView();
        String tingchebean = getSP("tingchebean");
        rowsDTO = new Gson().fromJson(tingchebean, ZcTccBean.RowsDTO.class);
        name.setText(rowsDTO.getParkName());
        juli.setText(rowsDTO.getDistance()+"公里");
        if (rowsDTO.getOpen().equals("Y")){
            dwkf.setText("开放状态：对外开放");
        }else {
            dwkf.setText("开放状态：未开放");
        }
        weizhi.setText(rowsDTO.getAddress());
        tcf.setText(rowsDTO.getRates());
        kongwei.setText(rowsDTO.getVacancy());
        zs.setText("个/"+rowsDTO.getAllPark()+"个");
        sfck.setText("每小时"+rowsDTO.getRates()+"元，最高"+rowsDTO.getPriceCaps()+"元一天");
        xinxijiucuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZcZcwxqActivity.this,ZcZcwjcActivity.class));
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        name = (TextView) findViewById(R.id.name);
        juli = (TextView) findViewById(R.id.juli);
        dwkf = (TextView) findViewById(R.id.dwkf);
        weizhi = (TextView) findViewById(R.id.weizhi);
        tcf = (TextView) findViewById(R.id.tcf);
        kongwei = (TextView) findViewById(R.id.kongwei);
        zs = (TextView) findViewById(R.id.zs);
        sfck = (TextView) findViewById(R.id.sfck);
        xinxijiucuo = (ImageView) findViewById(R.id.xinxijiucuo);
    }
}