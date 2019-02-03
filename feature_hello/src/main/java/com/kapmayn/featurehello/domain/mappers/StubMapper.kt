package com.kapmayn.featurehello.domain.mappers

import com.kapmayn.featurehello.data.models.Stub
import com.kapmayn.models.StubModel
import javax.inject.Inject

class StubMapper
@Inject constructor() {

    fun map(item: Stub): StubModel {
        return with(item) {
            StubModel(
                id,
                stubFieldFirst,
                stubFieldSecond
            )
        }
    }
}