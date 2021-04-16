package com.xsonline.smartlib.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.activity.BaShiActivity;
import com.xsonline.smartlib.activity.DiTieActivity;
import com.xsonline.smartlib.activity.LivePayActivity;
import com.xsonline.smartlib.activity.MenZhengActivity;
import com.xsonline.smartlib.activity.TinCheChangActivity;
import com.xsonline.smartlib.activity.WeiZhangActivity;
import com.xsonline.smartlib.bean.AllServiceBean;
import com.xsonline.smartlib.bean.OneServiceBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BFragment extends BaseFragment {


    private EditText homeEditSearch;
    private TextView homeSearchBase;
    private ListView listServiceTitle;
    private ListView listServiceAll;
    private List<OneServiceBean.DataBean> dataBeanList;
    private List<AllServiceBean.RowsBean> rowsBeanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        initView(view);
        getServiceTitle();

        return view;
    }

    public void getServiceTitle(){
        getTools().sendGetRequest("http://" + getServerIp() + "/system/dict/data/type/sys_service", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                final OneServiceBean oneServiceBean = new Gson().fromJson(json, OneServiceBean.class);
                if (oneServiceBean.getCode() == 200) {
                    dataBeanList = oneServiceBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listServiceTitle.setAdapter(new MyTitleAdapter(dataBeanList));
                            getAllService();
                        }
                    });
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

    public void getAllService(){
        getTools().sendGetRequest("http://" + getServerIp() + "/service/service/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i(TAG, "onResponse: "+json);
                final AllServiceBean allServiceBean = new Gson().fromJson(json, AllServiceBean.class);
                if (allServiceBean.getCode() == 200) {
                    rowsBeanList = allServiceBean.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listServiceAll.setAdapter(new MyServiceAllTitle(dataBeanList,rowsBeanList));
                        }
                    });
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
        listServiceAll = (ListView) view.findViewById(R.id.list_service_all);
    }

    class MyTitleAdapter extends BaseAdapter{
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
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_one_service,null);
            TextView textView = view.findViewById(R.id.service_title);
            OneServiceBean.DataBean dataBean = (OneServiceBean.DataBean) getItem(i);
            textView.setText(dataBean.getDictLabel());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return view;
        }

    }

    class MyServiceAllTitle extends BaseAdapter{
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
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_service,null);
            TextView textView = view.findViewById(R.id.service_all_title);
            RecyclerView recyclerView = view.findViewById(R.id.rl_service);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
            recyclerView.setLayoutManager(linearLayoutManager);
            List<AllServiceBean.RowsBean> beans = new ArrayList<>();
            for (AllServiceBean.RowsBean bean:rowsBeanList) {
                if (bean.getServiceType().equals(dataBeanList.get(i).getDictValue())) {
                    beans.add(bean);
                }
            }
            recyclerView.setAdapter(new MyAdapter(beans));
            Log.i(TAG, "getView: "+rowsBeanList.get(i).getImgUrl());
            OneServiceBean.DataBean dataBean = (OneServiceBean.DataBean) getItem(i);
            textView.setText(dataBean.getDictLabel());
            return view;
        }

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<AllServiceBean.RowsBean> list;

        public MyAdapter(List<AllServiceBean.RowsBean> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_fw, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.fw_text.setText(list.get(position).getServiceName());
            Glide.with(getContext()).load("http://" + getServerIp() + list.get(position).getImgUrl()).error(R.mipmap.ic_launcher).into(holder.fw_image);
            holder.fw_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AllServiceBean.RowsBean bean = list.get(position);
//                    showToast(bean.getServiceName());
                    //根据ID跳转对应服务
                    switch (bean.getId()){
                        case 7://生活缴费
                            startActivity(new Intent(getContext(), LivePayActivity.class));
                            break;
                        case 2://城市地铁
                            startActivity(new Intent(getContext(), DiTieActivity.class));
                            break;
                        case 5://门诊预约
                            startActivity(new Intent(getContext(), MenZhengActivity.class));
                            break;
                        case 3://智慧巴士
                            startActivity(new Intent(getContext(), BaShiActivity.class));
                            break;
                        case 17://停车场
                            startActivity(new Intent(getContext(), TinCheChangActivity.class));
                            break;
                        case 9://违章查询
                            startActivity(new Intent(getContext(), WeiZhangActivity.class));
                            break;
                        default:
                            break;
                    }
                    Log.i(TAG, list.get(position).toString());
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout fw_ll;
            ImageView fw_image;
            TextView fw_text;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                fw_image = itemView.findViewById(R.id.fw_image);
                fw_ll = itemView.findViewById(R.id.fw_ll);
                fw_text = itemView.findViewById(R.id.fw_text);
            }
        }
    }


}