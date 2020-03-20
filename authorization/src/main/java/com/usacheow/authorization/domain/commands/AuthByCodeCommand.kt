package com.usacheow.authorization.domain.commands

import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthByCodeCommand
@Inject constructor() {

    fun execute(code: String): Completable {
        return Completable.complete().delay(2000, TimeUnit.MILLISECONDS)
    }
}