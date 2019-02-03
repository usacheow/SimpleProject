package com.kapmayn.core.domain.base

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class CompletableCommand {

    open fun execute(): Completable {
        return run().subscribeOn(Schedulers.io())
    }

    abstract fun run(): Completable
}