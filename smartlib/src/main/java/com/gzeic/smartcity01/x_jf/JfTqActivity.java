package com.gzeic.smartcity01.x_jf;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.CircularProgressView;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.TQqitianBean;
import com.gzeic.smartcity01.bean.TianQiBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfTqActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private TextView dqwd;
    private TextView jrQw;
    private TextView zdwd;
    private TextView tqqk;
    private TextView kqzl;
    private TextView tgwd;
    private TextView zwx;
    private CircularProgressView progress;
    private TextView shidu;
    private ListViewScrollView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_tq);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTianQi();
        getQiTian();
    }

    private void getQiTian() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/common/weather", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final TQqitianBean tQqitianBean = new Gson().fromJson(string, TQqitianBean.class);
                if (tQqitianBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TQqitianBean.DataDTO dataDTO = tQqitianBean.getData();
                            final List<TQqitianBean.DataDTO.WeatherListDTO> weatherList = dataDTO.getWeatherList();
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView shijian;
                                    public TextView tianqi;
                                    public TextView zgwd;
                                    public TextView zdwd;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                                        this.tianqi = (TextView) rootView.findViewById(R.id.tianqi);
                                        this.zgwd = (TextView) rootView.findViewById(R.id.zgwd);
                                        this.zdwd = (TextView) rootView.findViewById(R.id.zdwd);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return weatherList.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return weatherList.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(JfTqActivity.this).inflate(R.layout.item_jf_wltq, null);
                                    TQqitianBean.DataDTO.WeatherListDTO weatherListDTO = weatherList.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.shijian.setText(weatherListDTO.getDay());
                                    viewHolder.tianqi.setText("天气"+weatherListDTO.getWeather());
                                    viewHolder.zgwd.setText("最高温度"+weatherListDTO.getMinTemperature());
                                    viewHolder.zdwd.setText("最低温度"+weatherListDTO.getMaxTemperature());
                                    return view;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void getTianQi() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/common/weather/today", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final TianQiBean tianQiBean = new Gson().fromJson(string, TianQiBean.class);
                if (tianQiBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            TianQiBean.DataDTO tianQiBeanData = tianQiBean.getData();
                            dqwd.setText(tianQiBeanData.getTemperature() + "℃");
                            jrQw.setText(tianQiBeanData.getMinTemperature() + "℃");
                            zdwd.setText(tianQiBeanData.getMaxTemperature() + "℃");
                            tqqk.setText(tianQiBeanData.getWeather());
                            kqzl.setText(tianQiBeanData.getAir());
                            tgwd.setText(tianQiBeanData.getApparentTemperature() + "℃");
                            zwx.setText(tianQiBeanData.getUv());
                            progress.setProgress(Integer.valueOf(tianQiBeanData.getHumidity()));
                            shidu.setText("空气湿度：" + tianQiBeanData.getHumidity());
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        dqwd = (TextView) findViewById(R.id.dqwd);
        jrQw = (TextView) findViewById(R.id.jr_qw);
        zdwd = (TextView) findViewById(R.id.zdwd);
        tqqk = (TextView) findViewById(R.id.tqqk);
        kqzl = (TextView) findViewById(R.id.kqzl);
        tgwd = (TextView) findViewById(R.id.tgwd);
        zwx = (TextView) findViewById(R.id.zwx);
        progress = (CircularProgressView) findViewById(R.id.progress);
        shidu = (TextView) findViewById(R.id.shidu);
        list = (ListViewScrollView) findViewById(R.id.list);
    }
}