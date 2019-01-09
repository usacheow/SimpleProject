package com.kapmayn.corefeature.di

import com.kapmayn.corefeature.HelloFeatureApi
import com.kapmayn.corefeature.WorldFeatureApi

interface FeatureApiProvider {

    fun provideHelloFeatureApi(): HelloFeatureApi

    fun provideWorldFeatureApi(): WorldFeatureApi
}