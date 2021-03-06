package com.gzeic.smartcity01;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.Tools.Tools;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.gzeic.smartcity01.bean.UsersBean;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public abstract class BaseActivity extends AppCompatActivity {
    public String HTTP_HEAD = "http://";
//    getTools().sendGetRequestToken("http://" + getServerIp() + "", getToken(), new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//
//        }
//    });
    public String TAG = "test";
    Tools tools;
    public String getServerIp(){
        SharedPreferences sharedPreferences = getSharedPreferences("address",MODE_PRIVATE);
        String add = sharedPreferences.getString("add", null);
        return add;
    }

    public String getServer2Ip(){
        String add = null;
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("address2",MODE_PRIVATE);
            add = sharedPreferences.getString("address2", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }

    public void showToast(String text){
        Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
    }

    public Tools getTools(){
        if (tools==null){
            tools = new Tools();
        }
        return tools;
    }

    public String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        return token;
    }

    public String getThisTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//??????????????????
        return df.format(new Date());
    }

    public UsersBean.UserDTO getUserdata(){
        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        String userdata = sharedPreferences.getString("userdata", null);
        UsersBean myDataBean = new Gson().fromJson(userdata, UsersBean.class);
        return myDataBean.getUser();
    }

    public void putSP(String key,String value){
        SharedPreferences sharedPreferences = getSharedPreferences(key,MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).apply();
    }

    public String getSP(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    /**
     * ????????????
     *
     * @param activity
     * @param calendar
     */
//    public void showDatePickerDialog(final Activity activity, final Calendar calendar) {
//        // ??????????????????DatePickerDialog???????????????????????????????????????
//        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
//            // ???????????????(How the parent is notified that the date is set.)
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                // ????????????????????????????????????????????????????????????
//                jiancheshijian = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "";
//                showTimePickerDialog(activity,calendar);
//            }
//        }
//                // ??????????????????
//                , calendar.get(Calendar.YEAR)
//                , calendar.get(Calendar.MONTH)
//                , calendar.get(Calendar.DAY_OF_MONTH)).show();
//    }

    /**
     * ????????????
     *
     * @param activity
     * @param calendar
     */
//    public void showTimePickerDialog(Activity activity, Calendar calendar) {
//        // Calendar c = Calendar.getInstance();
//        // ????????????TimePickerDialog??????????????????????????????
//        // ???????????????Activity???context?????????
//        new TimePickerDialog(activity,
//                // ???????????????
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        button.setText(jiancheshijian  +"  " +hourOfDay + ":" + minute+":00");
//                        putSP("jcsj",jiancheshijian  +"  " +hourOfDay + ":" + minute+":00");
//                    }
//                }
//                // ??????????????????
//                , calendar.get(Calendar.HOUR_OF_DAY)
//                , calendar.get(Calendar.MINUTE)
//                // true????????????24?????????
//                , true).show();
//    }
}
