package com.gzeic.smartcity01.dcyc;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.NuoCheBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class DCBFragment extends BaseFragment {


    private ListView nuochejilulist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dc_b, container, false);
        initView(view);
        getjilu();

        return view;
    }

    private void getjilu() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/caraction/list?userId=1&pageNum=1&pageSize=1", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    NuoCheBean nuoCheBean = new Gson().fromJson(string, NuoCheBean.class);
                    String json = "{\n" +
                            "    \"total\": 2,\n" +
                            "    \"rows\": [\n" +
                            "        {\n" +
                            "            \"searchValue\": null,\n" +
                            "            \"createBy\": null,\n" +
                            "            \"createTime\": null,\n" +
                            "            \"updateBy\": null,\n" +
                            "            \"updateTime\": null,\n" +
                            "            \"remark\": null,\n" +
                            "            \"params\": {},\n" +
                            "            \"id\": 2,\n" +
                            "            \"cardId\": \"423016199812141526\",\n" +
                            "            \"names\": \"李四\",\n" +
                            "            \"tel\": \"18546235454\",\n" +
                            "            \"address\": \"高新区街道10号\",\n" +
                            "            \"imgUrl\": \"/profile/2020/11/04/601a561d-da76-498d-8bff-9d133743755c.jpg\",\n" +
                            "            \"plates\": \"辽FS1009\\t\",\n" +
                            "            \"userId\": 1,\n" +
                            "            \"file\": null\n" +
                            "        }\n" +
                            "    ],\n" +
                            "    \"code\": 200,\n" +
                            "    \"msg\": \"查询成功\"\n" +
                            "}";
                    NuoCheBean nuoCheBean1 = new Gson().fromJson(json, NuoCheBean.class);
                    final List<NuoCheBean.RowsDTO> nuoCheBeanRows = nuoCheBean.getRows();
                    final List<NuoCheBean.RowsDTO> nuoCheBeanRows1 = nuoCheBean1.getRows();
                    nuoCheBeanRows.addAll(nuoCheBeanRows1);
                    if (nuoCheBean.getCode() == 200) {
                      getActivity().runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              nuochejilulist.setAdapter(new BaseAdapter() {
                                  class ViewHolder {
                                      public View rootView;
                                      public TextView chepai;
                                      public TextView xinmin;
                                      public TextView dianhua;
                                      public TextView dizhi;

                                      public ViewHolder(View rootView) {
                                          this.rootView = rootView;
                                          this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                                          this.xinmin = (TextView) rootView.findViewById(R.id.xinmin);
                                          this.dianhua = (TextView) rootView.findViewById(R.id.dianhua);
                                          this.dizhi = (TextView) rootView.findViewById(R.id.dizhi);
                                      }

                                  }

                                  @Override
                                  public int getCount() {
                                      return nuoCheBeanRows.size();
                                  }

                                  @Override
                                  public Object getItem(int position) {
                                      return nuoCheBeanRows.get(position);
                                  }

                                  @Override
                                  public long getItemId(int position) {
                                      return position;
                                  }

                                  @Override
                                  public View getView(int position, View convertView, ViewGroup parent) {
                                      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_dcyc_ncjl, null);
                                      ViewHolder viewHolder = new ViewHolder(convertView);
                                      NuoCheBean.RowsDTO rowsDTO = nuoCheBeanRows.get(position);
                                      viewHolder.chepai.setText(rowsDTO.getPlates());
                                      viewHolder.dianhua.setText(rowsDTO.getTel());
                                      viewHolder.dizhi.setText(rowsDTO.getAddress());
                                      viewHolder.xinmin.setText(rowsDTO.getNames());
                                      return convertView;
                                  }
                              });
                          }
                      });
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getjilu();
    }

    private void initView(View view) {
        nuochejilulist = (ListView) view.findViewById(R.id.nuochejilulist);
    }
}