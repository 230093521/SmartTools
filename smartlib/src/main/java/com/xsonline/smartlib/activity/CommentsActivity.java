package com.xsonline.smartlib.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.NewsBean;
import com.xsonline.smartlib.bean.PlBean;
//import com.xsonline.smartlib.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommentsActivity extends BaseActivity {

    private ImageView plBase;
    private EditText etPl;
    private Button btnPl;
    private ListView plList;
    private List<PlBean.RowsBean> rowsBeanList;
    NewsBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_comments);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("news", MODE_PRIVATE);
        String news = sharedPreferences.getString("news", null);
        if (news!=null){
            rowsBean = new Gson().fromJson(news, NewsBean.RowsBean.class);
            getPlList(rowsBean.getId());
        }
    }

    public void getPlList(int pressId){
        getTools().sendGetRequest("http://" + getServerIp() + "/press/comments/list?pageNum=1&pageSize=10&pressId="+pressId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                PlBean plBean = new Gson().fromJson(json, PlBean.class);
                if (plBean.getCode() == 200) {
                    rowsBeanList = plBean.getRows();
                    initPlList();
                }
            }
        });
    }

    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        etPl = (EditText) findViewById(R.id.et_pl);
        btnPl = (Button) findViewById(R.id.btn_pl);
        plList = (ListView) findViewById(R.id.pl_list);
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pl = etPl.getText().toString();
                etPl.setText("");
                if (!pl.isEmpty()){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("userId","1");
                        jsonObject.put("pressId",rowsBean.getId());
                        jsonObject.put("content",pl);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", null);
                    Log.i(TAG, "onResponse: "+token);
                    getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/press/pressComment", token, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                           }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string();
                            Log.i(TAG, "onResponse: "+json);
                            PlBean plBean = new Gson().fromJson(json, PlBean.class);
                            if (plBean.getCode()==200){
                                getPlList(rowsBean.getId());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showToast("发表成功");
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    public void initPlList(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                plList.setAdapter(new MyAdapter(rowsBeanList));
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private List<PlBean.RowsBean> rowsBeanList;

        public MyAdapter(List<PlBean.RowsBean> rowsBeanList) {
            this.rowsBeanList = rowsBeanList;
        }

        @Override
        public int getCount() {
            return rowsBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return rowsBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_comment,null);
            ImageView imageView = view.findViewById(R.id.plx_image);
            TextView nickname = view.findViewById(R.id.plx_nickname);
            TextView time = view.findViewById(R.id.plx_time);
            TextView content = view.findViewById(R.id.plx_content);
            PlBean.RowsBean bean = (PlBean.RowsBean) getItem(i);
            Glide.with(getApplicationContext()).load("http://"+getServerIp()+bean.getAvatar()).error(R.mipmap.ic_launcher).into(imageView);
            nickname.setText(bean.getNickName());
            time.setText(bean.getCreateTime());
            content.setText(bean.getContent());
            return view;
        }
    }
}