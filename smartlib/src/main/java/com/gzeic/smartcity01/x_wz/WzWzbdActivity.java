package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;
import com.gzeic.smartcity01.bean.WzokBean;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WzWzbdActivity extends BaseActivity {
    String[] hptyle = {"小型汽车","大型新能源汽车","大型汽车","小型新能源汽车","领馆汽车","警用汽车"};
    String[] paizileityle = {"京","贵","辽","上","重"};
    private String http = "http://";
    private Spinner spinner;
    private Spinner paizilei;
    private EditText paihao;
    private Button chaxunbtn;
    private ImageView metro_base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_wzbd);
        spinner = findViewById(R.id.spinner);
        paizilei = findViewById(R.id.paizilei);
        paihao = findViewById(R.id.paihao);
        chaxunbtn = findViewById(R.id.chaxunbtn);
        metro_base = findViewById(R.id.metro_base);
        metro_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hptyle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paizileityle);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paizilei.setAdapter(adapter1);
        chaxunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = paihao.getText().toString();
                if (s==null){
                    Toast.makeText(WzWzbdActivity.this, "车牌号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    ApiUrl.setServerApiToken(getToken());
                    String s1 = paizileityle[paizilei.getSelectedItemPosition()];
                    Log.i("aaa",s1);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("engineNo","1212XS123113");
                    hashMap.put("plateNo",s1+" "+s);
                    hashMap.put("type",hptyle[spinner.getSelectedItemPosition()]);
                    getTools().sendPostJsonToken(http+getServerIp()+"/prod-api/api/traffic/car",hashMap).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String string = null;
                            try {
                                string = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Gson gson = new Gson();
                            final WzokBean ok = gson.fromJson(string, WzokBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (ok.getCode()==200){
                                        Toast.makeText(WzWzbdActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(WzWzbdActivity.this, "绑定失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                        }
                    });
                }
            }
        });
    }
}