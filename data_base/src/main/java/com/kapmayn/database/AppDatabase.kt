package com.kapmayn.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kapmayn.database.dao.StubDao
import com.kapmayn.database.dto.StubDto

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "featuredagger.db"
    }

    abstract fun stubDao(): StubDao
}