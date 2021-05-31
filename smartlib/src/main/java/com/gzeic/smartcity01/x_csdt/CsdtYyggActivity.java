package com.gzeic.smartcity01.x_csdt;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.DTgglbBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtYyggActivity extends BaseActivity {

    private ImageView metroBase;
    private TextView homeTitle;
    private ListView chenchelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_yygg);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getYygg();
    }

    private void getYygg() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/notice/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                DTgglbBean dTgglbBean = new Gson().fromJson(string, DTgglbBean.class);
                if (dTgglbBean.getCode() == 200) {
                    final List<DTgglbBean.RowsDTO> dTgglbBeanRows = dTgglbBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chenchelist.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView biaoti;
                                    public TextView shijian;
                                    public TextView neirong;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.biaoti = (TextView) rootView.findViewById(R.id.biaoti);
                                        this.shijian = (TextView) rootView.findViewById(R.id.shijian);
                                        this.neirong = (TextView) rootView.findViewById(R.id.neirong);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return dTgglbBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return dTgglbBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = LayoutInflater.from(CsdtYyggActivity.this).inflate(R.layout.item_gg, null);
                                    DTgglbBean.RowsDTO rowsDTO = dTgglbBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    viewHolder.shijian.setVisibility(View.GONE);
                                    viewHolder.biaoti.setText(rowsDTO.getTitle());
                                    viewHolder.neirong.setText(Html.fromHtml(rowsDTO.getContent()));
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
        metroBase = (ImageView) findViewById(R.id.metro_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        chenchelist = (ListView) findViewById(R.id.chenchelist);
    }
}