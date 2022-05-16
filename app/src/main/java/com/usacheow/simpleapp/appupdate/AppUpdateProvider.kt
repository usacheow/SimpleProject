package com.usacheow.simpleapp.appupdate

import android.app.Activity
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.usacheow.coredata.database.UserDataStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

private const val REQUEST_CODE = 11

interface AppUpdateProvider : UpdateRouter {

    fun availableUpdateInfo(): Flow<UpdateAvailableState>

    fun subscribeOnUpdateState(): Flow<InstallState>

    fun completeUpdate()
}

interface UpdateRouter {

    fun openUpdateScreen(data: UpdateAvailableState.Available, activity: Activity)
}

class AppUpdateProviderImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val userDataStorage: UserDataStorage,
) : AppUpdateProvider {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)

    override fun availableUpdateInfo() = flow<UpdateAvailableState> {
        val info = appUpdateManager.appUpdateInfo.await()

        when {
            info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE -> {
                userDataStorage.lastCheckedAvailableVersionCode = info.availableVersionCode()
                UpdateAvailableState.Available(info)
            }
            info.installStatus() == InstallStatus.DOWNLOADED -> {
                UpdateAvailableState.Downloaded
            }
            else -> {
                UpdateAvailableState.NotAvailable
            }
        }
    }.catch {
        UpdateAvailableState.NotAvailable
    }

    override fun openUpdateScreen(data: UpdateAvailableState.Available, activity: Activity) {
        appUpdateManager.startUpdateFlowForResult(data.info, AppUpdateType.FLEXIBLE, activity, REQUEST_CODE)
    }

    override fun subscribeOnUpdateState() = callbackFlow {
        val listener = InstallStateUpdatedListener { state ->
            val installState = when (state.installStatus()) {
                InstallStatus.DOWNLOADING -> {
                    val current = state.bytesDownloaded()
                    val total = state.totalBytesToDownload()
                    InstallState.Downloading((current * 100 / total).toInt())
                }
                InstallStatus.DOWNLOADED -> InstallState.Downloaded
                InstallStatus.FAILED -> InstallState.Failed
                else -> InstallState.Unknown
            }
            trySend(installState)
        }

        appUpdateManager.registerListener(listener)
        awaitClose { appUpdateManager.unregisterListener(listener) }
    }

    override fun completeUpdate() {
        appUpdateManager.completeUpdate()
    }

    private suspend fun <T> Task<T>.await(): T {
        return suspendCoroutine { continuation ->
            addOnCompleteListener {
                val e = exception
                val result = result
                when {
                    e != null -> continuation.resumeWithException(e)
                    result == null -> continuation.resumeWithException(NullPointerException())
                    else -> continuation.resume(result)
                }
            }
        }
    }
}

sealed class UpdateAvailableState {
    data class Available(val info: AppUpdateInfo) : UpdateAvailableState()
    object NotAvailable : UpdateAvailableState()
    object Downloaded : UpdateAvailableState()
}

sealed class InstallState {
    data class Downloading(val percent: Int) : InstallState()
    object Downloaded : InstallState()
    object Failed : InstallState()
    object Unknown : InstallState()
}