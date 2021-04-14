package com.xsonline.smartlib.view;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    String TAG = "test";

    public void showToast(String context){
        Toast.makeText(BaseActivity.this,context,Toast.LENGTH_SHORT).show();
    }

    public void putStringSp(String name,String name2,String context){
        SharedPreferences sharedPreferences = getSharedPreferences(name,MODE_PRIVATE);
        sharedPreferences.edit().putString(name2,context).apply();
    }

    public String getStringSp(String name,String name2){
        SharedPreferences sharedPreferences = getSharedPreferences(name,MODE_PRIVATE);
        return sharedPreferences.getString(name2,null);
    }

    public String getServerIP(){
        SharedPreferences sharedPreferences = getSharedPreferences("ip",MODE_PRIVATE);
        String ip2 = "http://" + sharedPreferences.getString("ip2", null);
        Log.i(TAG, "getServerIP: "+ip2);
        return ip2;
    }

    public void putServerIP(String ip){
        SharedPreferences sharedPreferences = getSharedPreferences("ip",MODE_PRIVATE);
        sharedPreferences.edit().putString("ip2",ip).apply();
    }

    public void putToken(String context){
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        sharedPreferences.edit().putString("token2",context).apply();
    }

    public String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        return sharedPreferences.getString("token2",null);
    }

}