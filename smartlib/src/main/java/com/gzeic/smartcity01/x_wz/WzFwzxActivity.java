package com.gzeic.smartcity01.x_wz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;

public class WzFwzxActivity extends BaseFragment {
    private ListView list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_wz_fwzx, container, false);
        list = inflate.findViewById(R.id.list);
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 6;
            }
            @Override
            public Object getItem(int position) {
                return position;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.itme_fwzx, parent, false);
                inflate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), WzFwtwxqActivity.class));
                    }
                });
                return inflate1;
            }
        });
        return inflate;
    }
}