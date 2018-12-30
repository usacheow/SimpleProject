package com.kapmayn.data.di

import com.kapmayn.data.api.StubApi
import com.kapmayn.data.db.AppDatabase

interface DataProvider {

    fun provideStubApi(): StubApi

    fun provideAppDatabase(): AppDatabase
}