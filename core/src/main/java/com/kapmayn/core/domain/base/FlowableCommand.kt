package com.kapmayn.core.domain.base

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

abstract class FlowableCommand<RESULT> {

    open fun execute(): Flowable<RESULT> {
        return run().subscribeOn(Schedulers.io())
    }

    abstract fun run(): Flowable<RESULT>
}