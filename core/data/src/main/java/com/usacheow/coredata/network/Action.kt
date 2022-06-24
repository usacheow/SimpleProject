package com.usacheow.coredata.network

import com.usacheow.corecommon.model.AppError
import com.usacheow.corecommon.model.Effect
import com.usacheow.corecommon.ext.log
import com.usacheow.coredata.source.NotificationsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.net.HttpURLConnection

interface Action {

    suspend fun <T : Any> execute(block: suspend CoroutineScope.() -> Effect<T>): Effect<T>
}

class ActionImpl @Inject constructor(
    private val notificationsSource: NotificationsSource,
) : Action {

    override suspend fun <T : Any> execute(block: suspend CoroutineScope.() -> Effect<T>): Effect<T> {
        val effect = try {
            coroutineScope {
                var effect = block()
                if (effect.errorOrNull?.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    // todo: refresh
                    effect = block()
                }
                effect
            }
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> throw e
                else -> Effect.error(AppError.Unknown(cause = e))
            }
        }

        return effect.also {
            it.errorOrNull?.let { error -> handle(error) }
        }
    }

    private suspend fun handle(error: AppError) {
        log(error.stackTraceToString())

        notificationsSource.sendError(error)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface ActionModule {

    @Binds
    @Singleton
    fun action(action: ActionImpl): Action
}