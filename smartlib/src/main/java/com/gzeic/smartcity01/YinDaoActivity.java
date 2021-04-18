package com.gzeic.smartcity01;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.xsonline.smartlib.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class YinDaoActivity extends BaseActivity implements View.OnClickListener {
    private TextView startTv;
    private TextView startNetwork;
    private List<String> imageList;
    private List<Integer> images;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yindao);
        initView();
        images = new ArrayList<>();
        images.add(R.drawable.yindao1);
        images.add(R.drawable.yindao2);
        images.add(R.drawable.yindao3);
        images.add(R.drawable.yindao4);
        images.add(R.drawable.yindao5);
        showBanner();

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==4){
                    startTv.setVisibility(View.VISIBLE);
                    startNetwork.setVisibility(View.VISIBLE);
                    banner.setScrollContainer(false);
                }else {
                    startTv.setVisibility(View.GONE);
                    startNetwork.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void showBanner(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                banner.setImageLoader(new MyImageLoader());
                banner.setImages(images);
                banner.isAutoPlay(false);
                banner.start();
            }
        });
    }


    private void initView() {
        startTv = (TextView) findViewById(R.id.start_tv);
        startNetwork = (TextView) findViewById(R.id.start_network);
        banner =findViewById(R.id.start_banner);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.start_tv) {
            SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
            String add = sharedPreferences.getString("add", null);
            if (add == null) {
                Toast.makeText(this, "请先设置网络", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("ceshi", "address=" + add);
                startActivity(new Intent(YinDaoActivity.this, WoDenLuActivity.class));
            }
        } else if (id == R.id.start_network) {
            startActivity(new Intent(YinDaoActivity.this, WangLuoActivity.class));
        }
    }

    class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(final Context context, final Object path, final ImageView imageView) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(context).load(path).into(imageView);
                }
            });
        }
    }

}