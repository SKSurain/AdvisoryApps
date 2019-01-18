package com.example.surai.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String Base_Url = "http://interview.advisoryapps.com/index.php/";
    private static RetrofitClient mInstance;
    private static Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) mInstance = new RetrofitClient();
        return mInstance;
    }

    public API getApi() {
        return retrofit.create(API.class);
    }
}
