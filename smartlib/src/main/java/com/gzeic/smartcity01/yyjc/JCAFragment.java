package com.gzeic.smartcity01.yyjc;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyxzBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JCAFragment extends BaseFragment implements View.OnClickListener {


    private TextView jcContents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jc_a, container, false);
        initView(view);
        getTools().sendGetRequest("http://"+getServerIp() + "/userinfo/carNotice/grt", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final YyxzBean yyxzBean = new Gson().fromJson(string, YyxzBean.class);
                if (yyxzBean.getCode()==200){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            YyxzBean.DataDTO data = yyxzBean.getData();
                            jcContents.setText(Html.fromHtml(data.getNotice()));
                        }
                    });
                }
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {

    }

    private void initView(View view) {
        jcContents = (TextView) view.findViewById(R.id.jc_contents);
    }
}