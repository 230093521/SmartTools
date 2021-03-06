package com.gzeic.smartcity01.zgz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.gzeic.smartcity01.bean.UsersBean;
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
    UsersBean.UserDTO myDataBeanData;
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
        myDataBeanData = getUserdata();
        Glide.with(getContext()).load("http://" + getServerIp() + myDataBeanData.getAvatar()).error(R.drawable.icon2).into(ivIcon);
        etZhanghao.setText(String.valueOf(myDataBeanData.getUserId()));
        etNichen.setText(myDataBeanData.getNickName());
        etYoujian.setText(myDataBeanData.getEmail());
        etDianhua.setText(myDataBeanData.getPhonenumber());
        if (!myDataBeanData.getSex().equals("0")) {
            sexNv.setChecked(true);
        }
//        getTools().sendGetRequestToken("http://" + getServerIp() + "/system/user/1", getToken(), new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                SharedPreferences userdata = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
//                userdata.edit().putString("userdata", json).apply();
//                MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
//                if (myDataBean.getCode() == 200) {
//                    myDataBeanData = myDataBean.getData();
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });
//                } else {
//
//                }
//            }
//        });
        getjianli();
        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putSP("tdjl", "");
                startActivity(new Intent(getContext(),ZgzJlActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getjianli();
    }

    private void getjianli() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/job/resume/2", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: ooooooooo"+string);
                ZgzQzxxBean zgzQzxxBean = new Gson().fromJson(string, ZgzQzxxBean.class);
                if (zgzQzxxBean.getCode()==200){
                    final ZgzQzxxBean.DataDTO zgzQzxxBeanData = zgzQzxxBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                etJingyan.setText(zgzQzxxBeanData.getExperience());
                                etXueli.setText(zgzQzxxBeanData.getEducation());
                                etJuzhu.setText(zgzQzxxBeanData.getAddress());
                            } catch (Exception e) {
                                e.printStackTrace();
                                etJingyan.setText("?????????");
                                etXueli.setText("?????????");
                                etJuzhu.setText("?????????");
                            }
                            try {
                                switch (zgzQzxxBeanData.getPositionId()){
                                    case 1:
                                        etQiwangzw.setText("java ???????????????");
                                        break;
                                    case 2:
                                        etQiwangzw.setText("??????");
                                        break;
                                    case 3:
                                        etQiwangzw.setText("??????");
                                        break;
                                    case 4:
                                        etQiwangzw.setText("???????????????");
                                        break;
                                    case 5:
                                        etQiwangzw.setText("??????");
                                        break;
                                    case 6:
                                        etQiwangzw.setText("?????????????????????");
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                etQiwangzw.setText("?????????");
                            }
                            try {
                                etXinzi.setText(zgzQzxxBeanData.getMoney());
                                etMiaosu.setText(zgzQzxxBeanData.getIndividualResume());
                                etJiaoyu.setText(zgzQzxxBeanData.getMostEducation());
                                etGongzuo.setText("?????????");
                            } catch (Exception e) {
                                e.printStackTrace();
                                etXinzi.setText("?????????");
                                etMiaosu.setText("?????????");
                                etJiaoyu.setText("?????????");
                                etGongzuo.setText("?????????");
                            }

                        }
                    });
                }
            }
        });

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