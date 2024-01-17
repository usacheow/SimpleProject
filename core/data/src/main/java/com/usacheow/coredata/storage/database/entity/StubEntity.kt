package com.usacheow.coredata.storage.database.entity

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import com.usacheow.coredata.SimpleAppDatabase

data class StubEntity(
    val id: Long,
    val stubFieldFirst: String,
    val stubFieldSecond: String
)

class StubDao(
    private val db: SimpleAppDatabase,
    private val ioDispatcher: CoroutineDispatcher,
) {

    fun observeStubs(): Flow<List<StubEntity>> {
        return db.stub_entityQueries.getStubs { id, stubFieldFirst, stubFieldSecond ->
            StubEntity(
                id = id,
                stubFieldFirst = stubFieldFirst,
                stubFieldSecond = stubFieldSecond,
            )
        }.asFlow().mapToList(ioDispatcher)
    }

    fun getPositions(): List<StubEntity> {
        return db.stub_entityQueries.getStubs { id, stubFieldFirst, stubFieldSecond ->
            StubEntity(
                id = id,
                stubFieldFirst = stubFieldFirst,
                stubFieldSecond = stubFieldSecond,
            )
        }.executeAsList()
    }

    fun getPosition(id: Long): StubEntity {
        return db.stub_entityQueries.getStub(id = id) { id, stubFieldFirst, stubFieldSecond ->
            StubEntity(
                id = id,
                stubFieldFirst = stubFieldFirst,
                stubFieldSecond = stubFieldSecond,
            )
        }.executeAsOne()
    }

    fun addPosition(entity: StubEntity) {
        db.stub_entityQueries.addStub(
            id = entity.id,
            stubFieldFirst = entity.stubFieldFirst,
            stubFieldSecond = entity.stubFieldSecond,
        )
    }

    fun deletePosition(id: Long) {
        db.stub_entityQueries.deleteStub(id = id)
    }

    fun updatePositionCount(id: Long, stubFieldFirst: String) {
        db.stub_entityQueries.updateFieldFirst(id = id, stubFieldFirst = stubFieldFirst)
    }
}