package com.gzeic.smartcity01.sstq;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class TqActivity extends BaseActivity {

    private ImageView metroBase;
    private ImageView shuaxin;
    private TextView jrTq;
    private TextView jrQw;
    private TextView jrTime;
    private TextView wlSj1;
    private TextView wlTq1;
    private TextView wlSj2;
    private TextView wlTq2;
    private TextView wlSj3;
    private TextView wlTq3;
    private TextView wlSj4;
    private TextView wlTq4;
    private TextView zw1;
    private TextView zw2;
    private TextView zw3;
    private TextView gm1;
    private TextView gm2;
    private TextView gm3;
    private TextView cy1;
    private TextView cy2;
    private TextView cy3;
    private TextView yd1;
    private TextView yd2;
    private TextView yd3;
    private TextView wr1;
    private TextView wr2;
    private TextView wr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_tq);
        initView();
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //天气详情
    private void getTqxq(String tq,String qw,String time) {
        jrTq.setText(tq);
        jrQw.setText(qw);
        jrTime.setText(time);
    }

    //生活指数
    private void getShzs(int guangzhao,int wendu,int eryang,int wuranshu) {
        if (guangzhao<1000){
            zw1.setText(String.valueOf(guangzhao));
            zw2.setText("弱");
            zw3.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
        }else if (guangzhao < 3000){
            zw1.setText(String.valueOf(guangzhao));
            zw2.setText("中等");
            zw3.setText("涂擦SPF大于15、PA+防晒护肤品");
        }else {
            zw1.setText(String.valueOf(guangzhao));
            zw2.setText("强");
            zw3.setText("尽量减少外出，需要涂抹高倍数防晒霜");
        }
        if (wendu<8){
            gm1.setText(String.valueOf(wendu));
            gm2.setText("较易发");
            gm3.setText("温度低，风较大，较易发生感冒，注意防护");
        }else {
            gm1.setText(String.valueOf(wendu));
            gm2.setText("少发");
            gm3.setText("无明显降温，感冒机率较低");
        }
        if (wendu<12){
            cy1.setText(String.valueOf(wendu));
            cy2.setText("冷");
            cy3.setText("建议穿长袖衬衫、单裤等服装");
        }else if (wendu<21){
            cy1.setText(String.valueOf(wendu));
            cy2.setText("舒适");
            cy3.setText("建议穿短袖衬衫、单裤等服装");
        }else {
            cy1.setText(String.valueOf(wendu));
            cy2.setText("热");
            cy3.setText("适合穿T恤、短薄外套等夏季服装");
        }

        if (eryang<3000){
            yd1.setText(String.valueOf(eryang));
            yd2.setText("适宜");
            yd3.setText("气候适宜，推荐您进行户外运动");
        }else if (eryang<6000){
            yd1.setText(String.valueOf(eryang));
            yd2.setText("中");
            yd3.setText("易感人群应适当减少室外活动");
        }else {
            yd1.setText(String.valueOf(eryang));
            yd1.setText("较不宜");
            yd3.setText("空气氧气含量低，请在室内进行休闲运动");
        }
        if (wuranshu<30){
            wr1.setText(String.valueOf(wuranshu));
            wr2.setText("优");
            wr3.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
        }else if (wuranshu<100){
            wr1.setText(String.valueOf(wuranshu));
            wr2.setText("良");
            wr3.setText("易感人群应适当减少室外活动");
        }else {
            wr1.setText(String.valueOf(wuranshu));
            wr2.setText("污染");
            wr3.setText("空气质量差，不适合户外活动");
        }
    }



    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        shuaxin = (ImageView) findViewById(R.id.shuaxin);
        jrTq = (TextView) findViewById(R.id.jr_tq);
        jrQw = (TextView) findViewById(R.id.jr_qw);
        jrTime = (TextView) findViewById(R.id.jr_time);
        wlSj1 = (TextView) findViewById(R.id.wl_sj1);
        wlTq1 = (TextView) findViewById(R.id.wl_tq1);
        wlSj2 = (TextView) findViewById(R.id.wl_sj2);
        wlTq2 = (TextView) findViewById(R.id.wl_tq2);
        wlSj3 = (TextView) findViewById(R.id.wl_sj3);
        wlTq3 = (TextView) findViewById(R.id.wl_tq3);
        wlSj4 = (TextView) findViewById(R.id.wl_sj4);
        wlTq4 = (TextView) findViewById(R.id.wl_tq4);
        zw1 = (TextView) findViewById(R.id.zw_1);
        zw2 = (TextView) findViewById(R.id.zw_2);
        zw3 = (TextView) findViewById(R.id.zw_3);
        gm1 = (TextView) findViewById(R.id.gm_1);
        gm2 = (TextView) findViewById(R.id.gm_2);
        gm3 = (TextView) findViewById(R.id.gm_3);
        cy1 = (TextView) findViewById(R.id.cy_1);
        cy2 = (TextView) findViewById(R.id.cy_2);
        cy3 = (TextView) findViewById(R.id.cy_3);
        yd1 = (TextView) findViewById(R.id.yd_1);
        yd2 = (TextView) findViewById(R.id.yd_2);
        yd3 = (TextView) findViewById(R.id.yd_3);
        wr1 = (TextView) findViewById(R.id.wr_1);
        wr2 = (TextView) findViewById(R.id.wr_2);
        wr3 = (TextView) findViewById(R.id.wr_3);
    }
}