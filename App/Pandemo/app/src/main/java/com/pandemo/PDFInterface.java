package com.pandemo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface PDFInterface {
    String IMAGEURL = Varii.URL;
    @Multipart
    @POST("app_uploadImage")
    Call<String> uploadImage(
            @Part MultipartBody.Part file,
            @Part("filename") RequestBody name,
            @Part("t1") String kitid,
            @Part("t2") String logid,
            @Part("t3") String dist
    );

    @Multipart
    @POST("app_uploadAadhaar")
    Call<String> uploadAadhaar(
            @Part MultipartBody.Part file,
            @Part("filename") RequestBody name,
            @Part("t1") String logid
    );

    @Multipart
    @POST("app_uploadPhoto")
    Call<String> uploadPhoto(
            @Part MultipartBody.Part file,
            @Part("filename") RequestBody name,
            @Part("t1") String logid
    );

}
