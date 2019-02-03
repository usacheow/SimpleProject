package com.kapmayn.datanetwork.di

import dagger.Module

@Module(
    includes = [
        OkHttpModule::class,
        RxModule::class,
        GsonModule::class,
        RetrofitModule::class
    ]
)
class NetworkModule