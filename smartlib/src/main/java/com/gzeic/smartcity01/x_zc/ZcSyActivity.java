package com.gzeic.smartcity01.x_zc;

import android.content.Intent;
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
import com.gzeic.smartcity01.CsDwActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.fragment.CFragment_zhsq;
import com.gzeic.smartcity01.x_csdt.CsdtSyActivity;

public class ZcSyActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup homeRadio;
    private RadioButton rbBtn1;
    private RadioButton rbBtn2;
    private RadioButton rbBtn3;
    private RadioButton rbBtn4;
    private RadioButton rbBtn5;
    private Fragment fragment1, fragment2, fragment3, fragment4, fragment5;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_zc_sy);
        initView();
        rbBtn1.setChecked(true);
        homeRadio.setOnCheckedChangeListener(this);
        setSelect(1);
        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZcSyActivity.this, CsDwActivity.class));
            }
        });
    }

    private void initView() {
        homeRadio = (RadioGroup) findViewById(R.id.home_radio);
        rbBtn1 = (RadioButton) findViewById(R.id.rb_btn1);
        rbBtn2 = (RadioButton) findViewById(R.id.rb_btn2);
        rbBtn3 = (RadioButton) findViewById(R.id.rb_btn3);
        rbBtn4 = (RadioButton) findViewById(R.id.rb_btn4);
        rbBtn5 = (RadioButton) findViewById(R.id.rb_btn5);
        textTitle = findViewById(R.id.home_title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String chengshi = getSP("chengshi");
        if (chengshi != null) {
            textTitle.setText(chengshi);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_btn1) {
            setSelect(1);
        } else if (i == R.id.rb_btn2) {
            setSelect(2);
        } else if (i == R.id.rb_btn3) {
            setSelect(3);
        } else if (i == R.id.rb_btn4) {
            setSelect(4);
        } else if (i == R.id.rb_btn5) {
            setSelect(5);
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
        if (fragment5 != null) {
            fragmentTransaction.hide(fragment5);
        }
        switch (x) {
            case 1:
                if (fragment1 == null) {
                    fragment1 = new ZCAFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment1);
                } else {
                    fragmentTransaction.show(fragment1);
                }
                rbBtn1.setChecked(true);
                break;
            case 2:
                if (fragment2 == null) {
                    fragment2 = new ZCBFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment2);
                } else {
                    fragmentTransaction.show(fragment2);
                }
                rbBtn2.setChecked(true);
                break;
            case 3:
                if (fragment3 == null) {
                    fragment3 = new CFragment_zhsq();
                    fragmentTransaction.add(R.id.home_fragment, fragment3);
                } else {
                    fragmentTransaction.show(fragment3);
                }
                rbBtn3.setChecked(true);

                break;
            case 4:
                if (fragment4 == null) {
                    fragment4 = new ZCDFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment4);
                } else {
                    fragmentTransaction.show(fragment4);
                }
                rbBtn4.setChecked(true);

                break;
            case 5:
                if (fragment5 == null) {
                    fragment5 = new ZCEFragment();
                    fragmentTransaction.add(R.id.home_fragment, fragment5);
                } else {
                    fragmentTransaction.show(fragment5);
                }
                rbBtn5.setChecked(true);

                break;
        }
        fragmentTransaction.commit();
    }
}