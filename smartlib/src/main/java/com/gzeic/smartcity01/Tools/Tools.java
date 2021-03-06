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
     * ?????????????????????
     *
     * @param content                ???????????????
     * @param width                  ???????????????
     * @param height                 ???????????????
     * @param character_set          ???????????????????????????UTF-8???
     * @param error_correction_level ????????? L???7% M???15% Q???25% H???35%
     * @param margin                 ???????????????????????????????????????????????????
     * @param color_black            ????????????
     * @param color_white            ????????????
     * @return BitMap
     */
    public Bitmap createQRCodeBitmap(String content, int width,int height,
                                            String character_set,String error_correction_level,
                                            String margin,int color_black, int color_white) {
        // ?????????????????????
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // ?????????>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.??????????????????????????? */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // ????????????????????????
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // ???????????????
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // ??????????????????
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.????????????????????????QRCodeWriter???encode????????????BitMatrix(?????????)?????? */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            /** 3.??????????????????,?????????BitMatrix(?????????)????????????????????????????????? */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)????????????true??????????????????false???????????????
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//????????????????????????
                    } else {
                        pixels[y * width + x] = color_white;// ????????????????????????
                    }
                }
            }
            /** 4.??????Bitmap??????,????????????????????????Bitmap???????????????????????????,?????????Bitmap?????? */
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
            return "{\"msg\":\"??????????????????\",\"code\":500}";
        }
        return json;
    }


    /**
     * get????????? ??????token
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
     * get??????Token
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
     * get??????token?????????
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
     * delete??????token?????????
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
     * post??????token ????????????
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
     * post??????token??????
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
     * put???????????????Token
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
     * ????????????
     * @param file
     * @return Call<ResponseBody>
     */
    public Call<ResponseBody> uploadFile(File file) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.SERVER_API_HTTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //????????????,?????????????????????
//        MediaType textType = MediaType.parse("text/plain");
        //???????????????????????????RequestBody,???????????????RequestBody??????
//        RequestBody name = RequestBody.create(textType, "file");
        //??????????????????RequestBody??????
        //?????????????????????????????????????????????????????????????????????multipart/form-data
        RequestBody imgBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //??????????????????MultipartBody.Part
        //?????????????????????????????????key???????????????????????????????????????????????????RequestBody??????
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), imgBody);
        return retrofit.create(RequestServices.class).postUpload(filePart);
    }

}
