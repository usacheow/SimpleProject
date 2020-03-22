package com.usacheow.coredata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.usacheow.coredata.database.dao.StubDao
import com.usacheow.coredata.database.dto.StubDto

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "featuredagger.db"
    }

    abstract fun stubDao(): StubDao
}