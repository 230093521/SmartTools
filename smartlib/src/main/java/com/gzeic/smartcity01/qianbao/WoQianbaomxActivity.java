package com.gzeic.smartcity01.qianbao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.QBBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoQianbaomxActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private ListView jilulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_qianbaomx);
        initView();
        getQBMX();
    }

    private void getQBMX() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/recharge/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                QBBean qbBean = new Gson().fromJson(string, QBBean.class);
                if (qbBean.getCode() == 200) {
                    final List<QBBean.RowsDTO> qbBeanRows = qbBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jilulist.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView jine;
                                    public TextView shijian;
                                    public TextView zhifu;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                                        this.zhifu = (TextView) rootView.findViewById(R.id.zhifu);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return qbBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return qbBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(WoQianbaomxActivity.this).inflate(R.layout.item_wo_czjl, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    QBBean.RowsDTO rowsDTO = qbBeanRows.get(position);
                                    viewHolder.shijian.setText(rowsDTO.getRechargeDate());
                                    viewHolder.jine.setText(rowsDTO.getMoney()+"元");
                                    viewHolder.zhifu.setText(rowsDTO.getPayType());
                                    return view;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        homeToprl = (RelativeLayout) findViewById(R.id.home_toprl);
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        jilulist = (ListView) findViewById(R.id.jilulist);
    }
}