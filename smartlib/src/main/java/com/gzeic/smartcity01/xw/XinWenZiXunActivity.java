package com.gzeic.smartcity01.xw;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.XwlblbBean;
import com.gzeic.smartcity01.bean.ZtBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XinWenZiXunActivity extends BaseActivity {
    private List<XwlblbBean.RowsDTO> ztlist;
    private ListView news_list;
    private ImageView setBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_xw_zixun);
        initView();
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/press/press/list",getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                XwlblbBean ztBean = new Gson().fromJson(json, XwlblbBean.class);
                if (ztBean.getCode() == 200) {
                    ztlist = new ArrayList<>();
                    ztlist = ztBean.getRows();
                    List<XwlblbBean.RowsDTO> ztlist2 = new ArrayList<>();
                    for (XwlblbBean.RowsDTO rowsBean : ztlist) {
                        Log.i(TAG, "onResponse: "+getSP("xwmc")+"  "+rowsBean.getContent());
                        if ((rowsBean.getContent()).contains(getSP("xwmc"))) {
                            Log.i(TAG, "XXXXXXXXXXXXXXXXXXXX: "+getSP("xwmc")+"  "+rowsBean.getContent());
                            ztlist2.add(rowsBean);
                        }
                    }
                    ztlist = ztlist2;
                    initNewList();
                }
            }
        });
        setBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initNewList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                news_list.setAdapter(new NewsAdapter(ztlist));
            }
        });
    }

    private void initView() {
        news_list = findViewById(R.id.news_list);
        setBack = (ImageView) findViewById(R.id.set_back);
    }

    class NewsAdapter extends BaseAdapter {
        private List<XwlblbBean.RowsDTO> ztlist;

        public NewsAdapter(List<XwlblbBean.RowsDTO> ztlist) {
            this.ztlist = ztlist;
        }

        @Override
        public int getCount() {
            return ztlist.size();
        }

        @Override
        public Object getItem(int i) {
            return ztlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View views = LayoutInflater.from(XinWenZiXunActivity.this).inflate(R.layout.item_xw, null);
            final XwlblbBean.RowsDTO rowsBean = (XwlblbBean.RowsDTO) getItem(i);
            Log.i("ceshi", "getView: " + rowsBean.getContent());
            ImageView imageView = views.findViewById(R.id.news_image);
            TextView title = views.findViewById(R.id.news_title);
            TextView content = views.findViewById(R.id.news_content);
            LinearLayout linearLayout = views.findViewById(R.id.news_ll);

            Glide.with(XinWenZiXunActivity.this).load("http://" + getServerIp() + rowsBean.getCover()).error(R.mipmap.ic_launcher).into(imageView);
            title.setText(rowsBean.getTitle());
            content.setText(Html.fromHtml(rowsBean.getContent()));
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String json = new Gson().toJson(rowsBean);
                    putSP("news", json);
                    startActivity(new Intent(XinWenZiXunActivity.this, XinWenActivity.class));
                }
            });
            return views;
        }
    }
}