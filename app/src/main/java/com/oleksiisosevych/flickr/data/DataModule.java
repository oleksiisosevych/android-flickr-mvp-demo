package com.oleksiisosevych.flickr.data;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleksiisosevych.flickr.data.api.FlickrService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cache(cache);
    }

    @Provides
    @Singleton
    public HttpUrl provideHttpUrl() {
        return HttpUrl.parse("https://api.flickr.com/");
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLogginInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    public OkHttpClient provideApiClient(Application app, HttpLoggingInterceptor loggingInterceptor) {
        return createOkHttpClient(app)
                .build()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    @Singleton
    public Gson provideGson(GsonBuilder gsonBuilder) {
        return gsonBuilder.create();
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    public FlickrService provideFlickrApiService(Retrofit retrofit) {
        return retrofit.create(FlickrService.class);
    }

}
