package com.kapmayn.coredata.di

import android.content.Context
import androidx.room.Room
import com.kapmayn.coredata.AppDatabase
import com.kapmayn.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        OkHttpModule::class,
        RxModule::class,
        GsonModule::class,
        RetrofitModule::class
    ]
)
class CoreDataModule {

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