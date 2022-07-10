package com.usacheow.coredata.database.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class StubEntity(
    @PrimaryKey
    val id: Long,
    val stubFieldFirst: String,
    val stubFieldSecond: String
)

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