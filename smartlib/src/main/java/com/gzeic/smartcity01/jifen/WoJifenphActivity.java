package com.gzeic.smartcity01.jifen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.JFphBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoJifenphActivity extends BaseActivity {

    private RelativeLayout homeToprl;
    private ImageView orderBase;
    private TextView homeTitle;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_jifenph);
        initView();
        orderBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPaiHang();
    }

    private void getPaiHang() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/park/score/top/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JFphBean jFphBean = new Gson().fromJson(string, JFphBean.class);
                if (jFphBean.getCode() == 200) {
                    final List<JFphBean.RowsDTO> jFphBeanRows = jFphBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public ImageView news_image;
                                    public ImageView huizhang;
                                    public TextView paimin;
                                    public TextView name;
                                    public TextView jifen;
                                    public LinearLayout news_ll;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                                        this.huizhang = (ImageView) rootView.findViewById(R.id.huizhang);
                                        this.paimin = (TextView) rootView.findViewById(R.id.paimin);
                                        this.name = (TextView) rootView.findViewById(R.id.name);
                                        this.jifen = (TextView) rootView.findViewById(R.id.jifen);
                                        this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return jFphBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return jFphBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(WoJifenphActivity.this).inflate(R.layout.item_wo_jfph, null);
                                    JFphBean.RowsDTO rowsDTO = jFphBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    viewHolder.name.setText(rowsDTO.getNickName());
                                    if (position==0){
                                        viewHolder.name.setTextColor(Color.YELLOW);
                                        viewHolder.huizhang.setVisibility(View.VISIBLE);
                                    }else if (position==1){
                                        viewHolder.name.setTextColor(Color.BLUE);
                                        viewHolder.huizhang.setImageResource(R.drawable.yajun);
                                        viewHolder.huizhang.setVisibility(View.VISIBLE);
                                    }else if (position==2){
                                        viewHolder.name.setTextColor(Color.CYAN);
                                        viewHolder.huizhang.setImageResource(R.drawable.jijun);
                                        viewHolder.huizhang.setVisibility(View.VISIBLE);
                                    }else {
                                        viewHolder.name.setTextColor(Color.GRAY);
                                        viewHolder.huizhang.setVisibility(View.GONE);
                                    }
                                    Glide.with(WoJifenphActivity.this).load("http://"+getServerIp()+rowsDTO.getAvatar()).error(R.drawable.icon2).into(viewHolder.news_image);
                                    viewHolder.jifen.setText(rowsDTO.getScore()+"");
                                    viewHolder.paimin.setText(position+1+"");
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
        list = (ListView) findViewById(R.id.list);
    }
}