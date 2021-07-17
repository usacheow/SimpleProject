package com.usacheow.coredata

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
        converter: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converter)
        .client(okHttp)
        .baseUrl(host)
        .build()
}