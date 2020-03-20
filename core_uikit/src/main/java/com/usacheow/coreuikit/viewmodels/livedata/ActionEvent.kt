package com.usacheow.coreuikit.viewmodels.livedata

sealed class ActionEvent

data class DataAction<DATA>(
    val data: DATA
) : ActionEvent()

class SimpleAction : ActionEvent()