package com.gzeic.smartcity01.mzyy;

import android.content.Intent;
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
import com.gzeic.smartcity01.bean.KaPianBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MzJzkpActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listCard;
    private RelativeLayout addJiuzhen;
    List<KaPianBean.RowsDTO> rows;
//    KaPianBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_mz_jzkp);
        initView();
        inintlist();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inintlist() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/hospital/patient/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                KaPianBean kaPianBean = new Gson().fromJson(string, KaPianBean.class);
                rows = kaPianBean.getRows();
                if (kaPianBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listCard.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView name;
                                    public ImageView setcard;
                                    public TextView idcard;
                                    public TextView phone;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.name = (TextView) rootView.findViewById(R.id.name);
                                        this.setcard = (ImageView) rootView.findViewById(R.id.setcard);
                                        this.idcard = (TextView) rootView.findViewById(R.id.idcard);
                                        this.phone = (TextView) rootView.findViewById(R.id.phone);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return rows.size();
                                }

                                @Override
                                public Object getItem(int i) {
                                    return rows.get(i);
                                }

                                @Override
                                public long getItemId(int i) {
                                    return i;
                                }

                                @Override
                                public View getView(int i, View view, ViewGroup viewGroup) {
                                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_mz_jzk, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
//                                    rowsBean = rows.get(i);
                                    final KaPianBean.RowsDTO rowsBeansss = rows.get(i);
                                    viewHolder.name.setText(rowsBeansss.getName());
                                    viewHolder.idcard.setText(rowsBeansss.getCardId());
                                    viewHolder.phone.setText(rowsBeansss.getTel());
                                    viewHolder.setcard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("kapian",new Gson().toJson(rowsBeansss));
                                            putSP("jiemian","001");
                                            startActivity(new Intent(MzJzkpActivity.this, MzJzkxxActivity.class));
                                        }
                                    });
                                    viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            putSP("kapian",new Gson().toJson(rowsBeansss));
                                            putSP("jiemian","002");
                                            startActivity(new Intent(MzJzkpActivity.this, MzGhActivity.class));
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
        metroBase = (ImageView) findViewById(R.id.metro_base);
        listCard = (ListView) findViewById(R.id.list_card);
        addJiuzhen = (RelativeLayout) findViewById(R.id.add_jiuzhen);
        addJiuzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putSP("jiemian","002");
//                putSP("kapian",new Gson().toJson(rowsBean));
                startActivity(new Intent(MzJzkpActivity.this, MzJzkxxActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        inintlist();
    }
}