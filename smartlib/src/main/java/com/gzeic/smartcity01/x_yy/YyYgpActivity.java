package com.gzeic.smartcity01.x_yy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.gzeic.smartcity01.CsDwActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyYpygBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YyYgpActivity extends BaseActivity {
    private ImageView back;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private TextView gps;
    private ListViewScrollView yingyuanlist;
    List<YyYpygBean.RowsBean> rows = new ArrayList<>();
    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_ygp);
        initView();
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YyYgpActivity.this, CsDwActivity.class));
            }
        });
        baseAdapter = new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public VideoView video;
                public ImageView bofangbtn;
                public ImageView img;
                public TextView name;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.video = (VideoView) rootView.findViewById(R.id.video);
                    this.bofangbtn = (ImageView) rootView.findViewById(R.id.bofangbtn);
                    this.img = (ImageView) rootView.findViewById(R.id.img);
                    this.name = (TextView) rootView.findViewById(R.id.name);
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_yy_yg, parent, false);
                final VideoView video = view.findViewById(R.id.video);
                TextView name = view.findViewById(R.id.name);
                final ImageView img = view.findViewById(R.id.img);
                name.setText(rows.get(position).getName());
                final ViewHolder viewHolder = new ViewHolder(view);
                video.setVideoPath(HTTP_HEAD + getServerIp() + rows.get(position).getVideo());
                Glide.with(parent.getContext()).load(HTTP_HEAD + getServerIp() + rows.get(position).getCover()).into(img);
                final boolean[] fag = {true};
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (fag[0]) {
                            video.start();
                            img.setVisibility(View.GONE);
                            fag[0] = false;
                            viewHolder.bofangbtn.setVisibility(View.GONE);
                        } else {
                            video.pause();
                            img.setVisibility(View.VISIBLE);
                            fag[0] = true;
                            viewHolder.bofangbtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
                return view;
            }
        };
        yingyuanlist.setAdapter(baseAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String chengshi = getSP("chengshi");
        if (chengshi != null) {
            gps.setText(chengshi);
        }
    }


    private void initView() {
        back = findViewById(R.id.back);
        ivMei = findViewById(R.id.iv_mei);
        homeEditSearch = findViewById(R.id.home_edit_search);
        gps = findViewById(R.id.gps);
        yingyuanlist = findViewById(R.id.yingyuanlist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_HOME) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String s = v.getText().toString();
                    if (s == null || s.equals("")) {
                        req(null);
                    } else {
                        req(s);
                    }
                    return true;
                }
                return false;
            }
        });
        req(null);
    }

    private void req(String s) {
        String url = null;
        if (s == null) {
            url = "http://" + getServerIp() + "/prod-api/api/movie/film/preview/list";
        } else {
            url = "http://" + getServerIp() + "/prod-api/api/movie/film/preview/list?name=" + s;
        }
        getTools().sendGet(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Log.i("aaa", string);
                YyYpygBean yg1 = gson.fromJson(string, YyYpygBean.class);
                rows = yg1.getRows();
                Log.i("aaa", rows.size() + "+++++++++");
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
}