package com.usacheow.authorization.domain.commands

import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SendCodeByPhoneCommand
@Inject constructor() {

    fun execute(phoneNumber: String): Completable {
        return Completable.complete().delay(2000, TimeUnit.MILLISECONDS)
    }
}