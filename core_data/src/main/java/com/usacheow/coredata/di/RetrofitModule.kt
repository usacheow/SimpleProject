package com.usacheow.coredata.di

import com.usacheow.coredata.BuildConfig
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import com.usacheow.di.ApplicationScope
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
        callAdapter: RxJava2CallAdapterFactory,
        rxCallFactory: RxErrorHandlingCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(callAdapter)
            .addCallAdapterFactory(rxCallFactory)
            .addConverterFactory(converter)
            .client(okHttp)
            .baseUrl(host)
            .build()
    }
}