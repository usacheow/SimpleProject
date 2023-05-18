package com.usacheow.featureexample

import com.usacheow.featureexample.presentation.viewmodel.FirstViewModel
import com.usacheow.featureexample.presentation.viewmodel.SecondViewModel
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider

val featureExampleDiModule by DI.Module {
    bindProvider { FirstViewModel() }
    bindFactory { params: SecondViewModel.Params -> SecondViewModel(params) }
}