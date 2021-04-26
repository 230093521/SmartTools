package com.gzeic.smartcity01.zgz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.ZwBean;
import com.gzeic.smartcity01.bean.ZwrmBean;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GZAFragment extends BaseFragment {


    private Banner banner;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private GridView gongge;
    private ListViewScrollView listview;
    List<Integer> imglist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gz_a, container, false);
        initView(view);
        imglist = new ArrayList<>();
        imglist.add(R.drawable.zgz1);
        imglist.add(R.drawable.zgz2);
        imglist.add(R.drawable.zgz3);
        imglist.add(R.drawable.zgz4);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imglist);
        banner.start();
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = homeEditSearch.getText().toString();
                if (toString.isEmpty()) {
                    showToast("输入不能为空");
                    return;
                }
                putSP("zgzss", toString);
                startActivity(new Intent(getContext(), ZgzSsActivity.class));
            }
        });
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/profession/list", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwBean zwBean = new Gson().fromJson(string, ZwBean.class);
                final List<ZwBean.RowsDTO> zwrmBeanRows = zwBean.getRows();
                if (zwBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gongge.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView fw_text;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.fw_text = (TextView) rootView.findViewById(R.id.fw_text);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return zwrmBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return zwrmBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_zw, null);
                                    final ZwBean.RowsDTO rowsDTO = zwrmBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.fw_text.setText(rowsDTO.getProfessionName());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            getZwList(String.valueOf(rowsDTO.getId()));
                                        }
                                    });
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });
        getZwList("1");
        return view;
    }

    private void getZwList(final String num) {
        getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/post/list?pageNum=1&pageSize=2&professionId=" + num, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZwrmBean zwrmBean = new Gson().fromJson(string, ZwrmBean.class);
                if (zwrmBean.getCode() == 200) {
                    final List<ZwrmBean.RowsDTO> zwrmBeanRows = zwrmBean.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView zwmc;
                                    public TextView zhizhe;
                                    public TextView didian;
                                    public TextView daiyu;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.zwmc = (TextView) rootView.findViewById(R.id.zwmc);
                                        this.zhizhe = (TextView) rootView.findViewById(R.id.zhizhe);
                                        this.didian = (TextView) rootView.findViewById(R.id.didian);
                                        this.daiyu = (TextView) rootView.findViewById(R.id.daiyu);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return zwrmBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return zwrmBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_zgz, null);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    final ZwrmBean.RowsDTO rowsDTO = zwrmBeanRows.get(position);
                                    viewHolder.daiyu.setText(rowsDTO.getSalary());
                                    viewHolder.didian.setText(rowsDTO.getAddress());
                                    viewHolder.zhizhe.setText(rowsDTO.getObligation());
                                    viewHolder.zwmc.setText(rowsDTO.getName());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            putSP("zwdata",new Gson().toJson(rowsDTO));
                                            startActivity(new Intent(getContext(), ZgzXqActivity.class));
                                        }
                                    });
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
        gongge = (GridView) view.findViewById(R.id.gongge);
        listview = (ListViewScrollView) view.findViewById(R.id.listview);
    }
}