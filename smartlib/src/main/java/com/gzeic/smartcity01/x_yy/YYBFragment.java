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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YyxqBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class YYBFragment extends BaseFragment {
    private String http = "http://";
    private String add;
    private ImageView ivMei;
    private EditText homeEditSearch;
    private ListViewScrollView yingyuanlist;
    private List<YyxqBean.RowsBean> rows = new ArrayList<>();
    private List<YyxqBean.RowsBean> rowss = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yy_b, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
        initView(view);
        getYylb("");
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == KeyEvent.KEYCODE_HOME){
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String s = v.getText().toString();
                    if (s.isEmpty()){
                        showToast("输入不能为空");
                        return true;
                    }
                    getYylb(s);
                    return true;
                }
                return false;
            }
        });

        return view;
    }
    private void initView(View view) {
        ivMei = view.findViewById(R.id.iv_mei);
        homeEditSearch = view.findViewById(R.id.home_edit_search);
        yingyuanlist = view.findViewById(R.id.yingyuanlist);
    }
    //影院列表
    private void getYylb(String name) {
        String url = HTTP_HEAD + getServerIp() + "/prod-api/api/movie/theatre/list";
        if (!name.equals("")){
            url = HTTP_HEAD + getServerIp() + "/prod-api/api/movie/theatre/list?name="+name;
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
                final Gson gson = new Gson();
                YyxqBean zb = gson.fromJson(string, YyxqBean.class);
                final List<YyxqBean.RowsBean> rows = zb.getRows();
                yingyuanlist.setAdapter(new BaseAdapter() {
                    class ViewHolder {
                        public View rootView;
                        public ImageView tupian;
                        public TextView name;
                        public TextView dizhi;
                        public RatingBar pingfen;
                        public LinearLayout news_ll;

                        public ViewHolder(View rootView) {
                            this.rootView = rootView;
                            this.tupian = (ImageView) rootView.findViewById(R.id.tupian);
                            this.name = (TextView) rootView.findViewById(R.id.name);
                            this.dizhi = (TextView) rootView.findViewById(R.id.dizhi);
                            this.pingfen = (RatingBar) rootView.findViewById(R.id.pingfen);
                            this.news_ll = (LinearLayout) rootView.findViewById(R.id.news_ll);
                        }
                    }

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
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.item_yy_yy, null);
                        ViewHolder viewHolder = new ViewHolder(view1);
                        final YyxqBean.RowsBean rowsBean = rows.get(position);
                        viewHolder.name.setText(rowsBean.getName());
                        viewHolder.dizhi.setText(rowsBean.getAddress());
                        float pf = rowsBean.getScore() + 0;
                        float pf1 = pf / 20;
                        viewHolder.pingfen.setRating(pf1);
                        String cover = rowsBean.getCover();
                        String replace = cover.replace("http://118.190.154.52:7777/", "/prod-api/");
                        Glide.with(getContext()).load(HTTP_HEAD + getServerIp() + replace).into(viewHolder.tupian);
                        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getContext(), YyYyxqActivity.class);
                                intent.putExtra("id", rowsBean.getId());
                                startActivity(intent);
                            }
                        });
                        return view1;
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });

    }


}