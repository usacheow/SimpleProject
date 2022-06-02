package com.usacheow.coredata.network.interceptors

import com.usacheow.coredata.database.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val AUTHORIZATION_HEADER = "Authorization"
private const val BEARER = "Bearer"

class AuthenticationInterceptor(
    private val tokenStorage: TokenStorage,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val customRequest = chain.request().newBuilder()
            .method(originalRequest.method, originalRequest.body)
            .header(AUTHORIZATION_HEADER, "$BEARER ${tokenStorage.decodedAccessToken}")
            .build()

        return chain.proceed(customRequest)
    }
}