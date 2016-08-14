package com.oleksiisosevych.flickr;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleksiisosevych.flickr.data.DataModule;
import com.oleksiisosevych.flickr.data.api.FlickrService;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class MockDataModule extends DataModule {

    private HttpUrl overrideHttpUrl;
    private HttpLoggingInterceptor overrideHttpLoggingInterceptor;
    private OkHttpClient overrideOkHttpClient;
    private GsonBuilder overrideGsonBuilder;
    private Gson overrideGson;
    private Retrofit overrideRetrofit;
    private FlickrService overrideFlickrService;

    public void setOverrideHttpUrl(HttpUrl overrideHttpUrl) {
        this.overrideHttpUrl = overrideHttpUrl;
    }

    public void setOverrideHttpLoggingInterceptor(HttpLoggingInterceptor overrideHttpLoggingInterceptor) {
        this.overrideHttpLoggingInterceptor = overrideHttpLoggingInterceptor;
    }

    public void setOverrideOkHttpClient(OkHttpClient overrideOkHttpClient) {
        this.overrideOkHttpClient = overrideOkHttpClient;
    }

    public void setOverrideGsonBuilder(GsonBuilder overrideGsonBuilder) {
        this.overrideGsonBuilder = overrideGsonBuilder;
    }

    public void setOverrideGson(Gson overrideGson) {
        this.overrideGson = overrideGson;
    }

    public void setOverrideRetrofit(Retrofit overrideRetrofit) {
        this.overrideRetrofit = overrideRetrofit;
    }

    public void setOverrideFlickrService(FlickrService overrideFlickrService) {
        this.overrideFlickrService = overrideFlickrService;
    }

    @Override
    public HttpUrl provideHttpUrl() {
        return overrideHttpUrl != null ? overrideHttpUrl : super.provideHttpUrl();
    }


    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLogginInterceptor() {
        return overrideHttpLoggingInterceptor != null ? overrideHttpLoggingInterceptor :
                super.provideLogginInterceptor();

    }

    @Provides
    @Singleton
    public OkHttpClient provideApiClient(Application app, HttpLoggingInterceptor loggingInterceptor) {
        return overrideOkHttpClient != null ? overrideOkHttpClient :
                super.provideApiClient(app, loggingInterceptor);
    }


    @Provides
    @Singleton
    public GsonBuilder provideGsonBuilder() {
        return overrideGsonBuilder != null ? overrideGsonBuilder :
                super.provideGsonBuilder();
    }

    @Provides
    @Singleton
    public Gson provideGson(GsonBuilder gsonBuilder) {
        return overrideGson != null ? overrideGson :
                super.provideGson(gsonBuilder);
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client, Gson gson) {
        return overrideRetrofit != null ? overrideRetrofit :
                super.provideRetrofit(baseUrl, client, gson);
    }

    @Provides
    @Singleton
    public FlickrService provideFlickrApiService(Retrofit retrofit) {
        return overrideFlickrService != null ? overrideFlickrService :
                super.provideFlickrApiService(retrofit);
    }
}
