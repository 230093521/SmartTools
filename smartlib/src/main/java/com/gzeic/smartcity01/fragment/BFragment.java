package com.gzeic.smartcity01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.gzeic.smartcity01.FuWuJieGuoActivity;
import com.gzeic.smartcity01.MainActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.AllServiceBean;
import com.gzeic.smartcity01.bean.FwBean;
import com.gzeic.smartcity01.bean.OneServiceBean;
import com.gzeic.smartcity01.mzyy.MzActivity;
import com.gzeic.smartcity01.shjf.ShActivity;
import com.gzeic.smartcity01.wzcx.WzActivity;
import com.gzeic.smartcity01.x_csdt.CsdtSyActivity;
import com.gzeic.smartcity01.x_hdgl.HdSyActivity;
import com.gzeic.smartcity01.x_jf.JfSyActivity;
import com.gzeic.smartcity01.x_wz.WzSyActivity;
import com.gzeic.smartcity01.x_yy.YySyActivity;
import com.gzeic.smartcity01.x_zc.ZcSyActivity;
import com.gzeic.smartcity01.zfz.ZfzActivity;
import com.gzeic.smartcity01.zgz.ZgzActivity;
import com.gzeic.smartcity01.zhbs.BaShiActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BFragment extends BaseFragment {
    private EditText homeEditSearch;
    private TextView homeSearchBase;
    private GridView listServiceAll;
    List<AllServiceBean.RowsBean> beans;
    private List<OneServiceBean.DataBean> dataBeanList;
    private List<AllServiceBean.RowsBean> rowsBeanList;
    private Button btnSousuo;
    private RelativeLayout quanbufuwu;
    private RelativeLayout shenghuofuwu;
    private RelativeLayout bianmingfuwu;
    private RelativeLayout chezhufuwu;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        initView(view);
        getFulist("全部服务");
        quanbufuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFulist("全部服务");
            }
        });

        shenghuofuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFulist("生活服务");
            }
        });

        bianmingfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFulist("便民服务");
            }
        });

        chezhufuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFulist("车主服务");
            }
        });

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = homeEditSearch.getText().toString();
                putSP("fwjg", s);
                homeEditSearch.setText("");
                startActivity(new Intent(getContext(), FuWuJieGuoActivity.class));
            }
        });
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String s = homeEditSearch.getText().toString();
                    putSP("fwjg", s);
                    homeEditSearch.setText("");
                    startActivity(new Intent(getContext(), FuWuJieGuoActivity.class));
                    return true;
                }
                return false;
            }
        });


        return view;
    }

    private void getFulist(final String name) {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/service/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                FwBean fwBean = new Gson().fromJson(string, FwBean.class);
                if (fwBean.getCode() == 200) {
                    List<FwBean.RowsDTO> rows = new ArrayList<>();
                    if (name.equals("全部服务")) {
                        rows = fwBean.getRows();
                    } else {
                        for (FwBean.RowsDTO fwBeanRow : fwBean.getRows()) {
                            if (fwBeanRow.getServiceType().equals(name)) {
                                rows.add(fwBeanRow);
                            }
                        }
                    }
                    final List<FwBean.RowsDTO> finalRows = rows;
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listServiceAll.setAdapter(new BaseAdapter() {
                                    class ViewHolder {
                                        public View rootView;
                                        public ImageView fw_image;
                                        public TextView fw_text;
                                        public LinearLayout fw_ll;

                                        public ViewHolder(View rootView) {
                                            this.rootView = rootView;
                                            this.fw_image = (ImageView) rootView.findViewById(R.id.fw_image);
                                            this.fw_text = (TextView) rootView.findViewById(R.id.fw_text);
                                            this.fw_ll = (LinearLayout) rootView.findViewById(R.id.fw_ll);
                                        }

                                    }

                                    @Override
                                    public int getCount() {
                                        return finalRows.size();
                                    }

                                    @Override
                                    public Object getItem(int position) {
                                        return finalRows.get(position);
                                    }

                                    @Override
                                    public long getItemId(int position) {
                                        return position;
                                    }

                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fw_x, null);
                                        final FwBean.RowsDTO rowsDTO = finalRows.get(position);
                                        ViewHolder viewHolder = new ViewHolder(view);
                                        viewHolder.fw_text.setText(rowsDTO.getServiceName());
                                        Glide.with(getContext()).load("http://" + getServerIp() + rowsDTO.getImgUrl()).into(viewHolder.fw_image);
                                        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                switch (rowsDTO.getId()) {
                                                    case 17://停哪儿
                                                        startActivity(new Intent(getContext(), ZcSyActivity.class));
                                                        break;
                                                    case 2://城市地铁
                                                        startActivity(new Intent(getContext(), CsdtSyActivity.class));
                                                        break;
                                                    case 3://智慧巴士
                                                        startActivity(new Intent(getContext(), BaShiActivity.class));
                                                        break;
                                                    case 5://门诊预约
                                                        startActivity(new Intent(getContext(), MzActivity.class));
                                                        break;
                                                    case 9://智慧交管
                                                        startActivity(new Intent(getContext(), WzSyActivity.class));
                                                        break;
                                                    case 7://生活缴费
                                                        startActivity(new Intent(getContext(), JfSyActivity.class));
                                                        break;
                                                    case 19://外卖订餐
                                                        startActivity(new Intent(getContext(), JfSyActivity.class));
                                                        break;
                                                    case 20://找房子
                                                        startActivity(new Intent(getContext(), ZfzActivity.class));
                                                        break;
                                                    case 18://看电影
                                                        startActivity(new Intent(getContext(), YySyActivity.class));
                                                        break;
                                                    case 21://找工作
                                                        startActivity(new Intent(getContext(), ZgzActivity.class));
                                                        break;
                                                    case 22://活动管理
                                                        startActivity(new Intent(getContext(), HdSyActivity.class));
                                                        break;
                                                    case 99://更多服务
                                                        MainActivity activity = (MainActivity) getActivity();
                                                        try {
                                                            activity.showFragment(R.id.navigation_dashboard);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
    //                                            startActivity(new Intent(getContext(), ZHDJActivity.class));
                                                        break;
                                                    //                                    case 21://智慧养老
                                                    //                                        startActivity(new Intent(getContext(), YangLaoActivity.class));
                                                    //                                        break;
                                                    //                                    case 22://智慧环保
                                                    //                                        startActivity(new Intent(getContext(), WeiZhangActivity.class));
                                                    //                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        });
                                        return view;
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    private void initView(View view) {
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        homeSearchBase = (TextView) view.findViewById(R.id.home_search_base);
        listServiceAll = (GridView) view.findViewById(R.id.list_service_all);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
        quanbufuwu = (RelativeLayout) view.findViewById(R.id.quanbufuwu);
        shenghuofuwu = (RelativeLayout) view.findViewById(R.id.shenghuofuwu);
        bianmingfuwu = (RelativeLayout) view.findViewById(R.id.bianmingfuwu);
        chezhufuwu = (RelativeLayout) view.findViewById(R.id.chezhufuwu);
    }

}