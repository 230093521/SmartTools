package com.gzeic.smartcity01;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTccxzBean;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoMzsmActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_mzsm);
        initView();
        getCcxz();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getCcxz() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/statement?type=3", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTccxzBean dTccxzBean = new Gson().fromJson(string, DTccxzBean.class);
                if (dTccxzBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DTccxzBean.DataDTO data = dTccxzBean.getData();
                            content.setText(Html.fromHtml(data.getContent()));
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        content = (TextView) findViewById(R.id.content);
    }
}