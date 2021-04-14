package com.xsonline.smartlib.view;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {
    public void showTos(String context){
        Toast.makeText(getContext(),context,Toast.LENGTH_SHORT).show();
    }

    public void putStringSp(String name,String name2,String context){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(name,MODE_PRIVATE);
        sharedPreferences.edit().putString(name2,context).apply();
    }

    public String getStringSp(String name,String name2){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(name,MODE_PRIVATE);
        return sharedPreferences.getString(name2,null);
    }

    public String getServerIP(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("ip",MODE_PRIVATE);
        String ip2 = "http://" + sharedPreferences.getString("ip2", null);
        Log.i(TAG, "getServerIP: "+ip2);
        return ip2;
    }

    public void putServerIP(String ip){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("ip",MODE_PRIVATE);
        sharedPreferences.edit().putString("ip2",ip).apply();
    }

    public void putToken(String context){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("token",MODE_PRIVATE);
        sharedPreferences.edit().putString("token2",context).apply();
    }

    public String getToken(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("token",MODE_PRIVATE);
        return sharedPreferences.getString("token2",null);
    }
}
