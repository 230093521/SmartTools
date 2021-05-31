package com.gzeic.smartcity01.dcyc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.load.engine.Resource;
import com.google.gson.Gson;
import com.gzeic.smartcity01.BaseFragment;
import com.xsonline.smartlib.R;
import com.gzeic.smartcity01.bean.NctjBean;
import com.gzeic.smartcity01.bean.SsqsjldBean;
import com.gzeic.smartcity01.zhsq.SqWuYeActivity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class DCAFragment extends BaseFragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dc_a, container, false);
        initView(view);
        getSsqSj();

        tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权进行权限申请
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                                } else {
                                    showToast("获取电话成功，正在拨打");
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse("tel:" + data.getPhone()));
                                    startActivity(intent);
                                }

                            }
                        });

                    }
                });

            }
        });


        return view;
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

//    private void uploadForm(File file, String url, Callback callback) {
//        String chepai = chepaihao.getText().toString();
//        if (chepai.isEmpty()) {
//            showToast("车牌号不能为空");
//            return;
//        }
//        String xingmina = xinmin.getText().toString();
//        if (xingmina.isEmpty()) {
//            showToast("姓名不能为空");
//            return;
//        }
//        String shoujihao2 = shoujihao.getText().toString();
//        if (shoujihao2.isEmpty()) {
//            showToast("手机号不能为空");
//            return;
//        }
//        String shenfenzhenga = shenfenzheng.getText().toString();
//        if (shenfenzhenga.isEmpty()) {
//            showToast("身份证号不能为空");
//            return;
//        }
//        String xiangxixinxi = xiangxi.getText().toString();
//        if (xiangxixinxi.isEmpty()) {
//            showToast("详细信息不能为空");
//            return;
//        }
//        String shenfenxinxi = shenlist.get(shengfen.getSelectedItemPosition());
//        String shixinxi = shilist.get(shiqu.getSelectedItemPosition());
//        String diquxinxi = qulist.get(diqu.getSelectedItemPosition());
////        if (file == null) {
////            //ImageView中的图像转为BitmapDrawable再到bitmap
////            BitmapDrawable drawable = (BitmapDrawable) tupian.getDrawable();
////            Bitmap bitmap = drawable.getBitmap();
////            file = getFile(bitmap);
////        }
////        RequestBody fileBody = RequestBody.create(MediaType.parse("application/from-data"), file);
////        MultipartBody multipartBody = new MultipartBody.Builder()
////                .setType(MultipartBody.FORM)
////                .addFormDataPart("cardId", shenfenzhenga)
////                .addFormDataPart("names", xingmina)
////                .addFormDataPart("userId", "1")
////                .addFormDataPart("tel", shoujihao2)
////                .addFormDataPart("address", shenfenxinxi + shixinxi + diquxinxi + xiangxixinxi)
////                .addFormDataPart("plates", chepai)
////                .addFormDataPart("file", "")
////                .build();
////        Request request = new Request.Builder()
////                .url(url)
////                .header("Authorization",getToken())
////                .post(multipartBody)
////                .build();
////        OkHttpClient okHttpClient = new OkHttpClient();
////        okHttpClient.newCall(request).enqueue(callback);
//
//    }


    public void senPostFile(MultipartBody multipartBody, String url, File file, Callback callback) {
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(callback);
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

        shenad = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, shenlist);
        shengfen.setAdapter(shenad);

        shiad = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, shilist);
        shiqu.setAdapter(shiad);

        quad = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, qulist);
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


    private void initView(View view) {
        chepaihao = (EditText) view.findViewById(R.id.chepaihao);
        xinmin = (EditText) view.findViewById(R.id.xinmin);
        shoujihao = (EditText) view.findViewById(R.id.shoujihao);
        shenfenzheng = (EditText) view.findViewById(R.id.shenfenzheng);
        shengfen = (Spinner) view.findViewById(R.id.shengfen);
        shiqu = (Spinner) view.findViewById(R.id.shiqu);
        diqu = (Spinner) view.findViewById(R.id.diqu);
        xiangxi = (EditText) view.findViewById(R.id.xiangxi);
        tijiaoanniu = (Button) view.findViewById(R.id.tijiaoanniu);
        tupian = (ImageView) view.findViewById(R.id.tupian);
    }
}