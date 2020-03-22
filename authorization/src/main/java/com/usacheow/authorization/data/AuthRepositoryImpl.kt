package com.usacheow.authorization.data

import com.usacheow.authorization.data.models.AccessToken
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class AuthRepositoryImpl : AuthRepository {

    override fun signInWithLoginAndPassword(login: String, password: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
    }

    override fun signUpWithLoginAndPassword(login: String, password: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
    }

    override fun signInWithPhone(phone: String): Completable {
        return Completable.complete().delay(2, TimeUnit.SECONDS)
    }

    override fun resendCode(phone: String): Completable {
        return Completable.complete().delay(2, TimeUnit.SECONDS)
    }

    override fun verifyPhone(phone: String, code: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
    }
}