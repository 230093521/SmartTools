package com.gzeic.smartcity01.x_yy;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyPlBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyYinPingActivity extends BaseActivity {
    private ImageView back;
    private ListViewScrollView yingyuanlist;
    private String add;
    List<YyPlBean.RowsBean> rows = new ArrayList<>();
    BaseAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_yp);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        initView();
        baseAdapter = new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView touxiang;
                public TextView name;
                public RatingBar pingfen;
                public TextView contents;
                public TextView fabutime;
                public TextView dianzanshu;
                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.touxiang = (ImageView) rootView.findViewById(R.id.touxiang);
                    this.name = (TextView) rootView.findViewById(R.id.name);
                    this.pingfen = (RatingBar) rootView.findViewById(R.id.pingfen);
                    this.contents = (TextView) rootView.findViewById(R.id.contents);
                    this.fabutime = (TextView) rootView.findViewById(R.id.fabutime);
                    this.dianzanshu = (TextView) rootView.findViewById(R.id.dianzanshu);
                }

            }

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
            public View getView(int position, View convertView, final ViewGroup parent) {
                convertView = LayoutInflater.from(YyYinPingActivity.this).inflate(R.layout.item_yy_pl, null);
                ViewHolder viewHolder = new ViewHolder(convertView);
                final YyPlBean.RowsBean rowsBean = rows.get(position);
                viewHolder.name.setText(rowsBean.getNickName());
                viewHolder.pingfen.setRating(rowsBean.getScore());
                Spanned spanned = Html.fromHtml(rowsBean.getContent());
                viewHolder.contents.setText(spanned);
                viewHolder.dianzanshu.setText(rowsBean.getLikeNum()+"个赞");
                viewHolder.fabutime.setText(rowsBean.getCommentDate()+"");
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(parent.getContext(), YyYpxqActivity.class);
                        intent.putExtra("id",rowsBean.getMovieId());
                        startActivity(intent);
                    }
                });
                return convertView;
            }
        };
        yingyuanlist.setAdapter(baseAdapter);
        req();
    }

    private void req() {
        getTools().sendGet("http://"+add+"/prod-api/api/movie/film/comment/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Gson gson = new Gson();
                YyPlBean pl = gson.fromJson(string, YyPlBean.class);
                rows = pl.getRows();
                baseAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        yingyuanlist = findViewById(R.id.yingyuanlist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}