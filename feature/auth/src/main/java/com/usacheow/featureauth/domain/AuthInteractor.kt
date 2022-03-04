package com.usacheow.featureauth.domain

import com.usacheow.corecommon.Completable
import com.usacheow.corecommon.Effect
import com.usacheow.featureauth.data.AuthRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AuthInteractor @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend fun signInWithLoginAndPassword(login: String, password: String): Effect<Completable> {
        return repository.signInWithLoginAndPassword(login, password).toCompletable()
    }

    suspend fun signUpWithLoginAndPassword(login: String, password: String): Effect<Completable> {
        return repository.signUpWithLoginAndPassword(login, password).toCompletable()
    }

    suspend fun signInWithPhone(phone: String): Effect<Completable> {
        return repository.signInWithPhone(phone)
    }

    suspend fun resendCode(phone: String): Effect<Completable> {
        return repository.resendCode(phone)
    }

    suspend fun verifyPhone(phone: String, code: String): Effect<Completable> {
        return repository.verifyPhone(phone, code).toCompletable()
    }
}