package com.kapmayn.core.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class ReactiveCommand<RESULT> {

    open fun execute(): Single<RESULT> {
        return run().subscribeOn(Schedulers.io())
    }

    abstract fun run(): Single<RESULT>
}