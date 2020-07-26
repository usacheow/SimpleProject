package com.usacheow.coredata.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.usacheow.coredata.BuildConfig
import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.network.interceptors.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT_CONNECTION_SECONDS = 20L
private const val TIMEOUT_IO_OPERATION = 15L

@Module
@InstallIn(ApplicationComponent::class)
class OkHttpModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun authenticationInterceptor(
        storage: Storage
    ) = AuthenticationInterceptor(storage)

    @Provides
    @Singleton
    fun chuckInterceptor(context: Context) = ChuckInterceptor(context)

    @Provides
    @Singleton
    fun okHttpBuilderClient(
        logger: HttpLoggingInterceptor,
        authentication: AuthenticationInterceptor,
        chuckInterceptor: ChuckInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IO_OPERATION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IO_OPERATION, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(authentication)
            .followRedirects(true)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(chuckInterceptor)
            builder.addInterceptor(logger)
        }
        return builder.build()
    }
}