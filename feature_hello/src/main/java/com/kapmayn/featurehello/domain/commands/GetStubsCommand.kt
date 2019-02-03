package com.kapmayn.featurehello.domain.commands

import com.kapmayn.core.domain.base.CacheReactiveCommand
import com.kapmayn.core.domain.cache.CacheDuration
import com.kapmayn.core.domain.cache.CacheManagerImpl
import com.kapmayn.featurehello.data.api.StubApi
import com.kapmayn.featurehello.domain.mappers.StubMapper
import com.kapmayn.models.StubModel
import io.reactivex.Single
import javax.inject.Inject

class GetStubsCommand
@Inject constructor(
    val api: StubApi,
    val mapper: StubMapper,
    cacheManager: CacheManagerImpl
) : CacheReactiveCommand<StubModel>(StubModel::class.java, cacheManager) {

    override fun run(): Single<StubModel> {
        return api.getStubs().map { mapper.map(it) }
    }

    override fun getCacheKey() = javaClass.simpleName

    override fun getMemoryCacheTime() = 5 * CacheDuration.ONE_MINUTE
}