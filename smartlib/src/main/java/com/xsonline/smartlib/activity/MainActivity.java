package com.xsonline.smartlib.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.xsonline.smartlib.R;
import com.xsonline.smartlib.fragment.AFragment;
import com.xsonline.smartlib.fragment.BFragment;
import com.xsonline.smartlib.fragment.CFragment;
import com.xsonline.smartlib.fragment.DFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup homeRadio;
    private RadioButton rbBtn1;
    private RadioButton rbBtn2;
    private RadioButton rbBtn3;
    private RadioButton rbBtn4;
    private Fragment fragment1,fragment2,fragment3,fragment4;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_main);
        initView();
        rbBtn1.setChecked(true);
        homeRadio.setOnCheckedChangeListener(this);
        setSelect(1);
    }

    private void initView() {
        homeRadio = (RadioGroup) findViewById(R.id.home_radio);
        rbBtn1 = (RadioButton) findViewById(R.id.rb_btn1);
        rbBtn2 = (RadioButton) findViewById(R.id.rb_btn2);
        rbBtn3 = (RadioButton) findViewById(R.id.rb_btn3);
        rbBtn4 = (RadioButton) findViewById(R.id.rb_btn4);
        textTitle = findViewById(R.id.home_title);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_btn1) {
            setSelect(1);
            textTitle.setText("智慧城市");
        } else if (i == R.id.rb_btn2) {
            setSelect(2);
            textTitle.setText("全部服务");
        } else if (i == R.id.rb_btn3) {
            setSelect(3);
            textTitle.setText("新闻");
        } else if (i == R.id.rb_btn4) {
            setSelect(4);
            textTitle.setText("个人中心");
        }
    }

    private void setSelect(int x) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment1!=null){
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2!=null){
            fragmentTransaction.hide(fragment2);
        }
        if (fragment3!=null){
            fragmentTransaction.hide(fragment3);
        }
        if (fragment4!=null){
            fragmentTransaction.hide(fragment4);
        }
        switch (x){
            case 1:
                if (fragment1==null){
                    fragment1=new AFragment();
                    fragmentTransaction.add(R.id.home_fragment,fragment1);
                }else {
                    rbBtn1.setChecked(true);
                    fragmentTransaction.show(fragment1);
                }
                break;
            case 2:
                if (fragment2==null){
                    fragment2=new BFragment();
                    fragmentTransaction.add(R.id.home_fragment,fragment2);
                }else {
                    rbBtn2.setChecked(true);
                    fragmentTransaction.show(fragment2);
                }
                break;
            case 3:
                if (fragment3==null){
                    fragment3=new CFragment();
                    fragmentTransaction.add(R.id.home_fragment,fragment3);
                }else {
                    rbBtn3.setChecked(true);
                    fragmentTransaction.show(fragment3);
                }
                break;
            case 4:
                if (fragment4==null){
                    fragment4=new DFragment();
                    fragmentTransaction.add(R.id.home_fragment,fragment4);
                }else {
                    rbBtn4.setChecked(true);
                    fragmentTransaction.show(fragment4);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}