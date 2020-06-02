package com.yash.movie_app.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yash.movie_app.di.scope.ApplicationScope
import com.yash.movie_app.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Joshi on 31-05-2020.
 */

@Module
class NetworkModule(
    private val baseUrl: String,
    private val application: Application
) {

    companion object {
        const val CACHE_SIZE = 10 * 1024 * 1024L // 10 MiB
    }

    @ApplicationScope
    @Provides
    fun provideGson() = GsonBuilder().create()

    @ApplicationScope
    @Provides
    fun provideOkHttpCache() = Cache(application.cacheDir, CACHE_SIZE)

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(cache: Cache) = with(OkHttpClient.Builder()) {
        cache(cache)
        addInterceptor(headersInterceptor()).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        build()
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private fun headersInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YWIxMTIxMDEwN2UwZDU2OTQxNjNjZTMxYmM2NzA3ZSIsInN1YiI6IjVlZDNkNmViZTRiNTc2MDAxZjM0MWQzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PiGYzIpKpiXcsnznTMQBHvU0-jbTjAKRZ7acS57LuKE"
                )
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}