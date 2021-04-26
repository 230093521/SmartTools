package com.gzeic.smartcity01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.zhdj.ZHDJDYXXActivity;
import com.gzeic.smartcity01.zhdj.ZHDJJYXCActivity;
import com.gzeic.smartcity01.zhdj.ZHDJPLActivity;
import com.gzeic.smartcity01.zhdj.ZHDJSuiShouPaiActivity;
import com.gzeic.smartcity01.zhdj.ZZDJHDActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class CFragment_zhdj extends BaseFragment {

    private ImageView metroBase;
    private Banner banner;
    private ImageView xuexi;
    private ImageView huodong;
    private ImageView liuyan;
    private ImageView paizhao;
    private ListView listDongtai;
    private List<Integer> viewlist;
    private List<testnews> ziyuanlist;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_c_zhdj, container, false);
        initView(root);
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
        ziyuanlist.add(new testnews("党建测试标题1", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian1));
        ziyuanlist.add(new testnews("党建测试标题2", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian2));
        ziyuanlist.add(new testnews("党建测试标题3", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian3));
        ziyuanlist.add(new testnews("党建测试标题4", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian4));
        ziyuanlist.add(new testnews("党建测试标题5", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian5));
        ziyuanlist.add(new testnews("党建测试标题6", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian6));
        ziyuanlist.add(new testnews("党建测试标题7", "党建测试内容党建测试内容党建测试内容党建测试内容", R.drawable.dangjian7));
        xuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJDYXXActivity.class));
            }
        });
        liuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJJYXCActivity.class));
            }
        });
        huodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZZDJHDActivity.class));
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZHDJSuiShouPaiActivity.class));
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_news, null);
                ViewHolder viewHolder = new ViewHolder(view);
                viewHolder.news_image.setImageResource(ziyuanlist.get(i).getZiyuan());
                viewHolder.news_title.setText(ziyuanlist.get(i).title);
                viewHolder.news_content.setText(ziyuanlist.get(i).neirong);

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        putSP("dang", new Gson().toJson(ziyuanlist.get(i)));
                        startActivity(new Intent(getContext(), ZHDJPLActivity.class));
                    }
                });
                return view;
            }
        });
        return root;
    }

    private void initView(View view) {
        metroBase = (ImageView) view.findViewById(R.id.metro_base);
        banner = (Banner) view.findViewById(R.id.banner);
        xuexi = (ImageView) view.findViewById(R.id.xuexi);
        huodong = (ImageView) view.findViewById(R.id.huodong);
        liuyan = (ImageView) view.findViewById(R.id.liuyan);
        paizhao = (ImageView) view.findViewById(R.id.paizhao);
        listDongtai = (ListView) view.findViewById(R.id.list_dongtai);

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