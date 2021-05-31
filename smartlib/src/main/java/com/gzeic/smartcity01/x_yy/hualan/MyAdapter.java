package com.gzeic.smartcity01.x_yy.hualan;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.YyYpccBean;
import com.gzeic.smartcity01.bean.YyYpxxxqBean;
import com.gzeic.smartcity01.x_yy.YyYyxqActivity;
import com.gzeic.smartcity01.x_yy.YyYpxqActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<YyYpccBean.RowsBean> rows;
    YyYyxqActivity yyGpxzActivity;
    List<YyYpxxxqBean.DataBean> ypxxss;
    Integer[] idss;
    private String add;
    private AdapterMeasureHelper mCardAdapterHelper = new AdapterMeasureHelper();

    public MyAdapter(YyYyxqActivity yyGpxzActivity, List<YyYpccBean.RowsBean> rows, Integer[] idss, List<YyYpxxxqBean.DataBean> ypxxss) {
        this.yyGpxzActivity = yyGpxzActivity;
        this.rows = rows;
        this.ypxxss = ypxxss;
        this.idss = idss;
        SharedPreferences sharedPreferences = yyGpxzActivity.getSharedPreferences("address", MODE_PRIVATE);
        add = sharedPreferences.getString("add", null);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yy_yplb, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        for (final YyYpxxxqBean.DataBean rowsBean:ypxxss){
            if (rowsBean.getId()==idss[position]){
                Glide.with(yyGpxzActivity).load("http://"+add+rowsBean.getCover()).error(R.drawable.icon2).into(holder.iv_big);
                holder.iv_big.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(yyGpxzActivity, YyYpxqActivity.class);
                        intent.putExtra("id", rowsBean.getId());
                        yyGpxzActivity.startActivity(intent);
                    }
                });
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return idss.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_big;
        public ViewHolder(final View view) {
            super(view);
            iv_big = (ImageView) view.findViewById(R.id.iv_big);
        }
    }
}
