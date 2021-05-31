package com.gzeic.smartcity01.zgz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZgzGsBean;
import com.gzeic.smartcity01.bean.ZwSsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZgzXqActivity extends BaseActivity {

    private ImageView newsBase;
    private TextView zwmc;
    private TextView gwzz;
    private TextView gsdd;
    private TextView xzdy;
    private TextView lxr;
    private TextView zwms;
    private TextView gznx;
    private TextView gsxx;
    private TextView gsjj;
    private Button btnToudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zgz_xq);
        initView();
        final String zwdata = getSP("zwdata");
        ZwSsBean.RowsDTO rowsDTO = new Gson().fromJson(zwdata, ZwSsBean.RowsDTO.class);
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zwmc.setText(rowsDTO.getName());
        gwzz.setText(rowsDTO.getObligation());
        gsdd.setText(rowsDTO.getAddress());
        xzdy.setText(rowsDTO.getSalary());
        lxr.setText(rowsDTO.getContacts());
        zwms.setText(rowsDTO.getObligation());
        gznx.setText(rowsDTO.getNeed());

//        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/job/post/" + rowsDTO.getCompanyId(), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String string = response.body().string();
//                ZgzGsBean zgzGsBean = new Gson().fromJson(string, ZgzGsBean.class);
//                if (zgzGsBean.getCode() == 200) {
//                    final ZgzGsBean.DataDTO data = zgzGsBean.getData();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            gsxx.setText(data.);
//                            gsjj.setText(data.getIntroductory());
//                        }
//                    });
//                }
//            }
//        });

        btnToudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("userId","1");
//                    jsonObject.put("postName","1");
//                    jsonObject.put("companyName","1");
//                    jsonObject.put("money","1");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                getTools().sendPostRequestToken();
                putSP("tdjl",zwdata);
                startActivity(new Intent(ZgzXqActivity.this,ZgzJlActivity.class));
            }
        });

    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        zwmc = (TextView) findViewById(R.id.zwmc);
        gwzz = (TextView) findViewById(R.id.gwzz);
        gsdd = (TextView) findViewById(R.id.gsdd);
        xzdy = (TextView) findViewById(R.id.xzdy);
        lxr = (TextView) findViewById(R.id.lxr);
        zwms = (TextView) findViewById(R.id.zwms);
        gznx = (TextView) findViewById(R.id.gznx);
        gsxx = (TextView) findViewById(R.id.gsxx);
        gsjj = (TextView) findViewById(R.id.gsjj);
        btnToudi = (Button) findViewById(R.id.btn_toudi);
    }
}