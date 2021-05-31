package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;
import com.gzeic.smartcity01.bean.WzokBean;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzJszbdActivity extends BaseActivity {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ImageView metroBase;
    private TextView homeTitle;
    private EditText jszh;
    private EditText jszyxq;
    private EditText sfzh;
    private Button bangding;
    private String http = "http://";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_jszbd);
        initView();
    }

    private void initView() {
        metroBase = findViewById(R.id.metro_base);
        homeTitle = findViewById(R.id.home_title);
        jszh = findViewById(R.id.jszh);
        jszyxq = findViewById(R.id.jszyxq);
        sfzh = findViewById(R.id.sfzh);
        bangding = findViewById(R.id.bangding);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bangding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUrl.setServerApiToken(getToken());
                String jszhsj = jszh.getText().toString();
                String jszyxqsj = jszyxq.getText().toString();
                final String sfzhsj = sfzh.getText().toString();
                if (jszhsj!=null&&jszyxqsj!=null&&sfzhsj!=null){
                    HashMap hashMap = new HashMap();
                    hashMap.put("applyDate",simpleDateFormat.format(new Date()));
                    hashMap.put("auditOffice","交警队");
                    hashMap.put("contact","13800000000");
                    hashMap.put("fileNo","12312312312");
                    hashMap.put("licenseLevel","C1");
                    hashMap.put("idCard",sfzhsj);
                    hashMap.put("licenseNo",jszhsj);
                    hashMap.put("timeout","否");
                    hashMap.put("verifyDate ",jszyxqsj);
                    JSONObject jsonObject = new JSONObject(hashMap);
                    getTools().sendPostRequestToken(jsonObject, http + getServerIp() + "/prod-api/api/traffic/license", getToken(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            WzokBean ok = gson.fromJson(string, WzokBean.class);
                            if (ok.getCode()==200){
                                Toast.makeText(WzJszbdActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(WzJszbdActivity.this, ok.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(WzJszbdActivity.this, "所有字段不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}