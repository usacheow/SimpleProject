package com.usacheow.coredata.network.error

import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
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

private open class RxCallAdapterErrorParser<RESPONSE>(private val gson: Gson) {

    protected fun takeException(throwable: Throwable): Exception {
        return when (throwable) {
            is HttpException -> handleException(throwable)
            is UnknownHostException,
            is SSLException,
            is ConnectException,
            is SocketTimeoutException -> IOException(throwable)
            else -> ServerException(throwable = throwable)
        }
    }

    private fun handleException(throwable: HttpException): Exception {
        val jsonError = throwable.response()?.errorBody()?.string()
        val errorResponse = gson.fromJson(jsonError, ErrorMessage::class.java)

        return when (throwable.response()?.code()) {
            HttpURLConnection.HTTP_UNAVAILABLE -> InvalidAccessTokenException(errorResponse.detail)
            else -> ServerException(errorResponse.detail)
        }
    }
}

private class SingleRxCallAdapterWrapper<RESPONSE>(
    private val wrapped: CallAdapter<RESPONSE, Single<*>>,
    gson: Gson
) : RxCallAdapterErrorParser<RESPONSE>(gson),
    CallAdapter<RESPONSE, Single<*>> {

    override fun adapt(call: Call<RESPONSE>): Single<*> {
        return wrapped.adapt(call).onErrorResumeNext { throwable ->
            Single.error(takeException(throwable))
        }
    }

    override fun responseType(): Type = wrapped.responseType()
}

private class CompletableRxCallAdapterWrapper<RESPONSE>(
    private val wrapped: CallAdapter<RESPONSE, Completable>,
    gson: Gson
) : RxCallAdapterErrorParser<RESPONSE>(gson),
    CallAdapter<RESPONSE, Completable> {

    override fun adapt(call: Call<RESPONSE>): Completable {
        return wrapped.adapt(call).onErrorResumeNext { throwable ->
            Completable.error(takeException(throwable))
        }
    }

    override fun responseType(): Type = wrapped.responseType()
}