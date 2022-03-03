package com.usacheow.coredata.network

import com.usacheow.corecommon.BuildInfo
import java.lang.IllegalStateException

enum class ApiService(
    val prod: String,
    val test: String = prod,
) {

    Stub("https://stub.com/api/v1/");

    fun getBaseUrl(buildInfo: BuildInfo) = when {
        buildInfo.isRelease -> prod
        buildInfo.isDebug -> test
        else -> throw IllegalStateException("Unknown build type")
    }
}