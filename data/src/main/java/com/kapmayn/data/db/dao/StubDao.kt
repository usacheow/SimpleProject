package com.kapmayn.data.db.dao

import androidx.room.*
import com.kapmayn.data.models.StubDto
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