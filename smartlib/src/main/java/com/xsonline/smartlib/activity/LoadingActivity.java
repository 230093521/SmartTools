package com.xsonline.smartlib.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import com.xsonline.smartlib.R;

import java.lang.ref.WeakReference;

public class LoadingActivity extends AppCompatActivity {
    private MyHandler myHandler = new MyHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);

        SharedPreferences sharedPreferences = getSharedPreferences("address",MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        if (add==null){
            startActivity(new Intent(LoadingActivity.this, GuideActivity.class));
            finish();
        }else {
            Log.i("ceshi", "address="+add);
            myHandler.sendEmptyMessageDelayed(1,3000);
        }
    }

    static class MyHandler extends Handler{
        WeakReference<LoadingActivity> weakReference;

        public MyHandler(LoadingActivity loadingActivity) {
            this.weakReference = new WeakReference<LoadingActivity>(loadingActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            weakReference.get().startActivity(new Intent(weakReference.get().getApplicationContext(), GuideActivity.class));
            weakReference.get().finish();
        }
    }

}