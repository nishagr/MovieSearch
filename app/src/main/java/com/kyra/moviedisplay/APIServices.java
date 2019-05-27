package com.kyra.moviedisplay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {

    String API_KEY = "c286ee83";
    String type="movie";
    @GET(".")
    Call<UserWrapper> getMovieList(@Query("s") String title, @Query("type") String type, @Query("page") int page,@Query("apikey")String key);

}
