package com.gzeic.smartcity01.x_wz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.WzCxlbBean;
import com.gzeic.smartcity01.bean.WzxxxqBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WZBFragment extends BaseFragment {
    private String http = "http://";
    private RadioButton bh;
    private RadioButton sqz;
    private RadioButton tg;
    private ListView list;
    List<WzCxlbBean.RowsBean> rowsxs = new ArrayList<>();
    List<WzCxlbBean.RowsBean> rowszs = new ArrayList<>();
    BaseAdapter baseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_b, container, false);
        initView(view);
        tg.setChecked(true);
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return rowsxs.size();
            }

            @Override
            public Object getItem(int position) {
                return rowsxs.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_wbjd, parent, false);
                TextView lsh = inflate.findViewById(R.id.lsh);
                TextView sqsj = inflate.findViewById(R.id.sqsj);
                TextView sljd = inflate.findViewById(R.id.sljd);
                final TextView cfdh = inflate.findViewById(R.id.cfdh);
                final TextView cwxw = inflate.findViewById(R.id.cwxw);
                final TextView cfrq = inflate.findViewById(R.id.cfrq);
                final TextView wzdd = inflate.findViewById(R.id.wzdd);
                lsh.setText(rowsxs.get(position).getId()+"");
                sqsj.setText(rowsxs.get(position).getApplyDate()+"");
                sljd.setText(rowsxs.get(position).getStatus()+"");
                cfdh.setText(rowsxs.get(position).getNo()+"");
                getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/" + rowsxs.get(position).getIllegalId(), getToken(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        WzxxxqBean wzxx = gson.fromJson(string, WzxxxqBean.class);
                        final WzxxxqBean.DataBean data = wzxx.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cwxw.setText(data.getTrafficOffence()+"");
                                cfrq.setText(data.getBadTime()+"");
                                wzdd.setText(data.getIllegalSites()+"");
                            }
                        });
                    }
                });
                return inflate;
            }
        };
        list.setAdapter(baseAdapter);
        qq();
        return view;
    }

    private void qq() {
        getTools().sendGetRequestToken(http + getServerIp() + "/prod-api/api/traffic/illegal/apply/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                WzCxlbBean cxsqlb = gson.fromJson(string, WzCxlbBean.class);
                final List<WzCxlbBean.RowsBean> rows = cxsqlb.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        xs(rows);
                    }
                });
            }
        });
    }
    private void xs(List<WzCxlbBean.RowsBean> rows) {
        rowszs = rows;
        for (WzCxlbBean.RowsBean rowsBean:rowszs){
            if (rowsBean.getAuditRemark().equals("通过")){
                rowsxs.add(rowsBean);
            }
        }
        baseAdapter.notifyDataSetChanged();
    }
    private void initView(View view) {
        bh = view.findViewById(R.id.bh);
        sqz = view.findViewById(R.id.sqz);
        tg = view.findViewById(R.id.tg);
        list = view.findViewById(R.id.list);
        bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzCxlbBean.RowsBean rowsBean:rowszs){
                    if (rowsBean.getAuditRemark().equals("驳回")){
                        rowsxs.add(rowsBean);
                    }
                }
                baseAdapter.notifyDataSetChanged();
                if (rowsxs.size()==0){
                    showToast("未获取到处理信息");
                }
            }
        });
        sqz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzCxlbBean.RowsBean rowsBean:rowszs){
                    if (rowsBean.getAuditRemark().equals("申请中")){
                        rowsxs.add(rowsBean);
                    }
                }
                baseAdapter.notifyDataSetChanged();
                if (rowsxs.size()==0){
                    showToast("未获取到处理信息");
                }
            }
        });
        tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowsxs.clear();
                for (WzCxlbBean.RowsBean rowsBean:rowszs){
                    if (rowsBean.getAuditRemark().equals("通过")){
                        rowsxs.add(rowsBean);
                    }
                }
                baseAdapter.notifyDataSetChanged();
                if (rowsxs.size()==0){
                    showToast("未获取到处理信息");
                }
            }
        });
    }
}