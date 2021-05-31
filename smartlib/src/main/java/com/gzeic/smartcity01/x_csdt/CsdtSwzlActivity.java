package com.gzeic.smartcity01.x_csdt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DtSwzlBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtSwzlActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private ListView listXwzl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_swzl);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSw();
    }

    private void getSw() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/found/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    DtSwzlBean dtSwzlBean = new Gson().fromJson(string, DtSwzlBean.class);
                    if (dtSwzlBean.getCode() == 200) {
                        final List<DtSwzlBean.DataDTO> dtSwzlBeanData = dtSwzlBean.getData();
                        final List<DtSwzlBean.DataDTO.MetroFoundListDTO> metroFoundListDTOS = new ArrayList<>();
                        for (DtSwzlBean.DataDTO dtSwzlBeanDatum : dtSwzlBeanData) {
                            metroFoundListDTOS.addAll(dtSwzlBeanDatum.getMetroFoundList());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listXwzl.setAdapter(new BaseAdapter() {
                                    class ViewHolder {
                                        public View rootView;
                                        public ImageView image;
                                        public TextView fxweizhi;
                                        public TextView fxsj;
                                        public TextView lqweizhi;
                                        public LinearLayout news_ll;

                                        public ViewHolder(View rootView) {
                                            this.rootView = rootView;
                                            this.image = (ImageView) rootView.findViewById(R.id.image);
                                            this.fxweizhi = (TextView) rootView.findViewById(R.id.fxweizhi);
                                            this.fxsj = (TextView) rootView.findViewById(R.id.fxsj);
                                            this.lqweizhi = (TextView) rootView.findViewById(R.id.lqweizhi);
                                            this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                                        }

                                    }

                                    @Override
                                    public int getCount() {
                                        return metroFoundListDTOS.size();
                                    }

                                    @Override
                                    public Object getItem(int position) {
                                        return metroFoundListDTOS.get(position);
                                    }

                                    @Override
                                    public long getItemId(int position) {
                                        return position;
                                    }

                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View inflate = LayoutInflater.from(CsdtSwzlActivity.this).inflate(R.layout.item_dt_swzl, null);
                                        DtSwzlBean.DataDTO.MetroFoundListDTO metroFoundListDTO = metroFoundListDTOS.get(position);
                                        ViewHolder viewHolder = new ViewHolder(inflate);
                                        viewHolder.fxsj.setText(metroFoundListDTO.getFoundTime());
                                        viewHolder.lqweizhi.setText(metroFoundListDTO.getClaimPlace());
                                        viewHolder.fxweizhi.setText(metroFoundListDTO.getFoundPlace());
                                        Glide.with(CsdtSwzlActivity.this).load("http://"+getServerIp()+metroFoundListDTO.getImgUrl()).into(viewHolder.image);
                                        return inflate;
                                    }
                                });
                            }
                        });
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("网络错误");
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        listXwzl = (ListView) findViewById(R.id.list_xwzl);
    }
}