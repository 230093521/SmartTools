package com.gzeic.smartcity01.x_csdt;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTxltBean;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DTDFragment extends BaseFragment {
    private PhotoView tupian;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dt_d, container, false);
        initView(view);
        getxlt();
        return view;
    }

    private void getxlt() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/city", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DTxltBean dTxltBean = new Gson().fromJson(string, DTxltBean.class);
                if (dTxltBean.getCode()==200){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getContext()).load("http://"+getServerIp()+dTxltBean.getData().getImgUrl()).into(tupian);
                        }
                    });
                }
            }
        });
    }


    private void initView(View view) {
        tupian = (PhotoView) view.findViewById(R.id.tupian);
    }
}