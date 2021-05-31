package com.gzeic.smartcity01.xw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.XwListBean;
import com.gzeic.smartcity01.bean.XwPlBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XinWenActivity extends BaseActivity {
    XwListBean.RowsDTO rowsDTO;
    private ImageView newsBase;
    private TextView homeTitle;
    private TextView newsTitle;
    private WebView webview;
    private ImageView tupian;
    private Button btnSousuo;
    private ListViewScrollView xwPllist;
    private EditText homeEditSearch;
    private TextView newsFbsj;
    private LinearLayout dianzanll;
    private TextView dianzanshu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xw);
        initView();
        String xwzx = getSP("xwzx");
        String yemian = getSP("yemian");
        try {
            if (yemian.equals("shzt")) {
                homeTitle.setText("主题详情");
            } else {
                homeTitle.setText("新闻详情");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "onCreate: " + xwzx + " " + yemian);
        rowsDTO = new Gson().fromJson(xwzx, XwListBean.RowsDTO.class);
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsTitle.setText(rowsDTO.getTitle());
        Glide.with(XinWenActivity.this).load("http://" + getServerIp() + rowsDTO.getCover()).into(tupian);
        String html = rowsDTO.getContent();
        html = html.replace("img src=\"", "img src=\"http://" + getServerIp() + "");
        html = html.replace("<img", "<img style=\"display:        ;max-width:100%;\"");
        Log.i("info", "src=\"/" + "xxxxxxxxxxxxx" + "src=\"http://" + getServerIp() + "");
        showWebView(html);
        Log.i(TAG, "onCreate: " + html);
        getXwpl();
        newsFbsj.setText(rowsDTO.getPublishDate());
        dianzanshu.setText(rowsDTO.getLikeNum() + "个赞");
        dianzanll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xwdianzan();
            }
        });

        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = homeEditSearch.getText().toString();
                if (toString.isEmpty()) {
                    showToast("内容不能为空");
                    return;
                }
                fapinglun();
            }
        });
    }

    private void xwdianzan() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",rowsDTO.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPutRequestToken(jsonObject,"http://" + getServerIp() + "/prod-api/press/press/like/"+rowsDTO.getId(), getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final XwPlBean xwPlBean = new Gson().fromJson(string, XwPlBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dianzanshu.setText(rowsDTO.getLikeNum()+1+"个赞");
                        showToast(xwPlBean.getMsg());
                    }
                });
            }
        });
    }

    private void fapinglun() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newsId", rowsDTO.getId());
            jsonObject.put("content", homeEditSearch.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/press/pressComment", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final XwPlBean xwPlBean = new Gson().fromJson(string, XwPlBean.class);
                if (xwPlBean.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(xwPlBean.getMsg());
                            getXwpl();
                        }
                    });
                }
            }
        });
    }

    private void getXwpl() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/press/comments/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                XwPlBean xwPlBean = new Gson().fromJson(string, XwPlBean.class);
                if (xwPlBean.getCode() == 200) {
                    final List<XwPlBean.RowsDTO> xwPlBeanRows = xwPlBean.getRows();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            xwPllist.setAdapter(new BaseAdapter() {
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

                                @Override
                                public int getCount() {
                                    return xwPlBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return xwPlBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(XinWenActivity.this).inflate(R.layout.item_xw_pl, null);
                                    final ViewHolder viewHolder = new ViewHolder(view);
                                    final XwPlBean.RowsDTO rowsDTO = xwPlBeanRows.get(position);
                                    Log.i(TAG, "getView: " + rowsDTO.getLikeNum() + "  " + rowsDTO.getId());
                                    viewHolder.dianzanshu.setText(String.valueOf(rowsDTO.getLikeNum()));
                                    viewHolder.plx_content.setText(rowsDTO.getContent());
                                    viewHolder.plx_nickname.setText(rowsDTO.getUserName());
                                    viewHolder.plx_time.setText(rowsDTO.getCommentDate());
                                    viewHolder.dianzanll.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            viewHolder.dianzanshu.setText(String.valueOf(rowsDTO.getLikeNum() + 1));
                                            pldianzan(rowsDTO.getId());
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

    private void pldianzan(int id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getTools().sendPutRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/press/pressComment/like/" + id, getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final XwPlBean xwPlBean = new Gson().fromJson(string, XwPlBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        getXwpl();
                        try {
                            showToast(xwPlBean.getMsg());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void showWebView(String html) {
        // 设置WevView要显示的网页
        webview.loadDataWithBaseURL(null, html, "text/html", "utf-8",
                null);
        WebSettings webSettings = webview.getSettings();//获取webview设置属性
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webSettings.setJavaScriptEnabled(true);//支持js
//        webSettings.setBuiltInZoomControls(true); // 显示放大缩小
//        webSettings.setSupportZoom(true); // 可以缩放
        webview.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        //        webview.getSettings().setBuiltInZoomControls(true); //页面添加缩放按钮
        //    ebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   //取消滚动条
        //   点击链接由自己处理，而不是新开Android的系统browser响应该链接。
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //设置点击网页里面的链接还是在当前的webview里跳转
                view.loadUrl(url);
                return true;
            }
        });
        //        webview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //            @Override
        //            public void onFocusChange(View v, boolean hasFocus) {
        //                if (hasFocus) {
        //                    try {
        //                        // 禁止网页上的缩放
        //                        Field defaultScale = WebView.class
        //                                .getDeclaredField("mDefaultScale");
        //                        defaultScale.setAccessible(true);
        //                        defaultScale.setFloat(webview, 1.0f);
        //                    } catch (SecurityException e) {
        //                        e.printStackTrace();
        //                    } catch (IllegalArgumentException e) {
        //                        e.printStackTrace();
        //                    } catch (IllegalAccessException e) {
        //                        e.printStackTrace();
        //                    } catch (NoSuchFieldException e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            }
        //        });
    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        homeTitle = (TextView) findViewById(R.id.home_title);
        newsTitle = (TextView) findViewById(R.id.news_title);
        webview = (WebView) findViewById(R.id.webview);
        tupian = (ImageView) findViewById(R.id.tupian);
        btnSousuo = (Button) findViewById(R.id.btn_sousuo);
        xwPllist = (ListViewScrollView) findViewById(R.id.xw_pllist);
        homeEditSearch = (EditText) findViewById(R.id.home_edit_search);
        newsFbsj = (TextView) findViewById(R.id.news_fbsj);
        dianzanll = (LinearLayout) findViewById(R.id.dianzanll);
        dianzanshu = (TextView) findViewById(R.id.dianzanshu);
    }
}