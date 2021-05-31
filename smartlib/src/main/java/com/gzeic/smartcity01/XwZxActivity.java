package com.gzeic.smartcity01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.XwListBean;
import com.xsonline.smartlib.R;

public class XwZxActivity extends BaseActivity {
    XwListBean.RowsDTO rowsDTO;
    private ImageView newsBase;
    private TextView homeTitle;
    private TextView newsTitle;
    private WebView webview;
    private ImageView tupian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xw_zx);
        initView();
        String xwzx = getSP("xwzx");
        String yemian = getSP("yemian");
        if (yemian.equals("shzt")) {
            homeTitle.setText("主题详情");
        } else {
            homeTitle.setText("新闻详情");
        }
        rowsDTO = new Gson().fromJson(xwzx, XwListBean.RowsDTO.class);
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsTitle.setText(rowsDTO.getTitle());
        Glide.with(XwZxActivity.this).load("http://"+getServerIp()+rowsDTO.getCover()).into(tupian);
        String html = rowsDTO.getContent();
        html = html.replace("img src=\"", "img src=\"http://" + getServerIp() + "");
        html = html.replace("<img", "<img style=\"display:        ;max-width:100%;\"");
        Log.i("info", "src=\"/" + "xxxxxxxxxxxxx" + "src=\"http://" + getServerIp() + "");
        showWebView(html);
        Log.i(TAG, "onCreate: " + html);
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
    }
}