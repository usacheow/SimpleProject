package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ApiError
import com.usacheow.coredata.network.Effect2
import com.usacheow.coredata.network.tryRun
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AViewModel @Inject constructor() : SimpleViewModel() {

    var x = 0

    init {
        viewModelScope.launch {
            makeSome(2).apply {
                tryRun { }
                isSuccess
                isError
                dataIfSuccess
                dataOrNull
                exceptionOrNull
                doOnSuccess { }
                doOnError { exception, data -> }
                map { }
                applyCacheData { null }
            }
        }

    }

    private fun makeSome(value: Int, cache: Int? = null): Effect2<Int> {
        return when (value % 2) {
            0 -> Effect2.success(5)
            else -> Effect2.error(ApiError.UnknownException(), cache)
        }
    }
}