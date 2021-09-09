package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ApiError
import com.usacheow.coredata.network.Effect
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AViewModel @Inject constructor() : SimpleViewModel() {

    var x = 0

//    init {
//        makeSome(2).apply {
//            getDataIfSuccess()
//
//        }
//    }
//
//    private fun makeSome(value: Int, cache: Int? = null): Effect<Int> {
//        return when (value % 2) {
//            0 -> Effect.Success(5)
//            else -> Effect.Error(ApiError.ApiException(), cache)
//        }
//    }
}