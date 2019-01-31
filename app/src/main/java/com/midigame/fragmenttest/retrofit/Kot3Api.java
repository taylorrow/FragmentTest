package com.midigame.fragmenttest.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Kot3Api {

    @GET("/xim/api.php")
    Call<Data> getData(@Query("query") String resourceName);
}
