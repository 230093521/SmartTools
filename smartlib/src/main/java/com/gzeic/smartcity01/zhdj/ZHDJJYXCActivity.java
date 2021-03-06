package com.gzeic.smartcity01.zhdj;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.LoginBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZHDJJYXCActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBase;
    private EditText etFeedback;
    private TextView submit;
    private TextView tvZishu;
    private ListViewScrollView plList;
    private List<ZHDJDYXXXQActivity.testZiyuan> ziyuanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_jyxc);
        initView();
        ivBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ziyuanList = new ArrayList<>();
        ziyuanList.add(new ZHDJDYXXXQActivity.testZiyuan("张三", "2020-12-22 12:30", "为加强基层党组织和群众的关系，进一步推进基层党员进社区活动，5月10日上午，崇文社区带领社区党员来到南京市雨花台烈士陵园进行参观，参观学习过程中，党员们互相交流经验，讨论了在生活当中群众遇到的热点难点问题，切实把人们的思想统一到党的十九大精神上来，把力量凝聚到实现党的十九大确定的任务中来。参观结束后，党员们回到社区进行座谈会，进一步巩固心得。"));
        ziyuanList.add(new ZHDJDYXXXQActivity.testZiyuan("李四", "2020-12-22 12:30", "提升党性修养，弘扬爱国主义精神，践行社会主义核心价值观，增强“四个意识”、坚定“四个自信”、做到“两个维护”注入了一剂有力的“强心剂”"));
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
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_xw_pl, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.plx_image.setImageResource(R.drawable.ic_icon);
            viewHolder.plx_nickname.setText(ziyuanList.get(i).getName());
            viewHolder.plx_content.setText(ziyuanList.get(i).getNeirong());
            viewHolder.plx_time.setText(ziyuanList.get(i).getTime());
            viewHolder.dianzanll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("点赞成功");
                }
            });
            return view;
        }

        class ViewHolder {
            public View rootView;
            public ImageView plx_image;
            public TextView plx_nickname;
            public TextView plx_time;
            public TextView plx_content;
            public TextView dianzanshu;
            public LinearLayout dianzanll;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.plx_image = (ImageView) rootView.findViewById(R.id.plx_image);
                this.plx_nickname = (TextView) rootView.findViewById(R.id.plx_nickname);
                this.plx_time = (TextView) rootView.findViewById(R.id.plx_time);
                this.plx_content = (TextView) rootView.findViewById(R.id.plx_content);
                this.dianzanshu = (TextView) rootView.findViewById(R.id.dianzanshu);
                this.dianzanll = (LinearLayout) rootView.findViewById(R.id.dianzanll);
            }

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
                                ziyuanList.add(new ZHDJDYXXXQActivity.testZiyuan("张三", "2020-12-22 12:30", etFeedback.getText().toString()));
                                plList.setAdapter(new MyAdapter());
                            }
                        });
                    }
                }
            });
        }
    }
}