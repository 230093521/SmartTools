package com.gzeic.smartcity01.x_csdt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DiTieZdxlxBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtXlxzActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listDitie;
    List<DiTieZdxlxBean.DataDTO> diTieXLMCBeanData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_xlxz);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getXLLB();
    }

    private void getXLLB() {
        String url = "http://" + getServerIp() + "/prod-api/api/metro/line/list";

        getTools().sendGetRequestToken(url, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: " + string);
                DiTieZdxlxBean diTieXLMCBean = null;
                diTieXLMCBean = new Gson().fromJson(string, DiTieZdxlxBean.class);
                if (diTieXLMCBean.getCode() == 200) {
                    diTieXLMCBeanData = diTieXLMCBean.getData();
                    HashSet<DiTieZdxlxBean.DataDTO> dataDTOS = new HashSet<>(diTieXLMCBeanData);
                    diTieXLMCBeanData = new ArrayList<>(dataDTOS);
                    Collections.sort(diTieXLMCBeanData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listDitie.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView xlmc;
                                    public TextView xlid;
                                    public Button btn_xuanze;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.xlmc = (TextView) rootView.findViewById(R.id.xlmc);
                                        this.xlid = (TextView) rootView.findViewById(R.id.xlid);
                                        this.btn_xuanze = (Button) rootView.findViewById(R.id.btn_xuanze);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return diTieXLMCBeanData.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return diTieXLMCBeanData.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = LayoutInflater.from(CsdtXlxzActivity.this).inflate(R.layout.item_dt_xlxz, null);
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    final DiTieZdxlxBean.DataDTO dataDTO = diTieXLMCBeanData.get(position);
                                    viewHolder.xlid.setText("ID:"+dataDTO.getLineId());
                                    viewHolder.xlmc.setText(dataDTO.getLineName());

                                    viewHolder.btn_xuanze.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            showToast("已选择"+dataDTO.getLineName());
                                            putSP("xuanzexianlu", new Gson().toJson(dataDTO));
                                            finish();
                                        }
                                    });
                                    return inflate;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listDitie = (ListView) findViewById(R.id.list_ditie);
    }
}