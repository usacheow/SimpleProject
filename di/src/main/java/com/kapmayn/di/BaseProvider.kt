package com.kapmayn.di

import android.app.Application
import android.content.Context
import android.content.res.Resources

interface BaseProvider {

    fun provideApplication(): Application

    fun provideContext(): Context

    fun provideResources(): Resources
}