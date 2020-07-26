package com.usacheow.coredata.di

import com.usacheow.coredata.BuildConfig
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    @Named("ENDPOINT")
    fun endpoint(): String = BuildConfig.ENDPOINT

    @Provides
    @Singleton
    fun retrofit(
        okHttp: OkHttpClient,
        @Named("ENDPOINT") host: String,
        converter: GsonConverterFactory,
        callAdapter: RxJava2CallAdapterFactory,
        rxCallFactory: RxErrorHandlingCallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(callAdapter)
        .addCallAdapterFactory(rxCallFactory)
        .addConverterFactory(converter)
        .client(okHttp)
        .baseUrl(host)
        .build()
}