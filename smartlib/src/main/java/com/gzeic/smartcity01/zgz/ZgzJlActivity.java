package com.gzeic.smartcity01.zgz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZgzLsBean;
import com.gzeic.smartcity01.bean.ZgzQzxxBean;
import com.gzeic.smartcity01.bean.ZwSsBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZgzJlActivity extends BaseActivity {

    private ImageView orderBase;
    private TextView homeTitle;
    private EditText etJingyan;
    private EditText etXueli;
    private EditText etJuzhu;
    private EditText etQiwangzw;
    private EditText etXinzi;
    private EditText etMiaosu;
    private EditText etJiaoyu;
    private EditText etGongzuo;
    private TextView tvSetSave;
    ZwSsBean.RowsDTO rowsDTO;
    ZgzQzxxBean.DataDTO zgzQzxxBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zgz_jl);
        initView();
        String tdjl = getSP("tdjl");
        if (tdjl!=null){
            rowsDTO = new Gson().fromJson(tdjl, ZwSsBean.RowsDTO.class);
            homeTitle.setText("简历投递");
            tvSetSave.setText("提交简历");
        }
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/resume/1", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZgzQzxxBean zgzQzxxBean = new Gson().fromJson(string, ZgzQzxxBean.class);
                if (zgzQzxxBean.getCode()==200){
                    zgzQzxxBeanData = zgzQzxxBean.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etJingyan.setText(zgzQzxxBeanData.getExperience());
                            etXueli.setText(zgzQzxxBeanData.getEducation());
                            etJuzhu.setText(zgzQzxxBeanData.getAddress());
                            switch (zgzQzxxBeanData.getPositionId()){
                                case "1":
                                    etQiwangzw.setText("java 开发工程师");
                                    break;
                                case "2":
                                    etQiwangzw.setText("设计");
                                    break;
                                case "3":
                                    etQiwangzw.setText("外教");
                                    break;
                                case "4":
                                    etQiwangzw.setText("前端工程师");
                                    break;
                                case "5":
                                    etQiwangzw.setText("牙医");
                                    break;
                                case "6":
                                    etQiwangzw.setText("全栈开发工程师");
                                    break;
                            }
                            etXinzi.setText(zgzQzxxBeanData.getMoney());
                            etMiaosu.setText(zgzQzxxBeanData.getIndividualResume());
                            etJiaoyu.setText(zgzQzxxBeanData.getMostEducation());
                            etGongzuo.setText("无");
                        }
                    });
                }
            }
        });
        tvSetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSetSave.getText().toString().equals("保存")){
                    showToast("修改成功");
                }else {
                    showToast("投递成功");
                    ZgzLsBean.RowsDTO rowsDTO = new ZgzLsBean.RowsDTO();
                    rowsDTO.setId(1);
                    rowsDTO.setUserId(2);
                    rowsDTO.setPostName(rowsDTO.getPostName());
                    rowsDTO.setCompanyName(rowsDTO.getCompanyName());
                    rowsDTO.setMoney(rowsDTO.getMoney());
                    rowsDTO.setSatrTime(rowsDTO.getSatrTime());
                    putSP("jlls",new Gson().toJson(rowsDTO));
                }
            }
        });

        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        etJingyan = (EditText) findViewById(R.id.et_jingyan);
        etXueli = (EditText) findViewById(R.id.et_xueli);
        etJuzhu = (EditText) findViewById(R.id.et_juzhu);
        etQiwangzw = (EditText) findViewById(R.id.et_qiwangzw);
        etXinzi = (EditText) findViewById(R.id.et_xinzi);
        etMiaosu = (EditText) findViewById(R.id.et_miaosu);
        etJiaoyu = (EditText) findViewById(R.id.et_jiaoyu);
        etGongzuo = (EditText) findViewById(R.id.et_gongzuo);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
    }
}