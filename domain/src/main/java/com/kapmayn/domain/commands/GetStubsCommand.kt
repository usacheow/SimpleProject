package com.kapmayn.domain.commands

import com.kapmayn.core.domain.CacheReactiveCommand
import com.kapmayn.coremodels.StubModel
import com.kapmayn.data.api.StubApi
import com.kapmayn.domain.cache.CacheDuration
import com.kapmayn.domain.cache.CacheManagerImpl
import com.kapmayn.domain.mappers.StubMapper
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