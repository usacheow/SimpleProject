package com.usacheow.coredata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.usacheow.coredata.database.dao.StubDao
import com.usacheow.coredata.database.dto.StubDto

private const val DATABASE_NAME = "simple.db"

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun stubDao(): StubDao
}