package com.gzeic.smartcity01.x_wz;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.WoDinDanActivity;
import com.gzeic.smartcity01.WoFanKuiActivity;
import com.gzeic.smartcity01.WoGywmActivity;
import com.gzeic.smartcity01.WoMiMaActivity;
import com.gzeic.smartcity01.WoZiLiaoActivity;
import com.gzeic.smartcity01.bean.UsersBean;
import com.gzeic.smartcity01.x_csdt.CsdtCcxzActivity;
import com.gzeic.smartcity01.x_csdt.CsdtSwzlActivity;


public class WZEFragment extends BaseFragment {
    private ImageView ivIcon;
    private TextView tvNickname;
    private ImageView xwzl;
    private ImageView ccxz;
    private ImageView dtrx;
    private RelativeLayout rlYjfk;
    private RelativeLayout rlGywm;
    private RelativeLayout rlGrsz;
    private RelativeLayout rlXgmm;
    private Button tvExit;
    private RelativeLayout rlWddd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_e, container, false);
        initView(view);
        UsersBean.UserDTO userdata = getUserdata();
        Glide.with(getContext()).load("http://" + getServerIp() + userdata.getAvatar()).error(R.drawable.icon2).into(ivIcon);
        tvNickname.setText(userdata.getNickName());
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("退出成功");
                getActivity().finish();
            }
        });
        xwzl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CsdtSwzlActivity.class));
            }
        });
        ccxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CsdtCcxzActivity.class));
            }
        });
        rlWddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoDinDanActivity.class));
            }
        });
        dtrx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    showToast("请授予拨打电话权限");
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:0755-01126341"));
                    startActivity(intent);
                }
            }
        });
        rlYjfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoFanKuiActivity.class));
            }
        });
        rlGywm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoGywmActivity.class));
            }
        });
        rlGrsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoZiLiaoActivity.class));
            }
        });
        rlXgmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WoMiMaActivity.class));
            }
        });


        return view;
    }


    private void initView(View view) {
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvNickname = (TextView) view.findViewById(R.id.tv_nickname);
        xwzl = (ImageView) view.findViewById(R.id.xwzl);
        ccxz = (ImageView) view.findViewById(R.id.ccxz);
        dtrx = (ImageView) view.findViewById(R.id.dtrx);
        rlYjfk = (RelativeLayout) view.findViewById(R.id.rl_yjfk);
        rlGywm = (RelativeLayout) view.findViewById(R.id.rl_gywm);
        rlGrsz = (RelativeLayout) view.findViewById(R.id.rl_grsz);
        rlXgmm = (RelativeLayout) view.findViewById(R.id.rl_xgmm);
        tvExit = (Button) view.findViewById(R.id.tv_exit);
        rlWddd = (RelativeLayout) view.findViewById(R.id.rl_wddd);
    }

}