package com.usacheow.coredata.storage.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.usacheow.coredata.storage.database.entity.StubDao
import com.usacheow.coredata.SimpleAppDatabase
import kotlinx.coroutines.CoroutineDispatcher

private const val DATABASE_NAME = "simple.db"

class AppDatabase(
    db: SimpleAppDatabase,
    ioDispatcher: CoroutineDispatcher,
) {

    companion object {
        fun newInstance(context: Context, ioDispatcher: CoroutineDispatcher): AppDatabase {
            val driver: SqlDriver = AndroidSqliteDriver(SimpleAppDatabase.Schema, context, DATABASE_NAME)
            return AppDatabase(SimpleAppDatabase(driver), ioDispatcher)
        }
    }

    val stubDao = StubDao(db, ioDispatcher)
}