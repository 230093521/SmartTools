package com.gzeic.smartcity01.zhdj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

public class ZHDJDYXXActivity extends BaseActivity {

    private ImageView plBase;
    private Banner banner;
    private ListViewScrollView xxlist;
    private List<Integer> viewlist;
    private List<ZHDJActivity.testnews> ziyuanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_xx);
        initView();
        banner.setImageLoader(new BaseActivity.GlideImageLoader());
        viewlist = new ArrayList<>();
        viewlist.add(R.drawable.dyxx1);
        viewlist.add(R.drawable.dyxx2);
        viewlist.add(R.drawable.dyxx3);
        banner.setImages(viewlist);
        banner.start();
        ziyuanlist = new ArrayList<>();
        ziyuanlist.add(new ZHDJActivity.testnews("党史故事100讲", "以党的发展历程为顺序，以党的重大事件为线索，以不同时期的典型事例、历史人物、精彩故事为主干，全景式回顾党的伟大历程和辉煌成就。", R.drawable.dyxx1));
        ziyuanlist.add(new ZHDJActivity.testnews("百炼成钢：中国共产党的100年", "该片站在中国共产党成立100周年的历史高度，撷取中国革命、建设、改革历程中的重要事件，用历史故事的形式，形象展示百年大党的光辉历程和伟大成就，以小入口折射大主题，以小故事揭示大道理，生动回答中国共产党为什么“能”、马克思主义为什么“行”、中国特色社会主义为什么“好”等重大问题。", R.drawable.dyxx2));
        ziyuanlist.add(new ZHDJActivity.testnews("全省城市社区榜样", "发现榜样学习榜样——全省城市社区榜样", R.drawable.dyxx3));
        ziyuanlist.add(new ZHDJActivity.testnews("应急管理应知应会系列微视频", "每一起生产安全事故的发生，每一条鲜活生命的伤亡，都是一张张悲痛万分的面孔，都是一段段痛入骨髓的记忆，都是一个个支离破损的家庭。安全生产是红线，是底线，是生命线。", R.drawable.dyxx3));
        ziyuanlist.add(new ZHDJActivity.testnews("“中共党史公开课”网络课程", "为了向广大党员干部和全社会提供更多优质、系统的党史学习资源，更好服务、推动党史学习教育深入开展，中央党校（国家行政学院）录制8讲“中共党史公开课”系列网络课程。\n" +
                "\n", R.drawable.dyxx3));

        xxlist.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public ImageView news_image;
                public TextView news_title;
                public TextView news_content;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.news_image = (ImageView) rootView.findViewById(R.id.news_image);
                    this.news_title = (TextView) rootView.findViewById(R.id.news_title);
                    this.news_content = (TextView) rootView.findViewById(R.id.news_content);
                }

            }

            @Override
            public int getCount() {
                return ziyuanlist.size();
            }

            @Override
            public Object getItem(int i) {
                return ziyuanlist.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_xw_kpsbj, null);
                ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.news_image.setImageResource(ziyuanlist.get(i).getZiyuan());
                viewHolder.news_title.setText(ziyuanlist.get(i).title);
                viewHolder.news_content.setText(ziyuanlist.get(i).neirong);

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("dang",new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(ZHDJDYXXActivity.this, ZHDJDYXXXQActivity.class));
                    }
                });
                return view;
            }
        });
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        banner = (Banner) findViewById(R.id.banner);
        xxlist = (ListViewScrollView) findViewById(R.id.xxlist);
    }
}