package com.gzeic.smartcity01.zgz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.gzeic.smartcity01.bean.ZgzQzxxBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GZCFragment extends BaseFragment {

    private RelativeLayout rlIcon;
    private ImageView ivIcon;
    private EditText etZhanghao;
    private EditText etNichen;
    private EditText etYoujian;
    private EditText etDianhua;
    private RadioGroup rgSex;
    private RadioButton sexNan;
    private RadioButton sexNv;
    MyDataBean.DataBean myDataBeanData;
    private EditText etJingyan;
    private EditText etXueli;
    private EditText etJuzhu;
    private EditText etQiwangzw;
    private EditText etXinzi;
    private EditText etMiaosu;
    private EditText etJiaoyu;
    private EditText etGongzuo;
    private Button btn_bianji;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gz_c, container, false);
        initView(view);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/system/user/1", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                SharedPreferences userdata = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
                userdata.edit().putString("userdata", json).apply();
                MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
                if (myDataBean.getCode() == 200) {
                    myDataBeanData = myDataBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getContext()).load("http://" + getServerIp() + myDataBeanData.getAvatar()).into(ivIcon);
                            etZhanghao.setText(String.valueOf(myDataBeanData.getUserId()));
                            etNichen.setText(myDataBeanData.getNickName());
                            etYoujian.setText(myDataBeanData.getEmail());
                            etDianhua.setText(myDataBeanData.getPhonenumber());
                            if (!myDataBeanData.getSex().equals("0")) {
                                sexNv.setChecked(true);
                            }
                        }
                    });
                } else {

                }
            }
        });

        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/resume/1", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZgzQzxxBean zgzQzxxBean = new Gson().fromJson(string, ZgzQzxxBean.class);
                if (zgzQzxxBean.getCode()==200){
                    final ZgzQzxxBean.DataDTO zgzQzxxBeanData = zgzQzxxBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etJingyan.setText(zgzQzxxBeanData.getExperience());
                            etXueli.setText(zgzQzxxBeanData.getEducation());
                            etJuzhu.setText(zgzQzxxBeanData.getAddress());
                            switch (zgzQzxxBeanData.getPositionId()){
                                case "1":
                                    etQiwangzw.setText("java 开发工程师");
                                    break;
                                case "2":
                                    etQiwangzw.setText("设计");
                                    break;
                                case "3":
                                    etQiwangzw.setText("外教");
                                    break;
                                case "4":
                                    etQiwangzw.setText("前端工程师");
                                    break;
                                case "5":
                                    etQiwangzw.setText("牙医");
                                    break;
                                case "6":
                                    etQiwangzw.setText("全栈开发工程师");
                                    break;
                            }
                            etXinzi.setText(zgzQzxxBeanData.getMoney());
                            etMiaosu.setText(zgzQzxxBeanData.getIndividualResume());
                            etJiaoyu.setText(zgzQzxxBeanData.getMostEducation());
                            etGongzuo.setText("无");
                        }
                    });
                }
            }
        });

        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ZgzJlActivity.class));
            }
        });

        return view;
    }

    private void initView(View view) {
        rlIcon = (RelativeLayout) view.findViewById(R.id.rl_icon);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        etZhanghao = (EditText) view.findViewById(R.id.et_zhanghao);
        etNichen = (EditText) view.findViewById(R.id.et_nichen);
        etYoujian = (EditText) view.findViewById(R.id.et_youjian);
        etDianhua = (EditText) view.findViewById(R.id.et_dianhua);
        rgSex = (RadioGroup) view.findViewById(R.id.rg_sex);
        sexNan = (RadioButton) view.findViewById(R.id.sex_nan);
        sexNv = (RadioButton) view.findViewById(R.id.sex_nv);
        etJingyan = (EditText) view.findViewById(R.id.et_jingyan);
        etXueli = (EditText) view.findViewById(R.id.et_xueli);
        etJuzhu = (EditText) view.findViewById(R.id.et_juzhu);
        etQiwangzw = (EditText) view.findViewById(R.id.et_qiwangzw);
        etXinzi = (EditText) view.findViewById(R.id.et_xinzi);
        etMiaosu = (EditText) view.findViewById(R.id.et_miaosu);
        etJiaoyu = (EditText) view.findViewById(R.id.et_jiaoyu);
        etGongzuo = (EditText) view.findViewById(R.id.et_gongzuo);
        btn_bianji = (Button) view.findViewById(R.id.btn_bianji);
    }
}