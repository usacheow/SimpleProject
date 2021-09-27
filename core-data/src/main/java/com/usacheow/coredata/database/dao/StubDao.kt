package com.usacheow.coredata.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.usacheow.coredata.database.entity.StubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StubDao {

    @Query("SELECT * FROM stubentity")
    fun getAll(): Flow<List<StubEntity>>

    @Insert
    suspend fun insertAll(vararg items: StubEntity)

    @Update
    suspend fun update(item: StubEntity)

    @Delete
    suspend fun delete(item: StubEntity)
}