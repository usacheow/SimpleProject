package com.usacheow.coredata.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StubEntity(
    @PrimaryKey
    val id: Long,
    val stubFieldFirst: String,
    val stubFieldSecond: String
)