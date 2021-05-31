package com.gzeic.smartcity01.x_yy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyPlBean;
import com.gzeic.smartcity01.bean.YyYpxxxqBean;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyYpxqActivity extends BaseActivity {
    public static String[] yplxs = {"故事","爱情","动作","喜剧","恐怖","惊悚","战争","科幻"};
    private RelativeLayout toplan;
    private ImageView back;
    private ImageView tupian;
    private TextView neirong;
    private TextView tvZhankai;
    private TextView tvShouqi;
    private Button pinglun;
    private TextView plzongshu;
    private ListViewScrollView pinglunlist;
    private Button btnGoupiao;
    YyYpxxxqBean.DataBean ypxx;
    private TextView name;
    private TextView leixin;
    private TextView xk;
    private TextView kg;
    private TextView sysj;
    private TextView pf;
    private String add;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#5F8BFA"));
        setContentView(R.layout.activity_yy_ypxq);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        id = getIntent().getIntExtra("id",-1);
        SharedPreferences sharedPreferences1 = getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences1.getString("token", null);
        ApiUrl.setServerApiToken("Bearer "+token);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTools().sendGetRequestToken("http://" + add + "/prod-api/api/movie/film/" + id,"Bearer "+token, new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpxxxqBean ypxx1 = gson.fromJson(string, YyYpxxxqBean.class);
                ypxx = ypxx1.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(ypxx.getName()+"");
                        leixin.setText("类型："+yplxs[Integer.valueOf(ypxx.getCategory())-1]);
                        xk.setText("想看："+ypxx.getLikeNum()+"人");
                        kg.setText("看过："+ypxx.getFavoriteNum()+"人");
                        sysj.setText("上映时间："+ypxx.getPlayDate());
                        pf.setText("评分："+ypxx.getScore()*2+"分");
                        Glide.with(YyYpxqActivity.this).load("http://"+add+ypxx.getCover()).error(R.drawable.icon2).into(tupian);
                        neirong.setText(Html.fromHtml(ypxx.getIntroduction())+"");
                    }
                });
            }
        });
        tvZhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxLines = neirong.getMaxLines();
                if (maxLines == 3) {
                    neirong.setMaxLines(99999999);
                    tvZhankai.setText("收起");
                } else {
                    neirong.setMaxLines(3);
                    tvZhankai.setText("展开");
                }
            }
        });
        pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YyYpxqActivity.this, YyPlActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        btnGoupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YyYpxqActivity.this, YyGpActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        plqq();
    }
    private void plqq() {
        getTools().sendGet("http://"+add+"/prod-api/api/movie/film/comment/list?movieId="+id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Log.i("aaa",string);
                YyPlBean pl = gson.fromJson(string, YyPlBean.class);
                final List<YyPlBean.RowsBean> rows = pl.getRows();
                pinglunlist.setAdapter(new BaseAdapter() {
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
                    public View getView(int position, View convertView, ViewGroup parent) {
                        convertView = LayoutInflater.from(YyYpxqActivity.this).inflate(R.layout.item_yy_pl, null);
                        ViewHolder viewHolder = new ViewHolder(convertView);
                        YyPlBean.RowsBean rowsBean = rows.get(position);
                        viewHolder.name.setText(rowsBean.getNickName());
                        viewHolder.pingfen.setRating(rowsBean.getScore());
                        viewHolder.contents.setText(Html.fromHtml(rowsBean.getContent()));
                        viewHolder.dianzanshu.setText(rowsBean.getLikeNum()+"个赞");
                        viewHolder.fabutime.setText(rowsBean.getCommentDate()+"");
                        return convertView;
                    }
                });
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.name);
        leixin = findViewById(R.id.leixin);
        xk = findViewById(R.id.xk);
        kg = findViewById(R.id.kg);
        sysj = findViewById(R.id.sysj);
        pf = findViewById(R.id.pf);
        toplan = (RelativeLayout) findViewById(R.id.toplan);
        back = (ImageView) findViewById(R.id.back);
        tupian = (ImageView) findViewById(R.id.tupian);
        neirong = (TextView) findViewById(R.id.neirong);
        tvZhankai = (TextView) findViewById(R.id.tv_zhankai);
        tvShouqi = (TextView) findViewById(R.id.tv_shouqi);
        pinglun = (Button) findViewById(R.id.pinglun);
        plzongshu = (TextView) findViewById(R.id.plzongshu);
        pinglunlist = (ListViewScrollView) findViewById(R.id.pinglunlist);
        btnGoupiao = (Button) findViewById(R.id.btn_goupiao);
    }

    class testPl {
        int id;
        int img;
        String name;
        String text;
        String shijian;
        int dianzan;
        int pf;

        public testPl(int id, int img, String name, String text, String shijian, int dianzan, int pf) {
            this.id = id;
            this.img = img;
            this.name = name;
            this.text = text;
            this.shijian = shijian;
            this.dianzan = dianzan;
            this.pf = pf;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getPf() {
            return pf;
        }

        public void setPf(int pf) {
            this.pf = pf;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        plqq();
    }
}