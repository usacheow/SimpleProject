package com.usacheow.featureauth.domain

import io.reactivex.Completable
import javax.inject.Inject

class AuthInteractor
@Inject constructor(
    private val repository: AuthRepository
) {

    fun signInWithLoginAndPassword(login: String, password: String): Completable {
        return repository.signInWithLoginAndPassword(login, password)
            .ignoreElement()
    }

    fun signUpWithLoginAndPassword(login: String, password: String): Completable {
        return repository.signUpWithLoginAndPassword(login, password)
            .ignoreElement()
    }

    fun signInWithPhone(phone: String): Completable {
        return repository.signInWithPhone(phone)
    }

    fun resendCode(phone: String): Completable {
        return repository.resendCode(phone)
    }

    fun verifyPhone(phone: String, code: String): Completable {
        return repository.verifyPhone(phone, code)
            .ignoreElement()
    }
}