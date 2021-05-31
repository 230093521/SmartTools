package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzclxqlbBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WzWzclActivity extends BaseActivity {
    //    static String[] wczt = new String[]{"已缴款","已处理未缴款","未处理","撤销申请中","已撤销"};
    private String http = "http://";
    private ListView list;
    private TextView cph;
    private TextView wclzs;
    private TextView wcl;
    private TextView ycl;
    private TextView yjk;
    private BaseAdapter baseAdapter;
    List<WzclxqlbBean.RowsBean> rowsxs = new ArrayList<>();
    List<WzclxqlbBean.RowsBean> rowszs = new ArrayList<>();
    private ImageView metroBase;
    private TextView bajdc;
    com.gzeic.smartcity01.bean.WzcllbBean.RowsDTO rowsDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_wzcl);
        initView();
        list = findViewById(R.id.list);
        cph = findViewById(R.id.cph);
        wclzs = findViewById(R.id.wclzs);
        wcl = findViewById(R.id.wcl);
        ycl = findViewById(R.id.ycl);
        yjk = findViewById(R.id.yjk);
        String clxx = getSP("clxx");
        rowsDTO = new Gson().fromJson(clxx, com.gzeic.smartcity01.bean.WzcllbBean.RowsDTO.class);
        cph.setText(rowsDTO.getPlateNo());
        hqwccl(rowsDTO.getPlateNo());
        bajdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WzWzclActivity.this, WzWzbdActivity.class);
                startActivity(intent);
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return rowsxs.size();
            }

            @Override
            public Object getItem(int position) {
                return rowsxs.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_wz_cl, parent, false);
                TextView lzlx = inflate.findViewById(R.id.lzlx);
                TextView fsrq = inflate.findViewById(R.id.fsrq);
                TextView fsdd = inflate.findViewById(R.id.fsdd);
                TextView cfdw = inflate.findViewById(R.id.cfdw);
                final Button clzt = inflate.findViewById(R.id.clzt);
                lzlx.setText(rowsxs.get(position).getTrafficOffence());
                fsrq.setText(rowsxs.get(position).getBadTime());
                fsdd.setText(rowsxs.get(position).getIllegalSites());
                cfdw.setText(rowsxs.get(position).getPerformOffice());
                if (rowsxs.get(position).getDisposeState().equals("未处理")) {
                    clzt.setText("接受处理");
                } else if (rowsxs.get(position).getDisposeState().equals("已缴款")) {
                    clzt.setText("已交款");
                } else if (rowsxs.get(position).getDisposeState().equals("已处理未缴款")) {
                    clzt.setText("已处理未交款");
                }
                clzt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (clzt.getText().toString().equals("接受处理")) {
                            clzt.setText("已处理未交款");
                        } else if (clzt.getText().toString().equals("已处理未交款")) {
                            Intent intent = new Intent(WzWzclActivity.this, WzWfkActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                return inflate;
            }
        };
        list.setAdapter(baseAdapter);
    }




    private void hqwccl(String cph) {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/list?licencePlate=" + cph, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                WzclxqlbBean wzcllb = gson.fromJson(string, WzclxqlbBean.class);
                final List<WzclxqlbBean.RowsBean> rows = wzcllb.getRows();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (rows.size() == 0) {
                            Toast.makeText(WzWzclActivity.this, "暂无违章信息", Toast.LENGTH_LONG).show();
                            wclzs.setText("0");
                        } else {
                            xswc(rows);
                        }
                    }
                });
            }
        });
    }

    private void xswc(final List<WzclxqlbBean.RowsBean> rowszs) {
        this.rowszs = rowszs;
        for (WzclxqlbBean.RowsBean rowsBean : rowszs) {
            if (rowsBean.getDisposeState().equals("未处理") || rowsBean.getDisposeState().equals("已处理未缴款")) {
                rowsxs.add(rowsBean);
            }
        }
        wclzs.setText(rowsxs.size() + "");
        baseAdapter.notifyDataSetChanged();
        wcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzclxqlbBean.RowsBean rowsBean : rowszs) {
                    if (rowsBean.getDisposeState().equals("未处理") || rowsBean.getDisposeState().equals("已处理未缴款")) {
                        rowsxs.add(rowsBean);
                    }
                }
                wcl.setTextColor(getColor(R.color.colorPrimary));
                ycl.setTextColor(Color.BLACK);
                yjk.setTextColor(Color.BLACK);
                baseAdapter.notifyDataSetChanged();
            }
        });
        ycl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzclxqlbBean.RowsBean rowsBean : rowszs) {
                    if (rowsBean.getDisposeState().equals("已处理")) {
                        rowsxs.add(rowsBean);
                    }
                }
                wcl.setTextColor(Color.BLACK);
                ycl.setTextColor(getColor(R.color.colorPrimary));
                yjk.setTextColor(Color.BLACK);
                baseAdapter.notifyDataSetChanged();
            }
        });
        yjk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzclxqlbBean.RowsBean rowsBean : rowszs) {
                    if (rowsBean.getDisposeState().equals("已缴款")) {
                        rowsxs.add(rowsBean);
                    }
                }
                wcl.setTextColor(Color.BLACK);
                ycl.setTextColor(Color.BLACK);
                yjk.setTextColor(getColor(R.color.colorPrimary));
                baseAdapter.notifyDataSetChanged();
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bajdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    boolean dyc = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (dyc) {
            dyc = false;
        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        bajdc = (TextView) findViewById(R.id.bajdc);
    }
}