package com.kapmayn.coredata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kapmayn.coredata.dao.StubDao
import com.kapmayn.coredata.dto.StubDto

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "featuredagger.db"
    }

    abstract fun stubDao(): StubDao
}