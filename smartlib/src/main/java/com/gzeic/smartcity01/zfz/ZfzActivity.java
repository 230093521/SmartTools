package com.gzeic.smartcity01.zfz;

import android.content.Intent;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.ZfzflBean;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZfzActivity extends BaseActivity {

    private ImageView metroBase;
    private Banner aBanner;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private TextView homeSearchBase;
    private TextView ershou;
    private TextView zhufang;
    private TextView loupan;
    private TextView zhongjie;
    private ListViewScrollView fyList;
    List<Integer> imglist;
    List<ZfzflBean.RowsDTO> zfzflBeanRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zfz);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imglist = new ArrayList<>();
        imglist.add(R.drawable.zfz1);
        imglist.add(R.drawable.zfz2);
        imglist.add(R.drawable.zfz3);
        imglist.add(R.drawable.zfz4);
        imglist.add(R.drawable.zfz5);
        imglist.add(R.drawable.zfz5);
        imglist.add(R.drawable.zfz6);
        aBanner.setImageLoader(new GlideImageLoader());
        aBanner.setImages(imglist);
        aBanner.start();
        showFz("二手");
        ershou.setTextColor(Color.parseColor("#FF0000"));
        ershou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFz("二手");
                ershou.setTextColor(Color.parseColor("#FF0000"));
                zhufang.setTextColor(Color.parseColor("#5A5A5A"));
                loupan.setTextColor(Color.parseColor("#5A5A5A"));
                zhongjie.setTextColor(Color.parseColor("#5A5A5A"));
            }
        });
        zhufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFz("租房");
                ershou.setTextColor(Color.parseColor("#5A5A5A"));
                zhufang.setTextColor(Color.parseColor("#FF0000"));
                loupan.setTextColor(Color.parseColor("#5A5A5A"));
                zhongjie.setTextColor(Color.parseColor("#5A5A5A"));
            }
        });
        loupan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFz("楼盘");
                ershou.setTextColor(Color.parseColor("#5A5A5A"));
                zhufang.setTextColor(Color.parseColor("#5A5A5A"));
                loupan.setTextColor(Color.parseColor("#FF0000"));
                zhongjie.setTextColor(Color.parseColor("#5A5A5A"));
            }
        });
        zhongjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFz("中介");
                ershou.setTextColor(Color.parseColor("#5A5A5A"));
                zhufang.setTextColor(Color.parseColor("#5A5A5A"));
                loupan.setTextColor(Color.parseColor("#5A5A5A"));
                zhongjie.setTextColor(Color.parseColor("#FF0000"));
            }
        });
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = homeEditSearch.getText().toString();
                if (s.isEmpty()){
                    showToast("输入不能为空");
                    return;
                }
                showSouSuo(s);
            }
        });

    }

    public void showFz(String types) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/house/housing/list?houseType="+types,getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZfzflBean zfzflBean = new Gson().fromJson(string, ZfzflBean.class);
                Log.i(TAG, "onResponse: "+string);
                if (zfzflBean.getCode() == 200) {
                   zfzflBeanRows = zfzflBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fyList.setAdapter(new Myadp());
                        }
                    });
                }
            }
        });
    }

    public void showSouSuo(String text) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/house/housing/list?sourceName=" + text, getToken(),new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZfzflBean zfzflBean = new Gson().fromJson(string, ZfzflBean.class);
                if (zfzflBean.getCode() == 200) {
                    zfzflBeanRows = zfzflBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fyList.setAdapter(new Myadp());
                        }
                    });
                }
            }
        });
    }

    class Myadp extends BaseAdapter{
        class ViewHolder {
            public View rootView;
            public ImageView news_image;
            public TextView title;
            public TextView mianji;
            public TextView jiage;
            public TextView jianjie;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                this.title = (TextView) rootView.findViewById(R.id.title);
                this.mianji = (TextView) rootView.findViewById(R.id.mianji);
                this.jiage = (TextView) rootView.findViewById(R.id.jiage);
                this.jianjie = (TextView) rootView.findViewById(R.id.jianjie);
            }

        }

        @Override
        public int getCount() {
            return zfzflBeanRows.size();
        }

        @Override
        public Object getItem(int position) {
            return zfzflBeanRows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(ZfzActivity.this).inflate(R.layout.item_zfz_fy, null);
            final ZfzflBean.RowsDTO rowsDTO = zfzflBeanRows.get(position);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.mianji.setText(String.valueOf(rowsDTO.getAreaSize()));
            viewHolder.jiage.setText(rowsDTO.getPrice());
            viewHolder.jianjie.setText(rowsDTO.getDescription());
            viewHolder.title.setText(rowsDTO.getSourceName());
            Glide.with(ZfzActivity.this).load("http://"+getServerIp()+rowsDTO.getPic()).into(viewHolder.news_image);
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toJson = new Gson().toJson(rowsDTO);
                    putSP("zfzxq",toJson);
                    startActivity(new Intent(ZfzActivity.this,ZfzXqActivity.class));
                }
            });
            return convertView;
        }
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        aBanner = (Banner) findViewById(R.id.a_banner);
        ivMei = (ImageView) findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) findViewById(R.id.home_edit_search);
        btnSousuo = (Button) findViewById(R.id.btn_sousuo);
        homeSearchBase = (TextView) findViewById(R.id.home_search_base);
        ershou = (TextView) findViewById(R.id.ershou);
        zhufang = (TextView) findViewById(R.id.zhufang);
        loupan = (TextView) findViewById(R.id.loupan);
        zhongjie = (TextView) findViewById(R.id.zhongjie);
        fyList = (ListViewScrollView) findViewById(R.id.fy_list);
    }
}