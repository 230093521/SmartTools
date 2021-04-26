package com.gzeic.smartcity01.yyjc;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YydindBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class JCCFragment extends BaseFragment {


    private ListViewScrollView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jc_c, container, false);
        initView(view);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/apt/list?userId=2&pageNum=1&pageSize=10", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                YydindBean yydindBean = new Gson().fromJson(string, YydindBean.class);
                final List<YydindBean.RowsDTO> yydindBeanRows = yydindBean.getRows();
                if (yydindBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView chepai;
                                    public TextView chejia;
                                    public TextView cheleixin;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                                        this.chejia = (TextView) rootView.findViewById(R.id.chejia);
                                        this.cheleixin = (TextView) rootView.findViewById(R.id.cheleixin);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return yydindBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return yydindBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jcwdyy, null);
                                    YydindBean.RowsDTO rowsDTO = yydindBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.chepai.setText(rowsDTO.getPlateNum());
                                    viewHolder.cheleixin.setText(rowsDTO.getAddress());
                                    viewHolder.chejia.setText(rowsDTO.getAptTime());
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });

        return view;
    }

    private void initView(View view) {
        listview = (ListViewScrollView) view.findViewById(R.id.listview);
    }
}