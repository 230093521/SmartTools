package com.gzeic.smartcity01.jifen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.JfhgBean;
import com.gzeic.smartcity01.bean.UsersBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoJifenhgActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private TextView yonghuming;
    private TextView kyjf;
    private LinearLayout newsLl;
    private ImageView newsImage;
    private TextView newsTitle;
    private TextView jifen;
    private Button duihuan1;
    private Button duihuan2;
    private Button duihuan3;
    private ListViewScrollView listview;
    UsersBean.UserDTO userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_jifenhg);
        initView();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userdata = getUserdata();
        yonghuming.setText("用户名：" + userdata.getNickName());
        kyjf.setText(userdata.getScore() + "");
        duihuan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userdata.getScore() < 100) {
                    showToast("积分不足");
                } else {
                    userdata.setScore(userdata.getScore() - 100);
                    kyjf.setText(userdata.getScore() + "");
                    showToast("兑换成功");
                }
            }
        });
        duihuan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userdata.getScore() < 300) {
                    showToast("积分不足");
                } else {
                    userdata.setScore(userdata.getScore() - 300);
                    kyjf.setText(userdata.getScore() + "");
                    showToast("兑换成功");
                }
            }
        });
        duihuan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userdata.getScore() < 500) {
                    showToast("积分不足");
                } else {
                    userdata.setScore(userdata.getScore() - 500);
                    kyjf.setText(userdata.getScore() + "");
                    showToast("兑换成功");
                }
            }
        });
        getJfhg();
    }

    private void getJfhg() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/product/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JfhgBean jfhgBean = new Gson().fromJson(string, JfhgBean.class);
                if (jfhgBean.getCode() == 200) {
                    final List<JfhgBean.RowsDTO> jfhgBeanRows = jfhgBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView name;
                                    public TextView jifen;
                                    public Button duihuan;
                                    public LinearLayout news_ll;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.name = (TextView) rootView.findViewById(R.id.name);
                                        this.jifen = (TextView) rootView.findViewById(R.id.jifen);
                                        this.duihuan = (Button) rootView.findViewById(R.id.duihuan);
                                        this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return jfhgBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return jfhgBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(WoJifenhgActivity.this).inflate(R.layout.item_jf_hg, null);
                                    final JfhgBean.RowsDTO rowsDTO = jfhgBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.name.setText(rowsDTO.getName());
                                    viewHolder.jifen.setText(rowsDTO.getScore() + "");
                                    viewHolder.duihuan.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (userdata.getScore() < rowsDTO.getScore()) {
                                                showToast("积分不足");
                                            } else {
                                                userdata.setScore(userdata.getScore() - rowsDTO.getScore());
                                                kyjf.setText(userdata.getScore() + "");
                                                showToast("兑换成功");
                                            }
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
        orderBase = (ImageView) findViewById(R.id.order_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        yonghuming = (TextView) findViewById(R.id.yonghuming);
        kyjf = (TextView) findViewById(R.id.kyjf);
        newsLl = (LinearLayout) findViewById(R.id.news_ll);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsTitle = (TextView) findViewById(R.id.news_title);
        jifen = (TextView) findViewById(R.id.jifen);
        duihuan1 = (Button) findViewById(R.id.duihuan1);
        duihuan2 = (Button) findViewById(R.id.duihuan2);
        duihuan3 = (Button) findViewById(R.id.duihuan3);
        listview = (ListViewScrollView) findViewById(R.id.listview);
    }
}