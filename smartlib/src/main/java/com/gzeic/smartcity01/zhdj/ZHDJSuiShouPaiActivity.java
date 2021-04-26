package com.gzeic.smartcity01.zhdj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gzeic.smartcity01.BaseActivity;
import com.xsonline.smartlib.R;


public class ZHDJSuiShouPaiActivity extends BaseActivity {

    private ImageView newsBase;
    private ImageView yanshizhaopian;
    private TextView xuanze;
    private TextView paizhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#03A9F4"));
        setContentView(R.layout.activity_zhdj_pai);
        initView();
        newsBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否授权这里以一个权限为例
                if (ContextCompat.checkSelfPermission(ZHDJSuiShouPaiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权进行权限申请
                    ActivityCompat.requestPermissions(ZHDJSuiShouPaiActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent();
                    /* 开启Pictures画面Type设定为image */
                    intent.setType("image/*");
                    /* 使用Intent.ACTION_GET_CONTENT这个Action */
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    /* 取得相片后返回本画面 */
                    startActivityForResult(intent, 2);
                }
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ZHDJSuiShouPaiActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权进行权限申请
                    ActivityCompat.requestPermissions(ZHDJSuiShouPaiActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 2);
                }
            }
        });

    }

    private void initView() {
        newsBase = (ImageView) findViewById(R.id.news_base);
        yanshizhaopian = (ImageView) findViewById(R.id.yanshizhaopian);
        xuanze = (TextView) findViewById(R.id.xuanze);
        paizhao = (TextView) findViewById(R.id.paizhao);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Bundle bundle = data.getExtras();
            //获取相机返回的数据，并转换为Bitmap图片格式，这是缩略图
            Bitmap bitmap = (Bitmap) bundle.get("data");
            yanshizhaopian.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//    private void saveScreenShot(Bitmap bitmap)  {
//        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        OutputStream outStream = null;
//        //以保存时间为文件名
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
//        String image_save_path =  sdf.format(date);
//
//        File file = new File(extStorageDirectory, image_save_path+".JPEG");//创建文件，第一个参数为路径，第二个参数为文件名
//        String picturePath=file.getPath();
//        try {
//            outStream = new FileOutputStream(file);//创建输入流
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//            outStream.close();
//            //这三行可以实现相册更新
//            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            Uri uri = Uri.fromFile(file);intent.setData(uri);
//            sendBroadcast(intent);
//        } catch(Exception e) {
//            Toast.makeText(ZHDJSuiShouPaiActivity.this, "exception:" + e,
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
}