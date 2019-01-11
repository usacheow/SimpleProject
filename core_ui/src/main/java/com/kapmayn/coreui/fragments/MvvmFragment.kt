package com.kapmayn.coreui.fragments

import androidx.lifecycle.ViewModelProvider
import com.kapmayn.core.base.Router
import javax.inject.Inject

abstract class MvvmFragment<ROUTER : Router> : SimpleFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var router: ROUTER

    protected open fun subscribe() {}
}