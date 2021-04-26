package com.gzeic.smartcity01;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gzeic.smartcity01.Tools.Tools;
import com.youth.banner.loader.ImageLoader;

public abstract class BaseActivity extends AppCompatActivity {
    //getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
    public String TAG = "test";
    Tools tools;
    public String getServerIp(){
        SharedPreferences sharedPreferences = getSharedPreferences("address",MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        return add;
    }

    public void showToast(String text){
        Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
