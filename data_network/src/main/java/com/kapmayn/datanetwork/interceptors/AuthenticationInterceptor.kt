package com.kapmayn.datanetwork.interceptors

import com.kapmayn.coreuikit.utils.Storage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(
    private val storage: com.kapmayn.coreuikit.utils.Storage
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