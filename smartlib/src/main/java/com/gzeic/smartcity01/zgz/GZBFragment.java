package com.gzeic.smartcity01.zgz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.ZgzLsBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class GZBFragment extends BaseFragment {

    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gz_b, container, false);
       initView(view);
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/deliver/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ZgzLsBean zgzLsBean = new Gson().fromJson(string, ZgzLsBean.class);
                if (zgzLsBean.getCode() == 200) {
                    final List<ZgzLsBean.RowsDTO> zgzLsBeanRows = zgzLsBean.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView qwzw;
                                    public TextView gsmc;
                                    public TextView qwxz;
                                    public TextView tdsj;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.qwzw = (TextView) rootView.findViewById(R.id.qwzw);
                                        this.gsmc = (TextView) rootView.findViewById(R.id.gsmc);
                                        this.qwxz = (TextView) rootView.findViewById(R.id.qwxz);
                                        this.tdsj = (TextView) rootView.findViewById(R.id.tdsj);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return zgzLsBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return zgzLsBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_zgzjl, null);
                                    ZgzLsBean.RowsDTO rowsDTO = zgzLsBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.gsmc.setText(rowsDTO.getCompanyName());
                                    viewHolder.qwxz.setText(rowsDTO.getMoney());
                                    viewHolder.tdsj.setText(rowsDTO.getSatrTime());
                                    viewHolder.qwzw.setText(rowsDTO.getPostName());
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
        list = (ListView) view.findViewById(R.id.list);
    }
}