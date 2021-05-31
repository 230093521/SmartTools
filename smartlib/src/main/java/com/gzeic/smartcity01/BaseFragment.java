package com.gzeic.smartcity01;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.Tools.Tools;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.gzeic.smartcity01.bean.UsersBean;
import com.youth.banner.loader.ImageLoader;

import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {
    public String TAG = "test";
    public String HTTP_HEAD = "http://";
    //    getTools().sendGetRequestToken("http://" + getServerIp() + "", getToken(), new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//
//        }
//    });
    Tools tools;
    public Tools getTools(){
        if (tools==null){
            tools = new Tools();
        }
        return tools;
    }

    public String getServerIp(){
        String add = null;
        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address",MODE_PRIVATE);
            add = sharedPreferences.getString("add", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }

    public String getServerIp2(){
        String add = null;
        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address2",MODE_PRIVATE);
            add = sharedPreferences.getString("address2", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }


    public UsersBean.UserDTO getUserdata(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        String userdata = sharedPreferences.getString("userdata", null);
        UsersBean myDataBean = new Gson().fromJson(userdata, UsersBean.class);
        return myDataBean.getUser();
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
        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(key,MODE_PRIVATE);
            sharedPreferences.edit().putString(key,value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getToken(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return "Bearer "+token;
    }

    public String getSP(String key){
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = getActivity().getSharedPreferences(key,MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sharedPreferences.getString(key, null);
    }
}
