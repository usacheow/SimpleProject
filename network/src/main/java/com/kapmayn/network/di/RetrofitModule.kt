package com.kapmayn.network.di

import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.network.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    @Named("ENDPOINT")
    fun provideEndpoint(): String = BuildConfig.ENDPOINT

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        okHttp: OkHttpClient,
        @Named("ENDPOINT") host: String,
        converter: GsonConverterFactory,
        callAdapter: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .client(okHttp)
            .baseUrl(host)
            .build()
    }
}