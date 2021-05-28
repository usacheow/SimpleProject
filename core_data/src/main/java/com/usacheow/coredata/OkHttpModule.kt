package com.usacheow.coredata

import android.app.Application
import com.readystatesoftware.chuck.ChuckInterceptor
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
private const val TIMEOUT_IO_OPERATION = 15L

@Module
@InstallIn(SingletonComponent::class)
class OkHttpModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun authenticationInterceptor(tokenStorage: TokenStorage) = AuthenticationInterceptor(tokenStorage)

    @Provides
    @Singleton
    fun chuckInterceptor(application: Application) = ChuckInterceptor(application)

    @Provides
    @Singleton
    fun okHttpBuilderClient(
        logger: HttpLoggingInterceptor,
        authentication: AuthenticationInterceptor,
        chuckInterceptor: ChuckInterceptor,
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