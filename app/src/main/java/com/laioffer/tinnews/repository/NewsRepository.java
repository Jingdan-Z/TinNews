package com.laioffer.tinnews.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laioffer.tinnews.TinNewsApplication;
import com.laioffer.tinnews.database.NewsDao;
import com.laioffer.tinnews.database.NewsDatabase;
import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsRepository {
    //request to get news
    private final NewsApi newsApi;
    //get data from local DB
    private final NewsDatabase database;
    public NewsRepository() {
        // used to implements of newsApi
        newsApi = RetrofitClient.newInstance().create(NewsApi.class);
        //create a database instance by casting application context into TinnewsApplication
        database = TinNewsApplication.getDatabase();
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
    //localDB
    //accessing the disk storage may be slow => dispatch it to background thread not the main UI thread
    //AsyncTask<Params, Progress, Result>
    private static class FavoriteAsyncTask extends AsyncTask<Article, Void, Boolean> {
        private final NewsDatabase database;
        private final MutableLiveData<Boolean> liveData;
        private FavoriteAsyncTask(NewsDatabase database, MutableLiveData<Boolean> liveData) {
            this.database = database;
            this.liveData = liveData;
        }
        @Override
        protected void onPreExecute() {
            Log.d("Thread", Thread.currentThread().getName() + "1.5");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        //Everything inside doInBackground would be executed on a separate background thread
        @Override
        protected Boolean doInBackground(Article...articles) {
            Article article = articles[0];
            try{
                database.newsDao().saveArticles(article);
            }catch (Exception e) {
                return false;
            }
            return true;
        }
        //After doInBackground finishes, onPostExecute would be executed back on the main UI thread
        @Override
        protected void onPostExecute(Boolean success) {
            liveData.setValue(success);
        }
    }
    //execute returns immediately. The database operation runs in the background
    // and notifies the result through the resultLiveData at a later time.
    public LiveData<Boolean> favoriteArticle(Article article) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new FavoriteAsyncTask(database, resultLiveData).execute(article);
        return resultLiveData;
    }
    public LiveData<List<Article>> getAllSavedArticles() {
        return database.newsDao().getAllArticles();
    }

    public void deleteSavedArticle(Article article) {
        AsyncTask.execute(() -> database.newsDao().deleteArticle(article));
    }


}
