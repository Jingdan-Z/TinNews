package com.laioffer.tinnews.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsRepository {
    //request to get news
    private final NewsApi newsApi;
    public NewsRepository() {
        // used to implements of newsApi
        newsApi = RetrofitClient.newInstance().create(NewsApi.class);
    }
    //implement getTopHeadlines API
    public LiveData<NewsResponse> getTopHeadlines(String country) {
        MutableLiveData<NewsResponse> topHeadlinesLiveData = new
                MutableLiveData<>();
        /** .enqueue
         * Asynchronously send the request and notify callback of its response or
         * if an error occurred talking to the server, creating the request,
         * or processing the response.
         */
        newsApi.getTopHeadlines(country)
                .enqueue(new Callback<NewsResponse>() {//async
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()) {
                            topHeadlinesLiveData.setValue((response.body()));
                        }else{
                            topHeadlinesLiveData.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        topHeadlinesLiveData.setValue(null);
                    }
                });
        return topHeadlinesLiveData;
    }
    //implement searchNews API
    public LiveData<NewsResponse> searchNews(String query) {
        MutableLiveData<NewsResponse> everythingLiveData = new
                MutableLiveData<>();
        newsApi.getEverything(query,40)
                .enqueue(new Callback<NewsResponse>() {//async
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()) {
                            everythingLiveData.setValue((response.body()));
                        }else{
                            everythingLiveData.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        everythingLiveData.setValue(null);
                    }
                });
        return everythingLiveData;
    }
}
