package com.example.orderform.model.api;


import com.example.orderform.model.bean.FormBean;
import com.example.orderform.model.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginApi {
    @FormUrlEncoded
    @POST
    Observable<LoginBean> login(@Field("phone") String phone, @Field("pwd") String pwd);


    @GET("order/verify/v1/findOrderListByStatus")
    Observable<FormBean> queryAllOrderForm(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("status") int status, @Query("page") int page, @Query("count") int count);

}
