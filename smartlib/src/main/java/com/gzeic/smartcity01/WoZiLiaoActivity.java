package com.gzeic.smartcity01;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gzeic.smartcity01.bean.MyDataBean;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoZiLiaoActivity extends BaseActivity implements View.OnClickListener {
    MyDataBean.DataBean dataBean;
    private RelativeLayout rlIcon;
    private ImageView ivIcon;
    private EditText etNickname;
    private RadioGroup rgSex;
    private RadioButton sexNan;
    private RadioButton sexNv;
    private EditText etZjh;
    private EditText etPhone;
    private TextView tvSetSave;
    private String imageUri;
    private ImageView infoBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_wo_ziliao);
        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        String userdata = sharedPreferences.getString("userdata", null);
        MyDataBean myDataBean = new Gson().fromJson(userdata, MyDataBean.class);
        dataBean = myDataBean.getData();
        initView();

    }

    private void initView() {
        rlIcon = (RelativeLayout) findViewById(R.id.rl_icon);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        etNickname = (EditText) findViewById(R.id.et_nickname);
        rgSex = (RadioGroup) findViewById(R.id.rg_sex);
        sexNan = (RadioButton) findViewById(R.id.sex_nan);
        sexNv = (RadioButton) findViewById(R.id.sex_nv);
        etZjh = (EditText) findViewById(R.id.et_zjh);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
        Glide.with(getApplicationContext()).load("http://" + getServerIp() + dataBean.getAvatar()).error(R.mipmap.ic_launcher).into(ivIcon);
        etNickname.setText(dataBean.getNickName());
        String idCard = dataBean.getIdCard();
        char[] chars = idCard.toCharArray();
        String idc = chars[0]+chars[1]+chars[2]+"***********"+chars[chars.length-4]+chars[chars.length-3]+chars[chars.length-2]+chars[chars.length-1];
        etZjh.setText(idc);
        etPhone.setText(dataBean.getPhonenumber());
        if (dataBean.getSex().equals("0")) {
            sexNan.setChecked(true);
        } else {
            sexNv.setChecked(true);
        }
        infoBase = (ImageView) findViewById(R.id.info_base);
        tvSetSave.setOnClickListener(this);
        rlIcon.setOnClickListener(this);
        infoBase.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.info_base) {
            finish();
        } else if (id == R.id.rl_icon) {//打开图库
            if (ActivityCompat.checkSelfPermission(WoZiLiaoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 200);
            }
        } else if (id == R.id.tv_set_save) {
            File file = null;
            if (imageUri != null) {
                file = new File(imageUri);
            }
            Log.i(TAG, "onClick: " + imageUri);
            JSONObject jsonObject = new JSONObject();
            int sex = 1;
            try {
                jsonObject.put("userId", "1");
                jsonObject.put("idCard", etZjh.getText().toString());
                jsonObject.put("userName", dataBean.getUserName());
                jsonObject.put("nickName", etNickname.getText().toString());
                jsonObject.put("email", dataBean.getEmail());
                jsonObject.put("phonenumber", etPhone.getText().toString());
                if (!sexNan.isChecked()) {
                    sex = 0;
                }
                jsonObject.put("sex", sex);
                jsonObject.put("file", file);
                jsonObject.put("remark", System.currentTimeMillis());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
            String token = sharedPreferences.getString("token", null);
            //保存请求
            final int finalSex = sex;
            getTools().sendPostRequestToken(jsonObject, "http://" + getServerIp() + "/system/user/updata", token, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i(TAG, "onResponse6666666666: " + json);
                    MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
                    dataBean.setNickName(etNickname.getText().toString());
                    dataBean.setSex("1");
                    dataBean.setPhonenumber(etPhone.getText().toString());
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                Log.i(TAG, "onActivityResult: " + data.getData() + "    " + requestCode + "    " + resultCode);
                imageUri = getRealPathFromURI(data.getData());
                Log.i(TAG, "onActivityResult: " + imageUri);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getApplicationContext()).load(data.getData()).error(R.mipmap.ic_launcher).into(ivIcon);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(contentURI, null, null, null, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}