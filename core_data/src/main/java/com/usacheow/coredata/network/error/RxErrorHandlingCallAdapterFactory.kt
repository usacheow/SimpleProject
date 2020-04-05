package com.usacheow.coredata.network.error

import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLException

class RxErrorHandlingCallAdapterFactory
@Inject constructor(
    private val original: RxJava2CallAdapterFactory,
    private val gson: Gson
) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val currentRawType = getRawType(returnType)
        val callAdapter = original.get(returnType, annotations, retrofit)

        if (currentRawType == Single::class.java) {
            val wrappedCall = callAdapter as? CallAdapter<*, Single<*>>
            return wrappedCall?.let {
                SingleRxCallAdapterWrapper(wrappedCall, gson)
            }
        }

        if (currentRawType == Completable::class.java) {
            val wrappedCall = callAdapter as? CallAdapter<*, Completable>
            return wrappedCall?.let {
                CompletableRxCallAdapterWrapper(wrappedCall, gson)
            }
        }

        return null
    }
}

private class SingleRxCallAdapterWrapper<RESPONSE>(
    private val wrapped: CallAdapter<RESPONSE, Single<*>>,
    private val gson: Gson
) : CallAdapter<RESPONSE, Single<*>> {

    override fun adapt(call: Call<RESPONSE>): Single<*> = wrapped.adapt(call)
        .onErrorResumeNext {
            Single.error(it.parse(gson))
        }

    override fun responseType(): Type = wrapped.responseType()
}

private class CompletableRxCallAdapterWrapper<RESPONSE>(
    private val wrapped: CallAdapter<RESPONSE, Completable>,
    private val gson: Gson
) : CallAdapter<RESPONSE, Completable> {

    override fun adapt(call: Call<RESPONSE>): Completable = wrapped.adapt(call)
        .onErrorResumeNext {
            Completable.error(it.parse(gson))
        }

    override fun responseType(): Type = wrapped.responseType()
}

fun Throwable.parse(gson: Gson) = when (this) {
    is HttpException -> parseHttp(gson)

    is UnknownHostException,
    is TimeoutException -> HostException(throwable = this)

    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> NoConnectivityException(throwable = this)

    else -> ServerException(throwable = this)
}

fun HttpException.parseHttp(gson: Gson): Exception {
    val message = gson.fromJson(
        response()?.errorBody()?.string(),
        ErrorMessage::class.java
    ).detail

    return when (response()?.code()) {
        HttpURLConnection.HTTP_BAD_REQUEST,
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_NOT_FOUND -> UnknownException(message, this)

        HttpURLConnection.HTTP_UNAVAILABLE -> InvalidAccessTokenException(message, this)

        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ServerException(message, this)

        else -> ServerException(message, this)
    }
}