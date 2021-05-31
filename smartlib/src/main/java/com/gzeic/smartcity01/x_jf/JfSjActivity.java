package com.gzeic.smartcity01.x_jf;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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

public class JfSjActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView homeBase;
    private TextView homeTitle;
    private TextView jiaofeijilu;
    private RadioButton zgyd;
    private RadioButton zglt;
    private RadioButton zgdx;
    private EditText etShouji;
    private Button btnChaxun;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_sj);
        initView();
        homeBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCysj();
        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shoujihao = etShouji.getText().toString();
                if (shoujihao.isEmpty()){
                    showToast("手机号不能为空");
                    return;
                }
                if (shoujihao.length()<11){
                    showToast("请输入正确的手机号");
                    return;
                }
                String yunying =null;
                if (zgyd.isChecked()) {
                    yunying = "中国移动";
                } else if (zglt.isChecked()) {
                    yunying = "中国联通";
                } else{
                    yunying = "中国电信";
                }
                putSP("shoujihao", etShouji.getText().toString());
                putSP("yunyingshang", yunying);
                startActivity(new Intent(JfSjActivity.this, JfSjjfActivity.class));
            }
        });

        jiaofeijilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JfSjActivity.this, JfSjjflsActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                                    public TextView yys;
                                    public TextView shouji;
                                    public TextView shijian;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.yys = (TextView) rootView.findViewById(R.id.yys);
                                        this.shouji = (TextView) rootView.findViewById(R.id.shouji);
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
                                    View view = LayoutInflater.from(JfSjActivity.this).inflate(R.layout.item_jf_sj_hm, null);
                                    final JfSjjlBean.RowsDTO rowsDTO = finalRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.yys.setText("运营商:中国移动");
                                    viewHolder.shijian.setText("缴费金额:"+rowsDTO.getRechargeTime());
                                    viewHolder.shouji.setText("手机号:"+rowsDTO.getPhonenumber());
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            putSP("shoujihao", rowsDTO.getPhonenumber());
                                            putSP("yunyingshang", "中国移动");
                                            startActivity(new Intent(JfSjActivity.this, JfSjjfActivity.class));
                                        }
                                    });
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
        jiaofeijilu = (TextView) findViewById(R.id.jiaofeijilu);
        zgyd = (RadioButton) findViewById(R.id.zgyd);
        zglt = (RadioButton) findViewById(R.id.zglt);
        zgdx = (RadioButton) findViewById(R.id.zgdx);
        etShouji = (EditText) findViewById(R.id.et_shouji);
        btnChaxun = (Button) findViewById(R.id.btn_chaxun);
        list = (ListView) findViewById(R.id.list);
    }
}