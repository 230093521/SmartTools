package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyXwBean;
import com.gzeic.smartcity01.xw.XinWenActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyXwActivity extends BaseActivity {
    private ImageView back;
    private ListView listview;
    BaseAdapter baseAdapter;
    List<YyXwBean.RowsBean> rows = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_xw);
        initView();
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return rows.size();
            }

            @Override
            public Object getItem(int position) {
                return rows.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_yy_xw_list, parent, false);
                ImageView img = inflate.findViewById(R.id.img);
                TextView zbt = inflate.findViewById(R.id.zbt);
                TextView fbt = inflate.findViewById(R.id.fbt);
                TextView fbsj = inflate.findViewById(R.id.fbsj);
                TextView dzs = inflate.findViewById(R.id.dzs);
                Glide.with(parent.getContext()).load("http://"+getServerIp()+rows.get(position).getCover()).into(img);
                zbt.setText(rows.get(position).getTitle());
                fbt.setText(Html.fromHtml(rows.get(position).getContent()));
                Object subTitle = rows.get(position).getSubTitle();
                if (subTitle!=null) {
                    fbt.setText(subTitle.toString());
                }
                fbsj.setText(rows.get(position).getPublishDate());
                dzs.setText(rows.get(position).getLikeNum()+"人点赞");
                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        putSP("xwzx",new Gson().toJson(rows.get(position)));
                        Intent intent = new Intent(YyXwActivity.this, XinWenActivity.class);
//                        Gson gson = new Gson();
//                        String s = gson.toJson(rows.get(position));
//                        intent.putExtra("xw",s);
                        startActivity(intent);
                    }
                });
                return inflate;
            }
        };
        listview.setAdapter(baseAdapter);
        req();
    }

    private void req() {
        getTools().sendGet("http://"+getServerIp()+"/prod-api/api/movie/press/press/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyXwBean xw = gson.fromJson(string, YyXwBean.class);
                rows = xw.getRows();
                baseAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
    private void initView() {
        back = findViewById(R.id.back);
        listview = findViewById(R.id.listview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}