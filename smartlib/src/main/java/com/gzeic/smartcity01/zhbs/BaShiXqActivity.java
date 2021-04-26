package com.gzeic.smartcity01.zhbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.BaShiXianLuBean;
import com.gzeic.smartcity01.bean.BashiBean;
import com.gzeic.smartcity01.bean.BashiXqBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BaShiXqActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView xl1;
    private TextView xl2;
    private TextView qian;
    private TextView gongli;
    private RecyclerView recBashiXianlu;
    private TextView btnNext;
    BashiBean.RowsBean rowsBean;
    List<BaShiXianLuBean.RowsBean> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_ba_shi_xq);
        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,true);
        recBashiXianlu.setLayoutManager(linearLayoutManager);
        String xianlu = getSP("xianlu");
        BashiBean.RowsBean rowsBean = new Gson().fromJson(xianlu, BashiBean.RowsBean.class);
        getTools().sendGetRequest("http://"+getServerIp()+"/userinfo/lines/"+rowsBean.getId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                BashiXqBean bashiXqBean = new Gson().fromJson(json, BashiXqBean.class);
                if (bashiXqBean.getCode()==200){
                    final BashiXqBean.DataBean data = bashiXqBean.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            qian.setText("￥"+data.getPrice());
                            gongli.setText("全程"+data.getMileage()+"公里");
                            xl1.setText(data.getFirst());
                            xl2.setText(data.getEnd());
                        }
                    });
                }
            }
        });

        getTools().sendGetRequest("http://"+getServerIp()+"/userinfo/busStop/list?pageNum=1&pageSize=10&linesId="+rowsBean.getId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                BaShiXianLuBean baShiXianLuBean = new Gson().fromJson(string, BaShiXianLuBean.class);
                if (baShiXianLuBean.getCode()==200){
                    rows = baShiXianLuBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recBashiXianlu.setAdapter(new RecAdapter());
                        }
                    });

                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaShiXqActivity.this, BaShiDinDanActivity.class));
            }
        });

    }

    class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{

        @NonNull
        @Override
        public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecAdapter.ViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_ditiexianlu,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecAdapter.ViewHolder holder, int position) {
            holder.textView.setText(rows.get(position).getName());
            if (rows.size()/2==position){
                holder.imageView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return rows.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            CardView imageView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.ditiexianlu_name);
                imageView = itemView.findViewById(R.id.xianlu_biaoji);
            }
        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        xl1 = (TextView) findViewById(R.id.xl1);
        xl2 = (TextView) findViewById(R.id.xl2);
        qian = (TextView) findViewById(R.id.qian);
        gongli = (TextView) findViewById(R.id.gongli);
        recBashiXianlu = (RecyclerView) findViewById(R.id.rec_bashi_xianlu);
        btnNext = (TextView) findViewById(R.id.btn_next);
    }
}