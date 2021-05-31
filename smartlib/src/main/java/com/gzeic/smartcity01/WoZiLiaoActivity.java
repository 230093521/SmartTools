package com.gzeic.smartcity01;

import android.Manifest;
import android.content.Intent;
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
import com.gzeic.smartcity01.bean.UsersBean;
import com.xsonline.smartlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WoZiLiaoActivity extends BaseActivity implements View.OnClickListener {
    UsersBean.UserDTO dataBean;
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
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_wo_ziliao);

        dataBean = getUserdata();
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
        etEmail = (EditText) findViewById(R.id.et_email);
        tvSetSave = (TextView) findViewById(R.id.tv_set_save);
        if (etEmail.getText().toString().isEmpty()){
            etEmail.setText("test@qq.com");
        }else {
            etEmail.setText(dataBean.getEmail());
        }
        Glide.with(getApplicationContext()).load("http://" + getServerIp() + dataBean.getAvatar()).error(R.drawable.icon2).into(ivIcon);
        etNickname.setText(dataBean.getNickName());
        String idCard = dataBean.getIdCard();
        String idc = null;
        try {
            char[] chars = idCard.toCharArray();
            idc = chars[0] + chars[1] + "***********" + chars[chars.length - 4] + chars[chars.length - 3] + chars[chars.length - 2] + chars[chars.length - 1];
            etZjh.setText(idc);
        } catch (Exception e) {
            e.printStackTrace();
            etZjh.setText("52************3682");
        }
        if (dataBean.getPhonenumber() == null) {
            etPhone.setText("15888888888");
        } else {
            if (dataBean.getPhonenumber().isEmpty()) {
                etPhone.setText("15888888888");
            } else {
                etPhone.setText(dataBean.getPhonenumber());
            }
        }
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
            JSONObject jsonObject = new JSONObject();
            int sex = 0;
            if (sexNv.isChecked()) {
                sex = 1;
            }
            try {
                jsonObject.put("email", etEmail.getText().toString());
                jsonObject.put("idCard", dataBean.getIdCard());
                jsonObject.put("nickName", etNickname.getText().toString());
                jsonObject.put("phonenumber", etPhone.getText().toString());
                jsonObject.put("sex", sex);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            getTools().sendPutRequestToken(jsonObject, "http://" + getServerIp() + "/prod-api/api/common/user", getToken(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    final UsersBean usersBean = new Gson().fromJson(string, UsersBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(usersBean.getMsg());
                        }
                    });
                }
            });
//                try {
//                    File file = null;
//                    if (imageUri != null) {
//                        file = new File(imageUri);
//                    }
//                    Log.i(TAG, "onClick: " + imageUri);
//                    int sex = 1;
//                    RequestBody fileBody = RequestBody.create(MediaType.parse("application/from-data"), file);
//                    MultipartBody multipartBody = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("userId", dataBean.getUserId()+"")
//                            .addFormDataPart("idCard", etZjh.getText().toString())
//                            .addFormDataPart("nickName", etNickname.getText().toString())
//                            .addFormDataPart("email", dataBean.getEmail())
//                            .addFormDataPart("phonenumber", etPhone.getText().toString())
//                            .addFormDataPart("sex", sex+"")
//                            .addFormDataPart("file", file.getName(), fileBody)
//                            .addFormDataPart("remark", "备注")
//                            .build();
//                    Request request = new Request.Builder().url("http://" + getServerIp() + "/system/user/updata")
//                            .header("Authorization",getToken()).post(multipartBody).build();
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    okHttpClient.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            String json = response.body().string();
//                            final MyDataBean myDataBean = new Gson().fromJson(json, MyDataBean.class);
//                            try {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        if (myDataBean.getCode()==200){
//                                            showToast("修改成功");
//                                            finish();
//                                        }else {
//                                            showToast(myDataBean.getMsg());
//                                        }
//                                    }
//                                });
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            showToast("未选择新头像");
//                        }
//                    });
//                }
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