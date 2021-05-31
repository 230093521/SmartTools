package com.gzeic.smartcity01.jifen;

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
import com.gzeic.smartcity01.bean.JfJlBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoJifenjlActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_jifenjl);
        initView();
        getJfJl();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getJfJl() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/score/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JfJlBean jfJlBean = new Gson().fromJson(string, JfJlBean.class);
                if (jfJlBean.getCode() == 200) {
                    final List<JfJlBean.RowsDTO> rows = jfJlBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView jine;
                                    public TextView hqsj;
                                    public TextView hqjf;
                                    public TextView zhifu;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.hqsj = (TextView) rootView.findViewById(R.id.hqsj);
                                        this.hqjf = (TextView) rootView.findViewById(R.id.hqjf);
                                        this.zhifu = (TextView) rootView.findViewById(R.id.zhifu);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return rows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return rows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = null;
                                    JfJlBean.RowsDTO rowsDTO = rows.get(position);
                                    if (rowsDTO.getType().equals("获取")) {
                                        inflate = LayoutInflater.from(WoJifenjlActivity.this).inflate(R.layout.item_wo_jfjl1, null);
                                    } else if (rowsDTO.getType().equals("扣除")) {
                                        inflate = LayoutInflater.from(WoJifenjlActivity.this).inflate(R.layout.item_wo_jfjl1, null);
                                    }
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    viewHolder.jine.setText(rowsDTO.getScore()+"");
                                    viewHolder.hqjf.setText(rowsDTO.getScore()+"");
                                    viewHolder.hqsj.setText(rowsDTO.getChangeDate());
                                    viewHolder.zhifu.setText(rowsDTO.getEvent());
                                    return inflate;
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
        list = (ListView) findViewById(R.id.list);
    }
}