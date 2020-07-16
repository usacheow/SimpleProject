package com.usacheow.coredata.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.converter.gson.GsonConverterFactory

@Module
class GsonModule {

    @Provides
    @Reusable
    fun gson(): Gson = GsonBuilder().disableHtmlEscaping().create()

    @Provides
    @Reusable
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)
}