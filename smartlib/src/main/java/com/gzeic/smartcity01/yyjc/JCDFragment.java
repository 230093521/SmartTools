package com.gzeic.smartcity01.yyjc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyxzBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class JCDFragment extends BaseFragment {


    private EditText regChepai;
    private EditText regChejia;
    private EditText regCheleixin;
    private EditText regGongli;
    private EditText regShouji;
    private Button regRegBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jc_d, container, false);
        initView(view);
        regRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chepai = regChepai.getText().toString();
                String chejia = regChejia.getText().toString();
                String regleixin = regCheleixin.getText().toString();
                String gongli = regGongli.getText().toString();
                String shouji = regShouji.getText().toString();
                if (chepai.isEmpty()){
                    showToast("车牌不能为空");
                    return;
                }
                if (chejia.isEmpty()){
                    showToast("类型不能为空");
                    return;
                }
                if (regleixin.isEmpty()){
                    showToast("类型不能为空");
                    return;
                }
                if (gongli.isEmpty()){
                    showToast("公里数不能为空");
                    return;
                }
                if (shouji.isEmpty()){
                    showToast("公里数不能为空");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userId","1");
                    jsonObject.put("plateNum",chepai);
                    jsonObject.put("mainNum",chejia);
                    jsonObject.put("carType",regleixin);
                    jsonObject.put("mileage",gongli);
                    jsonObject.put("phone",shouji);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/userinfo/cars", getToken(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        final YyxzBean yyxzBean = new Gson().fromJson(string, YyxzBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(yyxzBean.getMsg());
                            }
                        });
                    }
                });
            }
        });

        return view;
    }


    private void initView(View view) {
        regChepai = (EditText) view.findViewById(R.id.reg_chepai);
        regChejia = (EditText) view.findViewById(R.id.reg_chejia);
        regCheleixin = (EditText) view.findViewById(R.id.reg_cheleixin);
        regGongli = (EditText) view.findViewById(R.id.reg_gongli);
        regShouji = (EditText) view.findViewById(R.id.reg_shouji);
        regRegBtn = (Button) view.findViewById(R.id.reg_reg_btn);
    }
}