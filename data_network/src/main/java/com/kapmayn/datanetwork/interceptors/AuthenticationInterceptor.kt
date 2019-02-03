package com.kapmayn.datanetwork.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val customRequest = chain.request().newBuilder()
            .method(originalRequest.method(), originalRequest.body())
            .header("AuthToken", "token")
            .build()

        return chain.proceed(customRequest)
    }
}