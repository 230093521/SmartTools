package com.xsonline.smartlib.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.xsonline.smartlib.utils.Tools;
import com.youth.banner.loader.ImageLoader;

public abstract class BaseActivity extends AppCompatActivity {
    //getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));


    String TAG = "test";
    Tools tools;
    public String getServerIp(){
        SharedPreferences sharedPreferences = getSharedPreferences("address",MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        return add;
    }

    public void showToast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    public Tools getTools(){
        if (tools==null){
            tools = new Tools();
        }
        return tools;
    }

    public String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;
    }

    public void putSP(String key,String value){
        SharedPreferences sharedPreferences = getSharedPreferences(key,MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).apply();
    }

    public String getSP(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
