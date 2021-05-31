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
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ZHDJActivity extends BaseActivity {

    private ImageView metroBase;
    private Banner banner;
    private ImageView xuexi;
    private ImageView huodong;
    private ImageView liuyan;
    private ImageView paizhao;
    private ListView listDongtai;
    private List<Integer> viewlist;
    private List<testnews> ziyuanlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zhdj);
        initView();
        banner.setImageLoader(new GlideImageLoader());
        viewlist = new ArrayList<>();
        viewlist.add(R.drawable.dangjian1);
        viewlist.add(R.drawable.dangjian2);
        viewlist.add(R.drawable.dangjian3);
        viewlist.add(R.drawable.dangjian4);
        viewlist.add(R.drawable.dangjian5);
        viewlist.add(R.drawable.dangjian6);
        viewlist.add(R.drawable.dangjian7);
        banner.setImages(viewlist);
        banner.start();
        ziyuanlist = new ArrayList<>();
        ziyuanlist.add(new testnews("习近平出席海军三型主战舰艇集中交接入列活动", "4月23日，海军三型主战舰艇——长征18号艇、大连舰、海南舰在海南三亚某军港集中交接入列。中共中央总书记、国家主席、中央军委主席习近平出席交接入列活动并登上舰艇视察。这是习近平向长征18号艇授予军旗、命名证书。", R.drawable.dangjian1));
        ziyuanlist.add(new testnews("世界大变局：特殊背景，特殊命题", "博鳌亚洲论坛成立于2001年，是第一个将永久会址设在中国的大型国际会议组织。一年一度的论坛年会，为亚洲和世界贡献“博鳌方案”。去年的年会因疫情原因停办，这次的年会成为今年世界上第一个以线下会议为主的大型国际会议。", R.drawable.dangjian2));
        ziyuanlist.add(new testnews("习近平始终思考着人类前途命运的重大命题", "2017年，他在联合国日内瓦总部演讲时，发出“世界怎么了、我们怎么办”之问。2018年，在博鳌亚洲论坛年会上，他再次提问：“面对复杂变化的世界，人类社会向何处去？亚洲前途在哪里？”", R.drawable.dangjian3));
        ziyuanlist.add(new testnews("百年变局和世纪疫情交织叠加", "让时代之问的复杂性、紧迫性愈发突显。在这次博鳌亚洲论坛年会上，习近平说：“人类社会应该向何处去？我们应该为子孙后代创造一个什么样的未来？对这一重大命题，我们要从人类共同利益出发，以负责任态度作出明智选择。", R.drawable.dangjian4));
        ziyuanlist.add(new testnews("全球治理：世界要公道，不要霸道", "在4月20日的主旨演讲中，针对“人类社会应该向何处去？我们应该为子孙后代创造一个什么样的未来？”这一重大命题，习近平主席提出四点主张：我们要平等协商，开创共赢共享的未来；我们要开放创新，开创发展繁荣的未来；我们要同舟共济，开创健康安全的未来；我们要坚守正义，开创互尊互鉴的未来。", R.drawable.dangjian5));
        ziyuanlist.add(new testnews("“一带一路”：是阳光大道，不是私家小路", "在4月20日的主旨演讲中，习近平谈到今年年会的主题——“世界大变局：共襄全球治理盛举，合奏‘一带一路’强音”。继续高质量共建“一带一路”，建设四个更紧密的伙伴关系，也是习主席在今年博鳌亚洲论坛年会上为破解重大命题提出的重要方案。", R.drawable.dangjian6));
        ziyuanlist.add(new testnews("习近平出席领导人气候峰会并发表重要讲话", "4月22日晚，应美国总统拜登邀请，国家主席习近平在北京以视频方式出席领导人气候峰会，并发表题为《共同构建人与自然生命共同体》的重要讲话。", R.drawable.dangjian7));
        xuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZHDJActivity.this, ZHDJDYXXActivity.class));
            }
        });
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZHDJActivity.this, ZHDJJYXCActivity.class));
            }
        });
        huodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZHDJActivity.this, ZZDJHDActivity.class));
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZHDJActivity.this, ZHDJSspActivity.class));
            }
        });

        listDongtai.setAdapter(new BaseAdapter() {
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

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("dang", new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(ZHDJActivity.this, ZHDJPLActivity.class));
                    }
                });
                return view;
            }
        });

    }

    private void initView() {
        metroBase = (ImageView) findViewById(R.id.metro_base);
        banner = (Banner) findViewById(R.id.banner);
        xuexi = (ImageView) findViewById(R.id.xuexi);
        huodong = (ImageView) findViewById(R.id.huodong);
        liuyan = (ImageView) findViewById(R.id.liuyan);
        paizhao = (ImageView) findViewById(R.id.paizhao);
        listDongtai = (ListView) findViewById(R.id.list_dongtai);

    }

    static class testnews {
        String title;
        String neirong;
        int ziyuan;

        public testnews() {
        }

        public testnews(String title, String neirong, int ziyuan) {
            this.title = title;
            this.neirong = neirong;
            this.ziyuan = ziyuan;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNeirong() {
            return neirong;
        }

        public void setNeirong(String neirong) {
            this.neirong = neirong;
        }

        public int getZiyuan() {
            return ziyuan;
        }

        public void setZiyuan(int ziyuan) {
            this.ziyuan = ziyuan;
        }
    }


}