package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.TestWtBean;

import java.util.ArrayList;
import java.util.List;


public class WZDcFragment extends BaseFragment {

    private ListViewScrollView fw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_dc, container, false);
        initView(view);
        initlist();
        return view;
    }

    private void initlist() {
        final List<TestWtBean> testWtBeans = new ArrayList<>();
        testWtBeans.add(new TestWtBean("预选机动车号牌办理流程", "非实名注册用户（通过网上注册功能注册的用户为非实名注册用户）互联网注册用户在互联网登录后，点击【机动车业务】→【新车（含新能源汽车）注册登记预选号牌】下的【预选号牌】进入预选号牌流程。", 1));
        testWtBeans.add(new TestWtBean("期满换证办理流程", "机动车驾驶人应当于机动车驾驶证有效期满前九十日内，申请机动车驾驶证有效期满换证。如已超过有效期但未满11个月的，可通过本平台继续申请换证业务。超过有效期满11个月，未满1年的，需到机动车驾驶证核发地车辆管理所申请换证。网上办理期满换证业务，需先到指定医院（体检医院）进行体检并获取《机动车驾驶人身体条件证明》", 2));
        testWtBeans.add(new TestWtBean("考试费缴纳办理流程", "在开通网上支付考试费功能的地方，群众可以通过互联网服务平台缴纳考试相关的费用。用户在互联网登录后，点击【驾驶证业务】→【考试费缴纳】业务功能办理。", 3));
        testWtBeans.add(new TestWtBean("期满换证", "机动车驾驶人应当于机动车驾驶证有效期满前九十日内，申请机动车驾驶证有效期满换证。如已超过有效期但未满11个月的，可通过本平台继续申请换证业务。超过有效期满11个月，未满1年的，需到机动车驾驶证核发地车辆管理所申请换证。网上办理期满换证业务，需先到指定医院（体检医院）进行体检并获取《机动车驾驶人身体条件证明》。\n" +
                "\n" +
                "获取《机动车驾驶人身体条件证明》后，用户登录互联网服务平台后，点击【驾驶证业务】→【期满换证】业务功能办理。", 3));
        testWtBeans.add(new TestWtBean("新能源汽车换牌预约", "用户在互联网登录后，点击【机动车业务】→【换发新能源汽车号牌预约】业务功能办理。\n" +
                "\n" +
                "办理业务用户类型：互联网注册的个人用户和窗口注册的单位用户。", 3));
        testWtBeans.add(new TestWtBean("本地二手车过户预选号牌", "用户通过该功能可以为自己名下原登记地为本地的二手普通小型汽车预选号牌(详情请阅读预选号牌业务须知)， 选号之前请预先准备好“机动车登记证书”。用户在预选号牌过程中，需要录入登记证书编号、“车辆识别代号”（也叫车架号） 和卖方身份信息，同时，接收并录入手机短信验证码以验证本人预选号牌操作的真实性。\n" +
                "\n" +
                "如上图所示，如果用户不熟悉预选机动车号牌业务操作流程，可点击“模拟选号”链接进入模拟选号功能， 模拟选号功能与正式选号功能流程完全相同，旨在方便用户熟悉预选机动车号牌业务操作流程， 不校验基础信息的准确性，不保存任何信息，所选号牌号码均无效！用户正式预选号牌时，须确保基础信息的准确性！", 3));
        //服务中心
        fw.setAdapter(new BaseAdapter() {
            class ViewHolder {
                public View rootView;
                public TextView name;

                public ViewHolder(View rootView) {
                    this.rootView = rootView;
                    this.name = (TextView) rootView.findViewById(R.id.name);
                }
            }

            @Override
            public int getCount() {
                return testWtBeans.size();
            }

            @Override
            public Object getItem(int position) {
                return testWtBeans.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.itme_fwzx, parent, false);
                ViewHolder viewHolder = new ViewHolder(inflate1);
                viewHolder.name.setText(testWtBeans.get(position).getName());
                inflate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        putSP("fwwt",new Gson().toJson(testWtBeans.get(position)));
                        startActivity(new Intent(getActivity(), WzFwtwxqActivity.class));
                    }
                });
                return inflate1;
            }
        });
    }

    private void initView(View view) {
        fw = (ListViewScrollView) view.findViewById(R.id.fw);
    }
}