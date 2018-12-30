package com.kapmayn.data.di

import android.content.Context
import androidx.room.Room
import com.kapmayn.data.api.StubApi
import com.kapmayn.data.db.AppDatabase
import com.kapmayn.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideStubApi(retrofit: Retrofit): StubApi {
        return retrofit.create(StubApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}