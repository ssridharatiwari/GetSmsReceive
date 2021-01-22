package com.codunite.bhagyamatka.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    String SENDSMS = "bhagyematkaMsg";

    @FormUrlEncoded
    @POST(SENDSMS)
    Call<String> SendSms(@Field("mobile") String mobile,
                                 @Field("message") String sms);

}
