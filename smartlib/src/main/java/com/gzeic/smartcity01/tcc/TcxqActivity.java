package com.gzeic.smartcity01.tcc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.TinCheBean;

public class TcxqActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView name;
    private TextView juli;
    private TextView weizhi;
    private TextView tcf;
    private TextView sy;
    private TextView zs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_tcxq);
        initView();
        String tinche = getSP("tinche");
        TinCheBean.RowsBean rowsBean = new Gson().fromJson(tinche, TinCheBean.RowsBean.class);
        name.setText(rowsBean.getParkName());
        juli.setText(rowsBean.getDistance()+"公里");
        weizhi.setText(rowsBean.getAddress());
        tcf.setText(rowsBean.getRates()+"元");
        sy.setText("60个");
        zs.setText(rowsBean.getAllPark());
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        name = (TextView) findViewById(R.id.name);
        juli = (TextView) findViewById(R.id.juli);
        weizhi = (TextView) findViewById(R.id.weizhi);
        tcf = (TextView) findViewById(R.id.tcf);
        sy = (TextView) findViewById(R.id.sy);
        zs = (TextView) findViewById(R.id.zs);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}