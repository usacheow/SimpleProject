package com.usacheow.coredata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.usacheow.coredata.dao.StubDao
import com.usacheow.coredata.dto.StubDto

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "featuredagger.db"
    }

    abstract fun stubDao(): StubDao
}