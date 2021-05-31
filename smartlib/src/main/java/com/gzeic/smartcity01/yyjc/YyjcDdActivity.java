package com.gzeic.smartcity01.yyjc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyddBean;

import java.util.List;


public class YyjcDdActivity extends BaseActivity {

    private ListViewScrollView listview;
    private Button xuanze;
    List<YyddBean.RowsDTO> yyddBeanRows;
    int num;
    Myad myad;
    private ImageView news_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_yyjc_dd);
        initView();
        String json = "{\n" +
                "    \"total\": 15,\n" +
                "    \"rows\": [\n" +
                "        {\n" +
                "            \"searchValue\": null,\n" +
                "            \"createBy\": null,\n" +
                "            \"createTime\": null,\n" +
                "            \"updateBy\": null,\n" +
                "            \"updateTime\": null,\n" +
                "            \"remark\": null,\n" +
                "            \"params\": {},\n" +
                "            \"id\": 1,\n" +
                "            \"placeName\": \"丰台区潘北昊盛汽车检测有限公司\",\n" +
                "            \"remarks\": \"周一至周五:上午8:00—12:00;下午13:00—17:00\",\n" +
                "            \"address\": \"北京市丰台区新发地潘家庙518号院\",\n" +
                "            \"phone\": \"010-87504440\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"searchValue\": null,\n" +
                "            \"createBy\": null,\n" +
                "            \"createTime\": null,\n" +
                "            \"updateBy\": null,\n" +
                "            \"updateTime\": null,\n" +
                "            \"remark\": null,\n" +
                "            \"params\": {},\n" +
                "            \"id\": 2,\n" +
                "            \"placeName\": \"朝阳区公共交通第二机动车检测场\",\n" +
                "            \"remarks\": \"周一至周五:上午8:00—12:00;下午13:00—17:01\",\n" +
                "            \"address\": \"北京市朝阳区高碑店半壁店南里西区甲10号\",\n" +
                "            \"phone\": \"010-87744720\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"searchValue\": null,\n" +
                "            \"createBy\": null,\n" +
                "            \"createTime\": null,\n" +
                "            \"updateBy\": null,\n" +
                "            \"updateTime\": null,\n" +
                "            \"remark\": null,\n" +
                "            \"params\": {},\n" +
                "            \"id\": 3,\n" +
                "            \"placeName\": \"房山区燕山燕联机动车检测有限公司\",\n" +
                "            \"remarks\": \"周一至周五:上午8:00—12:00;下午13:00—17:02\",\n" +
                "            \"address\": \"北京市房山区燕山双泉路3号\",\n" +
                "            \"phone\": \"010-69341100\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"查询成功\"\n" +
                "}";
        YyddBean yyddBean = new Gson().fromJson(json, YyddBean.class);
        yyddBeanRows = yyddBean.getRows();
        myad = new Myad();
        listview.setAdapter(myad);
        xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YyddBean.RowsDTO rowsDTO = yyddBeanRows.get(num);
                putSP("didian", new Gson().toJson(rowsDTO));
                showToast("选择成功");
                finish();
            }
        });
        news_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class Myad extends BaseAdapter {

        @Override
        public int getCount() {
            return yyddBeanRows.size();
        }

        @Override
        public Object getItem(int position) {
            return yyddBeanRows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(YyjcDdActivity.this).inflate(R.layout.item_yyjc_dd, null);
            YyddBean.RowsDTO rowsDTO = yyddBeanRows.get(position);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.name.setText(rowsDTO.getPlaceName());
            viewHolder.shijian.setText(rowsDTO.getRemarks());
            viewHolder.dizhi.setText(rowsDTO.getAddress());
            viewHolder.dianhua.setText(rowsDTO.getPhone());
            if (num == position) {
                viewHolder.xuanze.setChecked(true);
            }
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num = position;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myad.notifyDataSetChanged();
                        }
                    });
                }
            });
            viewHolder.xuanze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        num = position;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myad.notifyDataSetChanged();
                            }
                        });
                    }
                }

            });

            return convertView;
        }

        class ViewHolder {
            public View rootView;
            public TextView name;
            public TextView shijian;
            public TextView dizhi;
            public TextView fjm;
            public TextView dianhua;
            public RadioButton xuanze;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.name = (TextView) rootView.findViewById(R.id.name);
                this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                this.dizhi = (TextView) rootView.findViewById(R.id.dizhi);
                this.fjm = (TextView) rootView.findViewById(R.id.fjm);
                this.dianhua = (TextView) rootView.findViewById(R.id.dianhua);
                this.xuanze = (RadioButton) rootView.findViewById(R.id.xuanze);
            }

        }

    }

    private void initView() {
        listview = (ListViewScrollView) findViewById(R.id.listview);
        xuanze = (Button) findViewById(R.id.xuanze);
        news_base = (ImageView) findViewById(R.id.news_base);
    }
}