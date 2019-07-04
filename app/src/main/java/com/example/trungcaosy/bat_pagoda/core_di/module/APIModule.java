package com.example.trungcaosy.bat_pagoda.core_di.module;

import com.example.trungcaosy.bat_pagoda.network.UserProfileService;
import com.example.trungcaosy.bat_pagoda.utils.DataConstant;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {


    public UserProfileService provideUserProfileService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideHttpClient())
                .build();

        return retrofit.create(UserProfileService.class);
    }

    @Singleton
    @Provides
    public OkHttpClient provideHttpClient(){
        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.readTimeout(1, TimeUnit.MINUTES);
        builderHttp.connectTimeout(1, TimeUnit.MINUTES);
        builderHttp.writeTimeout(1, TimeUnit.MINUTES);
        return builderHttp.build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }
//
//    @Provides
//    @Singleton
//    public TherapyHistoryRepos provideTherapyHistoryRepos(){
//        return new TherapyHistoryDataRepos(provideUserProfileService());
//    }
}