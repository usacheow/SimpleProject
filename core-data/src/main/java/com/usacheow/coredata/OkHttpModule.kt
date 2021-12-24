package com.usacheow.coredata

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.usacheow.coredata.database.TokenStorage
import com.usacheow.coredata.network.interceptors.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT_CONNECTION_SECONDS = 20L
private const val TIMEOUT_IO_OPERATION_SECONDS = 15L

@Module
@InstallIn(SingletonComponent::class)
class OkHttpModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun authenticationInterceptor(tokenStorage: TokenStorage) = AuthenticationInterceptor(tokenStorage)

    @Provides
    @Singleton
    fun chuckInterceptor(application: Application) = ChuckerInterceptor.Builder(application).build()

    @Provides
    @Singleton
    fun okHttpBuilderClient(
        logger: HttpLoggingInterceptor,
        authentication: AuthenticationInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IO_OPERATION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IO_OPERATION_SECONDS, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(authentication)
            .followRedirects(true)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(chuckerInterceptor)
            builder.addInterceptor(logger)
        }
        return builder.build()
    }
}