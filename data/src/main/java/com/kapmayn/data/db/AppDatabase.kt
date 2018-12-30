package com.kapmayn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kapmayn.data.db.dao.StubDao
import com.kapmayn.data.models.StubDto

@Database(entities = [StubDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "featuredagger.db"
    }

    abstract fun stubDao(): StubDao
}