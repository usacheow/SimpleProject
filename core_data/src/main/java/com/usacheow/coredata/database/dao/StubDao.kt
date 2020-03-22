package com.usacheow.coredata.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.usacheow.coredata.database.dto.StubDto
import io.reactivex.Flowable

@Dao
interface StubDao {

    @Query("SELECT * FROM stubdto")
    fun getAll(): Flowable<List<StubDto>>

    @Insert
    fun insertAll(vararg items: StubDto)

    @Update
    fun update(item: StubDto): Int

    @Delete
    fun delete(item: StubDto): Int
}