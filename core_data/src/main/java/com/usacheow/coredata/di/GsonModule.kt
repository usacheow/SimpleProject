package com.usacheow.coredata.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.usacheow.di.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GsonModule {

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder().disableHtmlEscaping().create()

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)
}