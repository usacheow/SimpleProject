package com.kapmayn.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory
@Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        val viewModelProvider = viewModels[viewModelClass]
            ?: throw IllegalArgumentException("$viewModelClass not found")

        return viewModelProvider.get() as T
    }
}