package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xsonline.smartlib.R;

import java.util.ArrayList;
import java.util.List;

public class YangLaoXjActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView xunjianlist;
    private List<Beans> beansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_yang_lao_xj);
        initView();
        beansList = new ArrayList<>();
        beansList.add(new Beans("张三", "2020-12-25 12:30", "2020-12-25 14:30", "001"));
        beansList.add(new Beans("李四", "2020-12-26 12:30", "2020-12-26 14:30", "002"));
        beansList.add(new Beans("王二", "2020-12-27 12:30", "2020-12-27 14:30", "002"));
        beansList.add(new Beans("王二", "2020-12-27 12:30", "2020-12-27 14:30", "002"));
        beansList.add(new Beans("王二", "2020-12-27 12:30", "2020-12-27 14:30", "002"));
        beansList.add(new Beans("麻子", "2020-12-28 12:30", "2020-12-28 14:30", "003"));
        beansList.add(new Beans("麻子", "2020-12-28 12:30", "2020-12-28 14:30", "003"));
        beansList.add(new Beans("麻子", "2020-12-28 12:30", "2020-12-28 14:30", "003"));
        xunjianlist.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public TextView name;
                public TextView kaishi;
                public TextView jiesu;
                public TextView fjm;
                public TextView fangmin;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.name = (TextView) rootView.findViewById(R.id.name);
                    this.kaishi = (TextView) rootView.findViewById(R.id.kaishi);
                    this.jiesu = (TextView) rootView.findViewById(R.id.jiesu);
                    this.fjm = (TextView) rootView.findViewById(R.id.fjm);
                    this.fangmin = (TextView) rootView.findViewById(R.id.fangmin);
                }

            }

            @Override
            public int getCount() {
                return beansList.size();
            }

            @Override
            public Object getItem(int i) {
                return beansList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_yanglao, null);
                ViewHolder viewHolder = new ViewHolder(view);
                Beans beans = beansList.get(i);
                viewHolder.name.setText(beans.getName());
                viewHolder.kaishi.setText(beans.getTime1());
                viewHolder.jiesu.setText(beans.getTime2());
                viewHolder.fangmin.setText(beans.getFangmin());
                return view;
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        xunjianlist = (ListView) findViewById(R.id.xunjianlist);
    }

    class Beans {
        String name;
        String time1;
        String time2;
        String fangmin;

        public Beans(String name, String time1, String time2, String fangmin) {
            this.name = name;
            this.time1 = time1;
            this.time2 = time2;
            this.fangmin = fangmin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime1() {
            return time1;
        }

        public void setTime1(String time1) {
            this.time1 = time1;
        }

        public String getTime2() {
            return time2;
        }

        public void setTime2(String time2) {
            this.time2 = time2;
        }

        public String getFangmin() {
            return fangmin;
        }

        public void setFangmin(String fangmin) {
            this.fangmin = fangmin;
        }
    }
}