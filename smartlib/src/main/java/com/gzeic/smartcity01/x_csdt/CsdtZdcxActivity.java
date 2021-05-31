package com.gzeic.smartcity01.x_csdt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DiTieXLMCBean;
import com.gzeic.smartcity01.bean.DiTieZdxlxBean;
import com.gzeic.smartcity01.bean.DiTieZdxxBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtZdcxActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listXlmc;
    private ListView listDitie;
    List<DiTieXLMCBean.DataDTO> diTieXLBeanData;
    List<DiTieZdxxBean.DataDTO> diTieZdxxBeanData;
    List<DiTieZdxxBean.DataDTO.LinesDTO> linesDTOS;
    List<DiTieZdxlxBean.DataDTO> diTieXLMCBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_cx);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getZDMC(0);
        getXLLB();
    }

    private void getZDMC(final int lineId) {
        String zdurl = null;
        if (lineId==0){
            zdurl = "http://" + getServerIp() + "/prod-api/api/metro/step/list";
        }else {
            zdurl = "http://" + getServerIp() + "/prod-api/api/metro/step/list?lineId="+lineId;
        }
        getTools().sendGetRequestToken(zdurl, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                final DiTieXLMCBean diTieXLBean = new Gson().fromJson(json, DiTieXLMCBean.class);
                if (diTieXLBean.getCode() == 200) {
                    diTieXLBeanData = diTieXLBean.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listDitie.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView service_title;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.service_title = (TextView) rootView.findViewById(R.id.service_title);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return diTieXLBeanData.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return diTieXLBeanData.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = LayoutInflater.from(CsdtZdcxActivity.this).inflate(R.layout.item_fw_fl, null);
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    final DiTieXLMCBean.DataDTO dataDTO = diTieXLBeanData.get(position);
                                    viewHolder.service_title.setText(dataDTO.getName());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (lineId==0){
                                                putSP("xianluid", "31");
                                            }else {
                                                putSP("xianluid", String.valueOf(lineId));
                                            }
                                             putSP("zhandianmc",dataDTO.getName());
                                             startActivity(new Intent(CsdtZdcxActivity.this, CsdtZdcxxqActivity.class));
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
                        DiTieZdxlxBean.DataDTO dataDTO = new DiTieZdxlxBean.DataDTO();
                        dataDTO.setLineId(0);
                        dataDTO.setLineName("全部");
                        List<DiTieZdxlxBean.DataDTO> diTieXLMCBeanData2 = new ArrayList<>();
                        diTieXLMCBeanData2.add(dataDTO);
                        diTieXLMCBeanData2.addAll(diTieXLMCBeanData);
                        HashSet<DiTieZdxlxBean.DataDTO> dataDTOS = new HashSet<>(diTieXLMCBeanData2);
                        diTieXLMCBeanData2 = new ArrayList<>(dataDTOS);
                        Collections.sort(diTieXLMCBeanData2);
                        final List<DiTieZdxlxBean.DataDTO> finalDiTieXLMCBeanData = diTieXLMCBeanData2;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listXlmc.setAdapter(new BaseAdapter() {
                                    class ViewHolder {
                                        public View rootView;
                                        public TextView xlmc;

                                        public ViewHolder(View rootView) {
                                            this.rootView = rootView;
                                            this.xlmc = (TextView) rootView.findViewById(R.id.xlmc);
                                        }

                                    }

                                    @Override
                                    public int getCount() {
                                        return finalDiTieXLMCBeanData.size();
                                    }

                                    @Override
                                    public Object getItem(int position) {
                                        return finalDiTieXLMCBeanData.get(position);
                                    }

                                    @Override
                                    public long getItemId(int position) {
                                        return position;
                                    }

                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View inflate = LayoutInflater.from(CsdtZdcxActivity.this).inflate(R.layout.item_dt_xlxx, null);
                                        ViewHolder viewHolder = new ViewHolder(inflate);
                                        final DiTieZdxlxBean.DataDTO dataDTO = finalDiTieXLMCBeanData.get(position);
                                        viewHolder.xlmc.setText(dataDTO.getLineName());
                                        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                getZDMC(dataDTO.getLineId());
                                            }
                                        });
                                        return inflate;
                                    }
                                });
                            }
                        });
                    }
//                {
//                    diTieZdxxBean = new Gson().fromJson(string, DiTieZdxxBean.class);
//                    if (diTieZdxxBean.getCode() == 200) {
//                        diTieZdxxBeanData = diTieZdxxBean.getData();
//                        linesDTOS = diTieZdxxBeanData.get(0).getLines();
//                        HashSet<DiTieZdxxBean.DataDTO.LinesDTO> linesDTOHashSet = new HashSet<>(CsdtZdcxActivity.this.linesDTOS);
//                        linesDTOS = new ArrayList<>(linesDTOHashSet);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                listDitie.setAdapter(new BaseAdapter() {
//                                    class ViewHolder {
//                                        public View rootView;
//                                        public TextView xlmc;
//
//                                        public ViewHolder(View rootView) {
//                                            this.rootView = rootView;
//                                            this.xlmc = (TextView) rootView.findViewById(R.id.xlmc);
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public int getCount() {
//                                        return CsdtZdcxActivity.this.linesDTOS.size();
//                                    }
//
//                                    @Override
//                                    public Object getItem(int position) {
//                                        return CsdtZdcxActivity.this.linesDTOS.get(position);
//                                    }
//
//                                    @Override
//                                    public long getItemId(int position) {
//                                        return position;
//                                    }
//
//                                    @Override
//                                    public View getView(int position, View convertView, ViewGroup parent) {
//                                        View inflate = LayoutInflater.from(CsdtZdcxActivity.this).inflate(R.layout.item_dt_xlxx, null);
//                                        ViewHolder viewHolder = new ViewHolder(inflate);
//                                        final DiTieZdxxBean.DataDTO.LinesDTO linesDTO = CsdtZdcxActivity.this.linesDTOS.get(position);
//                                        viewHolder.xlmc.setText(linesDTO.getLineName());
//                                        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                putSP("zhandianxq", String.valueOf(linesDTO.getLineId()));
//                                                startActivity(new Intent(CsdtZdcxActivity.this, CsdtZdcxxqActivity.class));
//                                            }
//                                        });
//                                        return inflate;
//                                    }
//                                });
//                            }
//                        });
//                    }
//                }

            }
        });
    }



    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listXlmc = (ListView) findViewById(R.id.list_xlmc);
        listDitie = (ListView) findViewById(R.id.list_ditie);
    }
}