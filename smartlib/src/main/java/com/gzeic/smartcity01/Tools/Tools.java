package com.gzeic.smartcity01.Tools;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Tools {

    public void sendPostRequest(JSONObject jsonObject, String url, Callback callback){
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType,String.valueOf(jsonObject));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void sendPostRequestToken(JSONObject jsonObject, String url,String token, Callback callback){
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization",token)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void sendGetRequest(String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("Content-Type","application/x-www-form-urlencoded").get().build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void sendGetRequestForm(String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).addHeader("Content-Type","application/x-www-form-urlencoded").get().build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void sendGetRequestToken(String url,String token,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization",token)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void sendFormData(){
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/from-data"), file);
//        MultipartBody multipartBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("cardId",shenfenzhenga)
//                .addFormDataPart("names",xingmina)
//                .addFormDataPart("userId","1")
//                .addFormDataPart("tel",shoujihao2)
//                .addFormDataPart("address",shenfenxinxi + shixinxi +diquxinxi +xiangxixinxi)
//                .addFormDataPart("plates",chepai)
//                .addFormDataPart("file", file.getName(), fileBody)
//                .build();
//        Request request = new Request.Builder().url(url).post(multipartBody).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.newCall(request).enqueue(callback);
    }

    public void sendPutRequestToken(JSONObject jsonObject, String url,String token, Callback callback){
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization",token)
                .put(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void sendDeleteRequestToken(String url,String token,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization",token)
                .delete()
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 生成简单二维码
     *
     * @param content                字符串内容
     * @param width                  二维码宽度
     * @param height                 二维码高度
     * @param character_set          编码方式（一般使用UTF-8）
     * @param error_correction_level 容错率 L：7% M：15% Q：25% H：35%
     * @param margin                 空白边距（二维码与边框的空白区域）
     * @param color_black            黑色色块
     * @param color_white            白色色块
     * @return BitMap
     */
    public Bitmap createQRCodeBitmap(String content, int width,int height,
                                            String character_set,String error_correction_level,
                                            String margin,int color_black, int color_white) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static String JsonFormat(Response<ResponseBody> response){
        String json = null;
        try {
            assert response.body() != null;
            json = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"msg\":\"请求内部错误\",\"code\":500}";
        }
        return json;
    }


    /**
     * get不带参 不带token
     * @param url
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendGet(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type","application/x-www-form-urlencoded");
        return requestServices.getSend(url, hashMap);
    }

    /**
     * get携带Token
     * @param url
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendGetToken(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type","application/x-www-form-urlencoded");
        hashMap.put("Authorization",ApiUrl.SERVER_API_TOKEN);
        return requestServices.getSendJson(url, hashMap);
    }

    /**
     * get携带token与参数
     * @param url
     * @param map
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendGetTokenMap(String url, Map<String, Object> map) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type","application/x-www-form-urlencoded");
        hashMap.put("Authorization",ApiUrl.SERVER_API_TOKEN);
        return requestServices.getSendJsonMap(url, hashMap,map);
    }

    /**
     * delete携带token与参数
     * @param url
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendDeleteToken(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Authorization",ApiUrl.SERVER_API_TOKEN);
        return requestServices.deleteSend(url, hashMap);
    }

    /**
     * post不带token 携带参数
     * @param url
     * @param hashMap
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendPostJson(String url, HashMap<String, String> hashMap) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, String.valueOf(new JSONObject(hashMap)));
        return requestServices.postJson(url, requestBody);
    }

    /**
     * post携带token参数
     * @param url
     * @param hashMap
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendPostJsonToken(String url, HashMap<String, Object> hashMap) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, String.valueOf(new JSONObject(hashMap)));
        return requestServices.postJsonToken(url, ApiUrl.SERVER_API_TOKEN, requestBody);
    }

    /**
     * put携带参数与Token
     * @param url
     * @param hashMap
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> sendPutJsonToken(String url, HashMap<String, Object> hashMap) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestServices requestServices = retrofit.create(RequestServices.class);
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, String.valueOf(new JSONObject(hashMap)));
        return requestServices.putJsonToken(url, ApiUrl.SERVER_API_TOKEN, requestBody);
    }

    /**
     * 文件上传
     * @param file
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> uploadFile(File file) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //声明类型,这里是文字类型
//        MediaType textType = MediaType.parse("text/plain");
        //根据声明的类型创建RequestBody,就是转化为RequestBody对象
//        RequestBody name = RequestBody.create(textType, "file");
        //将文件转化为RequestBody对象
        //需要在表单中进行文件上传时，就需要使用该格式：multipart/form-data
        RequestBody imgBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //将文件转化为MultipartBody.Part
        //第一个参数：上传文件的key；第二个参数：文件名；第三个参数：RequestBody对象
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), imgBody);
        return retrofit.create(RequestServices.class).postUpload(filePart);
    }

}
