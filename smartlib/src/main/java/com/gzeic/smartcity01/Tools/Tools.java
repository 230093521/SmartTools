package com.gzeic.smartcity01.Tools;

import org.json.JSONObject;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


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
                .header("Authorization",token)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void sendGetRequest(String url, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void sendGetRequestToken(String url, String token, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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
                .header("Authorization",token)
                .put(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
