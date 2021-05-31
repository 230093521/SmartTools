package com.gzeic.smartcity01.zgz;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.http.PUT;

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
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zgz_jl);
        initView();
        String tdjl = getSP("tdjl");
        if (tdjl != null) {
            if (!tdjl.isEmpty()) {
                rowsDTO = new Gson().fromJson(tdjl, ZwSsBean.RowsDTO.class);
                homeTitle.setText("简历投递");
                tvSetSave.setText("提交简历");
            }
        } else {
            homeTitle.setText("编辑简历");
            tvSetSave.setText("保存");
        }
        getJl();
        tvSetSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSetSave.getText().toString().equals("保存")) {
                    xiugaijianli();
                } else {
                    toudijl();
                }
                finish();
            }
        });
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xiugaijianli() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",2);
            jsonObject.put("mostEducation","博士");
            jsonObject.put("education",etXueli.getText().toString());
            jsonObject.put("address",etJuzhu.getText().toString());
            jsonObject.put("experience",etJingyan.getText().toString());
            jsonObject.put("individualResume",etMiaosu.getText().toString());
            jsonObject.put("money",etXinzi.getText().toString());
            jsonObject.put("positionId",2);
            jsonObject.put("files","/dev-apihttp://localhost:9090/profile/upload/2021/04/22/1ac5e66a-0381-4867-9026-3ec00fff3ecf.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        getTools().sendPutRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/api/job/resume", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: "+string);
                final ZgzLsBean zgzLsBean = new Gson().fromJson(string, ZgzLsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(zgzLsBean.getMsg());
                    }
                });
            }
        });
    }

    public void getJl() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/job/resume/2", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZgzQzxxBean zgzQzxxBean = new Gson().fromJson(string, ZgzQzxxBean.class);
                if (zgzQzxxBean.getCode() == 200) {
                    zgzQzxxBeanData = zgzQzxxBean.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                etJingyan.setText(zgzQzxxBeanData.getExperience());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                etXueli.setText(zgzQzxxBeanData.getEducation());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                etJuzhu.setText(zgzQzxxBeanData.getAddress());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                switch (zgzQzxxBeanData.getPositionId()) {
                                    case 1:
                                        etQiwangzw.setText("java 开发工程师");
                                        break;
                                    case 2:
                                        etQiwangzw.setText("设计");
                                        break;
                                    case 3:
                                        etQiwangzw.setText("外教");
                                        break;
                                    case 4:
                                        etQiwangzw.setText("前端工程师");
                                        break;
                                    case 5:
                                        etQiwangzw.setText("牙医");
                                        break;
                                    case 6:
                                        etQiwangzw.setText("全栈开发工程师");
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                etXinzi.setText(zgzQzxxBeanData.getMoney());
                                etMiaosu.setText(zgzQzxxBeanData.getIndividualResume());
                                etJiaoyu.setText(zgzQzxxBeanData.getMostEducation());
                                etGongzuo.setText("无");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    private void toudijl() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("companyId", rowsDTO.getCompanyId());
            jsonObject.put("companyName", rowsDTO.getName());
            jsonObject.put("id", rowsDTO.getId());
            jsonObject.put("money", etXinzi.getText().toString());
            jsonObject.put("postId", rowsDTO.getProfessionId());
            jsonObject.put("postName", etQiwangzw.getText().toString());
            jsonObject.put("remark", " ");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            jsonObject.put("satrTime", df.format(new Date()));
            jsonObject.put("userId", getUserdata().getUserId());
            jsonObject.put("userName", getUserdata().getNickName());
        } catch (JSONException e) {

        }
        getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/job/deliver", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final ZgzLsBean zgzLsBean = new Gson().fromJson(string, ZgzLsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(zgzLsBean.getMsg());
                    }
                });
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