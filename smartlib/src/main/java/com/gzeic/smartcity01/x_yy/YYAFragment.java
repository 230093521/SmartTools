package com.gzeic.smartcity01.x_yy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ApiUrl;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.Tools.SwitchTextView;
import com.gzeic.smartcity01.bean.TianQiBean;
import com.gzeic.smartcity01.bean.YyYpLbBean;
import com.gzeic.smartcity01.bean.YyYpxxBean;
import com.gzeic.smartcity01.bean.YyYpygBean;
import com.gzeic.smartcity01.bean.YyxqBean;
import com.gzeic.smartcity01.x_jf.JfTqActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YYAFragment extends BaseFragment {

    private ImageView ivMei;
    private EditText homeEditSearch;
    private TextView homeSearchBase;
    private Banner banner;
    private LinearLayout tianqi;
    private ImageView tqtb;
    private SwitchTextView tqgd;
    private LinearLayout tuijian;
    private ImageView yugao;
    private ImageView yingping;
    private ImageView xingwen;
    private LinearLayout ry;
    private LinearLayout yg;
    private ListViewScrollView zhoubianlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yy_a, container, false);
        initView(view);
        tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyTjActivity.class));
            }
        });
        yugao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyYgpActivity.class));
            }
        });
        yingping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyYinPingActivity.class));
            }
        });
        xingwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyXwActivity.class));
            }
        });
        getTianQi();
        //搜索
        homeEditSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_HOME) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    String s = v.getText().toString();
                    if (s == null || s.equals("")) {
                        Toast.makeText(getContext(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getContext(), YySousActivity.class);
                        intent.putExtra("conent", s);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
        tianqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), JfTqActivity.class));
            }
        });
        getLunbo();
        getYpxxLb();
        getYglb();
        getYylb();
        return view;
//        String cover = rowsBean.getCover();
//        String replace = cover.replace("http://118.190.154.52:7777/", "/prod-api/");
    }
    //影院列表
    private void getYylb() {
        getTools().sendGet(HTTP_HEAD + getServerIp() + "/prod-api/api/movie/theatre/list").enqueue(new Callback<ResponseBody>() {
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
                zhoubianlist.setAdapter(new BaseAdapter() {
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
    //预告列表
    private void getYglb() {
        getTools().sendGet(HTTP_HEAD + getServerIp() + "/prod-api/api/movie/film/preview/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpygBean yg1 = gson.fromJson(string, YyYpygBean.class);
                List<YyYpygBean.RowsBean> rows = yg1.getRows();
                for (final YyYpygBean.RowsBean rowsBean : rows) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.itme_yya, yg, false);
                    ImageView img = inflate.findViewById(R.id.img);
                    Button btn = inflate.findViewById(R.id.btn);
                    btn.setText("想看");
                    TextView bt = inflate.findViewById(R.id.bt);
                    bt.setText(rowsBean.getName());
                    Glide.with(getContext()).load(HTTP_HEAD + getServerIp() + rowsBean.getCover()).into(img);
                    yg.addView(inflate);
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(getContext(), YyYpxqActivity.class);
//                            intent.putExtra("id", rowsBean.getId());
//                            startActivity(intent);
                            Intent intent = new Intent(getContext(), YyypsyActivity.class);
                            Gson gson1 = new Gson();
                            String s = gson1.toJson(rowsBean);
                            intent.putExtra("jjsy", s);
                            startActivity(intent);
                        }
                    });
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiUrl.setServerApiToken("Bearer " + getToken());
                            getTools().sendPostJsonToken(HTTP_HEAD + getServerIp() + "/prod-api/api/movie/film/like/" + rowsBean.getId(), new HashMap<String, Object>()).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(getContext(), "已收藏", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });

    }
    //影片信息列表
    private void getYpxxLb() {
        getTools().sendGet(HTTP_HEAD + getServerIp() + "/prod-api/api/movie/film/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Gson gson = new Gson();
                YyYpxxBean ypxx = gson.fromJson(string, YyYpxxBean.class);
                List<YyYpxxBean.RowsBean> rows = ypxx.getRows();
                for (final YyYpxxBean.RowsBean rowsBean : rows) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.itme_yya, ry, false);
                    ImageView img = inflate.findViewById(R.id.img);
                    Button btn = inflate.findViewById(R.id.btn);
                    TextView bt = inflate.findViewById(R.id.bt);
                    bt.setText(rowsBean.getName());
                    Glide.with(getContext()).load(HTTP_HEAD + getServerIp() + rowsBean.getCover()).error(R.drawable.icon2).into(img);
                    ry.addView(inflate);
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), YyYpxqActivity.class);
                            intent.putExtra("id", rowsBean.getId());
                            startActivity(intent);
                        }
                    });
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), YyGpActivity.class);
                            intent.putExtra("id", rowsBean.getId());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });
    }
    //轮播图
    private void getLunbo() {
        getTools().sendGet(HTTP_HEAD + getServerIp() + "/prod-api/api/movie/rotation/list").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String string = null;
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                YyYpLbBean lb = gson.fromJson(string, YyYpLbBean.class);
                final List<YyYpLbBean.RowsBean> rows = lb.getRows();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(rows);
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object o, ImageView imageView) {
                                YyYpLbBean.RowsBean rowsBean = (YyYpLbBean.RowsBean) o;
                                Glide.with(getContext()).load(HTTP_HEAD + getServerIp() + rowsBean.getAdvImg()).into(imageView);
                            }
                        });
                        banner.start();
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            }
        });

    }
    //天气
    private void getTianQi() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/common/weather/today", getToken(), new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                final TianQiBean tianQiBean = new Gson().fromJson(string, TianQiBean.class);
                if (tianQiBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TianQiBean.DataDTO tianQiBeanData = tianQiBean.getData();
                            if (tianQiBeanData.getWeather().equals("晴")) {
                                tqtb.setImageResource(R.drawable.qingtian);
                            } else if (tianQiBeanData.getWeather().equals("雨")) {
                                tqtb.setImageResource(R.drawable.yutian);
                            } else {
                                tqtb.setImageResource(R.drawable.yingtian);
                            }
                            List<String> texts = new ArrayList<>();
                            texts.add("当前温度：" + tianQiBeanData.getTemperature() + "℃ 湿度：" + tianQiBeanData.getHumidity() + "  空气质量：" + tianQiBeanData.getAir());
                            tqgd.startPlay(texts);
                        }
                    });
                }
            }
        });
    }


    private void initView(View view) {
        ivMei = (ImageView) view.findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        homeSearchBase = (TextView) view.findViewById(R.id.home_search_base);
        banner = (Banner) view.findViewById(R.id.banner);
        tianqi = (LinearLayout) view.findViewById(R.id.tianqi);
        tqtb = (ImageView) view.findViewById(R.id.tqtb);
        tqgd = (SwitchTextView) view.findViewById(R.id.tqgd);
        tuijian = (LinearLayout) view.findViewById(R.id.tuijian);
        yugao = (ImageView) view.findViewById(R.id.yugao);
        yingping = (ImageView) view.findViewById(R.id.yingping);
        xingwen = (ImageView) view.findViewById(R.id.xingwen);
        ry = (LinearLayout) view.findViewById(R.id.ry);
        yg = (LinearLayout) view.findViewById(R.id.yg);
        zhoubianlist = (ListViewScrollView) view.findViewById(R.id.zhoubianlist);
    }
}