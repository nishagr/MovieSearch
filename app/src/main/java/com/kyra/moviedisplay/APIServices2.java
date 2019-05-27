package com.kyra.moviedisplay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices2 {

    String API_KEY = "c286ee83";
    String plot="full";
    @GET(".")
    Call<MovieDisplayData> getMovie(@Query("i") String imdbID, @Query("plot") String plot,@Query("apikey")String key);
}
