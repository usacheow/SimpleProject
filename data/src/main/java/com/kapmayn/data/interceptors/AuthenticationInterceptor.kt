package com.kapmayn.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
//        if (preferences.hasUserToken()) {
//            val token = preferences.getUserToken()
        original = original.newBuilder()
            .method(original.method(), original.body())
            .header("AuthToken", "token")
            .build()
//        }
        return chain.proceed(original)
    }
}