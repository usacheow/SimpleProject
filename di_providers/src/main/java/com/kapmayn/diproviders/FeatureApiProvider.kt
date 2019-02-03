package com.kapmayn.diproviders

import com.kapmayn.diproviders.featureapi.HelloFeatureApi
import com.kapmayn.diproviders.featureapi.WorldFeatureApi

interface FeatureApiProvider {

    fun provideHelloFeatureApi(): HelloFeatureApi

    fun provideWorldFeatureApi(): WorldFeatureApi
}