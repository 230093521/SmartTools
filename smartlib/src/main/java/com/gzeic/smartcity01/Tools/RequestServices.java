package com.gzeic.smartcity01.Tools;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RequestServices {

    @POST
    Call<ResponseBody> postJson(@Url String url, @Body RequestBody requestBody);

    @POST
    Call<ResponseBody> postJsonToken(@Url String url, @Header("Authorization") String token, @Body RequestBody requestBody);

    @Multipart
    @POST("/common/upload")
    Call<ResponseBody> postUpload(@Part MultipartBody.Part file);

    @PUT
    Call<ResponseBody> putJsonToken(@Url String url, @Header("Authorization") String token, @Body RequestBody requestBody);

    @GET
    Call<ResponseBody> getSendJsonMap(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, Object> map);

    @GET
    Call<ResponseBody> getSendJson(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    Call<ResponseBody> getSend(@Url String url, @HeaderMap Map<String, String> headers);

    @DELETE
    Call<ResponseBody> deleteSend(@Url String url, @HeaderMap Map<String, String> headers);





    @Headers({"phone-type:android", "version:1.1.1"})
    @GET("user/emails")
    Call<ResponseBody> getHeadersData();

    @Multipart
    @POST("user/followers")
    Call<ResponseBody> getPartData(@Part("name") RequestBody name, @Part MultipartBody.Part file);

    //    //声明类型,这里是文字类型
//    MediaType textType = MediaType.parse("text/plain");
//    //根据声明的类型创建RequestBody,就是转化为RequestBody对象
//    RequestBody name = RequestBody.create(textType, "这里是你需要写入的文本：刘亦菲");
//
//    //创建文件，这里演示图片上传
//    File file = new File("文件路径");
//if (!file.exists()) {
//        file.mkdir();
//    }
//
//    //将文件转化为RequestBody对象
////需要在表单中进行文件上传时，就需要使用该格式：multipart/form-data
//    RequestBody imgBody = RequestBody.create(MediaType.parse("image/png"), file);
//    //将文件转化为MultipartBody.Part
////第一个参数：上传文件的key；第二个参数：文件名；第三个参数：RequestBody对象
//    MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), imgBody);
//
//    Call<ResponseBody> partDataCall = retrofit.create(Api.class).getPartData(name, filePart);
    @Multipart
    @POST("user/followers")
    Call<ResponseBody> getPartMapData(@PartMap Map<String, MultipartBody.Part> map);
//    File file1 = new File("文件路径");
//    File file2 = new File("文件路径");
//        if (!file1.exists()) {
//        file1.mkdir();
//    }
//        if (!file2.exists()) {
//        file2.mkdir();
//    }
//
//    RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/png"), file1);
//    RequestBody requestBody2 = RequestBody.create(MediaType.parse("image/png"), file2);
//    MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("file1", file1.getName(), requestBody1);
//    MultipartBody.Part filePart2 = MultipartBody.Part.createFormData("file2", file2.getName(), requestBody2);
//
//    Map<String,MultipartBody.Part> mapPart = new HashMap<>();
//        mapPart.put("file1",filePart1);
//        mapPart.put("file2",filePart2);
//
//    Call<ResponseBody> partMapDataCall = retrofit.create(Api.class).getPartMapData(mapPart);


}
