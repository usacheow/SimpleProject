package com.usacheow.coredata.json

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSerializationApi::class)
class JsonModule {

    @Provides
    @Singleton
    fun jsonConverter(
        jsonProvider: KotlinxSerializationJsonProvider,
    ) = jsonProvider.get().asConverterFactory("application/json".toMediaType())
}