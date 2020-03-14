package com.kapmayn.coredata.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kapmayn.coredata.dto.StubDto
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