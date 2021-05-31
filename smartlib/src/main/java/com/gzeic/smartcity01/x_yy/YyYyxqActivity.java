package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyYpccBean;
import com.gzeic.smartcity01.bean.YyYpxxxqBean;
import com.gzeic.smartcity01.bean.YyYyxxxqBean;
import com.gzeic.smartcity01.x_yy.hualan.CardScaleHelper;
import com.gzeic.smartcity01.x_yy.hualan.HualanListener;
import com.gzeic.smartcity01.x_yy.hualan.MyAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gzeic.smartcity01.x_yy.YyYpxqActivity.yplxs;

public class YyYyxqActivity extends BaseActivity {
    public static String[] ypbflxs = {"2D","3D","IMAX","4DX"};
    private RecyclerView mRecyclerView,changcilist;
    private CardScaleHelper mCardScaleHelper;
    private Button btn_goupiao;
    private int jcurrentItemPos = 0;
    ImageView back;
    private YyYyxxxqBean.DataBean yyxx;
    private TextView yyname,dz,ypname,pf;
    LinearLayout sj;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_yyxq);
        initView();
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        id = getIntent().getIntExtra("id",-1);
        getYyxq();
    }

    private void initView() {
        btn_goupiao = findViewById(R.id.btn_goupiao);
        yyname = findViewById(R.id.yyname);
        ypname = findViewById(R.id.ypname);
        sj = findViewById(R.id.sj);
        pf = findViewById(R.id.pf);
        dz = findViewById(R.id.dz);
        changcilist = findViewById(R.id.changcilist);
        mRecyclerView = findViewById(R.id.recyclerview);
    }

    //影院详情
    private void getYyxq() {
        getTools().sendGet(HTTP_HEAD+getServerIp()+"/prod-api/api/movie/theatre/"+id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYyxxxqBean yyxx1 = gson.fromJson(string, YyYyxxxqBean.class);
                YyYyxxxqBean.DataBean data = yyxx1.getData();
                yyxx = data;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        yyname.setText(yyxx.getName());
                        dz.setText("地址："+yyxx.getAddress());
                    }
                });
                getYpcc();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }

    //影片场次
    private void getYpcc() {
        getTools().sendGet(HTTP_HEAD+getServerIp()+"/prod-api/api/movie/theatre/times/list?theaterId="+yyxx.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpccBean ypcc = gson.fromJson(string, YyYpccBean.class);
                List<YyYpccBean.RowsBean> rows = ypcc.getRows();
                getYpxxlb(rows);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }

    //影片信息列表
    private void getYpxxlb(final List<YyYpccBean.RowsBean> rows) {
        Set<Integer> ids = new HashSet();
        for (YyYpccBean.RowsBean rowsBean:rows){
            ids.add(rowsBean.getMovieId());
        }
        if (ids.size()==0){
            getYyxq();
            return;
        }
        final Integer[] idss = ids.toArray(new Integer[ids.size()-1]);
        final List<YyYpxxxqBean.DataBean> ypxxs = new ArrayList<>();
        for (int i = 0; i < idss.length; i++) {
            final int finalI = i;
            getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/movie/film/" + idss[i], getToken(), new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {

                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    String string = response.body().string();
                    Gson gson = new Gson();
                    YyYpxxxqBean ypxx = gson.fromJson(string, YyYpxxxqBean.class);
                    final YyYpxxxqBean.DataBean data = ypxx.getData();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (data==null){
                                showToast("该影院暂无影片信息");
                                return;
                            }
                            ypxxs.add(data);
                            if (ypxxs.size() == idss.length){
                                fz(rows,ypxxs,idss);
                            }
                        }
                    });
                }
            });

        }
    }

    private void fz(List<YyYpccBean.RowsBean> rows, List<YyYpxxxqBean.DataBean> ypxxs, Integer[] idss) {
        List<YyYpxxxqBean.DataBean> ypxxss = new ArrayList<>();
        for (Integer rowsBean:idss){
            for (YyYpxxxqBean.DataBean ypxx:ypxxs){
                if (ypxx==null){
                    getYyxq();
                    return;
                }else{
                    if (rowsBean==ypxx.getId()){
                        ypxxss.add(ypxx);
                    }
                }
            }
        }
        xr(rows,ypxxss,idss);
    }

    private void xr(final List<YyYpccBean.RowsBean> rows, final List<YyYpxxxqBean.DataBean> ypxxss, final Integer[] idss) {
        MyAdapter myAdapter = new MyAdapter(this, rows,idss,ypxxss);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(myAdapter);
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(jcurrentItemPos);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);
        mCardScaleHelper.setHualanListener(new HualanListener() {
            @Override
            public void wzcurrentItemPos(int currentItemPos) {
                if (jcurrentItemPos!=currentItemPos){
                    jcurrentItemPos = currentItemPos;
                    hd(rows,ypxxss,jcurrentItemPos,idss);
                }
            }
        });
        hd(rows,ypxxss,0, idss);
    }

    List<TextView> dates = new ArrayList<>();
    private void hd(final List<YyYpccBean.RowsBean> rows, final List<YyYpxxxqBean.DataBean> ypxxss, final int count, final Integer[] idss) {
        Integer idss1 = idss[count];
        for (YyYpxxxqBean.DataBean dataBean:ypxxss){
            if (dataBean.getId()==idss1){
                ypname.setText(dataBean.getName());
                pf.setText(dataBean.getScore()*2+"分");
            }
        }
        dates.clear();
        sj.removeAllViews();
        for (int i = 0; i < rows.size(); i++) {
            if (idss1==rows.get(i).getMovieId()) {
                final String playDate = rows.get(i).getPlayDate();
                final TextView inflate = (TextView) LayoutInflater.from(this).inflate(R.layout.itme_yy_gp_xz, sj, false);
                inflate.setText(playDate);
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (TextView textView:dates){
                            textView.setTextColor(Color.BLACK);
                        }
                        listxr(rows,ypxxss,count,playDate,idss);
                        inflate.setTextColor(Color.RED);
                    }
                });
                dates.add(inflate);
                sj.addView(inflate);
            }
        }
        if (dates.size()>0){
            dates.get(0).setTextColor(Color.RED);
            listxr(rows,ypxxss,count,dates.get(0).getText().toString(), idss);
        }
    }

    private void listxr(List<YyYpccBean.RowsBean> rows, final List<YyYpxxxqBean.DataBean> ypxxss, int count, String playDate, Integer[] idss) {
        final Integer idss1 = idss[count];
        final List<YyYpccBean.RowsBean> rowslist = new ArrayList<>();
        for (YyYpccBean.RowsBean rowsBean:rows){
            if (rowsBean.getMovieId()==idss1&&rowsBean.getPlayDate().equals(playDate)){
                rowslist.add(rowsBean);
            }
            Log.i("aaa",rowsBean.getMovieId()+"+++"+idss1+"+++"+rowsBean.getPlayDate()+"+++"+playDate);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        changcilist.setLayoutManager(linearLayoutManager);
        changcilist.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public YyYyxqActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_yy_xz_i, parent, false);
                ViewHolder viewHolder = new ViewHolder(inflate);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull final YyYyxqActivity.ViewHolder holder, final int position) {
                holder.kssj.setText(rowslist.get(position).getStartTime());
                holder.jssj.setText(rowslist.get(position).getEndTime());
                holder.bflx.setText(ypbflxs[Integer.valueOf(rowslist.get(position).getPlayType())-1]);
                for (YyYpxxxqBean.DataBean dataBean:ypxxss){
                    if (dataBean.getId()==idss1){
                        holder.yplx.setText(yplxs[Integer.valueOf(dataBean.getPlayType())-1]);
                        break;
                    }
                }
                holder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(holder.view.getContext(), YyXzActivity.class);
                        intent.putExtra("id",rowslist.get(position).getMovieId());
                        startActivity(intent);
                    }
                });
            }
            @Override
            public int getItemCount() {
                return rowslist.size();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView kssj,jssj,yplx,bflx,jg;
        Button btn;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            kssj = view.findViewById(R.id.kssj);
            jssj = view.findViewById(R.id.jssj);
            yplx = view.findViewById(R.id.yplx);
            bflx = view.findViewById(R.id.bflx);
            jg = view.findViewById(R.id.jg);
            btn = view.findViewById(R.id.btn);
        }
    }
}