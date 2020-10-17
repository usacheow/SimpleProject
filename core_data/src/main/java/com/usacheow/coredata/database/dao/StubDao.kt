package com.usacheow.coredata.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.usacheow.coredata.database.dto.StubDto
import kotlinx.coroutines.flow.Flow

@Dao
interface StubDao {

    @Query("SELECT * FROM stubdto")
    fun getAll(): Flow<List<StubDto>>

    @Insert
    suspend fun insertAll(vararg items: StubDto)

    @Update
    suspend fun update(item: StubDto)

    @Delete
    suspend fun delete(item: StubDto)
}