package com.gzeic.smartcity01.x_yy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyYpxxBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gzeic.smartcity01.x_yy.YyYyxqActivity.ypbflxs;
import static com.gzeic.smartcity01.x_yy.YyYpxqActivity.yplxs;

public class YySousActivity extends BaseActivity {

    private ImageView back;
    private EditText homeEditSearch;
    private ListViewScrollView yingyuanlist;
    private String add;
    List<YyYpxxBean.RowsBean> rows = new ArrayList<>();
    BaseAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_ss);
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        initView();
        String stringExtra = getIntent().getStringExtra("conent");
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return rows.size();
            }

            @Override
            public Object getItem(int position) {
                return rows.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_yy_yplib, parent, false);
                TextView name = view.findViewById(R.id.name);
                TextView yplx = view.findViewById(R.id.yplx);
                TextView bflx = view.findViewById(R.id.bflx);
                TextView pf = view.findViewById(R.id.pf);
                LinearLayout lin = view.findViewById(R.id.lin);
                ImageView tupian = view.findViewById(R.id.tupian);
                name.setText(rows.get(position).getName());
                yplx.setText(yplxs[Integer.valueOf(rows.get(position).getCategory())-1]);
                bflx.setText(ypbflxs[Integer.valueOf(rows.get(position).getPlayType())-1]);
                pf.setText(rows.get(position).getScore() * 2 + "分");
                Glide.with(parent.getContext()).load("http://" + add + rows.get(position).getCover()).into(tupian);
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), YyYpxqActivity.class);
                        intent.putExtra("id",rows.get(position).getId());
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        yingyuanlist.setAdapter(baseAdapter);
        req(stringExtra);
    }

    private void req(String stringExtra) {
        getTools().sendGet("http://"+add+"/prod-api/api/movie/film/list?name="+stringExtra).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpxxBean ypxx = gson.fromJson(string, YyYpxxBean.class);
                rows = ypxx.getRows();
                baseAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }

    private void initView() {
        back = findViewById(R.id.back);
        homeEditSearch = findViewById(R.id.home_edit_search);
        yingyuanlist = findViewById(R.id.yingyuanlist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == KeyEvent.KEYCODE_HOME){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String s = v.getText().toString();
                    if (s==null||s.equals("")){
                        Toast.makeText(getApplication(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    }else{
                        req(s);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}