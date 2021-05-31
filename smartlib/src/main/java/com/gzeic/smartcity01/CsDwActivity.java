package com.gzeic.smartcity01;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.SsqsjldBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsDwActivity extends BaseActivity {
    List<String> shenlist;
    List<String> shilist;
    List<String> qulist;
    SsqsjldBean[] ssqsjldBeans;
    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private Spinner shengfen;
    private Spinner shiqu;
    private Spinner diqu;
    private ArrayAdapter<String> shenad, shiad, quad;
    private Button btnSave;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_dw);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSsqSj();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diqusd = qulist.get(diqu.getSelectedItemPosition());
                putSP("chengshi",diqusd);
                showToast("操作成功");
                finish();
            }
        });
    }

    private void getSsqSj() {
        InputStream stream = getResources().openRawResource(R.raw.city);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ssqsjldBeans = new Gson().fromJson(reader, SsqsjldBean[].class);
        shenlist = new ArrayList<>();
        shilist = new ArrayList<>();
        qulist = new ArrayList<>();
        

        shenlist = getshen(ssqsjldBeans);
        shilist = getshi(ssqsjldBeans, shenlist.get(0));
        qulist = getqu(ssqsjldBeans, shenlist.get(0), shilist.get(0));

        shenad = new ArrayAdapter<>(CsDwActivity.this, android.R.layout.simple_list_item_1, shenlist);
        shengfen.setAdapter(shenad);

        shiad = new ArrayAdapter<>(CsDwActivity.this, android.R.layout.simple_list_item_1, shilist);
        shiqu.setAdapter(shiad);

        quad = new ArrayAdapter<>(CsDwActivity.this, android.R.layout.simple_list_item_1, qulist);
        diqu.setAdapter(quad);

        shengfen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shen = shenlist.get(position);
                shilist = getshi(ssqsjldBeans, shen);
                shiad.notifyDataSetChanged();
                shiqu.setSelection(0);

                String shiqumin = shilist.get(0);
                qulist = getqu(ssqsjldBeans, shen, shiqumin);
                quad.notifyDataSetChanged();
                diqu.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shiqu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shen = shenlist.get(shengfen.getSelectedItemPosition());
                String shiqumin = shilist.get(position);
                qulist = getqu(ssqsjldBeans, shen, shiqumin);
                quad.notifyDataSetChanged();
                diqu.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private List<String> getshen(SsqsjldBean[] shen) {
        shenlist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            shenlist.add(ssqsjldBean.getProvinceName());
        }
        return shenlist;
    }

    private List<String> getshi(SsqsjldBean[] shen, String shenfen) {
        shilist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            if (ssqsjldBean.getProvinceName().equals(shenfen)) {
                for (SsqsjldBean.MallCityListDTO mallCityListDTO : ssqsjldBean.getMallCityList()) {
                    shilist.add(mallCityListDTO.getCityName());
                }
            }
        }
        return shilist;
    }

    private List<String> getqu(SsqsjldBean[] shen, String shi, String qu) {
        qulist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            if (ssqsjldBean.getProvinceName().equals(shi)) {
                for (SsqsjldBean.MallCityListDTO mallCityListDTO : ssqsjldBean.getMallCityList()) {
                    if (mallCityListDTO.getCityName().equals(qu)) {
                        for (SsqsjldBean.MallCityListDTO.MallAreaListDTO mallAreaListDTO : mallCityListDTO.getMallAreaList()) {
                            qulist.add(mallAreaListDTO.getAreaName());
                        }
                    }
                }
            }
        }
        return qulist;
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        shengfen = (Spinner) findViewById(R.id.shengfen);
        shiqu = (Spinner) findViewById(R.id.shiqu);
        diqu = (Spinner) findViewById(R.id.diqu);
        btnSave = (Button) findViewById(R.id.btn_save);
    }
}