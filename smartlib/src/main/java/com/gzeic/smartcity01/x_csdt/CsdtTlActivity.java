package com.gzeic.smartcity01.x_csdt;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.XwlblbBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CsdtTlActivity extends BaseActivity {

    private ImageView metroBase;
    private ListView listDitie;
    View view;
    float posX, posY, curPosX, curPosY;
    private float mPosX, mPosY, mCurPosX, mCurPosY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdt_tl);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gettuli();

    }


    private void gettuli() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/press/press/list?type=4&pageNum=1&pageSize=6", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG, "onResponse: " + string);
                final XwlblbBean xwlbBean = new Gson().fromJson(string, XwlblbBean.class);
                if (xwlbBean.getCode() == 200) {
                    final List<XwlblbBean.RowsDTO> xwlbBeanRows = xwlbBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listDitie.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public ImageView tltb;
                                    public TextView tlmc;
                                    public TextView tlnr;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tltb = (ImageView) rootView.findViewById(R.id.tltb);
                                        this.tlmc = (TextView) rootView.findViewById(R.id.tlmc);
                                        this.tlnr = (TextView) rootView.findViewById(R.id.tlnr);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return xwlbBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return xwlbBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = LayoutInflater.from(CsdtTlActivity.this).inflate(R.layout.item_dt_tl, null);
                                    XwlblbBean.RowsDTO rowsDTO = xwlbBeanRows.get(position);
                                    ViewHolder viewHolder = new ViewHolder(inflate);
                                    viewHolder.tlmc.setText(rowsDTO.getTitle());
                                    viewHolder.tlnr.setText(Html.fromHtml(rowsDTO.getContent()));
                                    return inflate;
                                }
                            });
                            listDitie.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    switch (event.getAction()) {
                                        case MotionEvent.ACTION_DOWN:
                                            mPosX = event.getX();
                                            mPosY = event.getY();
                                            break;
                                        case MotionEvent.ACTION_MOVE:
                                            mCurPosX = event.getX();
                                            mCurPosY = event.getY();
                                            break;
                                        case MotionEvent.ACTION_UP:
                                            if (mCurPosX - mPosX > 0
                                                    && (Math.abs(mCurPosX - mPosX) > 400)) {
                                                Log.e("TAG", "wang向右");
                                                finish();
                                            } else if (mCurPosX - mPosX < 0
                                                    && (Math.abs(mCurPosX - mPosX) > 25)) {
                                                Log.e("TAG", "wang向左");
                                            }
                                            break;
                                    }
                                    return false;
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
        listDitie = (ListView) findViewById(R.id.list_ditie);
    }
}