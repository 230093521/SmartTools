package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.bean.LoginBean;
import com.xsonline.smartlib.ui.ListViewScrollView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JYXCActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBase;
    private EditText etFeedback;
    private TextView submit;
    private TextView tvZishu;
    private ListViewScrollView plList;
    private List<DYXXXQActivity.testZiyuan> ziyuanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_j_y_x_c);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ziyuanList = new ArrayList<>();
        ziyuanList.add(new DYXXXQActivity.testZiyuan("张三", "2020-12-22 12:30", "测试留言内容"));
        ziyuanList.add(new DYXXXQActivity.testZiyuan("李四", "2020-12-22 12:30", "测试留言内容"));
        plList.setAdapter(new MyAdapter());
    }

    private void initView() {
        ivBase = (ImageView) findViewById(R.id.iv_base);
        etFeedback = (EditText) findViewById(R.id.et_feedback);
        submit = (TextView) findViewById(R.id.submit);
        tvZishu = (TextView) findViewById(R.id.tv_zishu);
        submit.setOnClickListener(this);
        ivBase.setOnClickListener(this);
        etFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Editable text = etFeedback.getText();
                int len = text.length();
                tvZishu.setText(len + "" + "/150");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        plList = (ListViewScrollView) findViewById(R.id.pl_list);
    }

    class MyAdapter extends BaseAdapter {
        class ViewHolder {
            public View rootView;
            public ImageView plx_image;
            public TextView plx_nickname;
            public TextView plx_time;
            public TextView plx_content;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.plx_image = (ImageView) rootView.findViewById(R.id.plx_image);
                this.plx_nickname = (TextView) rootView.findViewById(R.id.plx_nickname);
                this.plx_time = (TextView) rootView.findViewById(R.id.plx_time);
                this.plx_content = (TextView) rootView.findViewById(R.id.plx_content);
            }

        }

        @Override
        public int getCount() {
            return ziyuanList.size();
        }

        @Override
        public Object getItem(int i) {
            return ziyuanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_comment, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.plx_image.setImageResource(R.drawable.ic_icon);
            viewHolder.plx_nickname.setText(ziyuanList.get(i).getName());
            viewHolder.plx_content.setText(ziyuanList.get(i).getNeirong());
            viewHolder.plx_time.setText(ziyuanList.get(i).getTime());
            return view;
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_base) {
            finish();
        } else if (id == R.id.submit) {
            if (etFeedback.getText().toString().isEmpty()) {
                return;
            }
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", etFeedback.getText().toString());
                jsonObject.put("userId", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/userinfo/feedback", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i(TAG, "onResponse: " + json);
                    LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    if (loginBean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("提交成功");
                                ziyuanList.add(new DYXXXQActivity.testZiyuan("张三", "2020-12-22 12:30", etFeedback.getText().toString()));
                                plList.setAdapter(new MyAdapter());
                            }
                        });
                    }
                }
            });
        }
    }
}