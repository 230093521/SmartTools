package com.gzeic.smartcity01;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gzeic.smartcity01.Tools.Tools;
import com.youth.banner.loader.ImageLoader;

import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {
    public String TAG = "test";
    Tools tools;
    public Tools getTools(){
        if (tools==null){
            tools = new Tools();
        }
        return tools;
    }

    public String getServerIp(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address",MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        return add;
    }

    public void showToast(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    public void putSP(String key,String value){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(key,MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).apply();
    }

    public String getSP(String key){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
}
