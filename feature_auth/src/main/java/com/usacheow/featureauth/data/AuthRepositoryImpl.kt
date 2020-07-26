package com.usacheow.featureauth.data

import com.usacheow.coredata.database.Storage
import com.usacheow.featureauth.data.models.AccessToken
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val storage: Storage
) : AuthRepository {

    override fun signInWithLoginAndPassword(login: String, password: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
            .doOnSuccess { storage.token = it.token }
    }

    override fun signUpWithLoginAndPassword(login: String, password: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
            .doOnSuccess { storage.token = it.token }
    }

    override fun signInWithPhone(phone: String): Completable {
        return Completable.complete().delay(2, TimeUnit.SECONDS)
    }

    override fun resendCode(phone: String): Completable {
        return Completable.complete().delay(2, TimeUnit.SECONDS)
    }

    override fun verifyPhone(phone: String, code: String): Single<AccessToken> {
        return Single.just(AccessToken("token")).delay(2, TimeUnit.SECONDS)
            .doOnSuccess { storage.phoneNumber = phone }
            .doOnSuccess { storage.token = it.token }
    }
}