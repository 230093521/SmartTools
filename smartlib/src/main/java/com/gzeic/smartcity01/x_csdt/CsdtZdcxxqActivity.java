package com.gzeic.smartcity01.x_csdt;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTzdxqBean;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtZdcxxqActivity extends BaseActivity {
    DTzdxqBean.DataDTO dTzdxqBeanData;
    private ImageView metroBase;
    private Banner banner;
    private TextView xlmc;
    private TextView xyzmc;
    private TextView scfcsj;
    private TextView mcfcsj;
    private TextView syzmc;
    List<String> imglist;
    LunBoBean lunBoBean;
    private TextView dqzd;
    String zhandianmc;
    private TextView zhandianmcx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_cxxq);
        String xianluid = getSP("xianluid");
        zhandianmc = getSP("zhandianmc");
        getZdxq(xianluid);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getLunbo();
    }

    private void getLunbo() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/rotation/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                lunBoBean = new Gson().fromJson(string, LunBoBean.class);
                if (lunBoBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imglist = new ArrayList<>();
                            for (LunBoBean.RowsDTO lunBoBeanRow : lunBoBean.getRows()) {
                                imglist.add("http://" + getServerIp() + lunBoBeanRow.getAdvImg());
                            }
                            banner.setImages(imglist);
                            banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object o, ImageView imageView) {
                                    Glide.with(context).load(o).into(imageView);
                                }
                            });
                            banner.start();
                        }
                    });
                }
            }
        });
    }

    private void getZdxq(String id) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/line/" + id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTzdxqBean dTzdxqBean = new Gson().fromJson(string, DTzdxqBean.class);
                if (dTzdxqBean.getCode() == 200) {
                    dTzdxqBeanData = dTzdxqBean.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            zhandianmcx.setText(dTzdxqBeanData.getName());
                            dqzd.setText("当前到站："+dTzdxqBeanData.getRunStationsName());
                            xlmc.setText(dTzdxqBeanData.getFirst() + "——" + dTzdxqBeanData.getEnd());
                            List<DTzdxqBean.DataDTO.MetroStepListDTO> metroStepList = dTzdxqBeanData.getMetroStepList();
                            DTzdxqBean.DataDTO.MetroStepListDTO syzbean = null;
                            DTzdxqBean.DataDTO.MetroStepListDTO xzybean = null;
                            for (int i = 0; i < metroStepList.size(); i++) {
                                if (metroStepList.get(i).getName().equals(dTzdxqBeanData.getRunStationsName())) {
                                    if (i == 0) {
                                        syzbean = metroStepList.get(i + 1);
                                        xzybean = metroStepList.get(i + 1);
                                    } else if (i == metroStepList.size() - 1) {
                                        syzbean = metroStepList.get(i - 1);
                                        xzybean = metroStepList.get(i - 1);
                                    } else {
                                        syzbean = metroStepList.get(i - 1);
                                        xzybean = metroStepList.get(i + 1);
                                    }
                                }
                            }
                            syzmc.setText("上一站：" + syzbean.getName());
                            xyzmc.setText("下一站：" + xzybean.getName());
                            scfcsj.setText("首车发车时间：" + dTzdxqBeanData.getStartTime());
                            mcfcsj.setText("末车发车时间：" + dTzdxqBeanData.getEndTime());
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        banner = (Banner) findViewById(R.id.banner);
        xlmc = (TextView) findViewById(R.id.xlmc);
        xyzmc = (TextView) findViewById(R.id.xyzmc);
        scfcsj = (TextView) findViewById(R.id.scfcsj);
        mcfcsj = (TextView) findViewById(R.id.mcfcsj);
        syzmc = (TextView) findViewById(R.id.syzmc);
        dqzd = (TextView) findViewById(R.id.dqzd);
        zhandianmcx = (TextView) findViewById(R.id.zhandianmcx);
    }
}