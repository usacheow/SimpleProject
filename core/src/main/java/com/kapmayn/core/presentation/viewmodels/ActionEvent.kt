package com.kapmayn.core.presentation.viewmodels

sealed class ActionEvent

data class DataAction<DATA>(
    val data: DATA
) : ActionEvent()

class SimpleAction : ActionEvent()