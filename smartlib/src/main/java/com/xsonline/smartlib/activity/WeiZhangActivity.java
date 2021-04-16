package com.xsonline.smartlib.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.RowsBean;
import com.xsonline.smartlib.bean.RowsListBean;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeiZhangActivity extends BaseActivity {
    private AlertDialog alertDialog1;//信息框
    private List<String> imageViews;
    private ImageView metroBase;
    private Banner banner;
    private TextView chelei;
    private TextView paizilei;
    private EditText paihao;
    private EditText fadongji;
    private Button chaxunbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_wei_zhang);
        initView();

        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/rotation/list?pageNum=1&pageSize=10&type=45", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final RowsListBean rowsListBean = new Gson().fromJson(json, RowsListBean.class);
                if (rowsListBean.getCode() == 200) {
                    imageViews = new ArrayList<>();
                    List<RowsBean> rows = rowsListBean.getRows();
                    for (RowsBean rs : rows) {
                        imageViews.add("http://"+getServerIp() + rs.getImgUrl());
                        Log.e("ceshi", "http://"+ getServerIp()+ rs.getImgUrl());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(imageViews);
                            banner.start();
                        }
                    });
                } else {
                   runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(rowsListBean.getMsg());
                        }
                    });
                }
            }
        });

        chaxunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (paihao.getText().length()<1||fadongji.getText().length()<1){
                    showToast("请输入完整信息");
                }else {
                    startActivity(new Intent(WeiZhangActivity.this,ChaXunJieGuoActivity.class));
                }

            }
        });
        chelei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList1(view);
            }
        });
        paizilei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList2(view);
            }
        });

    }



    public void showList1(View view) {
        final String[] items = {"小型汽车", "大型汽车", "挂车", "教练汽车", "临时行驶车"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
                chelei.setText(items[i]);
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    public void showList2(View view) {
        final String[] items = {"京", "沪", "粤A", "粤B"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
                paizilei.setText(items[i]);
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        banner = (Banner) findViewById(R.id.banner);
        chelei = (TextView) findViewById(R.id.chelei);
        paizilei = (TextView) findViewById(R.id.paizilei);
        paihao = (EditText) findViewById(R.id.paihao);
        fadongji = (EditText) findViewById(R.id.fadongji);
        chaxunbtn = (Button) findViewById(R.id.chaxunbtn);
    }
}