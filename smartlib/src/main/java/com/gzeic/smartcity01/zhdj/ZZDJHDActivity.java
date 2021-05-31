package com.gzeic.smartcity01.zhdj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

public class ZZDJHDActivity extends BaseActivity {

    private ImageView plBase;
    private Banner banner;
    private ListViewScrollView xxlist;
    private List<Integer> integerList;
    private List<ZHDJActivity.testnews> ziyuanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj_hd);
        initView();
        integerList = new ArrayList<>();
        integerList.add(R.drawable.zzhd1);
        integerList.add(R.drawable.zzhd2);
        integerList.add(R.drawable.zzhd3);
        integerList.add(R.drawable.zzhd4);
        integerList.add(R.drawable.zzhd5);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(integerList);
        banner.start();
        ziyuanlist = new ArrayList<>();
        ziyuanlist.add(new ZHDJActivity.testnews("党支部年度总结大会", "此次活动由第一党小组作为主要代表进行汇报，主要反思回顾了本学年个人在科研、学习、党政等方面的得与失，进行了自我剖析和自我总结。其中，组长钱成刚同志带头分享了他本学年在科研和党务工作方面的进展，并且提出了理论学习的重要性，反思自己在党政理论知识方面的短板，提醒大家要在忙碌的科研学习之余，注重理论知识的学习。陈艺虹同志则分享了她在疫情期间的感悟，表示要提高自己的沟通能力，珍惜在校时光。她的发言引起了在场同学的共鸣，大家纷纷表示：经过此次疫情的考验，更能够体会到在校时光来之不易，要只争朝夕，努力奋斗，不负青春年华。", R.drawable.zzhd1));
        ziyuanlist.add(new ZHDJActivity.testnews("硕士生党支部主题党日活动——党支部知识竞赛", "此次活动是由党支部全体成员参与，知识竞赛内容形式多样化，主要分为：轮流答题、抢答题、论述题三大类，其中轮流答题由党小组成员回答，抢答题分为红歌竞猜和党内知识，最后论述题由两个党支部小组论述，其余小组作为补充。", R.drawable.zzhd2));
        ziyuanlist.add(new ZHDJActivity.testnews("“学习航天精神，弘扬爱国情怀”主题党日活动", "为弘扬爱国主义情怀，鼓舞广大师生党员积极投身到社会主义的伟大事业建设当中，进一步贯彻落实习近平新时代中国特色社会主义思想，增强广大党员的使命感，10月14日，应用化学系教工党支部及各研究生党支部开展了主题党日活动，活动的主题是“学习航天精神，弘扬爱国情怀”", R.drawable.zzhd3));
        ziyuanlist.add(new ZHDJActivity.testnews("开展“不忘初心 牢记使命”主题教育知识竞赛", "为深入贯彻落实党中央发布的“不忘初心，牢记使命”决策部署，扎实推进本支部党员“不忘初心，牢记使命”主题教育，提高支部党员理论素养.", R.drawable.zzhd4));
        ziyuanlist.add(new ZHDJActivity.testnews("“不负韶华，不负时代”系列活动", "为更好服务材料科学与工程系2018级硕士班有志于赴公共部门就业的科大学子，提升广大同学公职就业竞争力，鼓励更多毕业生赴基层就业，向基层输送更多有理想、有担当、有信念、讲奉献的新时代青年学.", R.drawable.zzhd5));
        ziyuanlist.add(new ZHDJActivity.testnews("召开主题党日活动---“厉行节约，拒绝浪费”", "今年，在新冠疫情、极端气候、蝗虫灾害的多种因素的共同影响下，粮食生产和食品安全问题日益突出。近日，联合国也公布了一份关于世界粮食状况的报告，预示着一场前所未有的粮食危机正在迫近。民以食为天，食品供给和安全问题牵动着每一位老百姓的心，党中央高度重视，把粮食生产放在重要位置，保住人民的“饭碗”，也就稳住了大家的心。然而，就在世界粮食危机正在逼近的关头，我国的粮食浪费现象也是不断涌现。", R.drawable.zzhd1));
        ziyuanlist.add(new ZHDJActivity.testnews("“缅怀革命先烈，加强党员思想教育，牢记初心使命”党日主题活动", "根据教育部关于开展高校基层党组织常态化疫情防控“四个一”行动的工作以“迎党的生日、讲战疫故事、悟初心使命”为主要内容召开组织生活会提示要求，以及安徽省关于开展“全省党员干部党史教育日”活动的通知要求。\n" +
               "烟草中心党支部组织全体党员和中心部分骨干教师，前往皖南新四军旧部，开展“缅怀革命先烈，加强党员思想教育，牢记初心使命”党日主题活动。同时，深入皖南烟区开展烟草种植技术和烘烤技术交流，帮助烟区解决烤烟生产技术问题，深受当地烟草公司领导和广大烟农的欢迎。", R.drawable.zzhd2));
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
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_xw, null);
                ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.news_image.setImageResource(ziyuanlist.get(i).getZiyuan());
                viewHolder.news_title.setText(ziyuanlist.get(i).title);
                viewHolder.news_content.setText(ziyuanlist.get(i).neirong);
//                ZHDJActivity.testnews testnews = ziyuanlist.get(i);

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("huodong", new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(ZZDJHDActivity.this, ZHDJZZHDXQActivity.class));
                    }
                });
                return view;
            }
        });

    }

    private void initView() {
        plBase = (ImageView) findViewById(R.id.pl_base);
        banner = (Banner) findViewById(R.id.banner);
        xxlist = (ListViewScrollView) findViewById(R.id.xxlist);
        plBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}