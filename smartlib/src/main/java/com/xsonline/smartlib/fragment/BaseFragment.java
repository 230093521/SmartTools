package com.xsonline.smartlib.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.xsonline.smartlib.utils.Tools;
import com.youth.banner.loader.ImageLoader;
import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {
    String TAG = "test";
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

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
