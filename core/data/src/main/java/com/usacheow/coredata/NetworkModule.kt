package com.usacheow.coredata

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.usacheow.coredata.database.TokenStorage
import com.usacheow.coredata.json.KotlinxSerializationJsonProvider
import com.usacheow.coredata.network.interceptors.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSerializationApi::class)
class NetworkModule {

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
    fun jsonConverter(
        jsonProvider: KotlinxSerializationJsonProvider,
    ) = jsonProvider.get().asConverterFactory("application/json".toMediaType())
}