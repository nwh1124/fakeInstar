package com.practice.myhome.fakeInstar.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.practice.myhome.fakeInstar.BuildConfig;
import com.practice.myhome.fakeInstar.api.MainApi;
import com.practice.myhome.fakeInstar.service.ArticleService;
import com.practice.myhome.fakeInstar.service.MemberService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class SingletonModule {

    @Provides
    @Singleton
    public static MemberService provideMemberService() {
        return new MemberService();
    }

    @Provides
    @Singleton
    public static ArticleService provideArticleService(MainApi mainApi){
        return new ArticleService(mainApi);
    }

    @Provides
    @Singleton
    public static MainApi provideMainApi(Retrofit.Builder retrofitBuilder){
        Retrofit retrofit = retrofitBuilder
                .baseUrl(MainApi.baseUrl)
                .build();

        return retrofit.create(MainApi.class);
    }

    @Provides
    public static Retrofit.Builder provideRetrofitBuilder() {
        OkHttpClient okHttpClient = null;

        if(BuildConfig.DEBUG){
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create());

        if(okHttpClient != null){
            retrofitBuilder.client(okHttpClient);
        }

        return retrofitBuilder;
    }

}
