package com.usacheow.coredata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.usacheow.coredata.database.entity.StubDao
import com.usacheow.coredata.database.entity.StubEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "simple.db"

@Database(entities = [StubEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun stubDao(): StubDao
}

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.newInstance(context)
}