package com.usacheow.featuremain.presentation.viewmodel

import com.usacheow.corecommon.model.Effect
import com.usacheow.corecommon.model.State
import com.usacheow.corecommon.ext.log
import com.usacheow.coredata.network.Action
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CViewModel @Inject constructor(
    private val action: Action,
) : SimpleViewModel() {

    private val interactor = Interactor()

    val loadDataThroughFlow = interactor.loadDataFlow

    val job: Job = Job()
//    val job: Job = SupervisorJob()
    val scope = CoroutineScope(job + Dispatchers.Default)

    init {
        simpleLoadDataExample()
    }

    fun simpleLoadDataExample() {
/*        scope.launch {
            action.execute {
                supervisorScope {
                    val x1 = async { delay(500); throw IllegalArgumentException() }
                    val x2 = async { delay(1500); log("x2") }
                    x1.await()
                    x2.await()
                }
                Effect.success(4)
            }.doOnError { error, data -> log("error") }
                .doOnSuccess { log("success") }
        }
        scope.launch {
            try {
                action.start {
                    val x1 = async {
                        delay(1000)
                        throw IllegalArgumentException()
                    }
                    val x2 = async {
                        delay(1500)
                        log("x2")
                    }
                    x1.await()
                    x2.await()
                }
            } catch (e: Exception) {
                log("error")
            }
        }*/
        scope.launch {
            try {
                val x1 = scope.async {
                    delay(1000)
                    throw IllegalArgumentException()
                }
                val x2 = scope.async {
                    delay(1200)
                    log("x2")
                }
                x1.await()
                x2.await()
            } catch (e: Exception) {
                log("error")
            }
        }
    }

//    fun simpleLoadDataExample() {
//        viewModelScope.launch {
//            action.execute { interactor.loadDataMethod() }
//                .doOnSuccess { data -> }
//                .doOnError { error, data -> }
//        }
//    }
}

private class Interactor {

    val loadDataFlow = MutableStateFlow<State<String>>(Effect.success("success"))

    suspend fun loadDataMethod(): Effect<String> {
        delay(500)
        return Effect.success("success")
    }

    suspend fun failedDataMethod(scope: CoroutineScope) = scope.async {
        delay(500)
        throw IllegalArgumentException()
    }
}