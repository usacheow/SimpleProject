package com.usacheow.coredata

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.setRequestThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.setRequestThreads() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())