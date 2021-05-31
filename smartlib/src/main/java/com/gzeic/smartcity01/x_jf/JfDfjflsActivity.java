package com.gzeic.smartcity01.x_jf;

import androidx.appcompat.app.AppCompatActivity;

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
import com.gzeic.smartcity01.bean.JFdsrBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JfDfjflsActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_dfjfls);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getJfls();
    }

    private void getJfls() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/living/recharge/record/list?categoryId=3&pageNum=1&pageSize=8", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JFdsrBean jfSjjlBean = new Gson().fromJson(string, JFdsrBean.class);
                if (jfSjjlBean.getCode() == 200) {
                    List<JFdsrBean.RowsDTO> rows = jfSjjlBean.getRows();
                    final List<JFdsrBean.RowsDTO> finalRows = rows;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalRows.size()==0){
                                showToast("此账户没有缴费记录");
                                return;
                            }
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView jine;
                                    public TextView sfh;
                                    public TextView jfsj;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.jine = (TextView) rootView.findViewById(R.id.jine);
                                        this.sfh = (TextView) rootView.findViewById(R.id.sfh);
                                        this.jfsj = (TextView) rootView.findViewById(R.id.jfsj);
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
                                    View view = LayoutInflater.from(JfDfjflsActivity.this).inflate(R.layout.item_jf_sf_jfls, null);
                                    final JFdsrBean.RowsDTO rowsDTO = finalRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.jine.setText("缴费金额:" + rowsDTO.getAmount() + "");
                                    viewHolder.sfh.setText("电费号:" + rowsDTO.getPaymentNo());
                                    viewHolder.jfsj.setText("缴费时间:" + rowsDTO.getRechargeTime());
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