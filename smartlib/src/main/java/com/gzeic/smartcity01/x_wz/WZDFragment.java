package com.gzeic.smartcity01.x_wz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.fragment.CFragment_zhhb;

public class WZDFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    Fragment fragment1, fragment2, fragment3;
    private RadioGroup homeRadio;
    private RadioButton rbBtn1;
    private RadioButton rbBtn2;
    private RadioButton rbBtn3;
    private FrameLayout homeFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wz_d, container, false);
        initView(view);
        rbBtn1.setChecked(true);
        homeRadio.setOnCheckedChangeListener(this);
        setSelect(1);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_btn1) {
            setSelect(1);
        } else if (checkedId == R.id.rb_btn2) {
            setSelect(2);
        } else if (checkedId == R.id.rb_btn3) {
            setSelect(3);
        }
    }

    public void setSelect(int x) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
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

        switch (x) {
            case 1:
                if (fragment1 == null) {
                    fragment1 = new WZDaFragment();
                    fragmentTransaction.add(R.id.home_fragmentd, fragment1);
                } else {
                    rbBtn1.setChecked(true);
                    fragmentTransaction.show(fragment1);
                }
                break;
            case 2:
                if (fragment2 == null) {
                    fragment2 = new WZDbFragment();
                    fragmentTransaction.add(R.id.home_fragmentd, fragment2);
                } else {
                    rbBtn2.setChecked(true);
                    fragmentTransaction.show(fragment2);
                }
                break;
            case 3:
                if (fragment3 == null) {
                    fragment3 = new WZDcFragment();
                    fragmentTransaction.add(R.id.home_fragmentd, fragment3);
                } else {
                    rbBtn3.setChecked(true);
                    fragmentTransaction.show(fragment3);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void initView(View view) {
        homeRadio = (RadioGroup) view.findViewById(R.id.home_radio);
        rbBtn1 = (RadioButton) view.findViewById(R.id.rb_btn1);
        rbBtn2 = (RadioButton) view.findViewById(R.id.rb_btn2);
        rbBtn3 = (RadioButton) view.findViewById(R.id.rb_btn3);
        homeFragment = (FrameLayout) view.findViewById(R.id.home_fragment);
    }
}