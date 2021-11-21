package com.usacheow.coredata

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
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
        converter: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converter)
        .client(okHttp)
        .baseUrl(host)
        .build()
}