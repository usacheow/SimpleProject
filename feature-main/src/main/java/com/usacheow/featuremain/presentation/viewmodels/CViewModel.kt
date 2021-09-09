package com.usacheow.featuremain.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//https://bladecoder.medium.com/kotlins-flow-in-viewmodels-it-s-complicated-556b472e281a

@HiltViewModel
class CViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    var itemNumber = savedStateHandle.get<Int>("itemNumber")

    fun setQuery(query: String) = viewModelScope.launch {
//        _results.emit(query)
//        _results.value = query
        trigger.value = query
//        trigger.emit(query)
    }

//    private val trigger = MutableStateFlow("")
//    val results = trigger.mapLatest { query ->
//        Log.d("stupid_article", "again? $query")
//        query
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000L),
//        initialValue = ""
//    )

    private val trigger = MutableLiveData<String>("")
    val results: LiveData<String> = trigger.switchMap { query ->
        liveData {
            Log.d("stupid_article", "again? $query")
            emit(query)
        }
    }

//    private val _results = MutableSharedFlow<String>(replay = 1)
//    val results = _results.asSharedFlow()
//    private val _results = MutableLiveData<String>()
//    val results = _results.distinctUntilChanged()
}