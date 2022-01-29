package com.laioffer.tinnews.network;

import com.laioffer.tinnews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
        //these are all defined in the API doc
        //relative URL path
        @GET("top-headlines")
        //An invocation of a Retrofit method that
        // sends a request to a webserver and returns a response.
        // Each call yields its own HTTP request and response pair.
        //these are the parameters specify in the request
        Call<NewsResponse> getTopHeadlines(@Query("country") String country);

        @GET("everything")
        Call<NewsResponse> getEverything(
                @Query("q") String query, @Query("pageSize") int pageSize);

}
