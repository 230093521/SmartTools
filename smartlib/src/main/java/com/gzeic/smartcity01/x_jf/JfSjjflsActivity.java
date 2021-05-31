package com.gzeic.smartcity01.x_jf;

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
import com.gzeic.smartcity01.bean.JfSjjlBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfSjjflsActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_sjjfls);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCysj();
    }
    private void getCysj() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/phone/record/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JfSjjlBean jfSjjlBean = new Gson().fromJson(string, JfSjjlBean.class);
                if (jfSjjlBean.getCode() == 200) {
                    List<JfSjjlBean.RowsDTO> rows = jfSjjlBean.getRows();
                    HashSet<JfSjjlBean.RowsDTO> rowsDTOS = new HashSet<>(rows);
                    rows = new ArrayList<>(rowsDTOS);
                    final List<JfSjjlBean.RowsDTO> finalRows = rows;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView jine;
                                    public TextView shoujihao;
                                    public TextView shijian;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.shoujihao = (TextView) rootView.findViewById(R.id.shoujihao);
                                        this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return finalRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return finalRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(JfSjjflsActivity.this).inflate(R.layout.item_jf_sj_ls, null);
                                    final JfSjjlBean.RowsDTO rowsDTO = finalRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.jine.setText("缴费金额:"+rowsDTO.getRechargeAmount()+"");
                                    viewHolder.shijian.setText("手机号:"+rowsDTO.getRechargeTime());
                                    viewHolder.shoujihao.setText("交费时间:"+rowsDTO.getPhonenumber());
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
        homeBase = (ImageView) findViewById(R.id.home_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        list = (ListView) findViewById(R.id.list);
    }
}