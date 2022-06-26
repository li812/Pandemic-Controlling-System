package com.pandemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainApi {
    @FormUrlEncoded
    @POST("app_login")
    Call<List<Result>> login(
            @Field("t1") String name, @Field("t2") String ps
    );

    @GET("app_getdistrict")
    Call<List<districtDAO>> getdistrict(

    );

    @FormUrlEncoded
    @POST("app_getlocation")
    Call<List<loctionDAO>> getlocation(
            @Field("t1") String dist
    );

    @GET("app_getCategory")
    Call<List<categoryDAO>> getCategory(

    );

    @FormUrlEncoded
    @POST("app_register")
    Call<List<Result>> userregister(
            @Field("t1") String name,
            @Field("t2") String aadhaar,
            @Field("t3") String phone,
            @Field("t12") String dob,
            @Field("t4") String email,
            @Field("t5") String address,
            @Field("t6") String job,
            @Field("t7") String jobsec,
            @Field("t8") String jobdesc,
            @Field("t9") String dist,
            @Field("t10") String loc,
            @Field("t11") String pass
    );


    @FormUrlEncoded
    @POST("app_editPassword")
    Call<List<Result>> changePassword(
            @Field("t1") String cpass, @Field("t2") String npass, @Field("t3") String logid
    );

    @FormUrlEncoded
    @POST("app_getProfile")
    Call<List<publicDAO>> getUserProfile(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_getdoctorlist")
    Call<List<workerDAO>> getDoctorList(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_getashalist")
    Call<List<workerDAO>> getAshaworkers(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_docGetPublicList")
    Call<List<publicDAO>> getPublicList(
            @Field("t1") String dist
    );

    @FormUrlEncoded
    @POST("app_getShopList")
    Call<List<shopDAO>> getShopList(
            @Field("t1") String dist
    );
    @FormUrlEncoded
    @POST("app_getShopList1")
    Call<List<shopDAO>> getShopList1(
            @Field("t1") String dist,
            @Field("t2") String med
    );
    @FormUrlEncoded
    @POST("app_getMedicines")
    Call<List<medicineDAO>> getMedicines(
            @Field("t1") String shop
    );

    @FormUrlEncoded
    @POST("app_getprofilework")
    Call<List<workerDAO>> getprofilework(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_getAlert")
    Call<List<alertDAO>> getAlerts(
            @Field("t1") String dist
    );

    @FormUrlEncoded
    @POST("app_getTPR")
    Call<List<Result>> getTPR(
            @Field("t1") String dist
    );

    @FormUrlEncoded
    @POST("app_requestKit")
    Call<List<Result>> userRequestKit(
            @Field("t1") String ldate, @Field("t2") String logid, @Field("t3") String dist, @Field("t4") String cat
    );

    @FormUrlEncoded
    @POST("app_getKitDetails")
    Call<List<reqKitDAO>> getKitDetails(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_ashaKitRequests")
    Call<List<ashaReqKitDAO>> ashaGetKitDetails(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_issueKitRequest")
    Call<List<Result>> issueRequest(
            @Field("t1") String barcode, @Field("t2") String kitid
    );

    @FormUrlEncoded
    @POST("app_ashaVerifiedKitDetails")
    Call<List<ashaReqKitDAO>> ashaVerifiedKitDetails(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_getIssuedKitDetails")
    Call<List<userIssuedKitDAO>> userIssuedKitdetails(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_docGetCases")
    Call<List<docTestCasesDAO>> docGetCases(
            @Field("t1") String logid,
            @Field("t2") String dist
    );

    @FormUrlEncoded
    @POST("app_docCompletedTestCases")
    Call<List<docTestCasesDAO>> docCompletedTestCases(
            @Field("t1") String logid,
            @Field("t2") String dist
    );

    @FormUrlEncoded
    @POST("app_docTodayPositiveCases")
    Call<List<docTestCasesDAO>> docTodayTestCases(
            @Field("t1") String logid,
            @Field("t2") String dist
    );

    @FormUrlEncoded
    @POST("app_docAddReport")
    Call<List<Result>> docAddreport(
            @Field("t1") String caseid,
            @Field("t2") String result,
            @Field("t3") String msg,
            @Field("t4") String logid
    );

    @FormUrlEncoded
    @POST("app_addOrder")
    Call<List<Result>> addOrder(
            @Field("t1") String logid,
            @Field("t2") String qty,
            @Field("t3") String med,
            @Field("t4") String shop
    );

    @FormUrlEncoded
    @POST("app_userRemoveItem")
    Call<List<Result>> userRemoveItem(
            @Field("t1") String logid
    );

    @FormUrlEncoded
    @POST("app_getCartItems")
    Call<List<itemDAO>> getCartItems(
            @Field("t1") String user
    );

    @FormUrlEncoded
    @POST("app_userPlaceOrder")
    Call<List<Result>> userPlaceOrder(
            @Field("t1") String user,
            @Field("t2") String holder,
            @Field("t3") String cvv,
            @Field("t4") String exp,
            @Field("t5") String accno,
            @Field("t6") String total
    );

    @FormUrlEncoded
    @POST("app_userOrderDetails")
    Call<List<itemDAO>> getUserOrderItems(
            @Field("t1") String user
    );

    @FormUrlEncoded
    @POST("app_userSendServiceReq")
    Call<List<Result>> userSendServiceReq(
            @Field("t1") String user,
            @Field("t2") String reason,
            @Field("t3") String req,
            @Field("t4") String date,
            @Field("t5") String dist
    );

    @FormUrlEncoded
    @POST("app_userGetServiceBookings")
    Call<List<serviceDAO>> getServiceBookings(
            @Field("t1") String user
    );

    @FormUrlEncoded
    @POST("app_getAshaServiceRequests")
    Call<List<serviceDAO>> getAshaServiceRequests(
            @Field("t1") String user
    );

    @FormUrlEncoded
    @POST("app_ashaCompleteService")
    Call<List<Result>> ashaCompleteService(
            @Field("t1") String serviceid
    );

    @FormUrlEncoded
    @POST("app_getAshaServiceHistory")
    Call<List<serviceDAO>> getAshaServiceHistory(
            @Field("t1") String user
    );

    @FormUrlEncoded
    @POST("app_getUserChat")
    Call<List<chatDAO>> getUserChat(
            @Field("t1") String user,
            @Field("t2") String other
    );

    @FormUrlEncoded
    @POST("app_getDocChat")
    Call<List<chatDAO>> getDocChat(
            @Field("t1") String user,
            @Field("t2") String other
    );

    @FormUrlEncoded
    @POST("app_userSendMessage")
    Call<List<Result>> userSendMessage(
            @Field("t1") String user,
            @Field("t2") String other,
            @Field("t3") String msg
    );

    @FormUrlEncoded
    @POST("app_docSendMessage")
    Call<List<Result>> docSendMessage(
            @Field("t1") String user,
            @Field("t2") String other,
            @Field("t3") String msg
    );

}
