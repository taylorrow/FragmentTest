package com.midigame.fragmenttest.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static Kot3Api api;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://kot3.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Kot3Api.class);
    }

    public static Kot3Api getApi() {
        return api;
    }
}