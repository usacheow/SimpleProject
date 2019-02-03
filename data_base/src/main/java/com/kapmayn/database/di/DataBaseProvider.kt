package com.kapmayn.database.di

import com.kapmayn.database.AppDatabase

interface DataBaseProvider {

    fun provideAppDatabase(): AppDatabase
}