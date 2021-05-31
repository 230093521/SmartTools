package com.gzeic.smartcity01.x_yy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
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

public class YYDFragment extends BaseFragment {
    private EditText homeEditSearch;
    private ListViewScrollView yingyuanlist;
    List<YyYpxxBean.RowsBean> rows = new ArrayList<>();
    BaseAdapter baseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yy_d, container, false);
        initView(view);

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
                Button psssf = view.findViewById(R.id.btn_goupiaos);
                LinearLayout lin = view.findViewById(R.id.lin);
                ImageView tupian = view.findViewById(R.id.tupian);
                name.setText(rows.get(position).getName());
                yplx.setText("影片类型："+yplxs[Integer.valueOf(rows.get(position).getCategory())-1]);
                bflx.setText("播放类型"+ypbflxs[Integer.valueOf(rows.get(position).getPlayType())-1]);
                pf.setText(rows.get(position).getScore() * 2 + "分");
                Glide.with(parent.getContext()).load("http://" + getServerIp() + rows.get(position).getCover()).error(R.drawable.icon2).into(tupian);
                psssf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), YyYpxqActivity.class);
                        intent.putExtra("id",rows.get(position).getId());
                        startActivity(intent);
                    }
                });
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), YyYpxqActivity.class);
                        intent.putExtra("id",rows.get(position).getId());
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        yingyuanlist.setAdapter(baseAdapter);
        req(null);
        return view;
    }
    private void req(String stringExtra) {
        String url = null;
        if (stringExtra==null){
            url = "http://"+getServerIp()+"/prod-api/api/movie/film/list";
        }else{
            url = "http://"+getServerIp()+"/prod-api/api/movie/film/list?name="+stringExtra;
        }
        getTools().sendGet(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("aaa",string);
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
    private void initView(View view) {
        homeEditSearch = view.findViewById(R.id.home_edit_search);
        yingyuanlist = view.findViewById(R.id.yingyuanlist);
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == KeyEvent.KEYCODE_HOME){
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String s = v.getText().toString();
                    if (s==null||s.equals("")){
                        Toast.makeText(getContext(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
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