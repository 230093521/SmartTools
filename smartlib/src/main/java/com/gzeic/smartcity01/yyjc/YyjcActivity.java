package com.gzeic.smartcity01.yyjc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;

public class YyjcActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup homeRadio;
    private RadioButton rbBtn1;
    private RadioButton rbBtn2;
    private RadioButton rbBtn3;
    private RadioButton rbBtn4;
    private Fragment fragment1, fragment2, fragment3, fragment4;
    private TextView textTitle;
    private ImageView metroBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_yyjc);
        initView();
        rbBtn1.setChecked(true);
        homeRadio.setOnCheckedChangeListener(this);
        setSelect(1);
        metroBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        homeRadio = (RadioGroup) findViewById(R.id.home_radio);
        rbBtn1 = (RadioButton) findViewById(R.id.rb_btn1);
        rbBtn2 = (RadioButton) findViewById(R.id.rb_btn2);
        rbBtn3 = (RadioButton) findViewById(R.id.rb_btn3);
        rbBtn4 = (RadioButton) findViewById(R.id.rb_btn4);
        textTitle = findViewById(R.id.home_title);
        metroBase = (ImageView) findViewById(R.id.metro_base);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_btn1) {
            setSelect(1);
            textTitle.setText("预约须知");
        } else if (i == R.id.rb_btn2) {
            setSelect(2);
            textTitle.setText("立即预约");
        } else if (i == R.id.rb_btn3) {
            setSelect(3);
            textTitle.setText("我的预约");
        } else if (i == R.id.rb_btn4) {
            setSelect(4);
            textTitle.setText("车辆管理");
        }
    }

    public void setSelect(int x) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
        if (fragment3 != null) {
            fragmentTransaction.hide(fragment3);
        }
        if (fragment4 != null) {
            fragmentTransaction.hide(fragment4);
        }
        switch (x) {
            case 1:
                if (fragment1 == null) {
                    fragment1 = new JCAFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment1);
                } else {
                    rbBtn1.setChecked(true);
                    fragmentTransaction.show(fragment1);
                }
                rbBtn1.setChecked(true);
                break;
            case 2:
                if (fragment2 == null) {
                    fragment2 = new JCBFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment2);
                } else {
                    rbBtn2.setChecked(true);
                    fragmentTransaction.show(fragment2);
                }
                rbBtn2.setChecked(true);
                break;
            case 3:
                if (fragment3 == null) {
                    fragment3 = new JCCFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment3);
                } else {
                    rbBtn3.setChecked(true);
                    fragmentTransaction.show(fragment3);
                }
                rbBtn3.setChecked(true);
                break;
            case 4:
                if (fragment4 == null) {
                    fragment4 = new JCDFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment4);
                } else {
                    rbBtn4.setChecked(true);
                    fragmentTransaction.show(fragment4);
                }
                rbBtn4.setChecked(true);
                break;
        }
        fragmentTransaction.commit();
    }
}