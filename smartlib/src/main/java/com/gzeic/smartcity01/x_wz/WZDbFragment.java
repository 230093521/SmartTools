package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;


public class WZDbFragment extends BaseFragment {
    private ImageView ivMei;
    private EditText homeEditSearch;
    private Button btnSousuo;
    private TextView homeSearchBase;
    private ListViewScrollView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_db, container, false);
        initView(view);
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("暂无网点信息");
            }
        });
        return view;
    }

    private void initView(View view) {
        ivMei = (ImageView) view.findViewById(R.id.iv_mei);
        homeEditSearch = (EditText) view.findViewById(R.id.home_edit_search);
        btnSousuo = (Button) view.findViewById(R.id.btn_sousuo);
        homeSearchBase = (TextView) view.findViewById(R.id.home_search_base);
        listview = (ListViewScrollView) view.findViewById(R.id.listview);
    }
}