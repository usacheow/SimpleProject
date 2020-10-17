package com.usacheow.featureauth.domain

import com.usacheow.coredata.network.Completable
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.toCompletableResult
import com.usacheow.featureauth.data.AuthRepository
import javax.inject.Inject

class AuthInteractor
@Inject constructor(
    private val repository: AuthRepository
) {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect<Completable> {
        return repository.signInWithLoginAndPassword(login, password).toCompletableResult()
    }

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect<Completable> {
        return repository.signUpWithLoginAndPassword(login, password).toCompletableResult()
    }

    suspend fun signInWithPhone(phone: String): Effect<Completable> {
        return repository.signInWithPhone(phone)
    }

    suspend fun resendCode(phone: String): Effect<Completable> {
        return repository.resendCode(phone)
    }

    suspend fun verifyPhone(phone: String, code: String): Effect<Completable> {
        return repository.verifyPhone(phone, code).toCompletableResult()
    }
}