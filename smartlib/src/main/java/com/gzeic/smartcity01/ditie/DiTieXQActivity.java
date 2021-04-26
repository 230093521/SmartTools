package com.gzeic.smartcity01.ditie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.gzeic.smartcity01.bean.DiTieBean;
import com.gzeic.smartcity01.bean.DiTieXQBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiTieXQActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView xianluname1;
    private TextView xianluname2;
    private TextView ditieTime;
    private TextView ditiezhanshu;
    private RecyclerView recDitieXianlu;
    private List<DiTieXQBean.DataBean.MetroStepsListBean> metroStepsList;
    DiTieBean.RowsBean rowsBean;
    DiTieXQBean diTieXQBean;
    DiTieXQBean.DataBean data;
    String stationsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_di_tie_x_q);
        initView();
        String ditiejson = getSP("ditie");
        rowsBean = new Gson().fromJson(ditiejson, DiTieBean.RowsBean.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,true);
        recDitieXianlu.setLayoutManager(linearLayoutManager);

        getTools().sendGetRequest("http://"+getServerIp()+"/metro/"+rowsBean.getLineId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "sendGetRequestOnResponse: "+json);
                diTieXQBean = new Gson().fromJson(json, DiTieXQBean.class);
                data = diTieXQBean.getData();
                if (diTieXQBean.getCode()==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String lineName = rowsBean.getLineName();
                            int i = lineName.indexOf("-");
                            xianluname1.setText(String.valueOf(lineName.charAt(i-3))+String.valueOf(lineName.charAt(i-2))+String.valueOf(lineName.charAt(i-1)));
                            xianluname2.setText(String.valueOf(lineName.charAt(i+1))+String.valueOf(lineName.charAt(i+2))+String.valueOf(lineName.charAt(i+3)));
                            ditieTime.setText(rowsBean.getReachTime()+"分钟");
                            stationsNumber = String.valueOf(data.getStationsNumber());
                            String[] split = stationsNumber.split("\\.");
                            int km = data.getKm();
                            if (split[0]==null){
                                ditiezhanshu.setText("0站/"+km+"km");
                            }
                            ditiezhanshu.setText(split[0]+"站/"+km+"km");
                            metroStepsList = data.getMetroStepsList();
                            recDitieXianlu.setAdapter(new RecAdapter());
                            recDitieXianlu.scrollToPosition(metroStepsList.size()/2);
                        }
                    });
                }

            }
        });

    }

    class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_ditiexianlu,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String name = metroStepsList.get(position).getName();
            holder.textView.setText(name);
            String[] split = stationsNumber.split("\\.");
            int integer = Integer.parseInt(split[0]);
            Log.e(TAG, "onBindViewHolder: "+integer +" "+metroStepsList.get(position).getId());

            if (metroStepsList.get(position).getId()==metroStepsList.get(metroStepsList.size()/2).getId()){
                holder.imageView.setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(DiTieXQActivity.this, DiTieXianLuTuActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return metroStepsList.size();
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
        xianluname1 = (TextView) findViewById(R.id.xianluname1);
        xianluname2 = (TextView) findViewById(R.id.xianluname2);
        ditieTime = (TextView) findViewById(R.id.ditie_time);
        ditiezhanshu = (TextView) findViewById(R.id.ditiezhanshu);
        recDitieXianlu = (RecyclerView) findViewById(R.id.rec_ditie_xianlu);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}