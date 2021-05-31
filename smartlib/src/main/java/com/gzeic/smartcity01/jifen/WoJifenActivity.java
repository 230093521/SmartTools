package com.gzeic.smartcity01.jifen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.JfDjBean;
import com.gzeic.smartcity01.bean.UsersBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoJifenActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private TextView shouzhiminxi;
    private TextView yonghuming;
    private TextView kyjf;
    private TextView ljjf;
    private ProgressBar dengji;
    private ImageView paihang;
    private ImageView jilulist;
    private ImageView jifensc;
    UsersBean.UserDTO userdata;
    private TextView dengji2;
    private TextView julixiayiji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_jifen);
        initView();
        userdata = getUserdata();
        yonghuming.setText("用户名：" +userdata.getNickName());
        kyjf.setText(userdata.getScore()+"");
        ljjf.setText(userdata.getScore()+"");
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shouzhiminxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoJifenActivity.this, WoJifenjlActivity.class));
            }
        });
        getJiFenDengji();
        paihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoJifenActivity.this, WoJifenphActivity.class));
            }
        });
        jilulist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoJifenActivity.this, WoJifenjlActivity.class));
            }
        });
        jifensc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WoJifenActivity.this, WoJifenhgActivity.class));
            }
        });
    }


    private void getJiFenDengji() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/score/level/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JfDjBean jfDjBean = new Gson().fromJson(string, JfDjBean.class);
                if (jfDjBean.getCode() == 200) {
                    List<JfDjBean.RowsDTO> jfDjBeanRows = jfDjBean.getRows();
                    JfDjBean.RowsDTO jfDjBeanRowss = null;
                    for (JfDjBean.RowsDTO jfDjBeanRow : jfDjBeanRows) {
                        if (jfDjBeanRow.getScore() > userdata.getScore()) {
                            jfDjBeanRowss = jfDjBeanRow;
                        }
                    }
                    final JfDjBean.RowsDTO finalJfDjBeanRowss = jfDjBeanRowss;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int score = finalJfDjBeanRowss.getScore();
                            int ssss = score - userdata.getScore();
                            dengji2.setText(finalJfDjBeanRowss.getName());
                            julixiayiji.setText("距离下一级还差"+ssss+"经验");
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        shouzhiminxi = (TextView) findViewById(R.id.shouzhiminxi);
        yonghuming = (TextView) findViewById(R.id.yonghuming);
        kyjf = (TextView) findViewById(R.id.kyjf);
        ljjf = (TextView) findViewById(R.id.ljjf);
        dengji = (ProgressBar) findViewById(R.id.dengji);
        paihang = (ImageView) findViewById(R.id.paihang);
        jilulist = (ImageView) findViewById(R.id.jilulist);
        jifensc = (ImageView) findViewById(R.id.jifensc);
        dengji2 = (TextView) findViewById(R.id.dengji2);
        julixiayiji = (TextView) findViewById(R.id.julixiayiji);
    }
}