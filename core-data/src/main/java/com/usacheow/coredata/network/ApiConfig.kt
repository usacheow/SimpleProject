package com.usacheow.coredata.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.usacheow.core.BuildInfo
import com.usacheow.coredata.network.interceptors.AuthenticationInterceptor
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

class ApiConfig @Inject constructor(
    val authentication: AuthenticationInterceptor,

    private val loggerInterceptor: HttpLoggingInterceptor,
    private val chuckerInterceptor: ChuckerInterceptor,

    private val converter: Converter.Factory,
    private val buildInfo: BuildInfo,
) {

    fun <API> builder(clazz: Class<API>): Params<API> = Params(clazz)

    private fun createOkHttpClient(baseUrl: String, interceptors: Set<Interceptor>): Retrofit {
        val builder = okHttpClientBuilder()
        interceptors.forEach { builder.addInterceptor(it) }
        builder.build()

        return retrofitBuilder(baseUrl, builder.build()).build()
    }

    private fun retrofitBuilder(baseUrl: String, okHttp: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(converter)
        .client(okHttp)
        .baseUrl(baseUrl)

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .followRedirects(true)

        if (buildInfo.isDebug) {
            builder.addInterceptor(chuckerInterceptor)
            builder.addInterceptor(loggerInterceptor)
        }
        return builder
    }

    inner class Params<API>(var clazz: Class<API>) {

        private var service: ApiService? = null
        private val interceptors = mutableSetOf<Interceptor>()

        fun service(service: ApiService) = this.apply { this.service = service }

        fun interceptor(interceptor: Interceptor) = this.apply { interceptors += interceptor }

        fun build(): API = createOkHttpClient(requireNotNull(service).getBaseUrl(buildInfo), interceptors).create(clazz)
    }
}