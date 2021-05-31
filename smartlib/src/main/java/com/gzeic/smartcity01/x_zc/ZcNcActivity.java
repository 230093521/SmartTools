package com.gzeic.smartcity01.x_zc;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.NctjBean;
import com.gzeic.smartcity01.bean.SsqsjldBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZcNcActivity extends BaseActivity {
    private EditText chepaihao;
    private EditText xinmin;
    private EditText shoujihao;
    private EditText shenfenzheng;
    private Spinner shengfen;
    private Spinner shiqu;
    private Spinner diqu;
    private EditText xiangxi;
    private Button tijiaoanniu;
    private ArrayAdapter<String> shenad, shiad, quad;
    List<String> shenlist;
    List<String> shilist;
    List<String> qulist;
    SsqsjldBean[] ssqsjldBeans;
    File file;
    boolean flag = false;
    private ImageView tupian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_nc);
        initView();
        getSsqSj();
        tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ZcNcActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权进行权限申请
                    ActivityCompat.requestPermissions(ZcNcActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 2);
                }
            }
        });

        tijiaoanniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chepai = chepaihao.getText().toString();
                if (chepai.isEmpty()) {
                    showToast("车牌号不能为空");
                    return;
                }
                String xingmina = xinmin.getText().toString();
                if (xingmina.isEmpty()) {
                    showToast("姓名不能为空");
                    return;
                }
                String shoujihao2 = shoujihao.getText().toString();
                if (shoujihao2.isEmpty()) {
                    showToast("手机号不能为空");
                    return;
                }
                String shenfenzhenga = shenfenzheng.getText().toString();
                if (shenfenzhenga.isEmpty()) {
                    showToast("身份证号不能为空");
                    return;
                }
                String xiangxixinxi = xiangxi.getText().toString();
                if (xiangxixinxi.isEmpty()) {
                    showToast("详细信息不能为空");
                    return;
                }
                if (!flag){
                    showToast("您还未拍照，请先拍照噢！");
                    return;
                }
                getTools().sendGetRequest("http://" + getServerIp() + "/userinfo/caraction", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.i(TAG, "onResponse: " + string);
                        String json = "{\n" +
                                " \"msg\": \"操作成功\",\n" +
                                " \"code\": 200,\n" +
                                " \"data\": {\n" +
                                " \"searchValue\": null,\n" +
                                " \"createBy\": null,\n" +
                                " \"createTime\": null,\n" +
                                " \"updateBy\": null,\n" +
                                " \"updateTime\": null,\n" +
                                " \"remark\": null,\n" +
                                " \"params\": {},\n" +
                                " \"id\": null,\n" +
                                " \"licensePlate\": \"辽 F1M080\\t\",\n" +
                                " \"phone\": \"18675612226\",\n" +
                                " \"file\": null\n" +
                                " } }";
                        NctjBean nctjBean = new Gson().fromJson(json, NctjBean.class);
                        final NctjBean.DataDTO data = nctjBean.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (ActivityCompat.checkSelfPermission(ZcNcActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(ZcNcActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                                } else {
                                    //初始化
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ZcNcActivity.this);
                                    builder.setTitle("获取电话成功");
                                    builder.setMessage(data.getPhone());
                                    //底部的选择按钮1
                                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    //底部的选择按钮2
                                    builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            showToast("获取电话成功，正在拨打");
                                            Intent intent = new Intent(Intent.ACTION_CALL);
                                            intent.setData(Uri.parse("tel:" + data.getPhone()));
                                            startActivity(intent);
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    //创建builder.create();
                                    AlertDialog dialog = builder.create();
                                    //展示show
                                    dialog.show();

                                }

                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Bundle bundle = data.getExtras();
            //获取相机返回的数据，并转换为Bitmap图片格式，这是缩略图
            Bitmap bitmap = (Bitmap) bundle.get("data");
            tupian.setImageBitmap(bitmap);
            flag = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getSsqSj() {
        InputStream stream = getResources().openRawResource(R.raw.city);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ssqsjldBeans = new Gson().fromJson(reader, SsqsjldBean[].class);
        shenlist = new ArrayList<>();
        shilist = new ArrayList<>();
        qulist = new ArrayList<>();

        shenlist = getshen(ssqsjldBeans);
        shilist = getshi(ssqsjldBeans, shenlist.get(0));
        qulist = getqu(ssqsjldBeans, shenlist.get(0), shilist.get(0));

        shenad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shenlist);
        shengfen.setAdapter(shenad);

        shiad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shilist);
        shiqu.setAdapter(shiad);

        quad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, qulist);
        diqu.setAdapter(quad);

        shengfen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shen = shenlist.get(position);
                shilist = getshi(ssqsjldBeans, shen);
                shiad.notifyDataSetChanged();
                shiqu.setSelection(0);

                String shiqumin = shilist.get(0);
                qulist = getqu(ssqsjldBeans, shen, shiqumin);
                quad.notifyDataSetChanged();
                diqu.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shiqu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String shen = shenlist.get(shengfen.getSelectedItemPosition());
                String shiqumin = shilist.get(position);
                qulist = getqu(ssqsjldBeans, shen, shiqumin);
                quad.notifyDataSetChanged();
                diqu.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private List<String> getshen(SsqsjldBean[] shen) {
        shenlist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            shenlist.add(ssqsjldBean.getProvinceName());
        }
        return shenlist;
    }

    private List<String> getshi(SsqsjldBean[] shen, String shenfen) {
        shilist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            if (ssqsjldBean.getProvinceName().equals(shenfen)) {
                for (SsqsjldBean.MallCityListDTO mallCityListDTO : ssqsjldBean.getMallCityList()) {
                    shilist.add(mallCityListDTO.getCityName());
                }
            }
        }
        return shilist;
    }

    private List<String> getqu(SsqsjldBean[] shen, String shi, String qu) {
        qulist.clear();
        for (SsqsjldBean ssqsjldBean : shen) {
            if (ssqsjldBean.getProvinceName().equals(shi)) {
                for (SsqsjldBean.MallCityListDTO mallCityListDTO : ssqsjldBean.getMallCityList()) {
                    if (mallCityListDTO.getCityName().equals(qu)) {
                        for (SsqsjldBean.MallCityListDTO.MallAreaListDTO mallAreaListDTO : mallCityListDTO.getMallAreaList()) {
                            qulist.add(mallAreaListDTO.getAreaName());
                        }
                    }
                }
            }
        }
        return qulist;
    }

    private void initView() {
        chepaihao = (EditText) findViewById(R.id.chepaihao);
        xinmin = (EditText) findViewById(R.id.xinmin);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
        shenfenzheng = (EditText) findViewById(R.id.shenfenzheng);
        shengfen = (Spinner) findViewById(R.id.shengfen);
        shiqu = (Spinner) findViewById(R.id.shiqu);
        diqu = (Spinner) findViewById(R.id.diqu);
        xiangxi = (EditText) findViewById(R.id.xiangxi);
        tijiaoanniu = (Button) findViewById(R.id.tijiaoanniu);
        tupian = (ImageView) findViewById(R.id.tupian);
    }
}