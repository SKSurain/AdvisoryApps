package com.example.surai.myapplication;

import com.example.surai.myapplication.model.ListingResponse;
import com.example.surai.myapplication.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("listing")
    Call<ListingResponse> getListing(
            @Query("id") int id,
            @Query("token") String token
    );
};
