package com.usacheow.coredata.di

import com.usacheow.coredata.BuildConfig
import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.network.interceptors.AuthenticationInterceptor
import com.usacheow.di.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIMEOUT_CONNECTION_SECONDS = 20L
private const val TIMEOUT_IO_OPERATION = 15L

@Module
class OkHttpModule {

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @ApplicationScope
    fun provideAuthenticationInterceptor(
        storage: Storage
    ) = AuthenticationInterceptor(storage)

    @Provides
    @ApplicationScope
    fun provideOkHttpBuilderClient(
        logger: HttpLoggingInterceptor,
        authentication: AuthenticationInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IO_OPERATION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IO_OPERATION, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(authentication)
            .followRedirects(true)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logger)
        }
        return builder.build()
    }
}