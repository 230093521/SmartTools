package com.gzeic.smartcity01.wzcx;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzcxBean;

import java.util.List;


public class WzJgActivity extends BaseActivity {
    private ImageView metroBase;
    private TextView homeTitle;
    private ListView weizhanglist;
    private Button chakangenduo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wz_jg);
        initView();
        String json = "{\n" +
                "\"total\": 2,\n" +
                "\"rows\": [\n" +
                "{\n" +
                "\"searchValue\": null,\n" +
                "\"createBy\": null,\n" +
                "\"createTime\": null,\n" +
                "\"updateBy\": null,\n" +
                "\"updateTime\": null,\n" +
                "\"remark\": null,\n" +
                "\"params\": {},\n" +
                "\"id\": 1,\n" +
                "\"licencePlate\": \"京 123\",\n" +
                "\"disposeState\": \"1\",\n" +
                "\"badTime\": \"2020-10-23 17:03\",\n" +
                "\"money\": \"200\",\n" +
                "\"deductMarks\": \"6\",\n" +
                "\"illegalSites\": \"大连市万达广场\",\n" +
                "\"noticeNo\": \"123456\",\n" +
                "\"engineNumber\": \"1234\",\"catType\": \"小型汽车\",\n" +
                " \"trafficOffence\": \"闯红灯\"\n" +
                " },\n" +
                " {\n" +
                " \"searchValue\": null,\n" +
                " \"createBy\": null,\n" +
                " \"createTime\": null,\n" +
                " \"updateBy\": null,\n" +
                " \"updateTime\": null,\n" +
                " \"remark\": null,\n" +
                " \"params\": {},\n" +
                " \"id\": 5,\n" +
                " \"licencePlate\": \"京 123\",\n" +
                " \"disposeState\": \"1\",\n" +
                " \"badTime\": \"2020-10-23 17:03\",\n" +
                " \"money\": \"200\",\n" +
                " \"deductMarks\": \"6\",\n" +
                " \"illegalSites\": \"北京市\",\n" +
                " \"noticeNo\": \"123456\",\n" +
                " \"engineNumber\": \"1234\",\n" +
                " \"catType\": \"小型汽车\",\n" +
                " \"trafficOffence\": \"不礼让行人\"\n" +
                " }\n" +
                " ],\n" +
                " \"code\": 200,\n" +
                " \"msg\": \"查询成功\" }";
        WzcxBean wzcxBean = new Gson().fromJson(json, WzcxBean.class);
        final List<WzcxBean.RowsDTO> wzcxBeanRows1 = wzcxBean.getRows();
        final List<WzcxBean.RowsDTO> wzcxBeanRows2 = wzcxBean.getRows();
        List<WzcxBean.RowsDTO> wzcxBeanRows3 = wzcxBean.getRows();
        wzcxBeanRows1.addAll(wzcxBeanRows2);
        wzcxBeanRows1.addAll(wzcxBeanRows3);
        setWzcx(wzcxBeanRows1);
        chakangenduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wzcxBeanRows1.addAll(wzcxBeanRows2);
                setWzcx(wzcxBeanRows1);
                chakangenduo.setVisibility(View.GONE);
            }
        });

        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setWzcx(final List<WzcxBean.RowsDTO> wzcxBeanRows22) {
        weizhanglist.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public TextView didian;
                public TextView fenshu;
                public TextView jine;
                public TextView clzt;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.didian = (TextView) rootView.findViewById(R.id.didian);
                    this.fenshu = (TextView) rootView.findViewById(R.id.fenshu);
                    this.jine = (TextView) rootView.findViewById(R.id.jine);
                    this.clzt = (TextView) rootView.findViewById(R.id.clzt);
                }

            }

            @Override
            public int getCount() {
                return wzcxBeanRows22.size();
            }

            @Override
            public Object getItem(int position) {
                return wzcxBeanRows22.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(WzJgActivity.this).inflate(R.layout.item_wz_lb, null);
                ViewHolder viewHolder = new ViewHolder(convertView);
                final WzcxBean.RowsDTO rowsDTO = wzcxBeanRows22.get(position);
                viewHolder.didian.setText(rowsDTO.getIllegalSites());
                viewHolder.fenshu.setText(rowsDTO.getDeductMarks());
                viewHolder.jine.setText(rowsDTO.getMoney());
                if (rowsDTO.getDisposeState().equals("1")) {
                    viewHolder.clzt.setText("待处理");
                } else {
                    viewHolder.clzt.setText("已处理");
                }
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        putSP("wzcxjg", new Gson().toJson(rowsDTO));
                        startActivity(new Intent(WzJgActivity.this, WzxqActivity.class));
                    }
                });
                return convertView;
            }
        });
    }


    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        weizhanglist = (ListView) findViewById(R.id.weizhanglist);
        chakangenduo = (Button) findViewById(R.id.chakangenduo);
    }
}