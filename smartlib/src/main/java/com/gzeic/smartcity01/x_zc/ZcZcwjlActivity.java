package com.gzeic.smartcity01.x_zc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.TinCheJiLuBean;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcZcwjlActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView riqi1;
    private TextView shijian1;
    private TextView riqi2;
    private TextView shijian2;
    private RelativeLayout sousuo;
    private ListView listTcjl;
    private TextView chakangengduo;
    List<TinCheJiLuBean.RowsDTO> rows;
    Calendar calendar = Calendar.getInstance();
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_tcjl);
        initView();
        num = 1;
        initTinche();
        chakangengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTinche();
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        riqi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showriqi1();
            }
        });

        riqi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showriqi2();
            }
        });

        shijian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showshijian1();
            }
        });

        shijian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showshijian2();
            }
        });

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (riqi1.getText().toString().equals("日期")){
                    showToast("请选择入场日期");
                    return;
                }
                if (riqi2.getText().toString().equals("日期")){
                    showToast("请选择出场日期");
                    return;
                }
                if (shijian1.getText().toString().equals("时间")){
                    showToast("请选择入场时间");
                    return;
                }
                if (shijian2.getText().toString().equals("时间")){
                    showToast("请选择出场时间");
                    return;
                }
                initTinchejl(riqi1.getText().toString() + " " + shijian1.getText().toString(), riqi2.getText().toString() + " " + shijian2.getText().toString());
            }
        });
    }


    private void showshijian1() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(ZcZcwjlActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                shijian1.setText(i + ":" + i1);
            }
        }, calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true);
        timePickerDialog.show();
    }

    private void showshijian2() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(ZcZcwjlActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                shijian2.setText(i + ":" + i1);
            }
        }, calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), true);
        timePickerDialog.show();
    }

    public void showriqi1() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(ZcZcwjlActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                riqi1.setText(i + "-" + i1 + "-" + i2);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showriqi2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ZcZcwjlActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                riqi2.setText(i + "-" + i1 + "-" + i2);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void initTinchejl(String ruchang, String chuchang) {
        Log.i(TAG, "initTinchejl: "+ruchang+" "+chuchang);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/lot/record/list?entryTime="+ruchang+"&outTime="+chuchang, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                final TinCheJiLuBean tinCheJiLuBean = new Gson().fromJson(json, TinCheJiLuBean.class);
                if (tinCheJiLuBean.getCode() == 200) {
                    if (tinCheJiLuBean.getRows().size() == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("没有找到此时段的停车记录");
                            }
                        });
                        return;
                    }
                    if (num == 1) {
                        rows = tinCheJiLuBean.getRows();
                    } else {
                        rows.addAll(tinCheJiLuBean.getRows());
                    }
                    num += 1;
                    initChejl();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(tinCheJiLuBean.getMsg());
                    }
                });
            }
        });
    }

    private void initTinche() {
        getTools().sendGetRequest("http://" + getServerIp() + "/prod-api/api/park/lot/record/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                TinCheJiLuBean tinCheJiLuBean = new Gson().fromJson(json, TinCheJiLuBean.class);
                if (tinCheJiLuBean.getCode() == 200) {
                    if (num == 1) {
                        rows = tinCheJiLuBean.getRows();
                    } else {
                        rows.addAll(tinCheJiLuBean.getRows());
                    }
                    int total = tinCheJiLuBean.getTotal();
                    for (int i = 0; i < total - 6; i++) {
                        rows.remove(0);
                    }
                    num += 1;
                    initChejl();
                }
            }
        });
    }

    private void initChejl() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listTcjl.setAdapter(new BaseAdapter() {
                    class ViewHolder {
                        public View rootView;
                        public TextView chepai;
                        public TextView shoufeijine;
                        public TextView ruchang;
                        public TextView chuchang;
                        public TextView tinchechang;

                        public ViewHolder(View rootView) {
                            this.rootView = rootView;
                            this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                            this.shoufeijine = (TextView) rootView.findViewById(R.id.shoufeijine);
                            this.ruchang = (TextView) rootView.findViewById(R.id.ruchang);
                            this.chuchang = (TextView) rootView.findViewById(R.id.chuchang);
                            this.tinchechang = (TextView) rootView.findViewById(R.id.yonghuming);
                        }

                    }

                    @Override
                    public int getCount() {
                        return rows.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return rows.get(i);
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tc_jl, null);
                        ViewHolder viewHolder = new ViewHolder(view);
                        TinCheJiLuBean.RowsDTO rowsBean = rows.get(i);
                        viewHolder.chepai.setText(rowsBean.getPlateNumber());
                        viewHolder.shoufeijine.setText("收费金额:" + rowsBean.getMonetary());
                        viewHolder.tinchechang.setText(rowsBean.getParkName());
                        viewHolder.ruchang.setText(rowsBean.getEntryTime());
                        viewHolder.chuchang.setText(rowsBean.getOutTime());
                        return view;
                    }
                });
            }
        });
    }


    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        riqi1 = (TextView) findViewById(R.id.riqi1);
        shijian1 = (TextView) findViewById(R.id.shijian1);
        riqi2 = (TextView) findViewById(R.id.riqi2);
        shijian2 = (TextView) findViewById(R.id.shijian2);
        sousuo = (RelativeLayout) findViewById(R.id.sousuo);
        listTcjl = (ListView) findViewById(R.id.list_tcjl);
        chakangengduo = (TextView) findViewById(R.id.chakangengduo);
    }
}