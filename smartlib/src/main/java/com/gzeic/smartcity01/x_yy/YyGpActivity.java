package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyYpccBean;
import com.gzeic.smartcity01.bean.YyYpxxxqBean;
import com.gzeic.smartcity01.bean.YyYyxxxqBean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyGpActivity extends BaseActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd号");
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    private RelativeLayout toplan;
    private ImageView back;
    private ImageView tupian;
    private RatingBar pingfen;
    private LinearLayout shijianxuanze;
    private ListViewScrollView yingyuanliebiao;
    Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
    List<testSj> testSjList;
    List<testSj> testSjList1;
    int id;
    String add;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView name,lx,sc,sysj,pf;
    List<TextView> textViews = new ArrayList<>();
    YyYpxxxqBean.DataBean ypxx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#5F8BFA"));
        setContentView(R.layout.activity_yy_gp);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        String token = getToken();
        ApiUrl.setServerApiToken("Bearer " + token);
        initView();
        textViews.add(text1);
        textViews.add(text2);
        textViews.add(text3);
        textViews.add(text4);
        textViews.add(text5);
        textViews.add(text6);
        textViews.add(text7);
        id = getIntent().getIntExtra("id", -1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTools().sendGetToken("http://"+add+"/prod-api/api/movie/film/"+id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpxxxqBean ypxx1 = gson.fromJson(string, YyYpxxxqBean.class);
                ypxx = ypxx1.getData();
                if (ypxx!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(YyGpActivity.this).load("http://"+add+ypxx.getCover()).into(tupian);
                            name.setText(ypxx.getName());
                            lx.setText("类型："+ YyYpxqActivity.yplxs[Integer.valueOf(ypxx.getCategory())-1]);
                            sc.setText("时长："+ypxx.getDuration()+"分钟");
                            sysj.setText("上映时间："+ypxx.getPlayDate());
                            pf.setText("评分："+ypxx.getScore()*2+"分");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
        testSjList = new ArrayList<>();
        testSjList1 = new ArrayList<>();
        long time = new Date().getTime();
        for (int i = 1; i < 8; i++) {
            Date newDate = new Date(time + i * 24 * 60 * 60 * 1000);
            String format = dateFormat.format(newDate);
            final String format1 = dateFormat1.format(newDate);
            testSjList.add(new testSj(i, format));
            testSjList1.add(new testSj(i, format1));
            textViews.get(i-1).setText(format);
            final int finalI = i;
            textViews.get(i-1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (TextView textView:textViews){
                        textView.setTextColor(Color.BLACK);
                    }
                    textViews.get(finalI -1).setTextColor(Color.RED);
                    getYpcc(format1);
                }
            });
        }
        getYpcc(null);

    }

    private void getYpcc(String time) {
        Log.i("aaa", time + "++++++++++");
        getTools().sendGet("http://" + add + "/prod-api/api/movie/theatre/times/list?movieId=" + id + "&playDate=" + time).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("aaa", string);
                final Gson gson = new Gson();
                YyYpccBean ypcc = gson.fromJson(string, YyYpccBean.class);
                final List<YyYpccBean.RowsBean> rows = ypcc.getRows();
                final List<YyYyxxxqBean.DataBean> yyxxs = new ArrayList<>();
                for (int i = 0; i < rows.size(); i++) {
                    final int finalI = i;
                    YyGpActivity.this.getTools().sendGetToken("http://" + add + "/prod-api/api/movie/theatre/" + rows.get(i).getTheaterId()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String string1 = null;
                            try {
                                string1 = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            YyYyxxxqBean yyxx = gson.fromJson(string1, YyYyxxxqBean.class);
                            final YyYyxxxqBean.DataBean data = yyxx.getData();
                            if (data != null) {
                                yyxxs.add(data);
                            }
                            listxs(yyxxs, rows);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        }
                    });
                }
                listxs(yyxxs, rows);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
    public void listxs(final List<YyYyxxxqBean.DataBean> yyxxs, final List<YyYpccBean.RowsBean> rows) {
        final List<YyYyxxxqBean.DataBean> yyxxrs = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < yyxxs.size(); j++) {
                if (rows.get(i).getTheaterId()==yyxxs.get(j).getId()){
                    yyxxrs.add(yyxxs.get(j));
                }
            }
        }
        yingyuanliebiao.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView tupian;
                public TextView name;
                public TextView dizhi;
                public TextView jg;
                public LinearLayout news_ll;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.tupian = (ImageView) rootView.findViewById(R.id.tupian);
                    this.name = (TextView) rootView.findViewById(R.id.name);
                    this.dizhi = (TextView) rootView.findViewById(R.id.dizhi);
                    this.jg = rootView.findViewById(R.id.jg);
                    this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                }
            }

            @Override
            public int getCount() {
                return yyxxrs.size();
            }

            @Override
            public Object getItem(int position) {
                return yyxxrs.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view1 = LayoutInflater.from(YyGpActivity.this).inflate(R.layout.item_yy_gp, null);
                final ViewHolder viewHolder = new ViewHolder(view1);
                final YyYpccBean.RowsBean rowsBean = rows.get(position);
                viewHolder.name.setText(rowsBean.getTheatreName());
                viewHolder.jg.setText(rowsBean.getPrice() + "元");
                viewHolder.jg.setTextColor(Color.RED);
                String cover = yyxxrs.get(position).getCover();
                String replace = cover.replace("http://118.190.154.52:7777/", "/prod-api/");
                Glide.with(parent.getContext()).load("http://" + add + replace).into(viewHolder.tupian);
                viewHolder.dizhi.setText("地址："+yyxxrs.get(position).getAddress());
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(YyGpActivity.this, YyYyxqActivity.class);
                        intent.putExtra("id",yyxxrs.get(position).getId());
                        startActivity(intent);
                    }
                });
                return view1;
            }
        });
    }

    class testSj {
        int id;
        String time;

        public testSj(int id, String time) {
            this.id = id;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    private void initView() {
        toplan = (RelativeLayout) findViewById(R.id.toplan);
        back = (ImageView) findViewById(R.id.back);
        tupian = (ImageView) findViewById(R.id.tupian);
        pingfen = (RatingBar) findViewById(R.id.pingfen);
        shijianxuanze = findViewById(R.id.shijianxuanze);
        yingyuanliebiao = (ListViewScrollView) findViewById(R.id.yingyuanliebiao);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        name = findViewById(R.id.name);
        lx = findViewById(R.id.lx);
        sc = findViewById(R.id.sc);
        sysj = findViewById(R.id.sysj);
        pf = findViewById(R.id.pf);
    }
}