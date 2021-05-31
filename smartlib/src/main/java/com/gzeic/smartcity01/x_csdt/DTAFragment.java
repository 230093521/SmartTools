package com.gzeic.smartcity01.x_csdt;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.HorizontalListView;
import com.gzeic.smartcity01.XwZxActivity;
import com.gzeic.smartcity01.bean.DTsyxlBean;
import com.gzeic.smartcity01.bean.LunBoBean;
import com.gzeic.smartcity01.bean.XwListBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DTAFragment extends BaseFragment {

    private Banner banner;
    private ImageView zdcx;
    private ImageView xlcx;
    private ImageView ccxz;
    private ImageView yygg;
    private TextView zdmc;
    private ListView zdlist;
    List<XwListBean.RowsDTO> xwListBeanRows;

    List<String> imglist;
    LunBoBean lunBoBean;
    private HorizontalListView xwlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dt_a, container, false);
        initView(view);
        getLunbo();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {

            }
        });
        zdcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CsdtZdcxActivity.class));
            }
        });
        xlcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CsdtXltActivity.class));
            }
        });
        ccxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CsdtCcxzActivity.class));
            }
        });
        yygg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CsdtYyggActivity.class));
            }
        });

        getDtzd();

        getTPXW();

        xwlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                putSP("yemian","xw");
                putSP("xwzx",new Gson().toJson(xwListBeanRows.get(position)));
                startActivity(new Intent(getContext(), XwZxActivity.class));
            }
        });

        return view;
    }

    private void getTPXW() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/press/press/list?pageNum=1&pageSize=6&top=y", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                XwListBean xwListBean = new Gson().fromJson(json, XwListBean.class);
                if (xwListBean.getCode() == 200) {
                    xwListBeanRows = xwListBean.getRows();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            xwlist.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public ImageView fa_zt_image1;
                                    public TextView fa_zt_text1;
                                    public LinearLayout fa_zt_ll1;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.fa_zt_image1 = (ImageView) rootView.findViewById(R.id.fa_zt_image1);
                                        this.fa_zt_text1 = (TextView) rootView.findViewById(R.id.fa_zt_text1);
                                        this.fa_zt_ll1 = (LinearLayout) rootView.findViewById(R.id.fa_zt_ll1);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return xwListBeanRows.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return xwListBeanRows.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_xw_kp, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    final XwListBean.RowsDTO rowsDTO = xwListBeanRows.get(position);
                                    viewHolder.fa_zt_text1.setText(rowsDTO.getTitle());
                                    Glide.with(getContext()).load("http://"+getServerIp()+rowsDTO.getCover()).into(viewHolder.fa_zt_image1);

                                    return view;
                                }
                            });
                        }
                    });
                }
            }
        });

    }

    private void getDtzd() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/list?currentName=建国门", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                DTsyxlBean dTsyxlBean = new Gson().fromJson(string, DTsyxlBean.class);
                if (dTsyxlBean.getCode() == 200) {
                    final List<DTsyxlBean.DataDTO> dTsyxlBeanData = dTsyxlBean.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            zdlist.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView zdmc;
                                    public TextView dzsj;
                                    public TextView syz;
                                    public TextView syzsuoshu;
                                    public TextView xyz;
                                    public TextView xyzshuoshu;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.zdmc = (TextView) rootView.findViewById(R.id.zdmc);
                                        this.dzsj = (TextView) rootView.findViewById(R.id.dzsj);
                                        this.syz = (TextView) rootView.findViewById(R.id.syz);
                                        this.syzsuoshu = (TextView) rootView.findViewById(R.id.syzzd);
                                        this.xyz = (TextView) rootView.findViewById(R.id.xyz);
                                        this.xyzshuoshu = (TextView) rootView.findViewById(R.id.xyzzd);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return dTsyxlBeanData.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return dTsyxlBeanData.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_dt_zd, null);
                                    ViewHolder viewHolder = new ViewHolder(view);
                                    DTsyxlBean.DataDTO dataDTO = dTsyxlBeanData.get(position);
                                    viewHolder.zdmc.setText(dataDTO.getLineName());
                                    viewHolder.dzsj.setText(dataDTO.getReachTime() + "分钟");
                                    DTsyxlBean.DataDTO.PreStepDTO preStep = dataDTO.getPreStep();
                                    viewHolder.syz.setText(preStep.getName());
                                    DTsyxlBean.DataDTO.NextStepDTO nextStep = dataDTO.getNextStep();
                                    viewHolder.xyz.setText(nextStep.getName());
                                    viewHolder.xyzshuoshu.setText(dataDTO.getCurrentName());
                                    viewHolder.syzsuoshu.setText(dataDTO.getCurrentName());
                                    return view;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void getLunbo() {
        getTools().sendGetRequestToken("http://" + getServerIp() + "/prod-api/api/metro/rotation/list", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                lunBoBean = new Gson().fromJson(string, LunBoBean.class);
                if (lunBoBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imglist = new ArrayList<>();
                            for (LunBoBean.RowsDTO lunBoBeanRow : lunBoBean.getRows()) {
                                imglist.add("http://" + getServerIp() + lunBoBeanRow.getAdvImg());
                            }
                            banner.setImages(imglist);
                            banner.setImageLoader(new ImageLoader() {
                                @Override
                                public void displayImage(Context context, Object o, ImageView imageView) {
                                    Glide.with(context).load(o).into(imageView);
                                }
                            });
                            banner.start();
                        }
                    });
                }
            }
        });
    }


    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        zdcx = (ImageView) view.findViewById(R.id.zdcx);
        xlcx = (ImageView) view.findViewById(R.id.xlcx);
        ccxz = (ImageView) view.findViewById(R.id.ccxz);
        yygg = (ImageView) view.findViewById(R.id.yygg);
        zdmc = (TextView) view.findViewById(R.id.zdmc);
        zdlist = (ListView) view.findViewById(R.id.zdlist);
        xwlist = (HorizontalListView) view.findViewById(R.id.xwlist);
    }
}