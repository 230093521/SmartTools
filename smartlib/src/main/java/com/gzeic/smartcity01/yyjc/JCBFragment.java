package com.gzeic.smartcity01.yyjc;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.Tools.ListViewScrollView;
import com.gzeic.smartcity01.bean.YycheliangBean;
import com.gzeic.smartcity01.bean.YyddBean;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class JCBFragment extends BaseFragment {

    private ListViewScrollView listview;
    List<YycheliangBean.RowsDTO> yycheliangBeanRows;
    Myadapter myadapter;
    int num;
    private Button button;
    private Button didian;
    private Button add;
    String jiancheshijian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jc_b, container, false);
        initView(view);
        showData();
        didian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),YyjcDdActivity.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = getSP("jcsj");
                String didian = getSP("didian");
                YyddBean.RowsDTO rowsDTO = new Gson().fromJson(didian, YyddBean.RowsDTO.class);
                String addressId = String.valueOf(rowsDTO.getId());
                YycheliangBean.RowsDTO rowsDTO1 = yycheliangBeanRows.get(num);
                String carId = String.valueOf(rowsDTO1.getId());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userId",getUserdata().getUserId());
                    jsonObject.put("carId",carId);
                    jsonObject.put("aptTime",time);
                    jsonObject.put("addressId",addressId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp()+"/userinfo/apt", getToken(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        final YyddBean yyddBean = new Gson().fromJson(string, YyddBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(yyddBean.getMsg());
                                YyjcActivity activity = (YyjcActivity) getActivity();
                                activity.setSelect(3);
                            }
                        });
                    }
                });
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getActivity(),Calendar.getInstance(Locale.CHINA));
            }
        });
        return view;
    }


    public void showData(){
        getTools().sendGetRequestToken("http://" + getServerIp() + "/userinfo/cars/list?userId=1&pageNum=1&pageSize=10", getToken(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                YycheliangBean yycheliangBean = new Gson().fromJson(string, YycheliangBean.class);
                yycheliangBeanRows = yycheliangBean.getRows();
                if (yycheliangBean.getCode() == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myadapter = new Myadapter();
                            listview.setAdapter(myadapter);
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        showData();
        String didian2 = getSP("didian");
        if (didian2!=null){
            if (!didian2.isEmpty()){
                YyddBean.RowsDTO rowsDTO = new Gson().fromJson(didian2, YyddBean.RowsDTO.class);
                didian.setText(rowsDTO.getAddress());
            }
        }
        String time = getSP("jcsj");
        if (time!=null){
            if(!time.isEmpty()){
                button.setText(time);
            }
        }
    }

    /**
     * 日期选择
     *
     * @param activity
     * @param calendar
     */
    public void showDatePickerDialog(final Activity activity, final Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                jiancheshijian = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "";
                showTimePickerDialog(activity,calendar);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     *
     * @param activity
     * @param calendar
     */
    public void showTimePickerDialog(Activity activity, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog(activity,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        button.setText(jiancheshijian  +"  " +hourOfDay + ":" + minute+":00");
                        putSP("jcsj",jiancheshijian  +"  " +hourOfDay + ":" + minute+":00");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                , true).show();
    }


    class Myadapter extends BaseAdapter {
        class ViewHolder {
            public View rootView;
            public TextView chepai;
            public TextView chejia;
            public TextView cheleixin;
            public TextView gongli;
            public TextView shoujihao;
            public RadioButton xuanze;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                this.chejia = (TextView) rootView.findViewById(R.id.chejia);
                this.cheleixin = (TextView) rootView.findViewById(R.id.cheleixin);
                this.gongli = (TextView) rootView.findViewById(R.id.gongli);
                this.shoujihao = (TextView) rootView.findViewById(R.id.shoujihao);
                this.xuanze = (RadioButton) rootView.findViewById(R.id.xuanze);
            }

        }


        @Override
        public int getCount() {
            return yycheliangBeanRows.size();
        }

        @Override
        public Object getItem(int position) {
            return yycheliangBeanRows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_yyjc_cl, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            YycheliangBean.RowsDTO rowsDTO = yycheliangBeanRows.get(position);
            viewHolder.chejia.setText(rowsDTO.getMainNum());
            viewHolder.cheleixin.setText(rowsDTO.getCarType());
            viewHolder.chepai.setText(rowsDTO.getPlateNum());
            viewHolder.gongli.setText(rowsDTO.getMileage());
            viewHolder.shoujihao.setText(rowsDTO.getPhone());
            if (num == position) {
                viewHolder.xuanze.setChecked(true);
            } else {
                viewHolder.xuanze.setChecked(false);
            }
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num = position;
                    myadapter.notifyDataSetChanged();
                }
            });
            viewHolder.xuanze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        num=position;
                        myadapter.notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }
    }


    private void initView(View view) {
        listview = (ListViewScrollView) view.findViewById(R.id.listview);
        button = (Button) view.findViewById(R.id.time);
        didian = (Button) view.findViewById(R.id.didian);
        add = (Button) view.findViewById(R.id.add);
    }
}