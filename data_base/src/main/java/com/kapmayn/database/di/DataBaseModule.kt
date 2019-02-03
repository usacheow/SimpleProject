package com.kapmayn.database.di

import android.content.Context
import androidx.room.Room
import com.kapmayn.database.AppDatabase
import com.kapmayn.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

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