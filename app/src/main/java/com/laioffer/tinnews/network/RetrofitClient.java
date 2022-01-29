package com.laioffer.tinnews.network;

import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;

import com.laioffer.tinnews.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
// providing a configured Retrofit instance, that can then instantiate a NewsApi implementation
    // TODO need to hide the api key when upload
    private static final String API_KEY = "xxx";
    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor()) //add x-api-key to the header
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//tell how json can be deserilized
                .client(okHttpClient)
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {//bested class

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}
