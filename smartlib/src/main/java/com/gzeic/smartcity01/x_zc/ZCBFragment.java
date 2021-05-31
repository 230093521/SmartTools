package com.gzeic.smartcity01.x_zc;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.ZcLcxxBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZCBFragment extends BaseFragment {

    private TextView jiayouliang;
    private TextView jine;
    private TextView lichen;
    private ListViewScrollView list;
    private Button tianjiajilu;
    int ljjyl,ljjyje,ljxslc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zc_b, container, false);
        initView(view);
        getlicheng();
        tianjiajilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ZcJytjjlActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getlicheng();
    }

    private void getlicheng() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/car/consumption", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: "+string);
                try {
                    final ZcLcxxBean zcLcxxBean = new Gson().fromJson(string, ZcLcxxBean.class);
                    if (zcLcxxBean.getCode() == 200) {
                        final ZcLcxxBean.RowsDTO rowsDTO = zcLcxxBean.getRows().get(0);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final List<ZcLcxxBean.RowsDTO> zcLcxxBeanRows = zcLcxxBean.getRows();
                                for (ZcLcxxBean.RowsDTO zcLcxxBeanRow : zcLcxxBeanRows) {
                                    ljjyje = ljjyje+zcLcxxBeanRow.getAmount();
                                    ljjyl = ljjyl+zcLcxxBeanRow.getGasFilling();
                                    ljxslc = ljxslc+zcLcxxBeanRow.getTravelDistance();
                                }
                                jiayouliang.setText(ljjyl + "L");
                                jine.setText(ljjyje + "元");
                                lichen.setText(ljxslc + "公里");
                                list.setAdapter(new BaseAdapter() {
                                    class ViewHolder {
                                        public View rootView;
                                        public TextView tjrq;
                                        public TextView licheng;
                                        public TextView jine;
                                        public TextView youjia;
                                        public TextView youliang;

                                        public ViewHolder(View rootView) {
                                            this.rootView = rootView;
                                            this.tjrq = (TextView) rootView.findViewById(R.id.tjrq);
                                            this.licheng = (TextView) rootView.findViewById(R.id.licheng);
                                            this.jine = (TextView) rootView.findViewById(R.id.jine);
                                            this.youjia = (TextView) rootView.findViewById(R.id.youjia);
                                            this.youliang = (TextView) rootView.findViewById(R.id.youliang);
                                        }

                                    }

                                    @Override
                                    public int getCount() {
                                        return zcLcxxBeanRows.size();
                                    }

                                    @Override
                                    public Object getItem(int position) {
                                        return zcLcxxBeanRows.get(position);
                                    }

                                    @Override
                                    public long getItemId(int position) {
                                        return position;
                                    }

                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_zc_jyjl, null);
                                        ZcLcxxBean.RowsDTO rowsDTO1 = zcLcxxBeanRows.get(position);
                                        ViewHolder viewHolder = new ViewHolder(view);
                                        viewHolder.jine.setText("金额："+rowsDTO1.getAmount()+"元");
                                        viewHolder.licheng.setText("里程："+rowsDTO1.getTravelDistance()+"公里");
                                        viewHolder.tjrq.setText("添加日期："+rowsDTO1.getTravelDate());
                                        int youjias = rowsDTO1.getAmount()/rowsDTO1.getGasFilling();
                                        viewHolder.youjia.setText("油价："+youjias+"元/L");
                                        viewHolder.youliang.setText("加油量："+rowsDTO1.getGasFilling()+"L");
                                        return view;
                                    }
                                });
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           showToast("未获取到车辆数据");
                       }
                   });
                }
            }
        });
    }

    private void initView(View view) {
        jiayouliang = (TextView) view.findViewById(R.id.jiayouliang);
        jine = (TextView) view.findViewById(R.id.jine);
        lichen = (TextView) view.findViewById(R.id.lichen);
        list = (ListViewScrollView) view.findViewById(R.id.list);
        tianjiajilu = (Button) view.findViewById(R.id.tianjiajilu);
    }
}