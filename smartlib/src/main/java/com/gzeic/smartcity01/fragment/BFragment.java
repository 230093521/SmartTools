package com.gzeic.smartcity01.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.gzeic.smartcity01.FuWuJieGuoActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.MyGridView;
import com.gzeic.smartcity01.Tools.MyListView;
import com.gzeic.smartcity01.bean.AllServiceBean;
import com.gzeic.smartcity01.bean.OneServiceBean;
import com.gzeic.smartcity01.dcyc.DcycActivity;
import com.gzeic.smartcity01.ditie.DiTieActivity;
import com.gzeic.smartcity01.mzyy.MzActivity;
import com.gzeic.smartcity01.shjf.ShActivity;
import com.gzeic.smartcity01.tcc.TccActivity;
import com.gzeic.smartcity01.wzcx.WzActivity;
import com.gzeic.smartcity01.yyjc.YyjcActivity;
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
    private ListView listServiceTitle;
    private MyListView listServiceAll;
    List<AllServiceBean.RowsBean> beans;
    private List<OneServiceBean.DataBean> dataBeanList;
    private List<AllServiceBean.RowsBean> rowsBeanList;
    private Button btnSousuo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        initView(view);
        getServiceTitle();

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = homeEditSearch.getText().toString();
                putSP("fwjg", s);
                homeEditSearch.setText("");
                startActivity(new Intent(getContext(), FuWuJieGuoActivity.class));
            }
        });

        return view;
    }


    public void getServiceTitle() {
        getTools().sendGetRequest("http://" + getServerIp() + "/system/dict/data/type/sys_service", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                final OneServiceBean oneServiceBean = new Gson().fromJson(json, OneServiceBean.class);
                if (oneServiceBean.getCode() == 200) {
                    dataBeanList = oneServiceBean.getData();
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listServiceTitle.setAdapter(new MyTitleAdapter(dataBeanList));
                                getAllService();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(oneServiceBean.getMsg());
                        }
                    });
                }
            }
        });
    }

    public void getAllService() {
        getTools().sendGetRequest("http://" + getServerIp() + "/service/service/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: " + json);
                final AllServiceBean allServiceBean = new Gson().fromJson(json, AllServiceBean.class);
                if (allServiceBean.getCode() == 200) {
                    rowsBeanList = allServiceBean.getRows();
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listServiceAll.setAdapter(new MyServiceAllTitle(dataBeanList, rowsBeanList));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(allServiceBean.getMsg());
                        }
                    });
                }
            }
        });
    }

    private void initView(View view) {
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        homeSearchBase = (TextView) view.findViewById(R.id.home_search_base);
        listServiceTitle = (ListView) view.findViewById(R.id.list_service_title);
        listServiceAll = (MyListView) view.findViewById(R.id.list_service_all);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
    }

    class MyTitleAdapter extends BaseAdapter {
        private List<OneServiceBean.DataBean> dataBeanList;

        public MyTitleAdapter(List<OneServiceBean.DataBean> dataBeanList) {
            this.dataBeanList = dataBeanList;
        }

        @Override
        public int getCount() {
            return dataBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return dataBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_one_service, null);
            TextView textView = view.findViewById(R.id.service_title);
            final OneServiceBean.DataBean dataBean = (OneServiceBean.DataBean) getItem(i);
            textView.setText(dataBean.getDictLabel());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initServer(dataBean.getDictCode());
                }
            });
            return view;
        }

    }

    class MyServiceAllTitle extends BaseAdapter {
        private List<OneServiceBean.DataBean> dataBeanList;
        private List<AllServiceBean.RowsBean> rowsBeanList;

        public MyServiceAllTitle(List<OneServiceBean.DataBean> dataBeanList, List<AllServiceBean.RowsBean> rowsBeanList) {
            this.dataBeanList = dataBeanList;
            this.rowsBeanList = rowsBeanList;
        }

        @Override
        public int getCount() {
            return dataBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return dataBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_service, null);
            TextView textView = view.findViewById(R.id.service_all_title);
            MyGridView myGridView = view.findViewById(R.id.rl_service);
            beans = new ArrayList<>();
            for (AllServiceBean.RowsBean bean : rowsBeanList) {
                if (bean.getServiceType().equals(dataBeanList.get(i).getDictValue())) {
                    beans.add(bean);
                }
            }
            myGridView.setAdapter(new MyAdapter(beans));
//            Log.i(TAG, "getView: " + rowsBeanList.get(i).getImgUrl());
            final OneServiceBean.DataBean dataBean = (OneServiceBean.DataBean) getItem(i);
            textView.setText(dataBean.getDictLabel());
            return view;
        }
    }

    public void initServer(int code) {
        final List<OneServiceBean.DataBean> dataBeans2 = new ArrayList<>();
        for (OneServiceBean.DataBean dataBean : dataBeanList) {
            if (dataBean.getDictCode() == code) {
                dataBeans2.add(dataBean);
            }
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listServiceAll.setAdapter(new MyServiceAllTitle(dataBeans2, rowsBeanList));
            }
        });
    }

    public void initServerName(String name) {
        final List<AllServiceBean.RowsBean> rowsBeanList2 = new ArrayList<>();
        final List<OneServiceBean.DataBean> dataBeanList2 = new ArrayList<>();
        int code = 0;
        for (AllServiceBean.RowsBean rowsBean : rowsBeanList) {
            if (rowsBean.getServiceName().contains(name)) {
                rowsBeanList2.add(rowsBean);
                code = Integer.parseInt(rowsBean.getServiceType());
                Log.i(TAG, "initServerName: " + name + rowsBean.getServiceName() + "code" + code);
            }
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listServiceAll.setAdapter(new MyServiceAllTitle(dataBeanList, rowsBeanList2));
            }
        });
    }


    class MyAdapter extends BaseAdapter {
        List<AllServiceBean.RowsBean> list;

        public MyAdapter(List<AllServiceBean.RowsBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fuwu2, null);
            ViewHolder holder = new ViewHolder(convertView);
            final AllServiceBean.RowsBean rowsBean = list.get(position);
            holder.fw_text.setText(list.get(position).getServiceName());
            Glide.with(getContext()).load("http://" + getServerIp() + list.get(position).getImgUrl()).error(R.mipmap.ic_launcher).into(holder.fw_image);
            holder.fw_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    showToast(bean.getServiceName());
                    //根据ID跳转对应服务
                    switch (rowsBean.getId()) {
                        case 7://生活缴费
                            startActivity(new Intent(getContext(), ShActivity.class));
                            break;
                        case 2://城市地铁
                            startActivity(new Intent(getContext(), DiTieActivity.class));
                            break;
                        case 25://预约检车
                            startActivity(new Intent(getContext(), YyjcActivity.class));
                            break;
                        case 24://堵车移车
                            startActivity(new Intent(getContext(), DcycActivity.class));
                            break;
                        case 23://找房子
                            startActivity(new Intent(getContext(), ZfzActivity.class));
                            break;
                        case 4://找工作
                            startActivity(new Intent(getContext(), ZgzActivity.class));
                            break;
                        case 5://门诊预约
                            startActivity(new Intent(getContext(), MzActivity.class));
                            break;
                        case 3://智慧巴士
                            startActivity(new Intent(getContext(), BaShiActivity.class));
                            break;
                        case 16://停车场
                            startActivity(new Intent(getContext(), TccActivity.class));
                            break;
                        case 9://违章查询
                            startActivity(new Intent(getContext(), WzActivity.class));
                            break;
//                                    case 20://智慧党建
//                                        startActivity(new Intent(getContext(), ZHDJActivity.class));
//                                        break;
//                                    case 21://智慧养老
//                                        startActivity(new Intent(getContext(), YangLaoActivity.class));
//                                        break;
//                                    case 22://智慧环保
//                                        startActivity(new Intent(getContext(), WeiZhangActivity.class));
//                                        break;
                        default:
                            break;
                    }
                    Log.i(TAG, list.get(position).toString());
                }
            });
            return convertView;
        }

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
    }
}