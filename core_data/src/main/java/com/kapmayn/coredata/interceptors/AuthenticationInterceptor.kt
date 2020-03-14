package com.kapmayn.coredata.interceptors

import com.kapmayn.coredata.Storage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(
    private val storage: Storage
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val customRequest = chain.request().newBuilder()
            .method(originalRequest.method(), originalRequest.body())
            .header("AuthToken", storage.token)
            .build()

        return chain.proceed(customRequest)
    }
}